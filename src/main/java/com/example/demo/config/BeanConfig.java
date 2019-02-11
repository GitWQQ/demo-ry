package com.example.demo.config;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.demo.util.MyBatisInterceptor;


//@EnableTransactionManagement
//@PropertySource(value = {"classpath:config/source.properties"})
@Configuration
public class BeanConfig {
 
   /* @Autowired
    private Environment env;
 
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("source.driverClassName").trim());
        dataSource.setUrl(env.getProperty("source.url").trim());
        dataSource.setUsername(env.getProperty("source.username").trim());
        dataSource.setPassword(env.getProperty("source.password").trim());
        return dataSource;
    }
 
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }*/
	
	/*@Bean(name="oneDataSource")
	@Qualifier("oneDataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.one")
	public DataSource oneDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="twoDataSource")
	@Qualifier("twoDataSource")
	@ConfigurationProperties(prefix="spring.datasource.two")
	public DataSource twoDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="oneJdbcTemplate")
	public JdbcTemplate oneJdbcTemplate(@Qualifier("oneDataSource")DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name="twoJdbcTemplate")
	public JdbcTemplate twoJdbcTemplate(@Qualifier("twoDataSource")DataSource dataSource){
			return new JdbcTemplate(dataSource);
	}*/
	
	@Bean
	public Interceptor getInterceptor(){
		return new MyBatisInterceptor();
	}
	
}