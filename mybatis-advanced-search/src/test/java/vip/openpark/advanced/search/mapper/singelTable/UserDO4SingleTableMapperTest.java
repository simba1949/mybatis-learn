package vip.openpark.advanced.search.mapper.singelTable;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vip.openpark.advanced.search.domain.UserDO;
import vip.openpark.advanced.search.mapper.UserDO4SingleTableMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/9 19:44
 */
@Slf4j
public class UserDO4SingleTableMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4SingleTableMapper userDO4SingleTableMapper;

    /**
     * junit5 中 @BeforeAll 修饰的必须要是 static 方法。
     * 只执行一次，执行时机是在所有 @Test 和 @BeforeEach 注解方法之前
     *
     * @throws IOException
     */
    @BeforeAll
    public static void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
        userDO4SingleTableMapper = sqlSession.getMapper(UserDO4SingleTableMapper.class);
    }

    /**
     * junit5 中 @AfterAll 修饰的必须要是 static 方法。
     * 只执行一次，执行时机是在所有 @Test 和 @AfterEach 注解方法之后。
     */
    @AfterAll
    public static void finalExec() {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectWithDefaultTest() {
        List<UserDO> userDOList = userDO4SingleTableMapper.selectWithDefault();
        Assertions.assertNotNull(userDOList, "查询异常");

        log.info("读取到记录数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele));
    }

    @Test
    public void selectWithAliasTest() {
        List<UserDO> userDOList = userDO4SingleTableMapper.selectWithAlias();
        Assertions.assertNotNull(userDOList, "查询异常");

        log.info("读取到记录数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele));
    }

    @Test
    public void selectWithResultMapTest() {
        List<UserDO> userDOList = userDO4SingleTableMapper.selectWithResultMap();
        Assertions.assertNotNull(userDOList, "查询异常");

        log.info("读取到记录数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele));
    }
}
