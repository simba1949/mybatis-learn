package vip.openpark.springboot.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author anthony
 * @version 2023/11/13 12:13
 */
@MapperScan("vip.openpark.springboot.integration.mapper")
@SpringBootApplication
public class MybatisWithSpringBootIntegrationApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisWithSpringBootIntegrationApplication.class, args);
	}
}