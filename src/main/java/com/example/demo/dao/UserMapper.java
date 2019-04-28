package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	
	
	/**
	 * 根据条件获取用户信息
	 * @param param
	 * @return
	 */
	public List getUserByParam(Map<String,Object> param);
	
	/**
	 * 获取所有的用户信息(无限制条件)
	 * @return
	 */
	public List getAllUsers();
	
	/**
	 * 添加用户信息
	 * @param paramMap
	 */
	public void addUserInfoselective(Map<String,Object> paramMap);
	
	/**
	 * 根据参数修改用户信息（如:修改密码）
	 * @param paramMap
	 */
	public void updateUserInfoByParam(Map<String,Object> paramMap);
	
}
