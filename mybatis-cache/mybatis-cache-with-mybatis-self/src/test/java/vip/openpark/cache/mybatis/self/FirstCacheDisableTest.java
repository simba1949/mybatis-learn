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
 * @version 2023/11/11 15:04
 */
@Slf4j
public class FirstCacheDisableTest {

    /**
     * <div>
     *     <h1>一级缓存，SESSION 级别</h1>
     *     <h1>STATEMENT 级别本来就是查询两次，无法测试</h1>
     *     <result>
     *         同一个 Session，两次查询：
     *              1.执行两次 SQL 查询
     *              2.读取到的对象不是同一个对象，（obj1 == obj2 => false）；
     *     </result>
     * </div>
     */
    @Test
    public void queryThenQueryTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

        try {
            UserDO userDO1 = userDOMapper.selectByPKWithFlushCacheIsTrue(2L);
            log.info("第一次读取到数据是：{}", userDO1.toString());

            UserDO userDO2 = userDOMapper.selectByPKWithFlushCacheIsTrue(2L);
            log.info("第二次读取到数据是：{}", userDO2.toString());

            log.info("两次读取的对象是否相等：{}", userDO1 == userDO2);
        } finally {
            // session 放弃提交，直接关闭
            sqlSession.close();
        }
    }
}