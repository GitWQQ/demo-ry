package com.example.demo.sys;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.util.LoggerUtils;
import com.example.demo.util.TokenManager;
import com.example.demo.util.verifyCodeUtils.VerifyCodeUtils;
import com.example.demo.util.verifyCodeUtils.vcode.Captcha;
import com.example.demo.util.verifyCodeUtils.vcode.GifCaptcha;
import com.example.demo.util.verifyCodeUtils.vcode.SpecCaptcha;



@Controller
@RequestMapping("/thy/static/check")
public class CommonController {
	
	/**
	 * 获取普通验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="getVCode",method=RequestMethod.GET)
	public void getVCode(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires",0);
		response.setContentType("image/jpg");
		
	   //��������ִ�  
		String verifyCode=VerifyCodeUtils.generateVerifyCode(4);
		//���뵽Session
		TokenManager.setSessionValue(VerifyCodeUtils.V_CODE,verifyCode);
		//����ͼƬ  
		int w = 146, h = 33;  
		try {
			VerifyCodeUtils.outputImage(w, h,response.getOutputStream(),verifyCode);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtils.fmtError(getClass(),e, "��ȡ��֤���쳣��%s",e.getMessage());
		}
	}
	
	
	/**
	 * 获取gif验证码
	 * @param response
	 */
	@RequestMapping(value="getGifCode.shtml",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		 try {
			 response.setHeader("Pragma", "No-cache");  
			 response.setHeader("Cache-Control", "no-cache");  
			 response.setDateHeader("Expires", 0);  
			 response.setContentType("image/gif");  
			 Captcha captcha = new GifCaptcha(146,42,4);
			 ServletOutputStream out=response.getOutputStream();
			 captcha.out(out);
			 out.flush();
	         TokenManager.setSessionValue(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());
		 } catch (IOException e) {
			e.printStackTrace();
			LoggerUtils.fmtError(getClass(),e, "��ȡ��֤���쳣��%s",e.getMessage());
		}
	}
	
	/**
	 * jgp验证码
	 * @param response
	 */
	@RequestMapping(value="getJPGCode.shtml",method=RequestMethod.GET)
	public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("image/jpg");  
			
			Captcha captcha = new SpecCaptcha(146,33,4);
			//���
			captcha.out(response.getOutputStream());
			HttpSession session = request.getSession(true);  
			//����Session
			session.setAttribute("_code",captcha.text().toLowerCase());  
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "��ȡ��֤���쳣��%s",e.getMessage());
		}
	}
	
}
