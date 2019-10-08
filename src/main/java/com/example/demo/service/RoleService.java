package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.Role;

public interface RoleService {
	
	 List<Role> getRolesByParam(String username);
	
	 List getRoles(Map<String,Object> param);

	 List getRolesMenu();
}
