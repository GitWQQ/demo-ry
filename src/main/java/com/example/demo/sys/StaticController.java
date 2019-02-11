package com.example.demo.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thy/static")
public class StaticController {

	/**
	 * 商品展示首页
	 * @return
	 */
	@RequestMapping("/mainPage")
	public String toMainPage(){
		return "shoppingPage";
	}
	
	/**
	 * 跳转到商城的登录页
	 * @return
	 */
	@RequestMapping("/toLoginPage")
	public String toLoginPage(){
		return "system/loginPage";
	}
	
	@RequestMapping("/registerPage")
	public String toReqisterPage(){
		return "system/registerPage";
	}
	
}
