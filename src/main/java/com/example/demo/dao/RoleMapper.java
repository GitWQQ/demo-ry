package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.Role;

public interface RoleMapper {
	List<Role> getRoleByParam(String username);
	
	List<Role> getRoles(Map<String,Object> map);

}
