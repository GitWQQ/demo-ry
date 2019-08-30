package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.PermissionService;

@Controller
@RequestMapping("/per")
public class PermissionController {

	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/getAllPermission")
	@ResponseBody
	public List getAllPermissionInfo(HttpServletRequest request){
		Map paramMap=getParamMap(request.getParameterMap());
		List result=permissionService.getAllPermission(paramMap);
		System.err.println("permissList:"+result);
		return result; 
	}
	
	@RequestMapping("/addPermission")
	@ResponseBody
	public Map<String,Object> addPermissionInfo(HttpServletRequest request){
		Map<String,Object> result=new HashMap<>();
		Map paramMap=getParamMap(request.getParameterMap());
		permissionService.addPermission(paramMap);
		result.put("success",true);
		return result;
	}
	
	@RequestMapping("/removePermission")
	@ResponseBody
	public Map<String,Object> removePermissionInfo(HttpServletRequest request){
		Map<String,Object> result=new HashMap<>();
		Map paramMap=getParamMap(request.getParameterMap());
		int is_remove=permissionService.removePermission(paramMap);
		System.out.println("is_remove:"+is_remove);
		if(is_remove==1){
			result.put("msg","true");
		}else{
			result.put("msg","false");
		}
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getPermissionById")
	public String getSendOrderRecord(HttpServletRequest request,Model model){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List result=permissionService.getAllPermission(paramMap);
		model.addAttribute("permissions",result);
		return "Permission/permissionEdit";
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
