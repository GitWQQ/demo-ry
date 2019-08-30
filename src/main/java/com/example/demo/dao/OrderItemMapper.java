package com.example.demo.dao;

import java.util.List;
import java.util.Map;

public interface OrderItemMapper {
	
	public List getOrderItemRecordByParam(Map<String,Object> paramMap);
	
	public Object getSendOrderCount(Map<String,Object> paramMap);
	
	public void removeOrderItem(Map<String,Object> paramMap);

	public void updateOrderItemByParam(Map<String,Object> paramMap);
	
	
	public List<Map<String,Object>> exportSaleRanking();
	
	
}
