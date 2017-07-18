package com.hishamx;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@MapperScan("com.hishamx.mapper")
public class SpringbootdemoApplication {
	private static Logger logger = Logger.getLogger(SpringbootdemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
		logger.info("SpringBoot Start Success");
	}
}
