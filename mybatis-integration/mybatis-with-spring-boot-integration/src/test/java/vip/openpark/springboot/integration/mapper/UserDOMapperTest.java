package vip.openpark.springboot.integration.mapper;

import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vip.openpark.springboot.integration.MybatisWithSpringBootIntegrationApplication;
import vip.openpark.springboot.integration.domain.UserDO;

import java.util.List;

/**
 * @author anthony
 * @version 2023/11/13 12:23
 */
@Slf4j
@SpringBootTest(classes = MybatisWithSpringBootIntegrationApplication.class)
public class UserDOMapperTest {

	@Resource
	private UserDOMapper userDOMapper;

	@Test
	public void selectAllTest(){
		List<UserDO> dataList = userDOMapper.selectAll();
		log.info("读取到{}记录数", dataList.size());
		log.info("数据：{}", JSON.toJSONString(dataList));
	}
}
