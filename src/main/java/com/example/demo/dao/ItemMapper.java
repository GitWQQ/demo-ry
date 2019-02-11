package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface ItemMapper {
	public List getAllItemRecord(Map<String,Object> paramMap);
	
	public List getItemRecordByParam(Map<String,Object> paramMap);
	
	public Object updateById(Map<String,Object> paramMap);
	
	public void updateByParamsSelective(Map<String,Object> paramMap);
	
	public void insertItemRecord(Map<String,Object> paramMap);
}
