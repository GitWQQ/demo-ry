package com.example.demo.util;
/**
 * 发送短信功能接口
 * @author 16406
 *
 */

public interface MessageUtil {
	
	/**
	 * 发送短信功能接口
	 * @param phoneNum 目的电话号码
	 * @param id 商品id
	 */
	public abstract void sendMessage(String phoneNum,String id);
}
