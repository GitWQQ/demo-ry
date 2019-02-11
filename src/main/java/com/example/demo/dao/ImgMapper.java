package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface ImgMapper {
	
	public void insertImgRecord(Map<String,Object> paramMap);
	
	public List getImgByParamMap(Map<String,Object> paramMap);
	
	public List getImgLBT(Map<String,Object> paramMap);
	
	public Object getImgByParamMap2(Map<String,Object> paramMap);
	
	public void updateById(Map<String,Object> paramMap);
	 
}
