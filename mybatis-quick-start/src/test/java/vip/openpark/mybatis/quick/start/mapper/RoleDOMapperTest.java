package vip.openpark.mybatis.quick.start.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vip.openpark.mybatis.quick.start.domain.RoleDO;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author anthony
 * @version 2018/9/5 19:24
 */
@Slf4j
public class RoleDOMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void findAllTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        RoleDOMapper mapper = sqlSession.getMapper(RoleDOMapper.class);
        List<RoleDO> roleDOS = mapper.findAll();
        for (RoleDO roleDO : roleDOS) {
            log.info("{}", roleDO.toString());
        }

        sqlSession.close();
    }
}