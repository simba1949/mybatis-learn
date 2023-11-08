package vip.openpark.single.table.operation.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vip.openpark.single.table.operation.domain.UserDO;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

/**
 * mybatis 加载外部化配置
 *
 * @author anthony
 * @version 2023/11/8 16:38
 */
@Slf4j
public class MybatisLoadOutPropertiesTest {

    @Test
    public void execTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        // 使用 Properties 的 API 加载这些 properties 文件
        Properties properties = new Properties();
        // 这里可以多次 load properties 配置文件
        properties.load(Resources.getResourceAsStream("db.properties"));

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, properties);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDO4ResultMapMapper userDO4ResultMapMapper = sqlSession.getMapper(UserDO4ResultMapMapper.class);
        List<UserDO> userDOList = userDO4ResultMapMapper.selectAll();
        Assertions.assertNotNull(userDOList, "查询数据异常");

        log.info("从数据库中读取到数据条数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele.toString()));


        sqlSession.commit();
        sqlSession.close();
    }
}