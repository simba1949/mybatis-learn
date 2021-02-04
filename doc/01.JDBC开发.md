# JDBC开发

## 前言

版本说明

```properties
mysql-connector-java=8.0.23
```

相关链接

* mysql-connector-java maven 地址：https://mvnrepository.com/artifact/mysql/mysql-connector-java

## JDBC概述

JDBC：`Java Database Connectivity` ，是 sun 公司为了简化和统一 Java 连接数据库定义的一套规范
JDBC和数据库驱动的关系：接口（JDBC）和实现（驱动jar）的关系

## JDBC开发基本步骤

1. 注册驱动
2. 获得连接
3. 获取执行 SQL 语句的对象：Statement 或者 PreparedStatement
4. 执行 SQL 语句
5. 处理结果
6. 释放资源