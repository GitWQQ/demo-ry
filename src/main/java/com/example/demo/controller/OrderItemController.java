package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping("/thy")
public class OrderItemController {

	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 获取未处理订单集合
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/order/getOrderItemRecordByParam")
	@ResponseBody
	public List getOrderItemRecordByParam(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<>();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		return  orderItemService.getOrderItemRecordByParam(paramMap);
	}
	
	/**
	 * 获取发货单信息/获取发货单详情
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/order/getSendOrderRecord")
	public String getSendOrderRecord(HttpServletRequest request,Model model){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List result=orderService.getOrderRecordByParam(paramMap);
		model.addAttribute("orders",result);
		if("detail".equals(paramMap.get("czbs"))){
			return "itemOrder/order_detail";
		}
		return "itemOrder/order_send";
	}
	
	/**
	 * 订单数据获取数据，转换成json数据
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/order/getSendOrderRecordData")
	@ResponseBody
	public List getSendOrderRecordData(HttpServletRequest request){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		List result=orderService.getOrderRecordByParam(paramMap);
		return result; 
	}
	
	/**
	 * 查询未处理订单的数量（徽标数）
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/getSendOrderCount")
	@ResponseBody
	public Map<String,Object> getSendOrderCount(HttpServletRequest request){
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		Map<String,Object> resultMap=new HashMap<>();
		Integer count=(Integer)orderItemService.getSendOrderCount(paramMap);
		resultMap.put("count",count.toString());
		return resultMap;
	}
	
	/**
	 * 发货
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/toOrderSend")
	@ResponseBody
	public Map<String,Object> toOrderSend(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<>();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		orderService.toSendOrder(paramMap);
		orderItemService.updateOrderItemByParam(paramMap);
		resultMap.put("msg","发货成功!");
		return resultMap;
	}
	
	/**
	 * 删除订单
	 * @param request
	 * @return
	 */
	@RequestMapping("/order/removeOrder")
	@ResponseBody
	public Map<String,Object> toRemoveOrder(HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<>();
		Map<String,Object> paramMap=getParamMap(request.getParameterMap());
		orderItemService.removeOrderItem(paramMap);
		orderService.removeOrder(paramMap);
		resultMap.put("msg","删除成功");
		return resultMap;
	}
	
	//获取参数
	private Map<String, Object> getParamMap(Map<String, String[]> map){
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	    	for (String key : map.keySet()) {
				if (map.get(key) instanceof String[]) {
					if (((String[]) map.get(key)).length > 0) {					
						paramMap.put(key, ((String[]) map.get(key))[0]);
					}
				}
			} 
	    	return paramMap;
	 }
}
