package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.Author;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping(value="/data/mybatis/author")
@MapperScan("com.example.demo.dao")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	/**
	 * 查询用户
	 * 
	 */
	@RequestMapping(value="/{userId:\\d+}",method=RequestMethod.GET)
	public 	Author getAuthor(@PathVariable Integer userId,HttpServletRequest request){
		System.out.println("userId:"+userId);
		Author author=this.authorService.findAuthor(userId);
		if(author==null){
			throw new RuntimeException("查询错误");
		}else{
			return author;
		}
	 }
}
