package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable{
	//id 唯一表示主键
	@Id
	private String id;

	//用户名(昵称)
	@Column(unique=true)
	private String username;

	//真实姓名
	private String realname;

	//身份证号
	private String sfzh;

	//年龄
	private Integer age;

	//用户类型 0：管理员/超级管理员  1：vip/会员用户  2：普通用户
	private Integer usertype;

	//性别
	private String sex;

	//联系电话
	private String phone;

	//联系邮箱
	private String email;

	//密码
	private String password;

	//加密密码的盐
	private String salt;

	//创建日期
	private String created;

	//修改日期
	private String updated;

	//关于xh
	private String about_xh;

	//地址
	private String address;
	//录入人
	private String lrr;

	//角色集合，一对多，一个用户可以有多个角色
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "user")
	private List<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() { return realname; }

	public void setRealname(String realname) { this.realname = realname; }

	public String getSfzh() { return sfzh; }

	public void setSfzh(String sfzh) { this.sfzh = sfzh; }

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	
	public String getAbout_xh() {
		return about_xh;
	}

	public void setAbout_xh(String about_xh) {
		this.about_xh = about_xh;
	}



	public User(String id, String username, String realname,String sfzh,Integer age, Integer usertype,
				String password, String sex, String phone, String email, String salt,
			String created, String updated, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.realname=realname;
		this.sfzh=sfzh;
		this.age=age;
		this.usertype=usertype;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.salt = salt;
		this.created = created;
		this.updated = updated;
		this.roles = roles;
	}
	
	public User(String username,String realname,String sex, String phone, String email) {
		super();
		this.username = username;
		this.realname=realname;
		this.sex=sex;
		this.phone = phone;
		this.email = email;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
}
