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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author anthony
 * @version 2023/11/8 11:10
 */
@Slf4j
public class UserDO4DynamicSqlIfMapperTest {
    private static SqlSession sqlSession;
    private static UserDO4DynamicSqlIfMapper userDO4DynamicSqlIfMapper;

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
        userDO4DynamicSqlIfMapper = sqlSession.getMapper(UserDO4DynamicSqlIfMapper.class);
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
    public void exec(){
        String username = "李白";
        List<Byte> gendersList = Arrays.asList((byte)0, (byte)1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2023-11-01 00:00:00", dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse("2023-11-30 00:00:00", dateTimeFormatter);

        Set<Byte> blEnables = new HashSet<>();
        blEnables.add((byte)0);
        blEnables.add((byte)1);

        Map<Object, Object> map = new HashMap<>();
        map.put("1", "libai");
        map.put("2", "dufu");

        List<UserDO> userDOList = userDO4DynamicSqlIfMapper.select(username, gendersList, start, end, blEnables, map);
        Assertions.assertNotNull(userDOList, "查询数据异常");

        log.info("从数据库中读取到数据条数：{}", userDOList.size());
        userDOList.forEach(ele -> log.info("{}", ele.toString()));
    }
}
