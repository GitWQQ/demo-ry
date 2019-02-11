package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MongoDBDao;
import com.example.demo.domain.MongoDB;
@Service
public class MongoDBServiceImpl implements MongoDBService {

	@Autowired
	private MongoDBDao mongoDBDao;
		
	@Override
	public void save(MongoDB mongoDB) {
		mongoDBDao.save(mongoDB);
	}

	@Override
	public MongoDB findByName(String name) {
		return this.mongoDBDao.findByName(name);
	}

}
