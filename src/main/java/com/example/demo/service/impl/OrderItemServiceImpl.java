package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderItemMapper;
import com.example.demo.service.OrderItemService;
import com.example.demo.util.CommonUtil;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	public List getOrderItemRecordByParam(Map<String, Object> paramMap) {
		paramMap.put("yxx","1");
		/*paramMap.put("sfcl","0");//是否处理，1表示已处理，0表示未处理
*/		return orderItemMapper.getOrderItemRecordByParam(paramMap);
	}
	
	/**
	 * 查询未处理订单总数
	 */
	@Override
	public Object getSendOrderCount(Map<String, Object> paramMap) {
		paramMap.put("yxx","1");
		paramMap.put("sfcl","0");
		return orderItemMapper.getSendOrderCount(paramMap);
	}

	@Override
	public Object addSendOrder(Map<String, Object> paramMap) {
		
		return null;
	}

	@Override
	public void removeOrderItem(Map<String, Object> paramMap) {
		paramMap.put("yxx","0");
		paramMap.put("gxsjc",CommonUtil.getTstamp());
		orderItemMapper.removeOrderItem(paramMap);
	}

	/**
	 * 根据条件修改订单
	 */
	@Override
	public void updateOrderItemByParam(Map<String, Object> paramMap) {
		Map<String,Object> param=new HashMap<>();
		param.put("sfcl","1");//是否处理，1表示已经处理，0表示未处理
		param.put("gxsjc",CommonUtil.getTstamp());
		param.put("order_id",paramMap.get("order_id"));
		orderItemMapper.updateOrderItemByParam(param);
	}

}
