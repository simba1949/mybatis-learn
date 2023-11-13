package vip.openpark.mapper.mapper;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import vip.openpark.mapper.domain.UserDO;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/13 9:53
 */
@Slf4j
public class UserDOMapperTest {
	private static SqlSession sqlSession;
	private static UserDOMapper userDOMapper;

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

		// 配置通用 mapper
		MapperHelper mapperHelper = new MapperHelper();
		mapperHelper.processConfiguration(sqlSession.getConfiguration());

		userDOMapper = sqlSession.getMapper(UserDOMapper.class);
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
	public void selectAllTest() {
		List<UserDO> dataList = userDOMapper.selectAll();
		log.info("读取到{}记录数", dataList.size());
		log.info("数据：{}", JSON.toJSONString(dataList));
	}
}