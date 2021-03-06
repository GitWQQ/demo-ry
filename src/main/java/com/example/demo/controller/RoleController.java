package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.example.demo.domain.Role;
import com.example.demo.util.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("/getRoles")
	@ResponseBody
	public List getRoles(HttpServletRequest request){
		System.out.println("========ROLE=============================");
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List<Role> list=roleService.getRoles(paramMap);
		List<Node> listNode=new ArrayList<>();

		return list;
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
