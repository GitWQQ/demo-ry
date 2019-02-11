package com.example.demo;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.impl.MailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSendTest {

	@Resource 
	MailServiceImpl mailServiceImpl;
	
	@Test
	public void sendSimpleMailTest(){
		mailServiceImpl.sendSimpleMail("1640611853@qq.com","这是第一封测试邮件","测试已经成功了吗");
	}
	
	@Test
	public void sendHtmlMailTest() throws MessagingException{
		String content="<html>\n"+
							"<body>\n"+
								"<h3>HELLO WORLD WANG DI</h3>\n"+
							"</body>\n"+
						"</html>";
		
		mailServiceImpl.sendHtmlMail("1640611853@qq.com","hahaha",content);
	}
	
	@Test
	public void senAttachmentsMail() throws MessagingException{
		String filePath="F:\\照片\\6.jpg";
		mailServiceImpl.sendAttachmentsMail("1640611853@qq.com","666","厉害了这都知道", filePath);
	}
	
	@Test
	public void sendInlineResourcesMail() throws MessagingException{
		String imgpath="F:\\照片\\6.jpg";
		String rscId="001";
		String content="<html>"
							+"<body>"
								+ "<img src=\'cid:"+rscId+"\'>"
								+ "</img>"
							+ "</body>"
						+"</html>";
		mailServiceImpl.sendInLineResourceMail("1640611853@qq.com","","",imgpath, rscId);
	}
	
}	
