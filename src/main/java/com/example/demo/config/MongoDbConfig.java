package com.example.demo.config;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.awt.AppContext;

/*@Configuration
@EnableMongoRepositories*/
public class MongoDbConfig {
	/*
	private String mongoHost="localhost";
	private int mongoPort=27017;
	private String dbName="gwh";
			
	private static final String MONGO_BASE_PACKAGE="com.example.demo.domain";
	
	@Autowired
	private ApplicationContext AppContext;

	
	
	@Override 
	protected String getDatabaseName() {
		return dbName;
	}
	
	@Override
	public Mongo mongo() throws Exception {
		MongoClient mongoClient=new MongoClient(mongoHost, mongoPort);
		return mongoClient;
	}
	
	@Override
	protected String getMappingBasePackage(){
		return MONGO_BASE_PACKAGE;
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception{
		return new MongoTemplate(mongo(),getDatabaseName());
	}
	
	*/
	
}
