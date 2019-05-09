package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.Permission;

public interface PermissionMapper {
	
	public List<Permission> getPermissionByParam(String rolename);
}
