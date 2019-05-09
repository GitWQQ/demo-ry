package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OnlineMesgService;

@RestController
@RequestMapping("/kf")
public class KfController {

	@Autowired
	private OnlineMesgService onlineMesgService;
	
	
	@RequestMapping("/sendOnlineMesg")
	@ResponseBody
	public Map<String,Object> sendOnLineMesg(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> param=getParamMap(request.getParameterMap());
		onlineMesgService.sendOnlineMesg(param);
		Map<String,Object> result=new HashMap<>();
		result.put("msg","留言成功");
		return result; 
	}
	
	@RequestMapping("/getOnlineMesg")
	public List getOnlineMesg(){
		
		List list=new ArrayList<>();
		return list;
	}
	
	//获取参数
	@SuppressWarnings("unused")
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
