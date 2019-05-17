package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getAllUser")
	@ResponseBody
	public List getAllUser(HttpServletRequest request){
		System.err.println("===========AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA+++++++++++++++++++++++++++");
		List result=userService.getAllUsers();
		System.out.println("result:"+result);
		return result; 
	}
	
	//获取参数
	private Map<String, Object> getParamMap(Map<String, String[]> map){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (String key : map.keySet()) {
			if (map.get(key) instanceof String[]) {
				if (((String[]) map.get(key)).length > 0) {					
					paramMap.put(key, ((String[]) map.get(key))[0]);
				}
			}
		} 
		return paramMap;
	}
}
