package vip.openpark.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import vip.openpark.interceptor.common.PageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * 1. 查询总数
 * 2. 查询分页（当前页、每页多少条记录数）
 *
 * @author anthony
 * @version 2023/11/12 19:48
 */
@Slf4j
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class})
})
public class OpenPageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取原始 SQL 语句
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql(); // boundSql 为原始SQL
        log.info("原始SQL：【{}】", sql);

        // SQL 入参
        Object parameterObject = boundSql.getParameterObject();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        // 查询 SQL 总数
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String mapperMethodName = mappedStatement.getId();
        // 以 ByPage 结尾的方法处理
        if (mapperMethodName.endsWith("ByPage")) {
            Map<String, Object> params = (Map<String, Object>) parameterObject;
            int pageNum = (int) params.get("pageNum");
            int pageSize = (int) params.get("pageSize");
            PageInfo<?> pageInfo = new PageInfo<>();
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);

            String countSql = "select count(*) from (" + sql + ") temp";
            log.info("查询总数的SQL：【{}】", countSql);

            // 执行 JDBC 操作
            Connection condition = (Connection) invocation.getArgs()[0];
            PreparedStatement preparedStatement = condition.prepareStatement(countSql);
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // 获取总记录数
                pageInfo.setTotal(resultSet.getLong(1));
                // 计算出总页数，分页起始数据
                pageInfo.count();
            }
            resultSet.close();
            preparedStatement.close();

            // 修改原始 SQL ，增加分页
            String pageSql = pageSql(sql, pageInfo);
            log.info("分页的SQL：【{}】", pageSql);

            // 将新SQL语句交给mybatis
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }

        return invocation.proceed();
    }

    /**
     * 根据原始 SQL 生成分页 SQL
     *
     * @return
     */
    public String pageSql(String sql, PageInfo<?> pageInfo) {
        return sql + " limit " + pageInfo.getStartIndex() + "," + pageInfo.getTotalSelect();
    }
}