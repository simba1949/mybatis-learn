package vip.openpark.cache.mybatis.self;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
public class SecondCacheTest {

    /**
     * <div>
     *     <explain>
     *         mybatis 二级缓存，需要手动开启，在 Mapper.xml 配置中添加 <cache/> 即可
     *         1. 在 Mapper.xml 配置中不添加 <cache/>，即不开启二级缓存：会执行两次 SQL 查询，需要通过日志观察
     *         2. 在 Mapper.xml 配置中添加 <cache/>，即开启二级缓存：只会执行一次 SQL 查询，需要通过日志观察
     *     </explain>
     *     <h1>不同会话，开启二级缓存</h1>
     *     <result>
     *         开启二级缓存，不同会话，两次查询，
     *         1. 执行了一次 SQL 查询；
     *         2. 读取到的对象不是同一个对象，（obj1 == obj2 => false）；
     *     </result>
     * </div>
     */
    @Test
    public void diffSessionWithQueryThenQuery() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDO userDO1 = null;
        try {
            UserDOMapper userDOMapper = sqlSession1.getMapper(UserDOMapper.class);
            userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession1.close();
        }

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDO userDO2 = null;
        try {
            UserDOMapper userDOMapper = sqlSession2.getMapper(UserDOMapper.class);
            userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession2.close();
        }

        log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
    }

    /**
     * <div>
     *     <explain>
     *         mybatis 二级缓存，需要手动开启，在 Mapper.xml 配置中添加 <cache/> 即可
     *         1. 在 Mapper.xml 配置中不添加 <cache/>，即不开启二级缓存：会执行两次 SQL 查询，需要通过日志观察
     *         2. 在 Mapper.xml 配置中添加 <cache/>，即开启二级缓存：只会执行一次 SQL 查询，需要通过日志观察
     *     </explain>
     *     <result>
     *         开启二级缓存，不同会话，两次查询，在第一次查询会话中，穿插未提交的更新操作
     *         1. 执行了两次 SQL 查询；
     *         2. 读取到的对象不是同一个对象，（obj1 == obj2 => false）；
     *     </result>
     * </div>
     */
    @Test
    public void diffSessionWithQueryAndUnCommitUpdateThenQuery() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDO userDO1 = null;
        try {
            UserDOMapper userDOMapper = sqlSession1.getMapper(UserDOMapper.class);
            userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            // 在第一个查询的 Session 做不提交更新
            userDO1.setRealName("新-李白");
            userDOMapper.updateByPk(userDO1);
        } finally {
            // session 放弃提交，直接关闭
            sqlSession1.close();
        }


        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDO userDO2 = null;
        try {
            UserDOMapper userDOMapper = sqlSession2.getMapper(UserDOMapper.class);
            userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession2.close();
        }

        log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
    }

    /**
     * <div>
     *     <explain>
     *         mybatis 二级缓存，需要手动开启，在 Mapper.xml 配置中添加 <cache/> 即可
     *         1. 在 Mapper.xml 配置中不添加 <cache/>，即不开启二级缓存：会执行两次 SQL 查询，需要通过日志观察
     *         2. 在 Mapper.xml 配置中添加 <cache/>，即开启二级缓存：只会执行一次 SQL 查询，需要通过日志观察
     *     </explain>
     *     <result>
     *         开启二级缓存，不同会话，两次查询，中间穿插未提交的更新会话操作
     *         1. 执行了一次 SQL 查询；
     *         2. 读取到的对象不是同一个对象，（obj1 == obj2 => false）；
     *     </result>
     * </div>
     */
    @Test
    public void diffSessionWithQueryThenUnCommitUpdateThenQuery() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDO userDO1 = null;
        try {
            UserDOMapper userDOMapper = sqlSession1.getMapper(UserDOMapper.class);
            userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession1.close();
        }


        SqlSession sqlSession4Update = sqlSessionFactory.openSession();
        try {
            UserDOMapper userDOMapper = sqlSession4Update.getMapper(UserDOMapper.class);
            // 这里不做会话提交
            userDO1.setRealName("新-李白");
            userDOMapper.updateByPk(userDO1);
        } finally {
            // session 放弃提交，直接关闭
            sqlSession4Update.close();
        }


        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDO userDO2 = null;
        try {
            UserDOMapper userDOMapper = sqlSession2.getMapper(UserDOMapper.class);
            userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession2.close();
        }

        log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
    }

    /**
     * <div>
     *     <explain>
     *         mybatis 二级缓存，需要手动开启，在 Mapper.xml 配置中添加 <cache/> 即可
     *         1. 在 Mapper.xml 配置中不添加 <cache/>，即不开启二级缓存：会执行两次 SQL 查询，需要通过日志观察
     *         2. 在 Mapper.xml 配置中添加 <cache/>，即开启二级缓存：只会执行一次 SQL 查询，需要通过日志观察
     *     </explain>
     *     <result>
     *         开启二级缓存，不同会话，两次查询，中间穿插已提交的更新会话操作
     *         1. 执行了两次次 SQL 查询；
     *         2. 读取到的对象不是同一个对象，（obj1 == obj2 => false）；
     *     </result>
     * </div>
     */
    @Test
    public void diffSessionWithQueryThenCommitUpdateThenQuery() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserDO userDO1 = null;
        try {
            UserDOMapper userDOMapper = sqlSession1.getMapper(UserDOMapper.class);
            userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession1.close();
        }


        SqlSession sqlSession4Update = sqlSessionFactory.openSession();
        try {
            UserDOMapper userDOMapper = sqlSession4Update.getMapper(UserDOMapper.class);
            // 这里不做会话提交
            userDO1.setRealName("新-李白");
            userDOMapper.updateByPk(userDO1);
        } finally {
            // session 提交，关闭
            sqlSession4Update.commit();
            sqlSession4Update.close();
        }


        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserDO userDO2 = null;
        try {
            UserDOMapper userDOMapper = sqlSession2.getMapper(UserDOMapper.class);
            userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession2.close();
        }

        log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
    }
}