package top.simba1949.basic;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.simba1949.basic.mapper.SysUserMapper4InsertMapper;
import top.simba1949.basic.model.SysUser;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author anthony
 * @date 2021/2/1
 */
public class InsertApp {
    public static void main(String[] args) throws IOException {
        // 读取数据库配置
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 通过Session工厂获取连接
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // insert
        insert(sqlSession);

        // 提交结果
        sqlSession.commit();
        // 关闭Session
        sqlSession.close();
    }

    public static void insert(SqlSession sqlSession){
        SysUserMapper4InsertMapper mapper = sqlSession.getMapper(SysUserMapper4InsertMapper.class);
        // insert(mapper);
        // insertByJdbc(mapper);
        // nsertBySelectKey(mapper);
        // insertBatch4List(mapper);
        // insertBatch4Set(mapper);
        // insertBatch4Map(mapper);
        insertStatic(mapper);
    }

    /**
     * 简单插入
     * @param mapper
     */
    public static void insert(SysUserMapper4InsertMapper mapper){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("username 4 insert");
        sysUser.setUserPassword("password 4 insert");
        sysUser.setUserEmail("user email 4 insert");
        sysUser.setUserInfo("user info 4 insert");
        sysUser.setHeadImg("head img 4 insert");
        sysUser.setCreateTime(LocalDateTime.now());

        mapper.insert(sysUser);
        System.out.println(sysUser);
    }

    /**
     * 插入并返回主键信息
     * @param mapper
     */
    public static void insertByJdbc(SysUserMapper4InsertMapper mapper){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("username 4 insertByJdbc");
        sysUser.setUserPassword("password 4 insertByJdbc");
        sysUser.setUserEmail("user email 4 insertByJdbc");
        sysUser.setUserInfo("user info 4 insertByJdbc");
        sysUser.setHeadImg("head img 4 insertByJdbc");
        sysUser.setCreateTime(LocalDateTime.now());

        mapper.insertByJdbc(sysUser);
        System.out.println(sysUser);
    }

    /**
     *  插入后，使用selectKey将主键信息设置到对象中
     *  待定，没有设置到值
     * @param mapper
     */
    public static void insertBySelectKey(SysUserMapper4InsertMapper mapper){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("username 4 insertBySelectKey");
        sysUser.setUserPassword("password 4 insertBySelectKey");
        sysUser.setUserEmail("user email 4 insertBySelectKey");
        sysUser.setUserInfo("user info 4 insertBySelectKey");
        sysUser.setHeadImg("head img 4 insertBySelectKey");
        sysUser.setCreateTime(LocalDateTime.now());

        mapper.insertBySelectKey(sysUser);
        System.out.println(sysUser);
    }

    /**
     * 批量插入，list
     * @param mapper
     */
    public static void insertBatch4List(SysUserMapper4InsertMapper mapper){
        List<SysUser> list = Stream.iterate(0, i -> i + 1).limit(10)
                .map(i -> {
                    SysUser sysUser = new SysUser();
                    sysUser.setUsername("username 4 insertBatch4List " + i);
                    sysUser.setUserPassword("password 4 insertBatch4List" + i);
                    sysUser.setUserEmail("user email 4 insertBatch4List " + i);
                    sysUser.setUserInfo("user info 4 insertBatch4List " + i);
                    sysUser.setHeadImg("head img 4 insertBatch4List " + i);
                    return sysUser;
                }).collect(Collectors.toList());

        mapper.insertBatch4List(list);
    }

    /**
     * 批量插入，set
     * @param mapper
     */
    public static void insertBatch4Set(SysUserMapper4InsertMapper mapper){
        Set<SysUser> set = Stream.iterate(0, i -> i + 1).limit(10)
                .map(i -> {
                    SysUser sysUser = new SysUser();
                    sysUser.setUsername("username 4 insertBatch4Set " + i);
                    sysUser.setUserPassword("password 4 insertBatch4Set" + i);
                    sysUser.setUserEmail("user email 4 insertBatch4Set " + i);
                    sysUser.setUserInfo("user info 4 insertBatch4Set " + i);
                    sysUser.setHeadImg("head img 4 insertBatch4Set " + i);
                    return sysUser;
                }).collect(Collectors.toSet());

        mapper.insertBatch4Set(set);
    }

    /**
     * 批量插入，map
     * @param mapper
     */
    public static void insertBatch4Map(SysUserMapper4InsertMapper mapper){
        Map<String, String> map = Stream.iterate(0, i -> i + 1).limit(10)
                .map(i -> {
                    SysUser sysUser = new SysUser();
                    sysUser.setUsername("username 4 insertBatch4Map " + i);
                    sysUser.setUserPassword("password 4 insertBatch4Map " + i);
                    return sysUser;
                }).collect(Collectors.toMap(SysUser::getUsername, SysUser::getUserPassword));

        mapper.insertBatch4Map(map);
    }

    /**
     * 静态值，静态方法插入
     * @param mapper
     */
    public static void insertStatic(SysUserMapper4InsertMapper mapper){
        mapper.insertStatic();
    }
}
