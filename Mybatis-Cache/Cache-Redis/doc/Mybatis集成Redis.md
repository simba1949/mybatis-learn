# Mybatis集成Redis

## pom.xml 添加依赖

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-redis -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-redis</artifactId>
    <version>1.0.0-beta2</version>
</dependency>
```

## redis配置文件

```properties
host=localhost
port=6379
connectionTimeout=5000
soTimeout=5000
password=
database=0
clientName=
```

## 修改 mapper.xml 映射文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="top.simba1949.mapper.SysUserMapper">
  <cache type="org.mybatis.caches.redis.RedisCache"/>
</mapper>
```

