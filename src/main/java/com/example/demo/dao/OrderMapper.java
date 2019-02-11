package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
	
	public List getOrderRecordByParam(Map paramMap);
	
	public void  toSendOrder(Map<String,Object> paramMap);
	
	public void removeOrder(Map<String,Object> paramMap);
}
