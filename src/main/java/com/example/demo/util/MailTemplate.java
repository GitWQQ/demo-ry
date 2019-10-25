package com.example.demo.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailTemplate {
	
	//主题：
	public static final String SUBJECT="【 如意劳保温馨提示^_^ 】";
	//内容
	public static final String CONTENT_HEAD="亲爱的用户【";
	public static final String CONTENT_FOOT="】您已注册成功，欢迎您加入如意劳保，您将获得若干新人积分到您的账户";
	
	//发送者
	@Value("${spring.mail.username}")
	private static String from;
	
	//接收者，接收者可以是一个或多个
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

	@Override
	public String toString() {
		return "MailTemplate [to=" + Arrays.toString(to) + "]";
	}
	
	
}
