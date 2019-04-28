package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ZdMapper;
import com.example.demo.service.ZdService;

@Service
public class ZdServiceImpl implements ZdService {

	@Autowired
	private ZdMapper zdMapper;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getZdInfo(Map<String,Object> paramMap) {
		List list=new ArrayList<>();
	 	String zddm=paramMap.get("zddm").toString();
	 	String []zd=zddm.split(",");   //[ZT,WLXX]
	 	for(int i=0;i<zd.length;i++){
	 		paramMap.put("zddm",zd[i]);
	 		paramMap.put("yxx","1");
	 		list.add(zdMapper.getZdInfo(paramMap));
	 	}
	 	return list;
	}
}
