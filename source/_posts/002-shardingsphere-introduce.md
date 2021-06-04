---
title: shardingsphere介绍
typora-root-url: ../../source
date: 2021-01-05
tags:
  - shardingsphere
  - 水平分库
categories:
  - shardingsphere
banner_img: /images/general/2.jpg
index_img: /images/general/2.jpg
---

## 简介

[ShardingSphere 官网](https://shardingsphere.apache.org/)

Apache ShardingSphere 是一套开源的分布式数据库中间件解决方案组成的生态圈，它由 JDBC、Proxy 和 Sidecar（规划中）这 3 款相互独立，却又能够混合部署配合使用的产品组成。 它们均提供标准化的数据分片、分布式事务和数据库治理功能，可适用于如 Java 同构、异构语言、云原生等各种多样化的应用场景。

Apache ShardingSphere 定位为关系型数据库中间件，旨在充分合理地在分布式的场景下利用关系型数据库的计算和存储能力，而并非实现一个全新的关系型数据库。 它通过关注不变，进而抓住事物本质。关系型数据库当今依然占有巨大市场，是各个公司核心业务的基石，未来也难于撼动，我们目前阶段更加关注在原有基础上的增量，而非颠覆。

Apache ShardingSphere 5.x 版本开始致力于可插拔架构，项目的功能组件能够灵活的以可插拔的方式进行扩展。 目前，数据分片、读写分离、数据加密、影子库压测等功能，以及对 MySQL、PostgreSQL、SQLServer、Oracle 等 SQL 与协议的支持，均通过插件的方式织入项目。 开发者能够像使用积木一样定制属于自己的独特系统。Apache ShardingSphere 目前已提供数十个 SPI 作为系统的扩展点，而且仍在不断增加中。

ShardingSphere 已于 2020 年 4 月 16 日成为 [Apache 软件基金会](https://apache.org/index.html#projects-list)的顶级项目。

### ShardingSphere-JDBC

定位为轻量级 Java 框架，在 Java 的 JDBC 层提供的额外服务。 它使用客户端直连数据库，以 jar 包形式提供服务，无需额外部署和依赖，可理解为增强版的 JDBC 驱动，完全兼容 JDBC 和各种 ORM 框架。

- 适用于任何基于 JDBC 的 ORM 框架，如：JPA, Hibernate, Mybatis, Spring JDBC Template 或直接使用 JDBC。
- 支持任何第三方的数据库连接池，如：DBCP, C3P0, BoneCP, Druid, HikariCP 等。
- 支持任意实现 JDBC 规范的数据库，目前支持 MySQL，Oracle，SQLServer，PostgreSQL 以及任何遵循 SQL92 标准的数据库。

![image-20201216153825102](/images/shardingsphere-introduce/image-20201216153825102.png)

### ShardingSphere-Proxy

定位为透明化的数据库代理端，提供封装了数据库二进制协议的服务端版本，用于完成对异构语言的支持。 目前提供 MySQL 和 PostgreSQL 版本，它可以使用任何兼容 MySQL/PostgreSQL 协议的访问客户端(如：MySQL Command Client, MySQL Workbench, Navicat 等)操作数据，对 DBA 更加友好。

- 向应用程序完全透明，可直接当做 MySQL/PostgreSQL 使用。
- 适用于任何兼容 MySQL/PostgreSQL 协议的的客户端。

![ShardingSphere-Proxy Architecture](/images/shardingsphere-introduce/shardingsphere-proxy-brief.png)

### ShardingSphere-Scaling

ShardingSphere-Scaling 是一个提供给用户的通用的 ShardingSphere 数据接入迁移，及弹性伸缩的解决方案。

### ShardingSphere-UI

ShardingSphere-UI 是 ShardingSphere 的一个简单而有用的 web 管理控制台。它用于帮助用户更简单的使用 ShardingSphere 的相关功能，目前提供注册中心管理、动态配置管理、数据库编排等功能。

项目结构上采取了前后端分离的方式，前端使用 Vue 框架，后端采用 Spring Boot 框架。使用标准的 Maven 方式进行打包，部署，同时也可以采用前后端分离的方式本地运行，方便开发调试。

### ShardingSphere-Sidecar（TODO）

定位为 Kubernetes 的云原生数据库代理，以 Sidecar 的形式代理所有对数据库的访问。 通过无中心、零侵入的方案提供与数据库交互的的啮合层，即 `Database Mesh`，又可称数据库网格。

Database Mesh 的关注重点在于如何将分布式的数据访问应用与数据库有机串联起来，它更加关注的是交互，是将杂乱无章的应用与数据库之间的交互进行有效地梳理。 使用 Database Mesh，访问数据库的应用和数据库终将形成一个巨大的网格体系，应用和数据库只需在网格体系中对号入座即可，它们都是被啮合层所治理的对象。

![ShardingSphere-Sidecar Architecture](/images/shardingsphere-introduce/shardingsphere-sidecar-brief.png)

|            | _ShardingSphere-JDBC_ | _ShardingSphere-Proxy_ | _ShardingSphere-Sidecar_ |
| :--------- | :-------------------- | :--------------------- | :----------------------- |
| 数据库     | 任意                  | MySQL/PostgreSQL       | MySQL/PostgreSQL         |
| 连接消耗数 | 高                    | 低                     | 高                       |
| 异构语言   | 仅 Java               | 任意                   | 任意                     |
| 性能       | 损耗低                | 损耗略高               | 损耗低                   |
| 无中心化   | 是                    | 否                     | 是                       |
| 静态入口   | 无                    | 有                     | 无                       |

### 应用

ShardingSphere-JDBC 采用无中心化架构，用于 Java 开发的高性能的轻量级应用。

ShardingSphere-Proxy 提供静态入口以及异构语言的支持，用于数据迁移及运维查询。

ShardingSphere-Scaling 用于数据迁移。

![image-20201216154030084](/images/shardingsphere-introduce/image-20201216154030084.png)
