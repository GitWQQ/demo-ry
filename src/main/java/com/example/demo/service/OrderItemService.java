package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface OrderItemService {

	public List getOrderItemRecordByParam(Map<String,Object> paramMap);
	
	public Object getSendOrderCount(Map<String,Object> paramMap);
	
	public Object addSendOrder(Map<String,Object> paramMap);
	
	public void removeOrderItem(Map<String,Object> paramMap);

	public void updateOrderItemByParam(Map<String,Object> paramMap);

}
