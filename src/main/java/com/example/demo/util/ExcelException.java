package com.example.demo.util;

/**
 * 自定义异常类
 * @author 16406
 *
 */

@SuppressWarnings("serial")
public class ExcelException  extends Exception{

	public ExcelException() {  
		
	}  
	
	public ExcelException(String message) {  
	   super(message);  
	}  
	 
	public ExcelException(Throwable cause) {  
	    super(cause);  
	}  
	
	public ExcelException(String message, Throwable cause) {  
        super(message, cause);  
    }  
	
}
