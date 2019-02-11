package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import com.mysql.fabric.xmlrpc.base.Param;
import com.sun.tools.corba.se.idl.constExpr.And;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 根据条件获取指定的用户信息
	 */
	@Override
	public List getUserByParam(Map<String,Object> param) {
		return userMapper.getUserByParam(param);
	}

	/**
	 * 获取所有的用户信息(无限制条件)
	 */
	@Override
	public List getAllUsers() {
		List result=userMapper.getAllUsers();
		if (result !=null) {
			return result;
		}
		return null;
	}
	/**
	 * 添加新用户（有盐值方法）
	 */
	/*@Override
	public void addUserInfoselective(Map<String,Object> paramMap) {
		paramMap.put("id",CommonUtil.getID());
		paramMap.put("salt",CommonUtil.getSalt());//获取盐
		String password=paramMap.get("password").toString();
		if(password !=null && !"".equals(password)){
			paramMap.put("password",CommonUtil.encodePassphrase(paramMap.get("salt").toString(),password));
		}
		paramMap.put("created",CommonUtil.getNowDate());
		paramMap.put("updated",CommonUtil.getNowDate());
		userMapper.addUserInfoselective(paramMap);
	}*/
	
	
	/**
	 * 添加新用户(无盐值)
	 */
	@Override
	public void addUserInfoselective(Map<String,Object> paramMap) {
		paramMap.put("id",CommonUtil.getID());
		String password=paramMap.get("password").toString();
		if(password !=null && !"".equals(password)){
			paramMap.put("password",CommonUtil.encodePassphrase("",password));
		}
		paramMap.put("created",CommonUtil.getNowDate());
		paramMap.put("updated",CommonUtil.getNowDate());
		userMapper.addUserInfoselective(paramMap);
	}
}
