package com.example.demo.sys;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	@RequestMapping("/menu")
	@ResponseBody
	public List getMenu(HttpServletRequest request,HttpServletResponse response){
		Node tree=getTreeJson();
		return tree.getNodes();
	}
	
	@RequestMapping("/toTree")
	public String toTree(){
		return "/resource/resource";
	}
	
	public Node getTreeJson(){
		//从数据库中获取所有的资源
		List<Resource> resList=resourceService.loadAll();
		
		//把所有资源转换成树模型的节点集合，
		List<Node> nodes=new ArrayList<Node>();
		for (Resource resource : resList) {
			Node node=new Node();
			node.setHref(resource.getUrl());
			node.setIcon(resource.getIcon());
			node.setNodeId(resource.getNodeId());
			node.setPid(resource.getPid());
			node.setText(resource.getName());
			nodes.add(node);
		}
		
		Node tree=new Node();
		Node mt=tree.createTree(nodes);
		return mt;
		
	}
}
