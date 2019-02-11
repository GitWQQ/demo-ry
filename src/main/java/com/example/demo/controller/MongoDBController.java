package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.MongoDB;
import com.example.demo.service.MongoDBService;

@RestController
@RequestMapping("/mongo")
public class MongoDBController {
	
	@Autowired
	private MongoDBService mongoDBService;
	
	@Autowired
	private MongoTemplate 	mongoTemplate;
	
	@GetMapping("/save")
	public MongoDB save(){
		MongoDB mongoDB=new MongoDB(2,"Tsng", 20);
		mongoTemplate.save(mongoDB);
		return mongoDB;
	}
	
	@GetMapping("/find")
	public List<MongoDB> find(){
		List<MongoDB> mongoDBList=mongoTemplate.findAll(MongoDB.class);
		return mongoDBList;
	}
	
	@GetMapping("/findByName")
	public MongoDB findByName(@RequestParam("name") String name){
		MongoDB mongoDB=mongoDBService.findByName(name);
		return mongoDB;
	}
}
