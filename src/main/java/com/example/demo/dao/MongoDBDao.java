package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.MongoDB;

public interface MongoDBDao  extends MongoRepository<MongoDB,Integer>{
	MongoDB findByName(String name);
}
