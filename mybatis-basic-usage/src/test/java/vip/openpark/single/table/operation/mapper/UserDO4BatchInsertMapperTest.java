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
import java.util.*;

/**
 * @author anthony
 * @version 2023/11/7 17:12
 */
@Slf4j
public class UserDO4BatchInsertMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4BatchInsertMapper userDO4BatchInsertMapper;

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
        userDO4BatchInsertMapper = sqlSession.getMapper(UserDO4BatchInsertMapper.class);
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
    public void batchInsertByListTest() {
        List<UserDO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserDO userDO = UserDO.builder().code("LIST-CODE-" + i).username("LIST-UN-" + i).password("LIST-PD-" + i).build();
            list.add(userDO);
        }

        int affectRow = userDO4BatchInsertMapper.batchInsertByList(list);
        Assertions.assertEquals(10, affectRow, "新增失败");
    }

    @Test
    public void batchInsertByListWithParamTest() {
        List<UserDO> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserDO userDO = UserDO.builder().code("LIST-CODE-P-" + i).username("LIST-UN-P-" + i).password("LIST-PD-P-" + i).build();
            list.add(userDO);
        }

        int affectRow = userDO4BatchInsertMapper.batchInsertByListWithParam(list);
        Assertions.assertEquals(10, affectRow, "新增失败");
    }

    @Test
    public void batchInsertBySetTest() {
        Set<UserDO> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            UserDO userDO = UserDO.builder().code("SET-CODE-" + i).username("SET-UN" + i).password("SET-PD-" + i).build();
            set.add(userDO);
        }

        int affectRow = userDO4BatchInsertMapper.batchInsertBySet(set);
        Assertions.assertEquals(10, affectRow, "新增失败");
    }

    @Test
    public void batchInsertBySetWithParamTest() {
        Set<UserDO> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            UserDO userDO = UserDO.builder().code("SET-CODE-P-" + i).username("SET-UN-P-" + i).password("SET-PD-P-" + i).build();
            set.add(userDO);
        }

        int affectRow = userDO4BatchInsertMapper.batchInsertBySetWithParam(set);
        Assertions.assertEquals(10, affectRow, "新增失败");
    }

    @Test
    public void batchInsertByMapTest() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("MAP-CODE-" + i, "MAP-UN-" + i);
        }

        int affectRow = userDO4BatchInsertMapper.batchInsertByMap(map);
        Assertions.assertEquals(10, affectRow, "新增失败");
    }

    @Test
    public void batchInsertByMapWithParamTest() {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("MAP-CODE-P-" + i, "MAP-UN-P-" + i);
        }

        int affectRow = userDO4BatchInsertMapper.batchInsertByMapWithParam(map);
        Assertions.assertEquals(10, affectRow, "新增失败");
    }
}