package com.example.demo.service;

import java.util.List;
import java.util.Map;
import com.mysql.fabric.xmlrpc.base.Param;

public interface UserService {
	
	public  List getUserByParam(Map<String,Object> param);
	
	public List getAllUsers();
	
	public void addUserInfoselective(Map<String,Object> paramMap);
	
	public void updateUserInfoByParam(Map<String,Object> paramMap);
}
