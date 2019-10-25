package com.example.demo.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AboutMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.dao.UserRoleMapper;
import com.example.demo.domain.About;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.util.CommonUtil;
import com.example.demo.util.JavaMailUtil;
import com.example.demo.util.MailTemplate;

@Service
public class UserServiceImpl  implements UserService{
	
	@Value("${spring.mail.username}")
	private  String from;
	
	@Autowired
	private AboutMapper aboutMapper;
	
	@Autowired
	private JavaMailUtil javaMailUtil;
	 
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
	public List getAllUsersByParam(Map paramMap) {
		System.out.println("paramMap:"+paramMap);
		System.out.println("========BB=================");
		if("userSearch".equals(paramMap.get("action"))){
			System.out.println("=========AA=================");
			String username_like=paramMap.get("username").toString();
			paramMap.put("username_like",username_like);
			paramMap.put("username","");
		}
		List result=userMapper.getAllUsersByParam(paramMap);
		if (result !=null) {
			return result;
		}
		return null;
	}
	/**
	 * 添加新用户（有盐值方法）
	 * @throws IOException 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,
			timeout=36000,rollbackFor=Exception.class)
	@Override
	public void addUserInfoselective(Map<String,Object> paramMap) throws IOException, AddressException, MessagingException {
		//注册新用户赠送10积分
		Map<String,Object> aboutMap=new HashMap<>();
		aboutMap.put("xh",CommonUtil.getXH("A"));
		aboutMap.put("jf",10);
		aboutMap.put("start","一星");
		aboutMapper.addAbout(aboutMap);
				
		//注册信息
		Subject subject=SecurityUtils.getSubject();
		User user=(User)subject.getPrincipal();
		Calendar calendar=Calendar.getInstance();
		paramMap.put("id",CommonUtil.getID());
		paramMap.put("salt",CommonUtil.getSalt().toString());//获取盐
		String password=paramMap.get("password").toString();
		if(password !=null && !"".equals(password)){
			paramMap.put("password",CommonUtil.md5(paramMap.get("salt").toString(),password));
		}
		String sfzh=paramMap.get("sfzh").toString();
		Integer yearNow=calendar.get(Calendar.YEAR);
		Integer yearBirth=Integer.parseInt(sfzh.substring(6,10));
		Integer age=(yearNow-yearBirth);
		paramMap.put("age",age);
		paramMap.put("usertype",2);
		paramMap.put("created",CommonUtil.getNowDate());
		paramMap.put("updated",CommonUtil.getNowDate());
		paramMap.put("about_xh",aboutMap.get("xh"));
		if(user !=null){
			paramMap.put("lrr",user.getUsername());
			System.out.println("lrr:"+user.getUsername());
		}
		//持久化用户注册信息
		userMapper.addUserInfoselective(paramMap);
		
		//添加用户角色关系
		Map<String,Object> p=new HashMap<>();
		p.put("xh",CommonUtil.getId());
		p.put("uid",paramMap.get("id"));
		p.put("rid","2");
		//设置用户角色，默认为普通用户
		userRoleMapper.addUserRole(p);
		
		//发送邮件提示用户注册成功
		if(paramMap.get("email")!=null && !"".equals(paramMap.get("email"))){
			String content=MailTemplate.CONTENT_HEAD+paramMap.get("username")+MailTemplate.CONTENT_FOOT;
			// 爱的用户【XXX】您已注册成功，欢迎您加入如意劳保，您将获得若干新人积分到您的账户"
			javaMailUtil.sendTxtMail(MailTemplate.SUBJECT,content,from,paramMap.get("email").toString());
		}
		
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
		User user=(User)subject.getPrincipal();
		//给新密码加密加盐
		String newPassWord=paramMap.get("newPassword").toString();
		newPassWord=CommonUtil.md5(user.getSalt(),newPassWord);
		paramMap.put("password",newPassWord);
		paramMap.put("username",user.getUsername());
		paramMap.put("created",CommonUtil.getNowDate());
		userMapper.updateUserInfoByParam(paramMap);
	}

	/**
	 * 注销指定用户信息
	 */
	@Override
	public void removeUserById(Map<String, Object> paramMap) {
		userMapper.removeUserByParam(paramMap);
		String uid=paramMap.get("id").toString();
		if(uid!=null && !"".equals(uid)){
			paramMap.put("uid",uid);
			userRoleMapper.delUserRoleById(paramMap);
		}
		
	}

	public static void main(String[] args) {
		String sfzh="342222199204026852";
		String sub=sfzh.substring(6,10);
		Calendar calendar=Calendar.getInstance();
		int yearNow=calendar.get(Calendar.YEAR);
		System.out.println(yearNow);
		System.out.println(yearNow-Integer.parseInt(sub));
	}
}
