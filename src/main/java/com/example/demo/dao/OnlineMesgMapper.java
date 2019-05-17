package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface OnlineMesgMapper {
	
	public void sendOnlineMesg(Map<String,Object> param);
	
	public List getOnlineMesg(Map<String,Object> param);

	public Integer getOnlineCount(Map<String,Object> param);
	
	public void updateByParam(Map<String,Object> param);

	
}
