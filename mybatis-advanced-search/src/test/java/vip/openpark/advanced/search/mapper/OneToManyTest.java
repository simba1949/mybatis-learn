package vip.openpark.advanced.search.mapper;

import com.alibaba.fastjson2.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vip.openpark.advanced.search.domain.OneToManyRoleDO;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/10 17:48
 */
@Slf4j
public class OneToManyTest {
	private static SqlSession sqlSession;
	private static OneToManyRoleDOMapper oneToManyRoleDOMapper;

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
		oneToManyRoleDOMapper = sqlSession.getMapper(OneToManyRoleDOMapper.class);
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
	public void selectWithOneXmlTest() {
		List<OneToManyRoleDO> dataList = oneToManyRoleDOMapper.selectWithOneXml();
		Assertions.assertNotNull(dataList, "数据查询异常");

		log.info("查询到数据总记录数：{}", dataList.size());
		log.info("查询到数据：{}", JSONArray.toJSONString(dataList));
	}

	@Test
	public void selectWithManyXmlTest() {
		List<OneToManyRoleDO> dataList = oneToManyRoleDOMapper.selectWithManyXml();
		Assertions.assertNotNull(dataList, "数据查询异常");

		log.info("查询到数据总记录数：{}", dataList.size());
		log.info("查询到数据：{}", JSONArray.toJSONString(dataList));
	}
}