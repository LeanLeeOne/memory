package com.leanlee.memory.back;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 李晓辉
 */
@MapperScan("com.leanlee.memory.back.mapper")
@SpringBootApplication
public class PortalApplication {

	public static final String MODULE_NAME = "memory_back";

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	@Resource
	private RestTemplateBuilder restTemplateBuilder;

	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}

}
