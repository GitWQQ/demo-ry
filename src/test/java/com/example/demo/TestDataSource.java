package com.example.demo;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDataSource {

	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	
	@Test
	public void testDataSource(){
		System.out.println("driverClassName:"+driverClassName);
		System.out.println("userName:"+username);
		System.out.println("passWord:"+password);
		System.out.println("url:"+url);
	}
	
	@Test
	public void dataSource(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:application.properties");
		DataSource dataSource=context.getBean(DataSource.class);
		System.out.println(dataSource);
	}
}
