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
 * @version 2023/11/7 14:49
 */
@Slf4j
public class UserDO4InsertMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4InsertMapper userDO4InsertMapper;

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
        userDO4InsertMapper = sqlSession.getMapper(UserDO4InsertMapper.class);
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

    /**
     * 普通新增数据
     */
    @Test
    public void insertByCommon() {
        UserDO userDO = UserDO.builder().code("S-01").username("dufu").password("dufu123").build();
        int affectRow = userDO4InsertMapper.insertByCommon(userDO);

        Assertions.assertEquals(1, affectRow, "数据新增失败");
    }

    /**
     * 新增数据
     * 使用JDBC方式返回主键自增的值，新增成功后会将 id 设置入参中
     */
    @Test
    public void insertByJdbc() {
        UserDO userDO = UserDO.builder().code("S-02").username("baijuyi").password("baijuyi123").build();
        int affectRow = userDO4InsertMapper.insertByJdbc(userDO);
        log.info("新增数据后的id是：{}", userDO.getId());

        Assertions.assertEquals(1, affectRow, "数据新增失败");
    }

    /**
     * 新增数据
     * 使用selectKey返回主键的值，新增成功后会将 id 设置入参中
     */
    @Test
    public void insertBySelectKey() {
        UserDO userDO = UserDO.builder().code("S-03").username("dumu").password("dumu123").build();
        int affectRow = userDO4InsertMapper.insertBySelectKey(userDO);
        log.info("新增数据后的id是：{}", userDO.getId());

        Assertions.assertEquals(1, affectRow, "数据新增失败");
    }

    /**
     * 新增数据
     * 通过静态方法、静态属性进行新增数据
     */
    @Test
    public void insertByStaticFieldAndMethod() {
        int affectRow = userDO4InsertMapper.insertByStaticFieldAndMethod();
        Assertions.assertEquals(1, affectRow, "数据新增失败");
    }
}