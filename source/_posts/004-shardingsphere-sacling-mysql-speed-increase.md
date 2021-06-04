---
title: shardingsphere之mysql数据迁移优化
typora-root-url: ../../source
date: 2021-01-07
tags:
  - shardingsphere
  - mysql
  - 水平分库
categories:
  - shardingsphere
banner_img: /images/general/4.jpg
index_img: /images/general/4.jpg
---

水平分库项目，用了 ShardingSphere-scaling 做数据迁移，由于存量数据迁移过程中过于缓慢，对数据迁移模块进行优化。

迁移效率提升 600%，校验效率提升 6000%。

| 表名           | 数据   | 迁移耗时      | 校验耗时     |
| -------------- | ------ | ------------- | ------------ |
| screen_case    | 16000w | 12h（优化前） |              |
| dc_rule_random | 7000w  | 1h            | 8h（优化前） |
| ap_apply       | 14000w | 2h            | 14m          |
| ap_apply_ext   | 17000w | 4h2m          | 18m          |

## 批量插入

`rewriteBatchedStatements=true`

改写存量数据为批量插入，代码如下：

```java
try (Connection connection = dataSource.getConnection()) {
    connection.setAutoCommit(false);
    try (PreparedStatement ps = connection.prepareStatement(insertSql)) {
        ps.setQueryTimeout(60);
        for (DataRecord record : list) {
            for (int i = 0; i < record.getColumnCount(); i++) {
                ps.setObject(i + 1, record.getColumn(i).getValue());
            }
            ps.addBatch();
        }
        ps.executeBatch();
    }
    connection.commit();
}
```

当时以为这样写就可以了，但是迁移速度还是很慢只比原来单条插入快了一倍左右，经过研究发现，这并不是真正的批量插入，只是多条插入一次事务，降低了事务开销。

```mysql
 INSERT IGNORE INTO `user`(`id`,`cust_no`,`age`,`name`,`delete_at`) VALUES(59985,'UR59985',98,'name:1605538600723',0) ;
 INSERT IGNORE INTO `user`(`id`,`cust_no`,`age`,`name`,`delete_at`) VALUES(59986,'UR59986',23,'name:160512312311',0) ;
```

在 MySQL JDBC 中，批操作的提交默认是逐条进行的。而在链接中加入以下参数，会把多条语句合并成一条提交。当 SQL 语句累积到一定数量（由数据库可接受的最大数据包大小决定），再一次性提交到数据库，减少了与数据库的交互次数，大大提高了效率。`rewriteBatchedStatements=true`

```mysql
INSERT IGNORE INTO `user`(`id`,`cust_no`,`age`,`name`,`delete_at`) VALUES(50200,'UR50200',98,'name:1606138884516',0),(50202,'UR50202',98,'name:1606138884516',0),(50204,'UR50204',98,'name:1606138884516',0);
```

分析源码可以发现：
当 mysql 版本>4.1.1，并且 rewriteBatchedStatements=true，批操作语句总数大于 4 条时（nbrCommands > 4），才会合并成一条语句提交。

```java
com.mysql.jdbc.StatementImpl#executeBatchInternal

protected long[] executeBatchInternal() throws SQLException {
	...
	nbrCommands = (long[])this.batchedArgs.size();	// 获取批操作语句数量
    this.batchedGeneratedKeys = new ArrayList(this.batchedArgs.size());
    boolean multiQueriesEnabled = locallyScopedConn.getAllowMultiQueries();	// 当前连接是否允许一次查询多条语句，默认为 false
    Object sqlEx;
	if (locallyScopedConn.versionMeetsMinimum(4, 1, 1) && (multiQueriesEnabled || locallyScopedConn.getRewriteBatchedStatements() && nbrCommands > 4)) {
		sqlEx = this.executeBatchUsingMultiQueries(multiQueriesEnabled, (int)nbrCommands, individualStatementTimeout);
	    return (long[])sqlEx;
    }
    ...
}
```

## 预编译设置

jdbc 客户端参数：

cachePrepStmts：默认 false.是否缓存 prepareStatement 对象。每个连接都有一个缓存，是以 sql 为唯一标识的 LRU cache. 同一连接下，不同 stmt 可以不用重新创建 prepareStatement 对象。

prepStmtCacheSize：LRU cache 中 prepareStatement 对象的个数。一般设置为最常用 sql 的个数。

prepStmtCacheSqlLimit：prepareStatement 对象的大小。超出大小不缓存。

## 服务端编译

useServerPrepStmts=false 关闭服务器端编译，sql 语句在客户端编译好再发送给服务器端。

如果为 true,sql 会采用占位符方式发送到服务器端，在服务器端再组装 sql 语句。

占位符方式：`INSERT INTO t (c1,c2) VALUES (？,？),(？,？),(？,？);`

此方式就会产生一个问题，当列数\*提交记录数>65535

时就会报错：Prepared statement contains too many placeholders,

这是由于我把“提交记录数量”设为 10000，而要插入记录的表字段有 30 个，所以要进行批量插入时需要 30\*10000=300000 > 65535 ，故而报错。

**解决方案**

**方案 1：**把 DB 连接中的 rewriteBatchedStatements 给设置为 false（或者去掉），不过这个操作会影响数据的插入速度。

**方案 2：**更改表输出的设计。确保 30 个输出字段的和提交记录数量的乘积不超过 65535。比如把提交记录数量由 10000 更改为 450（30\*2000=60000< 65535）

当然我们的目的是为了提高数据库写速度，并且不考虑 sql 注入问题，所以当`rewriteBatchedStatements =true`时`useServerPrepStmts=false`配合使用较为合适。

## 压缩数据传输

`useCompression=true`压缩数据传输，优化客户端和 MySQL 服务器之间的通信性能。

```java
com.mysql.jdbc.MysqlIO#doHandshake

void doHandshake(String user, String password, String database) throws SQLException {
 ···
    //
    // Can't enable compression until after handshake 在握手后
    才能启用压缩
    //
    if (((this.serverCapabilities & CLIENT_COMPRESS) != 0) && this.connection.getUseCompression() && !(this.mysqlInput instanceof CompressedInputStream)) {
        // The following matches with ZLIB's compress()
        this.deflater = new Deflater();
        this.useCompression = true;
        this.mysqlInput = new CompressedInputStream(this.connection, this.mysqlInput);
    }
···
}
```

https://dev.mysql.com/doc/refman/5.7/en/server-system-variables.html#sysvar_have_compress

![image-20201124001333217](/images/shardingsphere-sacling-mysql-speed-increase/image-20201124001333217.png)

压缩协议提升网络传输性能，对于一些网络环境较差的用户会有很大的帮助，但是会相应地增加 CPU 开销，适用于传输数据量很大带宽不高的情况，这是一个 CPU 和网络资源的平衡问题。

经过测试 直连数据库会速度提高 5% 左右，效果不明显。通过 shardingsphere-proxy 连接数据库速度也并无明显提升

因此目前带宽无瓶颈的情况下，未使用压缩协议

直连数据库批量插入 5 分钟：

| useCompression | 100 条 avg | 1000 条 avg | 10000 条 avg |
| -------------- | ---------- | ----------- | ------------ |
| true           | 11ms       | 42ms        | 307ms        |
| false          | 12ms       | 43ms        | 321ms        |

通过 shardingsphere-proxy 连接数据库批量插入 5 分钟：

| useCompression | 100 条 avg | 1000 条 avg | 10000 条 avg |
| -------------- | ---------- | ----------- | ------------ |
| true           | 31ms       | 101ms       | 725ms        |
| false          | 32ms       | 100ms       | 732ms        |

**适用场景**

MySQL 压缩协议适合的场景是 MySQL 的服务器端和客户端之间传输的数据量很大，或者可用带宽不高的情况，典型的场景有如下两个：

a、查询大量的数据，带宽不够（比如导出数据的时候）；

b、复制的时候 binlog 量太大，启用 slave_compressed_protocol 参数进行日志压缩复制。

### 压缩协议简介

压缩协议是 MySQL 通信协议的一部分，要启用压缩协议进行数据传输，需要 MySQL 服务器端和客户端都支持 zlib 算法。启动压缩协议会导致 CPU 负载略微上升。使用启用压缩协议使用-C 参数或者 --compress=true 参数启动客户端的压缩功能。如果启用了-C 或者 compress=true 选项，那么在连接到服务器段的时候，会发送 0x0020（CLIENT_COMPRESS）的服务器权能标志位，和服务器端协商通过后（3 次握手以后），就支持压缩协议了。由于采用压缩，数据包的格式会发生变化，具体的变化如下：

未压缩的数据包格式：

![img](/images/shardingsphere-sacling-mysql-speed-increase/1497240545250_627_1497240545431.png)

压缩后的数据包格式：

![img](/images/shardingsphere-sacling-mysql-speed-increase/1497240560529_3423_1497240560716.png)

大家可能留意到压缩后的数据报格式有压缩和未压缩之分，这个是 MySQL 为了较少 CPU 开销而做的一个优化。如果内容小于 50 个字节的时候，就不对内容进行压缩，而大于 50 字节的时候，才会启用压缩功能。具体的规则如下：

当第三个字段的值等于 0x00 的时候，表示当前包没有压缩，因此 n`*`byte 的内容为 1`*`byte,n`*`byte，即请求类型和请求内容。

当第三个字段的值大于 0x00 的时候，表示当前包已采用 zlib 压缩，因此使用的时候需要对 n`*`byte 进行解压，解压后内容为 1`*`byte,n`*`byte，即请求类型和请求内容。

## 结论

**原库读取数据设置**

`useServerPrepStmts=true`
`cachePrepStmts=true`

**新库插入数据设置**

`rewriteBatchedStatements=true `
`useServerPrepStmts=false `
`useCompression=false`

## 参考文章

https://my.oschina.net/u/4418437/blog/3648369
https://cloud.tencent.com/developer/article/1005252
https://www.cnblogs.com/lispking/p/3604063.html
https://stackoverflow.com/questions/2506460/when-should-i-use-mysql-compressed-protocol
https://blog.csdn.net/qq_43153418/article/details/104002252
https://www.cnblogs.com/justfortaste/p/3920140.html
