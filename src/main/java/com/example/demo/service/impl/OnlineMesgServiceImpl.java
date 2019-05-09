package com.example.demo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OnlineMesgMapper;
import com.example.demo.service.OnlineMesgService;
import com.example.demo.util.CommonUtil;

@Service
public class OnlineMesgServiceImpl implements OnlineMesgService{
	
	@Autowired
	private OnlineMesgMapper onlineMesgMapper;
	
	@Override
	public void sendOnlineMesg(Map<String,Object> param){
		param.put("xh",CommonUtil.getID());
		onlineMesgMapper.sendOnlineMesg(param);
	}
	
}
