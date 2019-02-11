package com.example.demo.util;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.example.demo.domain.User;

public class UserInfoLoginToken extends UsernamePasswordToken {
	
	private static final long serialVersionUID = 1L;
	private User userInfo;

	public UserInfoLoginToken(String username,String password,User userInfo) {
		super(username,password);
		this.userInfo = userInfo;
	}
	public UserInfoLoginToken(String username,String password) {
		super(username,password);
	}
	public UserInfoLoginToken(String username,char[] password,
			boolean rememberMe,String host,User userInfo){
		super(username,password,rememberMe,host);
		this.userInfo=userInfo;
	}
	
	public UserInfoLoginToken(String username,String password,boolean rememberMe,User userInfo) {
		super(username,password,rememberMe);
		this.userInfo=userInfo;
	}

	public UserInfoLoginToken(String username ,String password,boolean rememberMe,
			String host,User userInfo){
		super(username,password,rememberMe,host);
		this.userInfo=userInfo;
	}
	
	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	
}
