package com.tuling;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.environment.EnvironmentManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)/*(exclude = GlobalTransactionAutoConfiguration.class)*/
@EnableDiscoveryClient
public class TulingmallProductApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(TulingmallProductApplication.class, args);
		String prop=applicationContext.getEnvironment().getProperty("spring.mvc.pathmatch.matchingStrategy");
		System.out.println(">>>>>>>>>>>>>>>>>>>"+prop);
	}

}

