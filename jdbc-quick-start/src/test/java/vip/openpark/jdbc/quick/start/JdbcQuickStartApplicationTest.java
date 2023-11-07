package vip.openpark.jdbc.quick.start;

import com.mysql.cj.jdbc.Driver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import vip.openpark.jdbc.quick.start.domain.UserDO;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author anthony
 * @version 2023/11/7 11:21
 */
@Slf4j
public class JdbcQuickStartApplicationTest {

    private static final String DB_URL = "jdbc:mysql://172.17.35.120:3306/example?serverTimezone=Asia/Shanghai";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    @Test
    public void exec(){
        List<UserDO> result = new ArrayList<>();

        Connection connection = null; // 数据库连接
        Statement statement = null; // 数据库执行语句对象
        ResultSet resultSet = null; // 查询结果集

        try {
            // 1.注册驱动
            DriverManager.registerDriver(new Driver());

            // 2.建立连接
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 3.获取执行SQL语句的对象 Statement
            statement = connection.createStatement();

            // 4.执行SQL语句
            String sql = "select * from user";
            resultSet = statement.executeQuery(sql);

            // 5.处理结果集
            while (resultSet.next()) {
                // 可以写列名，也可以写列的排序值（排序值从1开始，也不能使用负数）
                log.info("id={}，code={}", resultSet.getString("id"), resultSet.getString(2));

                // 封装成对象
                UserDO temp = new UserDO();
                temp.setId(resultSet.getLong("id"));
                temp.setUsername(resultSet.getString("username"));
                temp.setPassword(resultSet.getString("password"));
                String createTime = resultSet.getString("gmt_create");
                if (null != createTime) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    temp.setGmtCreate(LocalDateTime.parse(createTime, formatter));
                }
                result.add(temp);
            }
        } catch (SQLException e) {
            log.info("执行查询结果异常，异常信息：{}", e.getMessage(), e);
        } finally {
            // 6. 释放资源
            try {
                // 关闭结果集
                if (Objects.nonNull(resultSet)) {
                    resultSet.close();
                }
                // 关闭执行 SQL 语句的对象statement
                if (Objects.nonNull(statement)) {
                    statement.close();
                }
                // 关闭连接
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (Exception e) {
                log.info("释放资源异常，异常信息：{}", e.getMessage(), e);
            }
        }
        // 打印结果集
        result.forEach(ele -> log.info("{}", ele.toString()));
    }
}
