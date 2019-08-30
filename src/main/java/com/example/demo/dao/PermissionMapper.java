package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.domain.Permission;

public interface PermissionMapper {
	
	/**
	 * ShiroRealm授权时的方法
	 * @param rolename
	 * @return
	 */
	public List<Permission> getPermissionByParam(String rolename);
	
	/**
	 * 添加权限信息
	 * @param paramMap
	 */
	public void addPermission(Map paramMap);
	
	/**
	 * 获取所有的权限信息
	 * @return
	 */
	public List getAllPermissionByParam2(Map paramMap);
	
	/**
	 * 删除权限信息
	 * @param paramMap
	 */
	public int removePermission(Map paramMap);
	

}
