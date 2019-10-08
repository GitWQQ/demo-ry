package com.example.demo.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.domain.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.Resource;
import com.example.demo.service.ResourceService;
import com.example.demo.util.Node;

@Controller
@RequestMapping("/thy/sys/resource")
public class ResourceController {


	@Autowired
	private ResourceService resourceService;


	@Autowired
	private RoleService roleService;

	@RequestMapping("/roleMenu")
	@ResponseBody
	public List getRoleMenu(HttpServletRequest request,HttpServletResponse response){
		Node tree=getTreeJson("role");
		return tree.getNodes();
	}

	@GetMapping("menu")
	@ResponseBody
	public List getMenu(HttpServletRequest request,HttpServletResponse response){
		Node tree=getTreeJson("resource");
		return tree.getNodes();
	}

	@RequestMapping("/toTree")
	public String toTree(){
		return "/resource/resource";
	}


	public Node getTreeJson(String action){
		//从数据库中获取所有的资源
	    List<Node> nodes = new ArrayList<Node>();
		if("role".equals(action)) {
			List<Role> roles = roleService.getRolesMenu();
			for (Role role : roles) {
				Node node = new Node();
				node.setText(role.getRoleName());                          //把所有资源转换成树模型的节点集合，
				node.setNodeId(role.getRoleCode());
				node.setPid("0");
				nodes.add(node);
			}
		}
		if("resource".equals(action)) {
			List<Resource> resList = resourceService.loadAll();
			for (Resource resource : resList) {
				Node node = new Node();
				node.setHref(resource.getUrl());
				node.setIcon(resource.getIcon());
				node.setNodeId(resource.getNodeId());
				node.setPid(resource.getPid());
				node.setText(resource.getName());
				nodes.add(node);
			}
		}
		Node tree=new Node();
		Node mt=tree.createTree(nodes);
		return mt;
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
