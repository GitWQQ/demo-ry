package com.example.demo.util.example;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {
	
	public static int online=0;
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("創建Session");
		online++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("銷毀session");
	}

}
