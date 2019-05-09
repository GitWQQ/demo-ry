package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleMapper;
import com.example.demo.domain.Role;
import com.example.demo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRolesByParam(String username) {
		System.err.println("username:"+username);
		if(username!=null && !"".equals(username)){
			return roleMapper.getRoleByParam(username);
		}
		return null;
	}

	
}
