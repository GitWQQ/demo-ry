package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserMapper;
import com.example.demo.dao.UserRoleMapper;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import com.mysql.fabric.xmlrpc.base.Param;

@Service
public class UserServiceImpl  implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	
	/**
	 * 根据条件获取指定的用户信息
	 */
	@SuppressWarnings("rawtypes")
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
	@Transactional(value="txManager1")
	@Override
	public void addUserInfoselective(Map<String,Object> paramMap) {
		paramMap.put("id",CommonUtil.getID());
		String salt=CommonUtil.getSalt().toString();
		paramMap.put("salt",salt);//获取盐
		String password=paramMap.get("password").toString();
		if(password !=null && !"".equals(password)){
			paramMap.put("password",CommonUtil.md5(paramMap.get("salt").toString(),password));
		}
		paramMap.put("created",CommonUtil.getNowDate());
		paramMap.put("updated",CommonUtil.getNowDate());
		userMapper.addUserInfoselective(paramMap);
		Map<String,Object> p=new HashMap<>();
		p.put("xh",CommonUtil.getId());
		p.put("uid",paramMap.get("id"));
		p.put("rid","2");
		userRoleMapper.addUserRole(p);
	}
	
	/**
	 * 添加新用户(无盐值)
	 *//*
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
*/
	/**
	 * 根据条件修改用户信息
	 */
	@Override
	public void updateUserInfoByParam(Map<String, Object> paramMap) {
		//获取当前用户
		Subject subject=SecurityUtils.getSubject();
		User user=(User)subject;
		//给新密码加密加盐
		String newPassWord=paramMap.get("newPassword").toString();
		newPassWord=CommonUtil.encodePassphrase(user.getSalt(),newPassWord);
		paramMap.put("password",newPassWord);
		paramMap.put("username",user.getUsername());
		paramMap.put("created",CommonUtil.getNowDate());
		userMapper.updateUserInfoByParam(paramMap);
	}
}
