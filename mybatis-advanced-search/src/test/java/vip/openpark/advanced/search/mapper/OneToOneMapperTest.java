package vip.openpark.advanced.search.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import vip.openpark.advanced.search.domain.OneToOneUserDO;
import vip.openpark.advanced.search.mapper.OneToOneUserDOMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/9 21:11
 */
@Slf4j
public class OneToOneMapperTest {
	private static SqlSession sqlSession;
	private static OneToOneUserDOMapper oneToOneUserDOMapper;

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
		oneToOneUserDOMapper = sqlSession.getMapper(OneToOneUserDOMapper.class);
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
	public void selectWithOneXmlAndAliasTest() {
		List<OneToOneUserDO> dataList = oneToOneUserDOMapper.selectWithOneXmlAndAlias();
		Assertions.assertNotNull(dataList, "数据查询异常");

		log.info("查询到数据总记录数：{}", dataList.size());
		dataList.forEach(ele -> log.info("{}", ele.toString()));
	}

	@Test
	public void selectWithOneXmlAndResultMapAndAliasTest() {
		List<OneToOneUserDO> dataList = oneToOneUserDOMapper.selectWithOneXmlAndResultMapAndAlias();
		Assertions.assertNotNull(dataList, "数据查询异常");

		log.info("查询到数据总记录数：{}", dataList.size());
		dataList.forEach(ele -> log.info("{}", ele.toString()));
	}

	@Test
	public void selectWithOneXmlAndResultMapAndAssociationTest() {
		List<OneToOneUserDO> dataList = oneToOneUserDOMapper.selectWithOneXmlAndResultMapAndAssociation();
		Assertions.assertNotNull(dataList, "数据查询异常");

		log.info("查询到数据总记录数：{}", dataList.size());
		dataList.forEach(ele -> log.info("{}", ele.toString()));
	}

	@Test
	public void selectWithManyXmlAndResultMapAndAssociationTest() {
		List<OneToOneUserDO> dataList = oneToOneUserDOMapper.selectWithManyXmlAndResultMapAndAssociation();
		Assertions.assertNotNull(dataList, "数据查询异常");

		log.info("查询到数据总记录数：{}", dataList.size());
		dataList.forEach(ele -> log.info("{}", ele.toString()));
	}
}
