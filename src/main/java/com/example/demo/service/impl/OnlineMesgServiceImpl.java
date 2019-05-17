package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OnlineMesgMapper;
import com.example.demo.domain.User;
import com.example.demo.service.OnlineMesgService;
import com.example.demo.util.CommonUtil;

@Service
public class OnlineMesgServiceImpl implements OnlineMesgService{
	
	@Autowired
	private OnlineMesgMapper onlineMesgMapper;
	
	@Override
	public void sendOnlineMesg(Map<String,Object> param){
		param.put("xh",CommonUtil.getID());
		param.put("yxx","1");
		param.put("status","0");
		onlineMesgMapper.sendOnlineMesg(param);
	}

	@Override
	public List getOnlineMesg() {
		Map<String,Object> param=new HashMap<>();
		List result=new ArrayList<>();
		param.put("yxx","1");
		result=onlineMesgMapper.getOnlineMesg(param);
		return result;
	}

	@Override
	public Integer getOnlineCount() {
		Map<String,Object> param=new HashMap<>();
		param.put("yxx","1");
		param.put("status","0");
		return onlineMesgMapper.getOnlineCount(param);
	}
	
	/**
	 * 阅读在线留言
	 * 更改status值等于1，表示已经阅读
	 */
	@Override
	public void readingMsg(Map<String, Object> param) {
		Subject subject=SecurityUtils.getSubject();
		User user=(User)subject.getPrincipal();
		System.out.println("username=============:"+user.getUsername());
		if(user !=null){
			param.put("status","1");
			param.put("rname",user.getUsername());
			param.put("rtime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			onlineMesgMapper.updateByParam( param);
		}
	}
	
}
