package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.Role;

public interface RoleMapper {
	public List<Role> getRoleByParam(String username);
	
	public List<Role> getRoles(Map<String,Object> map);

}
