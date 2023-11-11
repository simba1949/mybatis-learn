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
 * @version 2023/11/11 14:10
 */
@Slf4j
public class FirstCacheWithSessionLevelTest {

    /**
     * <div>
     *     <h1>一级缓存，SESSION 级别</h1>
     *     <properties>
     *         需要在 mybatis-config.xml 配置，也可以不用配置，默认是会话级别
     *         <setting name="localCacheScope" value="SESSION"/>
     *     </properties>
     *     <result>
     *         同一个 Session，两次查询：
     *              1.只执行一次 SQL 查询
     *              2.读取到的对象是同一个对象（obj1 == obj2 => true）
     *     </result>
     * </div>
     */
    @Test
    public void oneSessionQueryThenQueryTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

        try {
            UserDO userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            UserDO userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());

            log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
        } finally {
            // session 放弃提交，直接关闭
            sqlSession.close();
        }
    }

    /**
     * <div>
     *     <h1>一级缓存，SESSION 级别</h1>
     *     <properties>
     *         需要在 mybatis-config.xml 配置，也可以不用配置，默认是会话级别
     *         <setting name="localCacheScope" value="SESSION"/>
     *     </properties>
     *     <result>
     *         同一个 Session，两次查询，中间穿插未提交的 update 语句：
     *              1.执行两次 SQL 查询
     *              2.读取到的对象不是同一个对象，（obj1 == obj2 => false）
     *              3.第二次读取数据的时候读取到未提交的数据【解决方案是将一级缓存级别设置为 STATEMENT】
     *     </result>
     * </div>
     */
    @Test
    public void oneSessionQueryThenUnCommitUpdateThenQueryTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

        try {
            UserDO userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            // 设置值，此时 session 并未关闭
            userDO1.setRealName("新-李太白");
            // 更新语句，这里不提交
            userDOMapper.updateByPk(userDO1);

            UserDO userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());

            log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
        } finally {
            // session 放弃提交，直接关闭
            sqlSession.close();
        }
    }

    /**
     * <div>
     *     <h1>一级缓存，SESSION 级别</h1>
     *     <properties>
     *         需要在 mybatis-config.xml 配置，也可以不用配置，默认是会话级别
     *         <setting name="localCacheScope" value="SESSION"/>
     *     </properties>
     *     <result>
     *         同一个 Session，两次查询，中间穿插已提交的 update 语句：
     *              1.执行两次 SQL 查询
     *              2.读取到的对象不是同一个对象，（obj1 == obj2 => false），且值已经发生变化
     *     </result>
     * </div>
     */
    @Test
    public void oneSessionQueryThenCommitUpdateThenQueryTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

        try {
            UserDO userDO1 = userDOMapper.selectByPK(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            // 设置值，此时 session 并未关闭
            userDO1.setRealName("新-李太白");
            // 更新语句，这里提交 Session
            userDOMapper.updateByPk(userDO1);
            sqlSession.commit();

            UserDO userDO2 = userDOMapper.selectByPK(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());

            log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
        } finally {
            // session 放弃提交，直接关闭
            sqlSession.close();
        }
    }


    /**
     * <div>
     *     <h1>一级缓存，SESSION 级别</h1>
     *     <properties>
     *         需要在 mybatis-config.xml 配置，也可以不用配置，默认是会话级别
     *         <setting name="localCacheScope" value="SESSION"/>
     *     </properties>
     *     <result>
     *         不同 Session，两次查询：
     *              1.执行两次 SQL 查询
     *              2.读取到的对象不是同一个对象，（obj1 == obj2 => false）
     *     </result>
     * </div>
     */
    @Test
    public void differentSessionQueryThenCommitUpdateThenQueryTest() throws IOException {
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
}