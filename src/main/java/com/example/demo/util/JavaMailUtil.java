package com.example.demo.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class JavaMailUtil {
	@Autowired
	 private JavaMailSenderImpl javaMailSender;
	
	 /**
     * 发送简单文本邮件
     * @param title
     * @param content
     * @param from
     * @param to
     * @return
     */
	public String sendTxtMail(String title,String content,String from,String to){
		
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
        System.out.println("邮件已经发送");
        return "邮件已经发送";
    }

	
	 /**
     * 发送html
     * @param title
     * @param content
     * @param from
     * @param to
     * @return
     */
	
	 public String sendHtmlMail(String title,String content,String from,String[] to){
	        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
	        try {
	            mimeMessageHelper.setTo(to);
	            mimeMessageHelper.setFrom(from);
	            mimeMessageHelper.setSubject(title);
	            StringBuilder sb = new StringBuilder();
	            sb.append("<html><head></head>");
	            sb.append("<body><h1>spring 邮件测试</h1><p>hello ! spring mail</p>");
	            sb.append("<span style='color:red'>test</span></html>");
	            mimeMessageHelper.setText(sb.toString(),true);
	            javaMailSender.send(mimeMailMessage);
	            System.out.println("邮件发送成功");
	            return "邮件发送成功";
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	 	
	 /**
	     * 发送图片
	     * @param title
	     * @param content
	     * @param from
	     * @param to
	     * @return
	     */
	    public String sendAttachedImageMail(String title,String content,String from,String[] to){
	        try {
	            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
	            mimeMessageHelper.setTo(to);
	            mimeMessageHelper.setFrom(from);
	            mimeMessageHelper.setSubject(title);
	            StringBuilder sb = new StringBuilder();
	            sb.append("<html><head></head>");
	            sb.append("<body><h1>spring 邮件测试</h1><p>hello ! spring mail</p>");
	            sb.append("<span style=\"color:red\">test</span>");
	            sb.append("<img src=\"cid:imageId\"/>");
	            sb.append("</html>");
	            mimeMessageHelper.setText(sb.toString(),true);
	            FileSystemResource img = new FileSystemResource(new File("E:/1.png"));
	            mimeMessageHelper.addInline("imageId",img);
	            javaMailSender.send(mimeMailMessage);
	            System.out.println("邮件发送成功");
	            return "邮件发送成功";
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	 
	    /**
	     * 发送附件
	     * @param title
	     * @param content
	     * @param from
	     * @param to
	     * @return
	     */
	    public String sendAttendedFileMail(String title,String content,String from,String[] to){
	        try {
	            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
	            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
	            mimeMessageHelper.setTo(to);
	            mimeMessageHelper.setFrom(from);
	            mimeMessageHelper.setSubject(title);
	            StringBuilder sb = new StringBuilder();
	            sb.append("<html><head></head>");
	            sb.append("<body><h1>spring 邮件测试</h1><p>hello ! spring mail</p>");
	            sb.append("<span style=\"color:red\">test</span>");
	            sb.append("<img src=\"cid:imageId\"/>");
	            sb.append("</html>");
	            mimeMessageHelper.setText(sb.toString(),true);
	            FileSystemResource img = new FileSystemResource(new File("E:/1.png"));
	            mimeMessageHelper.addInline("imageId",img);
	            mimeMessageHelper.addAttachment("imageId.png",img);
	            javaMailSender.send(mimeMailMessage);
	            System.out.println("邮件发送成功");
	            return "邮件发送成功";
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }

}
