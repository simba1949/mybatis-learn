# Mybatis缓存集成Ehcache

## pom.xml 依赖

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.1.0</version>
</dependency>
```

## 配置 Ehcache

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd">

    <!-- 磁盘缓存位置 -->
    <diskStore path="F:/cache"/>

    <!-- 默认缓存 -->
    <defaultCache
            maxEntriesLocalHeap="10000"
            eternal="false"
            copyOnRead="true"
            copyOnWrite="true"
            timeToIdleSeconds="3600"
            timeToLiveSeconds="3600"
            overflowToDisk="true"
            diskPersistent="true"
    />
</ehcache>
```

## Mapper.xml文件重新配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.simba1949.mapper.SysRoleMapper">
  <!--ehcache-cache提供俩个缓存实现，
	org.mybatis.caches.ehcache.EhcacheCache
	org.mybatis.caches.ehcache.LoggingEhcache(带日志的缓存)
	在 mapper.xml 映射文件 使用type配置缓存
  -->
  <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
</mapper>
```

