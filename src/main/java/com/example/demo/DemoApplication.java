package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

/* 	@Configuration:
 *  ①
 * 	@Configuration是一个类级注释，指示对象是一个bean定义的源 ,@Configuration类通过@bean注解的公共方法声明bean
 *  ②
 * 	@Configuration注解等价于XML中配置的beans ，@Configuration一般与@Bean注解配合使用 
 *  
 *  @ComponentScan 注解自动扫描指定包下的全部标有@Component注解的类，并注册成bean，
 *  	包括@Component下的子注解@Controller,@Service ,@Repository
 *  ③
 *  @EnableAutoConfiguration
 *  启用 Spring 应用程序上下文的自动配置，试图猜测和配置您可能需要的bean。
 *  自动配置类通常采用基于你的 classpath 和已经定义的 beans 对象进行应用。
 */
