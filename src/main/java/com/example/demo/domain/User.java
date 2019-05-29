package com.example.demo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class User implements Serializable{
	@Id
	private String id;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String sex;
	
	private String phone;
	
	private String email;
	
	private String salt;//加密密码的盐
	
	private String created;
	
	private String updated;
	
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone + ", email="
				+ email + ", salt=" + salt + ", created=" + created + ", updated=" + updated + ", roles=" + roles + "]";
	}

	public User(String id, String username, String password, String sex, String phone, String email, String salt,
			String created, String updated, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.salt = salt;
		this.created = created;
		this.updated = updated;
		this.roles = roles;
	}
	
	public User(String username,String sex, String phone, String email) {
		super();
		this.username = username;
		this.sex=sex;
		this.phone = phone;
		this.email = email;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
