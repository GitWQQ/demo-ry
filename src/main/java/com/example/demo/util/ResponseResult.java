package com.example.demo.util;

import java.util.Map;

public class ResponseResult {
	
	private int code;
	private String message;
	private Map result;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map getResult() {
		return result;
	}
	public void setResult(Map result) {
		this.result = result;
	}
	
}
