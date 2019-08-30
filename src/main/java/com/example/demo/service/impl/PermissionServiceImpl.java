package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PermissionMapper;
import com.example.demo.domain.Permission;
import com.example.demo.service.PermissionService;

@Service
public class PermissionServiceImpl  implements PermissionService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> getPermissionByParam(String rolename) {
		if(rolename !=null && !"".equals(rolename)){
			return permissionMapper.getPermissionByParam(rolename);
		}
		return null;
	}

	@Override
	public List getAllPermission(Map paramMap) {
		List result=permissionMapper.getAllPermissionByParam2(paramMap);
		if(result !=null)
			return result;
		return null;
	}

	@Override
	public void addPermission(Map paramMap) {	
		permissionMapper.addPermission(paramMap);
	}

	@Override
	public int removePermission(Map paramMap) {
		return permissionMapper.removePermission(paramMap);
	}

	
}
