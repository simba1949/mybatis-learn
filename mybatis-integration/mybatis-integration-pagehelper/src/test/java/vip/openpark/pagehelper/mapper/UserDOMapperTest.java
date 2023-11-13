package vip.openpark.pagehelper.mapper;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import vip.openpark.pagehelper.domain.UserDO;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author anthony
 * @version 2023/11/13 11:55
 */
@Slf4j
public class UserDOMapperTest {

	@Test
	public void rowBoundsTest() throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

		List<UserDO> dataList = userDOMapper.selectByRowBounds(new RowBounds(2, 2));
		log.info("读取到{}记录数", dataList.size());
		log.info("数据：{}", JSON.toJSONString(dataList));

		sqlSession.close();
	}

	@Test
	public void startPageTest() throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

		// 但是调用时需要注意的是,PageHelper.startPage 方法后的第一个 Select 方法才会被分页
		PageHelper.startPage(2, 2);
		List<UserDO> dataList = userDOMapper.selectByStartPage();
		log.info("读取到{}记录数", dataList.size());
		log.info("数据：{}", JSON.toJSONString(dataList));

		sqlSession.close();
	}

	@Test
	public void offsetPageTest() throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserDOMapper userDOMapper = sqlSession.getMapper(UserDOMapper.class);

		// 但是调用时需要注意的是,PageHelper.startPage 方法后的第一个 Select 方法才会被分页
		PageHelper.offsetPage(2, 2);
		List<UserDO> dataList = userDOMapper.selectByOffsetPage();
		log.info("读取到{}记录数", dataList.size());
		log.info("数据：{}", JSON.toJSONString(dataList));

		sqlSession.close();
	}
}
