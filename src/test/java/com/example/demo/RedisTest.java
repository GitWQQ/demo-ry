package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.dao.ValueRedisDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
	
	 @Autowired
	 private ValueRedisDao valueRedisDao;
	 @Test
	 public void test() throws Exception {
	   this.valueRedisDao.save("LiangGzone");
	   System.out.println(this.valueRedisDao.getParam());
	 }
}
