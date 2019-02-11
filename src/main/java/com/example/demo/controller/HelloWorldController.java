package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ResController意思就是Controller里面的所有方法都是以json格式输出的
@RestController 
public class HelloWorldController {

	@RequestMapping("/hello")
	public String index(){
		return "Hello World";
	}
}
