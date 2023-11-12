package vip.openpark.generator.maven.integration.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import vip.openpark.generator.maven.integration.domain.UserDO;

import java.io.IOException;
import java.io.Reader;

/**
 * @author anthony
 * @version 2023/11/12 12:08
 */
@Slf4j
public class UserDOMapperTest {
    @Test
    public void queryThenQueryTest() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 使用 Java 编码的方式集成通用 mapper：https://github.com/abel533/Mapper/wiki/1.1-java#112-%E7%BC%96%E5%86%99%E4%BB%A3%E7%A0%81%E9%9B%86%E6%88%90
        // 创建一个 MapperHelper
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.processConfiguration(sqlSession.getConfiguration());

        UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

        try {
            UserDO userDO = userDOMapper.selectByPrimaryKey(2L);
            log.info("读取到的数据是：{}", userDO.toString());
        } finally {
            // session 放弃提交，直接关闭
            sqlSession.close();
        }
    }
}