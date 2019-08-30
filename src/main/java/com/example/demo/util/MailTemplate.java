package com.example.demo.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailTemplate {
	
	//主题：
	public static final String SUBJECT="【如意劳保欢温馨提示】";
	//内容
	public static final String CONTENT_HEAD="欢迎【";
	public static final String CONTENT_FOOT="】加入如意商城";
	
	//发送者
	@Value("${spring.mail.username}")
	private static String from;
	
	//接收者
	private  String[] to;
	
	public String[] getTo() {
		return to;
	}
	public void setTo(String[] to) {
		this.to = to;
	}
	public static String getFrom() {
		return from;
	}
	public static void setFrom(String from) {
		MailTemplate.from = from;
	}
	
	public static void main(String [] args){
		
		System.out.println(from);
	}
	@Override
	public String toString() {
		return "MailTemplate [to=" + Arrays.toString(to) + "]";
	}
	
	
}
