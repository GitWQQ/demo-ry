package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface ItemService {
	
	public List getAllItemRecord(Map<String,Object> param);
	
	public List getItemRecordByParam(Map<String,Object> paramMap);
	
	public Object updateById(Map<String,Object> paramMap);
	
	public void updateByParamsSelective(Map<String,Object> paramMap);
	
	public void insertItemRecord(Map<String,Object> paramMap);
}
