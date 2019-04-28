package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/thy")
public class Demo {

	@RequestMapping("/test1")
	@ResponseBody
	public String test(){
		return "hello world";
	}
}
