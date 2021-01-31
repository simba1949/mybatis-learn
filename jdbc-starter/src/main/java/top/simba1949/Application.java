package top.simba1949;

import com.mysql.cj.jdbc.Driver;
import top.simba1949.common.SysUser;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author anthony
 * @date 2021/1/31 9:28
 */
public class Application {
    public static void main(String[] args) {
        List<SysUser> result = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 1.注册驱动
            DriverManager.registerDriver(new Driver());
            // 2.建立连接
            String url = "jdbc:mysql://192.168.8.15:3306/my_learn?serverTimezone=Asia/Shanghai";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
            // 3.获取执行SQL语句的对象 Statement
            statement = connection.createStatement();
            // 4.执行SQL语句
            String sql = "select * from sys_user";
            resultSet = statement.executeQuery(sql);
            // 5.处理结果集
            // 5.处理结果
            while (resultSet.next()){
                // 可以写列名，也可以写列的排序值（排序值从1开始，也不能使用负数）
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString(2));
                // 封装成对象
                SysUser temp = new SysUser();
                temp.setId(resultSet.getLong("id"));
                temp.setUsername(resultSet.getString("user_name"));
                temp.setUserPassword(resultSet.getString("user_password"));
                temp.setUserEmail(resultSet.getString("user_email"));
                temp.setUserInfo(resultSet.getString("user_info"));
                String createTime = resultSet.getString("create_time");
                if (null != createTime){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    temp.setCreateTime(LocalDateTime.parse(createTime, formatter));
                }
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. 释放资源
            try {
                // 关闭结果集
                if (Objects.nonNull(resultSet)){
                    resultSet.close();
                }
                // 关闭执行 SQL 语句的对象statement
                if (Objects.nonNull(statement)){
                    statement.close();
                }
                // 关闭连接
                if (Objects.nonNull(connection)){
                    connection.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        // 打印结果集
        result.forEach(System.out::println);
    }
}
