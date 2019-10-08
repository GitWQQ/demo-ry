package com.example.demo.domain;

import groovy.transform.Field;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Role implements Serializable{

	@Id
	private String id;
	private String roleName;
	private String roleCode;
	private Date create_time;

	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "role")
	private List<Permission> permissions;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role{" +
				"id='" + id + '\'' +
				", roleName='" + roleName + '\'' +
				", roleCode='" + roleCode + '\'' +
				", create_time=" + create_time +
				", user=" + user +
				", permissions=" + permissions +
				'}';
	}

}
