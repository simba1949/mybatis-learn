package vip.openpark.mapper.mapper;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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

	@Test
	public void selectAllTest() throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 配置通用 mapper
		MapperHelper mapperHelper = new MapperHelper();
		mapperHelper.processConfiguration(sqlSession.getConfiguration());

		UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

		List<UserDO> dataList = userDOMapper.selectAll();
		log.info("读取到{}记录数", dataList.size());
		log.info("数据：{}", JSON.toJSONString(dataList));

		sqlSession.commit();
		sqlSession.close();
	}
}