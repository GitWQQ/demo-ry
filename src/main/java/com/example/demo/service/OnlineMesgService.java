package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface OnlineMesgService {

	public void sendOnlineMesg(Map<String,Object> param);
	
	public  List  getOnlineMesg();
	
	public  Integer  getOnlineCount(); 
	
	public void readingMsg(Map<String,Object> param);


}
