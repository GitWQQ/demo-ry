package com.example.demo.domain;

import org.springframework.data.annotation.Id;

public class MongoDB {
	
	@Id
	private Integer id;
	
	private String name;
	
	private int age;
	
	public MongoDB(Integer id,String name,int age){
		this.id=id;
		this.name=name;
		this.age=age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MongoDB [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
