package com.example.demo.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String permission;
	@ManyToOne(fetch=FetchType.EAGER)
	private Role role;
	
	//权限名称
	private String permission_name;
	
	//创建日期
	private Date create_time;
	
	//更新时间戳
	private String gxsjc;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getPermission_name(){
		return permission_name;
	}
	
	public void setPermission_name(String permission_name){
		this.permission_name=permission_name;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time=create_time;
	}
	
	public String getGxsjc() {
		return gxsjc;
	}
	public void setGxsjc(String gxsjc) {
		this.gxsjc=gxsjc;
	}
}
