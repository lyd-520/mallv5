package com.tuling;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class TulingmallAuthcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TulingmallAuthcenterApplication.class, args);
	}

}
