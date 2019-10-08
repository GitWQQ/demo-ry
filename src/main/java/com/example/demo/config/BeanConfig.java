package com.example.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.demo.util.MyBatisInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	/**
	 * mybatis拦截器 bean ，必须
	 * @return
	 */
	@Bean
	public Interceptor getInterceptor(){
		return new MyBatisInterceptor();
	}
	
	/**
	 * html页面运用 shiro标签，必须
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
	    return new ShiroDialect();
	}
	
}