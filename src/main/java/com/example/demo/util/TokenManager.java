package com.example.demo.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import com.example.demo.domain.User;

public class TokenManager {

	/**
	 * ��ȡ��ǰ�û�����
	 * 
	 */
	public static User getCurrentUser(){
		User userInfo=(User)SecurityUtils.getSubject().getPrincipal();
		return userInfo;
	}
	
	/**
	 * 创建Session,Shiro里的session
	 * 
	 */
	public static Session getSession(){
		Session session=SecurityUtils.getSubject().getSession();
		return session;
	}
	
	/**
	 * 设置Session的值
	 */
	
	public static void setSessionValue(Object sessionId,Object object){
		getSession().setAttribute(sessionId,object);
	}
	/**
	 *获取Session的值
	 */
	
	public static Object getSessionValue(Object key){
		return  getSession().getAttribute(key);
	}
	
	/**
	 * 获取验证码
	 */
	public static String getYZM(){
		String yzm=(String)getSession().getAttribute("_CODE");
		return yzm;
	}
}
