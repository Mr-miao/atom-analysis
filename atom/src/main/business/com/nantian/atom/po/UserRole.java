package com.nantian.atom.po;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "t_user_role", catalog = "")
@Component("userRole")
public class UserRole extends  BaseInterfaceVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer userId;
	
	private Integer roleId;
	private String roleName;
	
	private String back1;
	
	private String back2;

	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_USER_ROLE_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "UserRoleGenerator")    
	@GenericGenerator(name = "UserRoleGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="role_id")
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name="back1")
	public String getBack1() {
		return back1;
	}

	public void setBack1(String back1) {
		this.back1 = back1;
	}

	@Column(name="back2")
	public String getBack2() {
		return back2;
	}

	public void setBack2(String back2) {
		this.back2 = back2;
	}
	@Column(name="role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public Map<String, Object> formatValue(Object po) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
