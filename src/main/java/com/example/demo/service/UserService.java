package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.mysql.fabric.xmlrpc.base.Param;

public interface UserService {
	
	/**
	 * 根据条件获取指定用户信息
	 * @param param
	 * @return
	 */
	List getUserByParam(Map<String,Object> param);
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	 List getAllUsersByParam(Map paramMap);
	
	/**
	 * 添加用户信息，注册
	 * @param paramMap
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	 void addUserInfoselective(Map<String,Object> paramMap) throws IOException, AddressException, MessagingException;
	
	/**
	 * 修改指定用户信息
	 * @param paramMap
	 */
	 void updateUserInfoByParam(Map<String,Object> paramMap);

	/**
	 * 根据条件注销用户
	 * 根据唯一标识ID
	 * @param paramMap
	 */
	 void removeUserById(Map<String,Object> paramMap);
}
