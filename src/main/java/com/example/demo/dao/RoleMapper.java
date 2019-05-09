package com.example.demo.dao;

import java.util.List;

import com.example.demo.domain.Role;

public interface RoleMapper {
	public List<Role> getRoleByParam(String username);
}
