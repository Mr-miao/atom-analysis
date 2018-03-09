package com.nantian.atom.po;

import static javax.persistence.GenerationType.IDENTITY;

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
@Table(name = "t_opt_role", catalog = "")
@Component("optRole")
public class OptRole extends  BaseInterfaceVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	


	private Integer optId;
	
	private Integer roleId;
	
	private String optRoleBack1;
	
	private String optRoleBack2;
	
	private String optRoleBack3;

	public OptRole() {
	}

	public OptRole(Integer id, Integer optId, Integer roleId, String optRoleBack1, String optRoleBack2,
			String optRoleBack3) {
		super();
		this.id = id;
		this.optId = optId;
		this.roleId = roleId;
		this.optRoleBack1 = optRoleBack1;
		this.optRoleBack2 = optRoleBack2;
		this.optRoleBack3 = optRoleBack3;
	}

	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_OPT_ROLE_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "OptRoleGenerator")    
	@GenericGenerator(name = "OptRoleGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "opt_id")
	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	@Column(name = "role_id")
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "back1")
	public String getOptRoleBack1() {
		return optRoleBack1;
	}

	public void setOptRoleBack1(String optRoleBack1) {
		this.optRoleBack1 = optRoleBack1;
	}

	@Column(name = "back2")
	public String getOptRoleBack2() {
		return optRoleBack2;
	}

	public void setOptRoleBack2(String optRoleBack2) {
		this.optRoleBack2 = optRoleBack2;
	}

	@Column(name = "back3")
	public String getOptRoleBack3() {
		return optRoleBack3;
	}

	public void setOptRoleBack3(String optRoleBack3) {
		this.optRoleBack3 = optRoleBack3;
	}

	@Override
	public Map<String, Object> formatValue(Object po) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
