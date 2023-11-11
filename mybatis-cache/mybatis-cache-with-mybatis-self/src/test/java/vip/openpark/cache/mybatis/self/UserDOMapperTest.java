package vip.openpark.cache.mybatis.self;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vip.openpark.cache.mybatis.self.domain.UserDO;
import vip.openpark.cache.mybatis.self.mapper.UserDOMapper;

import java.io.IOException;
import java.io.Reader;

/**
 * @author anthony
 * @version 2023/11/11 12:05
 */
@Slf4j
public class UserDOMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * junit5 中 @BeforeAll 修饰的必须要是 static 方法。
     * 只执行一次，执行时机是在所有 @Test 和 @BeforeEach 注解方法之前
     *
     * @throws IOException
     */
    @BeforeAll
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * mybatis 一级缓存是无法关闭的，默认开启
     * 默认情况下，只启用了本地的会话缓存，它仅仅对一个会话中的数据进行缓存
     * 一级缓存作用域，支持会话级别（SESSION）、语句级别（STATEMENT），默认是会话级别
     */
    @Test
    public void mybatisFirstCacheTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

        try {
            UserDO userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            // 设置值，此时 session 并未关闭
            userDO1.setRealName("新-李太白");

            UserDO userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession.close();
        }
    }

    /**
     * mybatis 二级缓存，需要手动开启，在 Mapper.xml 配置中添加 <cache/> 即可
     * 1. 在 Mapper.xml 配置中不添加 <cache/>，即不开启二级缓存：会执行两次 SQL 查询，需要通过日志观察
     * 2. 在 Mapper.xml 配置中添加 <cache/>，即开启二级缓存：只会执行一次 SQL 查询，需要通过日志观察
     */
    @Test
    public void mybatisSecondCacheTest() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        try {
            UserDOMapper userDOMapper = sqlSession1.getMapper(UserDOMapper.class);
            UserDO userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            // 设置值，此时 session 并未关闭
            userDO1.setRealName("李太白");
        } finally {
            // session 放弃提交，直接关闭
            sqlSession1.close();
        }

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            UserDOMapper userDOMapper = sqlSession2.getMapper(UserDOMapper.class);
            UserDO userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession2.close();
        }
    }
}