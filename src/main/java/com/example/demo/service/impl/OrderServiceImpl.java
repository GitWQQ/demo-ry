package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderItemMapper;
import com.example.demo.dao.OrderMapper;
import com.example.demo.service.OrderService;
import com.example.demo.util.CommonUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper; 
	
	@Override
	public List getOrderRecordByParam(Map<String, Object> paramMap) {
		return orderMapper.getOrderRecordByParam(paramMap);
	}

	@Override
	public void removeOrder(Map<String, Object> paramMap) {
		 orderMapper.removeOrder(paramMap);
	}

	@Override
	public void toSendOrder(Map<String, Object> paramMap) {
		Map<String,Object> param=new HashMap<>();
		param.put("order_id",paramMap.get("order_id"));//订单Id
		param.put("payment_time",paramMap.get("payment_time"));//付款时间
		param.put("status",paramMap.get("status"));//支付状态
		param.put("payment_type",paramMap.get("payment_type"));//支付类型
		param.put("shipping_name",paramMap.get("shipping_name"));//物流名称
		param.put("shipping_code",paramMap.get("shipping_code"));//物流单号
		param.put("post_fee",paramMap.get("post_fee"));//邮费
		param.put("consign_time",paramMap.get("consign_time"));//发货时间
		param.put("gxsjc",CommonUtil.getTstamp());//更新时间戳
		orderMapper.toSendOrder(param);
		
	}

}
