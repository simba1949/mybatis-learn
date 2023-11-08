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

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/8 9:53
 */
@Slf4j
public class UserDO4DeleteMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4DeleteMapper userDO4DeleteMapper;

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
        userDO4DeleteMapper = sqlSession.getMapper(UserDO4DeleteMapper.class);
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
    public void deleteByIdsTest() {
        List<Long> ids = Arrays.asList(162L, 163L, 164L);
        int affectRow = userDO4DeleteMapper.deleteByIds(ids);

        Assertions.assertEquals(ids.size(), affectRow, "删除数据失败");
    }
}