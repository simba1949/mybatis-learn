# Mybatis简单测试案例

## mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
        <setting name="logImpl" value="log4j"/>
    </settings>

    <!--实体类别名设置-->
    <typeAliases>
        <package name="top.simba1949.common"/>
    </typeAliases>

    <!-- 插件(略) -->

    <!--配置环境-->
    <environments default="development">
        <environment id="development">
            <!--事务管理的配置-->
            <transactionManager type="JDBC"></transactionManager>
            <!--数据源的配置-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="19491001"/>
            </dataSource>
        </environment>
    </environments>

    <!--映射器（mappers）-->
    <mappers>
        <package name="top.simba1949.mapper"/>
    </mappers>
</configuration>
```

log4j.properties

```properties
##设置日志记录到控制台的方式
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.err
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n

##设置日志记录到文件的方式
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=mylog.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c{1}:%L - %m%n

##日志输出的级别，以及配置记录方案
log4j.rootLogger=debug, stdout

## 单独设置 hibernate sql 日志的级别（可看到绑定参数值）
log4j.logger.org.hibernate.type=TRACE

#------------------------------------------------------------------------
#    %m 输出代码中指定的消息
#    %p 输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL
#    %r 输出自应用启动到输出该log信息耗费的毫秒数
#    %c 输出所属的类目，通常就是所在类的全名
#    %t 输出产生该日志事件的线程名
#    %n 输出一个回车换行符，Windows平台为“rn”，Unix平台为“n”
#    %d 输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyyy MMM dd HH:mm:ss,SSS}，输出类似：2002年10月18日 ：10：28，921
#    %l 输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。
#    %x Used to output the NDC (nested diagnostic context) associated with the thread that generated the logging event
#    %X Used to output the MDC (mapped diagnostic context) associated with the thread that generated the logging event for specified key
#------------------------------------------------------------------------
```

## CountryDto.java

```java
package top.simba1949.common;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/5 16:48
 */
public class CountryDto {

    private Long id;
    private String countryName;
    private String countryCode;

    // getter/setter & toString() 略
}
```

## CountryMapper.java

```java
package top.simba1949.mapper;

import vip.openpark.mybatis.quick.start.domain.RoleDO;

import java.util.List;

/**
 * @author simba1949@outlook.com
 * @date 2018/9/5 16:48
 */
public interface CountryMapper {

    List<RoleDO> findAll();
}
```

## CountryMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="vip.openpark.mybatis.quick.start.mapper.RoleDOMapper">

    <select id="findAll" resultType="CountryDto">
        select * from country
    </select>

</mapper>
```

