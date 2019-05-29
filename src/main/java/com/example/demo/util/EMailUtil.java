package com.example.demo.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class EMailUtil {
	
	public static void sendEMail(String email,String emailMsg) throws AddressException, MessagingException{
		// 1.创建一个程序与邮件服务器会话对象 Session
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		//指定发送邮件的主机为 smtp.qq.com
		props.setProperty("mail.host", "smtp.qq.com");
		// 指定验证为true
		props.setProperty("mail.smtp.auth", "true");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("1640611853@qq.com", "mrzfyrsuyfjneiaj");
			}
		};
		//创建Session
		Session session = Session.getInstance(props, auth);
		//开启Session的debug模式，这样就可以看到程序发送email的运行状态
		session.setDebug(false);
		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("1640611853@qq.com")); // 设置发送者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者
		message.setSubject("如意商城注册结果");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.创建 Transport用于将邮件发送
		Transport.send(message);
	}
	
	public static void main(String[] args) {
		try {
			sendEMail("1640611853@qq.com","恭喜");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
