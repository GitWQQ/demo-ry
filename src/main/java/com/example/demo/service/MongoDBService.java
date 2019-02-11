package com.example.demo.service;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.MongoDB;

@Repository
public interface MongoDBService {
	
	void save(MongoDB mongoDB);
	
	MongoDB findByName(String name);
}
