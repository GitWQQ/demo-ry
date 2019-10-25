package com.example.demo.util;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class JavaMailUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	/**
	 * 发送简单文本邮件
	 *
	 * @param title
	 * @param content
	 * @param from
	 * @param to
	 * @return
	 */
	public void sendTxtMail(String title, String content, String from, String to) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(title);
		simpleMailMessage.setText(content);
		try {
			javaMailSender.send(simpleMailMessage);
			logger.info("简单邮件已经发送");
		} catch (Exception e) {
			logger.error("简单邮件发送失败", e);
		}
	}


	/**
	 * 发送html
	 *
	 * @param title
	 * @param content
	 * @param from
	 * @param to
	 * @return
	 */

	public String sendHtmlMail(String title, String content, String from, String to) {
		MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
		try {
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setSubject(title);
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<head></head>");
			sb.append("<body>");
			sb.append(content);
			sb.append("</body>");
			sb.append("</html>");
			mimeMessageHelper.setText(sb.toString(), true);
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
	 * @param subject
	 * @param content
	 * @param from
	 * @param to_arrays
	 * @return
	 */
		public void sendAttachedImageMail(String subject, String content, String from, String[] to_arrays,String filePath) {
			MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
			for (int num=0;num<to_arrays.length;num++) {
				String to = to_arrays[num];
				try {
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
					mimeMessageHelper.setFrom(from);
					mimeMessageHelper.setTo(to);
					mimeMessageHelper.setSubject(subject);
					StringBuilder sb = new StringBuilder();
					sb.append("<html><head></head>");
					sb.append("<body><h1>spring 邮件测试</h1><p>hello ! spring mail</p>");
					sb.append(content);
					sb.append("<span style=\"color:red\">test</span>");
					sb.append("<img src=\"cid:imageId\"/>");
					sb.append("</html>");
					mimeMessageHelper.setText(sb.toString(), true);
					FileSystemResource img = new FileSystemResource(new File(filePath));
					mimeMessageHelper.addInline("imageId", img);
					javaMailSender.send(mimeMailMessage);
					logger.info("邮件发送成功");
				} catch (MessagingException e) {
					e.printStackTrace();
					logger.error("发送邮件失败");
				}
			}
		}

	    /**
	     * 发送附件
	     * @param subject 主题
	     * @param content 内容
	     * @param from 发送方
	     * @param to_arrays 接收方
		 * @param filePath  附件路径
	     * @return
	     */
	    public void sendAttachmentsMail(String subject,String content,String from,String[] to_arrays,String filePath){
			MimeMessage mimeMailMessage=javaMailSender.createMimeMessage();
			for (int num=0;num<to_arrays.length;num++){
				String to =to_arrays[num];
				try{
					MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMailMessage,true);
					mimeMessageHelper.setFrom(from);
					mimeMessageHelper.setTo(to);
					mimeMessageHelper.setSubject(subject);
					mimeMessageHelper.setText(content,true);
					FileSystemResource file=new FileSystemResource(new File(filePath));
					String fileName=file.getFilename();
					mimeMessageHelper.addAttachment(fileName,file);
					javaMailSender.send(mimeMailMessage);
					String result="邮件发送成功";
					logger.info("邮件发送成功");
					System.out.println(result);
				}catch (MessagingException e){
					e.printStackTrace();
					logger.error("邮件发送失败");
				}
			}
	    }

}
