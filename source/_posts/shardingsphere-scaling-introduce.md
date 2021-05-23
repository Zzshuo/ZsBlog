---
title: shardingsphere之scaling
tags: shardingsphere
typora-root-url: ../../source
coauthor: 郑硕
categories: shardingsphere
date: 2021-01-05 17:06:20
---


## 简介

ShardingSphere-Scaling 是一个提供给用户的通用的 ShardingSphere 数据接入迁移，及弹性伸缩的解决方案。

## 使用方式

目前的弹性伸缩解决方案为：临时地使用两个数据库集群，伸缩完成后切换的方式实现。

![伸缩总揽](/images/shardingsphere-scaling-introduce/scaling-principle-overview.cn.png)

这种实现方式有以下优点：

1. 伸缩过程中，原始数据没有任何影响
2. 伸缩失败无风险
3. 不受分片策略限制

同时也存在一定的缺点：

1. 在一定时间内存在冗余服务器
2. 所有数据都需要移动

## 执行步骤

![image-20201216161515781](/images/shardingsphere-scaling-introduce/image-20201216161515781.png)

### 准备阶段

在准备阶段，弹性伸缩模块会进行数据源连通性及权限的校验，同时进行存量数据的统计、日志位点的记录，最后根据数据量和用户设置的并行度，对任务进行分片。

### 存量数据迁移阶段

执行在准备阶段拆分好的存量数据迁移作业，存量迁移阶段采用 JDBC 查询的方式，直接从数据节点中读取数据，并使用新规则写入到新集群中。

### 增量数据同步阶段

由于存量数据迁移耗费的时间受到数据量和并行度等因素影响，此时需要对这段时间内业务新增的数据进行同步。 不同的数据库使用的技术细节不同，但总体上均为基于复制协议或 WAL 日志实现的变更数据捕获功能。

- MySQL：订阅并解析 binlog
- PostgreSQL：采用官方逻辑复制 [test_decoding](https://www.postgresql.org/docs/9.4/test-decoding.html)

这些捕获的增量数据，同样会由弹性伸缩模块根据新规则写入到新数据节点中。当增量数据基本同步完成时（由于业务系统未停止，增量数据是不断的），则进入规则切换阶段。

### 规则切换阶段

在此阶段，可能存在一定时间的业务只读窗口期，通过业务停止写入或设置数据库只读或ShardingSphere的熔断机制，让旧数据节点中的数据短暂静态，确保增量同步已完全完成。

这个窗口期时间短则数秒，长则数分钟，取决于数据量和用户是否需要对数据进行强校验。 确认完成后，Apache ShardingSphere 可通过配置中心修改配置，将业务导向新规则的集群，弹性伸缩完成。

我们由于有写业务无法无法完全停止写入，目前只能尽可能减少写入，然后开启双写，并且停止迁移，进行数据校验，对有问题数据进行补处理。



## 架构设计

### ScalingTask

任务最小执行单元

主要实现类：

- InventoryDataScalingTask（History）存量
- IncrementalDataScalingTask（RealTime） 增量

### Dumper（Reader）

从原库获取数据，核心操作主要有：

- setChannel(Channel channel); 设置Channel
- dump(); 从原库获取数据，并保存数据到channel中

主要实现类：

- JDBCDumper
- LogDumper

### Importer（Writer）

把数据写入数据到新库,核心操作主要有：

- setChannel(Channel channel); 设置Channel
- write(); 写入数据到数据库

### Channel

```java
public interface Channel {
    void pushRecord(Record dataRecord) throws InterruptedException;
    List<Record> fetchRecords(int batchSize, int timeout);
    void ack();
    void close();
}

```

主要实现类：

- MemoryChannel
- DistributionChannel

### 存量迁移

![image-20201216175534892](/images/shardingsphere-scaling-introduce/image-20201216175534892.png)

1. 获取minId、maxId，并根据id和concurrency 把迁移id区间平均分配到各个Task（InventoryDataScalingTask）。

   minId=1，maxId=100，concurrency=10，则

   task1 id区间为 1-10，task2 id区间为 11-20 ......

2. 每个Task都有一个dumper 从原库批量获取DataRecord，并push到MemoryChannel，BlockingQueue长度为10000，超过则阻塞等待

3. Importer从MemoryChannel批量获取DataRecord并依次插入到新库中，然后ack()。

4. 当dumper获取不到数据时，则push一个FinishedRecord到MemoryChannel，dumper结束。

5. Importer获取到FinishedRecord时，Importer结束，Task执行完毕。

### 增量迁移

![image-20201216175621918](/images/shardingsphere-scaling-introduce/image-20201216175621918.png)

1. 每次Task（InventoryDataScalingTask）执行完成，callBack 检测是否所有Task（InventoryDataScalingTask）执行完成。都执行完成开启增量迁移Task（IncrementalDataScalingTask）。

2. 增量只有一个dumper 从迁移开始时记录的binlog位置subscribe数据，并根据hashcode和channelNumber取模push到Memorychannel。

   String index = Integer.toString(Math.abs(dataRecord.hashCode()) % channelNumber);

3. Importer从MemoryChannel批量获取DataRecord并依次插入/更新到新库中，然后ack()。