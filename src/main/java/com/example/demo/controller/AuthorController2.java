package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Author2;
import com.example.demo.service.AuthorService2;


@RestController
@RequestMapping(value="/data/mongodb/author")
public class AuthorController2 {
	
	@Autowired
	private AuthorService2 authorService2;
	
	/**
	 * 查询用户列表
	 */
	@RequestMapping(method=RequestMethod.GET)
	public Map<String,Object> getAuthorList(HttpServletRequest request){
		List<Author2> authorList=this.authorService2.findAuthorList();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("total",authorList.size());
		map.put("rows",authorList);
		return map;
	}
	
	@RequestMapping(value = "/{userId:\\d+}", method = RequestMethod.GET)
	public Author2 getAuthor(@PathVariable Long userId, HttpServletRequest request) {
	    Author2 author = this.authorService2.findAuthor(userId);
	    if(author == null){
	        throw new RuntimeException("查询错误");
	    }
	    return author;
	} 
	
	@RequestMapping(method=RequestMethod.POST)
	public void add(@RequestBody JSONObject jsonObject){
		String userId=jsonObject.getString("user_id");
		String realName=jsonObject.getString("real_name");
		String nickName=jsonObject.getString("nick_name");	
	}

}
