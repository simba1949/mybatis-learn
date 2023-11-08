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
 * @version 2023/11/8 15:10
 */
@Slf4j
public class UserDO4DynamicSqlTrimMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4DynamicSqlTrimMapper userDO4DynamicSqlTrimMapper;

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
        userDO4DynamicSqlTrimMapper = sqlSession.getMapper(UserDO4DynamicSqlTrimMapper.class);
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
    public void updateWithSetAndWhereTest() {
        UserDO userDO = UserDO.builder().id(170L)
                .code("CODE-updateSetAndWhere-170")
                .username("USERNAME-updateSetAndWhere-170")
                .realName("REALNAME-updateSetAndWhere-170")
                .nickName("NICKNAME-updateSetAndWhere-170")
                .password("PASSWORD-updateSetAndWhere-170")
                .build();

        int affectRow = userDO4DynamicSqlTrimMapper.updateWithSetAndWhere(userDO);
        Assertions.assertEquals(1, affectRow, "更新失败");
    }

    @Test
    public void updateWithTrimTest() {
        UserDO userDO = UserDO.builder().id(171L)
                .code("CODE-updateSetAndWhere-171")
                .username("USERNAME-updateSetAndWhere-171")
                .realName("REALNAME-updateSetAndWhere-171")
                .nickName("NICKNAME-updateSetAndWhere-171")
                .password("PASSWORD-updateSetAndWhere-171")
                .build();

        int affectRow = userDO4DynamicSqlTrimMapper.updateWithTrim(userDO);
        Assertions.assertEquals(1, affectRow, "更新失败");
    }
}