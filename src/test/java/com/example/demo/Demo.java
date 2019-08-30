package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import groovy.lang.Interceptor;

@Controller
@RequestMapping("/thy")
public class Demo{

	@RequestMapping("/test1")
	@ResponseBody
	public String test(){
		return "hello world";
	}
	
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer();
		sb.append("");
		StringBuilder sb2=new StringBuilder();
	
	}


}
