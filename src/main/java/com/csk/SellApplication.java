package com.csk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

//@SpringBootApplication(exclude = {RedisAutoConfiguration.class})//排除Redis自动配置
@SpringBootApplication
@EnableCaching //缓存支持  配置Redis缓存需要的
public class SellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}
}
