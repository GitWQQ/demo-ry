package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface PermissionService {
	
	public List getPermissionByParam(String role);
	
	/**
	 * 获取所有权限信息
	 * @param paramMap
	 * @return
	 */
	public List getAllPermission(Map paramMap);
	
	/**
	 * 添加新的权限
	 * @param paramMap
	 */
	public void addPermission(Map paramMap);
	
	/**
	 * 刪除
	 * @param paramMap
	 * @return
	 */
	public int removePermission(Map paramMap);
	

}
