package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(value="com.example.demo.dao") //// 添加对mapper包扫描
public class DemoApplication {
	public static void main(String[] args) {	
		SpringApplication.run(DemoApplication.class,   args);
	}
}
/**
 * @SpringBootApplication 注解:是一个复合注解，
 * 		包括 @ComponentScan，
 		   @SpringBootConfiguration，
 *         @EnableAutoConfiguration
 *                                         
 *        @SpringBootConfiguration,继承@Configuration,两者功能一致，标注当前类是配置类，并且会将当前类
 *        		内声明的一个或多个@Bean注解标注的方法的实例，放到Spring容器中，并且实例名就是方法名
 *        @ComponentScan: 扫描当前包及其子包下被@Component，@Controller，@Service，@Repository注解标记的类并纳入到spring容器中进行管理。
 *        				     是以前的<context:component-scan>（以前使用在xml中使用的标签，用来扫描包配置的平行支持）。所                                
 *			
 *		  @EnableAutoConfiguration的作用启动自动的配置，@EnableAutoConfiguration注解的意思就是Springboot根据你添加的jar包来配置你项目的默认配置，
 *						  比如根据spring-boot-starter-web ，来判断你的项目是否需要添加了webmvc和tomcat，就会自动的帮你配置web项目中所需要的默认配置。
 */
