package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
	
	public List  getOrderRecordByParam(Map<String, Object> paramMap);
	
	public void removeOrder(Map<String,Object> paramMap);
	
	public void toSendOrder(Map<String,Object> paramMap); 
}
