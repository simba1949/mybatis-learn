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

/**
 * @author anthony
 * @version 2023/11/8 9:38
 */
@Slf4j
public class UserDO4UpdateMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4UpdateMapper userDO4UpdateMapper;

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
        userDO4UpdateMapper = sqlSession.getMapper(UserDO4UpdateMapper.class);
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
    public void updateByIdTest() {
        UserDO userDO = UserDO.builder().id(171L).code("UPDATE-01").username("UPDATE").password("UPDATE").build();
        int affectRow = userDO4UpdateMapper.updateById(userDO);

        Assertions.assertEquals(1, affectRow, "数据更新失败");
    }
}