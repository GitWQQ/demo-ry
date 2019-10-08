package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	
	
	/**
	 * 根据条件获取用户信息
	 * @param param
	 * @return
	 */
	 List getUserByParam(Map<String,Object> param);
	
	/**
	 * 获取所有的用户信息(无限制条件)
	 * @return
	 */
	 List getAllUsers();
	
	/**
	 * 添加用户信息
	 * @param paramMap
	 */
	 void addUserInfoselective(Map<String,Object> paramMap);
	
	/**
	 * 根据参数修改用户信息（如:修改密码）
	 * @param paramMap
	 */
	 void updateUserInfoByParam(Map<String,Object> paramMap);
	
	 void removeUserByParam(Map<String,Object> paramMap);
}
