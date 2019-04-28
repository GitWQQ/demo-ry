package com.example.demo.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessageDemo {
	
	public static void main(String[] args) throws HttpException, IOException{
		//1.打开浏览器
		HttpClient client=new HttpClient();
		//创建请求方式 get/post
		//申请的服务器的地址 http://utf8.sms.webchinese.cn/
		PostMethod post=new PostMethod("http://utf8.sms.webchinese.cn/");
		//设置请求参数 //设置转码
		post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		//
		post.setParameter("Uid","");
		post.setParameter("Key","");
		post.setParameter("smsMob","");
		post.setParameter("smsText","");
		//提交请求并返回状态码
		int code=client.executeMethod(post);
		System.out.println("http返回的状态码：" + code);//如果打印出200说明正常
		//5. 获取服务器端返回的数据信息
		String result = post.getResponseBodyAsString();
		System.out.println("短信发送结果为：" + result);//如果返回1说明发送正常           
	}
}
