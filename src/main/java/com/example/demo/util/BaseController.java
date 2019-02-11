package com.example.demo.util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class BaseController {
	
	protected Logger logger=LoggerFactory.getLogger(getClass());
	
	/**
	 * 输出JSON数据
	 */
	
	public void writeJson(HttpServletResponse response,
			String jsonStr){
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",0);
		PrintWriter pw=null;
		try{
			pw=response.getWriter();
			pw.write(jsonStr);
			pw.flush();
		}catch(Exception e){
			logger.info("输出JSON数据异常",e);
		}finally {
			if(pw !=null){
				pw.close();
			}
		}
	}
	
	public void writeJson(HttpServletResponse response,Object obj){
		response.setContentType("text/json;charset=utf-8");
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires",0);
		PrintWriter pw=null;
		Gson gson=new Gson();
		try{
			pw=response.getWriter();
			pw.write(gson.toJson(obj));
			pw.flush();
		}catch(Exception e){
			logger.info("输出Json数据异常",e);
		}finally{
			if(pw!=null){
				pw.close();
			}
		}
	}
	
}
