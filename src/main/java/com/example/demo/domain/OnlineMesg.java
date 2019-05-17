package com.example.demo.domain;

import java.sql.Date;


public class OnlineMesg {
	//id
	private String xh;
	//名字
	private String name;
	//留言
	private String content;
	//電話
	private String phone;
	//email
	private String email;
	//创建日期
	private Date createtime;
	
	//阅读人
	private String rname;
	
	//
	private Date rtime;
	
	private String status;
	
	private String yxx;
	
	
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getYxx() {
		return yxx;
	}

	public void setYxx(String yxx) {
		this.yxx = yxx;
	}

}
