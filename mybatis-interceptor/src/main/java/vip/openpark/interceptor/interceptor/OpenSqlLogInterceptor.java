package vip.openpark.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/12 20:55
 */
@Slf4j
@Intercepts(value = {
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class OpenSqlLogInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];

        // mybatis 将 sql 和条件值封装到 BoundSql 对象中
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();

        Object result = null;

        long start = System.currentTimeMillis();
        try {
            result = invocation.proceed();
        } finally {
            long end = System.currentTimeMillis();
            log.info("自定义日志插件：当前正在执行的SQL共耗时【{}】毫秒", end - start);

            printSql(configuration, boundSql);
        }

        return result;
    }

    /**
     * 打印执行的 SQL
     *
     * @param configuration
     * @param boundSql
     */
    private void printSql(Configuration configuration, BoundSql boundSql) {
        // 获取 SQL 语句
        String sql = boundSql.getSql();
        if (null == sql || sql.length() == 0) {
            return;
        }
        // 非必要的空格换行替换
        sql = sql.replaceAll("\\n", " ").replaceAll("\\s+", " ");

        // 入参对象
        Object parameterObject = boundSql.getParameterObject();
        // 入参的值
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        if (null == parameterObject) {
            log.info("自定义日志插件：正在执行的SQL是 【{}】", sql);
            return;
        }

        // 获取类型处理器注册器
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = replace(sql, parameterObject);
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String property = parameterMapping.getProperty();
                if (metaObject.hasGetter(property)) {
                    Object obj = metaObject.getValue(property);
                    sql = replace(sql, obj);
                } else if (boundSql.hasAdditionalParameter(property)) {
                    // 动态参数
                    Object additionalParameter = boundSql.getAdditionalParameter(property);
                    sql = replace(sql, additionalParameter);
                }
            }
        }

        log.info("自定义日志插件：正在执行的SQL是 【{}】", sql);
    }

    /**
     * SQL 替换
     *
     * @param sql
     * @param parameterObject
     * @return
     */
    public String replace(String sql, Object parameterObject) {
        String result;
        if (parameterObject instanceof String) {
            result = "'" + parameterObject + "'";
        } else if (parameterObject instanceof Date) {
            result = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parameterObject) + "'";
        } else if (parameterObject instanceof LocalDateTime) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            result = "'" + formatter.format((LocalDateTime) parameterObject) + "'";
        } else {
            result = parameterObject.toString();
        }
        return sql.replaceFirst("\\?", result);
    }
}