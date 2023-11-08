package vip.openpark.single.table.operation.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vip.openpark.single.table.operation.domain.UserDO;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anthony
 * @version 2023/11/8 10:20
 */
@Slf4j
public class UserDO4DynamicSqlMultiParamMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4DynamicSqlMultiParamMapper userDO4DynamicSqlMultiParamMapper;

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
        userDO4DynamicSqlMultiParamMapper = sqlSession.getMapper(UserDO4DynamicSqlMultiParamMapper.class);
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
    public void selectByMultiParamWithMapTest() {
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "CODE");
        map.put("username", "UN");

        List<UserDO> userDOList = userDO4DynamicSqlMultiParamMapper.selectByMultiParamWithMap(map);
        Assertions.assertNotNull(userDOList, "查询数据异常");

        log.info("从数据库中读取到数据条数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele.toString()));
    }

    @Test
    public void selectByGetMultiParamWithMapTest() {
        Map<Object, Object> map = new HashMap<>();
        map.put("code", "CODE");
        map.put("username", "UN-7");

        List<UserDO> userDOList = userDO4DynamicSqlMultiParamMapper.selectByGetMultiParamWithMap(map);
        Assertions.assertNotNull(userDOList, "查询数据异常");

        log.info("从数据库中读取到数据条数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele.toString()));
    }

    @Test
    public void selectByMultiParamWithParamTest() {
        List<UserDO> userDOList = userDO4DynamicSqlMultiParamMapper.selectByMultiParamWithParam("LIST", "LIST-UN-8");
        Assertions.assertNotNull(userDOList, "查询数据异常");

        log.info("从数据库中读取到数据条数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele.toString()));
    }
}
