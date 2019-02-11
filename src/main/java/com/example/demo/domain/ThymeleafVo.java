package com.example.demo.domain;

import java.util.Date;

public class ThymeleafVo {
	
	private String name;
	
	private Integer score;
	
	private Integer male;
	
	private Date birthday;

	
	
	
	public ThymeleafVo(String name, Integer score, Integer male, Date birthday) {
		super();
		this.name = name;
		this.score = score;
		this.male = male;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMale() {
		return male;
	}

	public void setMale(Integer male) {
		this.male = male;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
