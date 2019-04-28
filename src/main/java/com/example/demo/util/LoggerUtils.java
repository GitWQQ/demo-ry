package com.example.demo.util;

import org.apache.log4j.Logger;

public class LoggerUtils {

	/**
	 * �Ƿ���Debug
	 */
	public static boolean isDebug =  Logger.getLogger(LoggerUtils.class).isDebugEnabled();
	
	/**
	 * Debug ���
	 * @param clazz  	Ŀ��.Class
	 * @param message	�����Ϣ
	 */
	public static void debug(Class<? extends Object> clazz ,String message){
		if(!isDebug)return ;
		Logger logger = Logger.getLogger(clazz);
		logger.debug(message);
	}
	/**
	 * Debug ���
	 * @param clazz  	Ŀ��.Class
	 * @param fmtString �����Ϣkey
	 * @param value		�����Ϣvalue
	 */
	public static void fmtDebug(Class<? extends Object> clazz,String fmtString,Object...value){
		if(!isDebug)return ;
		if(fmtString==null){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		debug(clazz, fmtString);
	}
	/**
	 * Error ���
	 * @param clazz  	Ŀ��.Class
	 * @param message	�����Ϣ
	 * @param e			�쳣��
	 */
	public static void error(Class<? extends Object> clazz ,String message,Exception e){
		Logger logger = Logger.getLogger(clazz);
		if(null == e){
			logger.error(message);
			return ;
		}
		logger.error(message, e);
	}
	/**
	 * Error ���
	 * @param clazz  	Ŀ��.Class
	 * @param message	�����Ϣ
	 */
	public static void error(Class<? extends Object> clazz ,String message){
		error(clazz, message, null);
	}
	/**
	 * �쳣���ֵ���
	 * @param clazz 	Ŀ��.Class
	 * @param fmtString	�����Ϣkey
	 * @param e			�쳣��
	 * @param value		�����Ϣvalue
	 */
	public static void fmtError(Class<? extends Object> clazz,Exception e,String fmtString,Object...value){
		if(fmtString==null){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString, e);
	}
	/**
	 * �쳣���ֵ���
	 * @param clazz		Ŀ��.Class
	 * @param fmtString �����Ϣkey
	 * @param value		�����Ϣvalue
	 */
	public static void fmtError(Class<? extends Object> clazz,
			String fmtString, Object...value) {
		if(fmtString ==null){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}
}
