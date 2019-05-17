package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.OnlineMesgService;

@Controller
@RequestMapping("/kf")
public class KfController {

	@Autowired
	private OnlineMesgService onlineMesgService;
	
	
	/**
	 * 持久化在线留言信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sendOnlineMesg")
	@ResponseBody
	public Map<String,Object> sendOnLineMesg(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> param=getParamMap(request.getParameterMap());
		onlineMesgService.sendOnlineMesg(param);
		Map<String,Object> result=new HashMap<>();
		result.put("msg","留言成功");
		return result; 
	}
	
	/**
	 * 获取所以在线留言信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getOnlineMesg")
	@ResponseBody
	public List getOnlineMesg(){
		List list=new ArrayList<>();
		list=onlineMesgService.getOnlineMesg();
		return list;
	}
	
	
	/**
	 * 获取所有在线留言数
	 * @return
	 */
	@RequestMapping("/getOnlineCount")
	@ResponseBody
	public Map<String,Object> getOnlineCount(){
		Map<String,Object> result=new HashMap<>();
		Integer num=onlineMesgService.getOnlineCount();
		result.put("num",num.toString());
		return result;
	}
	
	@RequestMapping("/readingMsg")
	@ResponseBody
	public Map<String,Object> readingMsg(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> result=new HashMap<>();
		Map<String,Object> param=getParamMap(request.getParameterMap());
		if(param.get("xh")!=null){
			onlineMesgService.readingMsg(param);
			result.put("success",true);
			result.put("msg","已经阅读");
			return result;
		}else{
			result.put("success", false);
			result.put("msg","阅读失败，请及时联系开发人员");
			return result;
		}
		
	}
	
	
	/**
	 * 获取参数
	 * @param map
	 * @return
	 */
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
