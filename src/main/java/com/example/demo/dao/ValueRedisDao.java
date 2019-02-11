package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.RedisBaseDao;

@Repository
public class ValueRedisDao {
	
	@Autowired
	public RedisBaseDao redisBaseDao;
	
	private String getKey(){
		return "param";
	}
	
	public void save(String param){
		this.redisBaseDao.addValue(this.getKey(),param);
	}
	public String getParam(){
        return this.redisBaseDao.getValue(this.getKey());
    }
}
