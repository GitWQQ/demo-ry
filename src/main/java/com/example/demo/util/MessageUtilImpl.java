package com.example.demo.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Component;

/**
 * 发送短信功能实现类
 * @author 16406
 *
 */
// MessageUtilImpl实现类
@Component("messageUtil")
public class MessageUtilImpl implements MessageUtil {

	@Override
	public void sendMessage(String phoneNum, String id) {
		//打开浏览器
		HttpClient client=new HttpClient();
		//创建请求方式post /get 
		PostMethod post=new PostMethod("http://utf8.sms.webchinese.cn/");
		//设置请求参数信息
		post.setParameter("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		post.setParameter("Uid","wqqgl");
		post.setParameter("Key","");
		post.setParameter("smsMob","");
		post.setParameter("smsText","");
		
		//提交请求并返回状态码
		int code=0;
		 try {
		 	code=client.executeMethod(post);
		 	System.out.println("http返回的状态码：" + code);
		 	//获取服务端返回的数据信息
		 	String result=post.getResponseBodyAsString();
		 	System.out.println("发送短信结果为"+result);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			post.releaseConnection();
		}
		
	}
}
