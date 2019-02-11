package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface ImgService {
	
	public void insertImgRecord(Map<String,Object> paramMap);
	
	public List getImgByParamMap(Map<String,Object> paramMap);
	
	public List getImgLBTByParamMap(Map<String,Object> paramMap);
	
	public Object getImgByParamMap2(Map<String,Object> paramMap);
	
	public void updateLbtByParam(Map<String,Object> paramMap);
}
