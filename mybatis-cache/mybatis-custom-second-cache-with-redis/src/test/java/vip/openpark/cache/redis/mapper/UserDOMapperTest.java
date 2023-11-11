package vip.openpark.cache.redis.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import vip.openpark.cache.redis.domain.UserDO;

import java.io.IOException;
import java.io.Reader;

/**
 * @author anthony
 * @version 2023/11/11 16:59
 */
@Slf4j
public class UserDOMapperTest {

    /**
     * 测试是否走 redis 的二级缓存
     *
     * @throws IOException
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
}