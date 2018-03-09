package com.nantian.atom.po;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.nantian.atom.generated.po.rolem.Branch;

/**
 *Device entity. 设备表
 */
@Entity
@Table(name = "t_role", catalog = "")
@Component("role")
public class Role extends  BaseInterfaceVO implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	/**
	 * 模块id
	 */
	private Integer roleId;
	/**
	 * 名称
	 */
	private String roleName;
	/**
	 * 描述信息
	 */
	private String roleDesc;
	 
	/**
	 * 描述信息
	 */
	private Branch branchId;

	/**
	 * 上级角色，即为创建该角色的角色
	 */
	private String rolePid;
	/** min constructor */
	public Role() {
		
	}

	



	/** max constructor 
	 * @return */
	public  Role(Integer id, String name,Branch branchId,String desc) {
		super();
		this.roleId = id;
		this.roleName = name;
		this.branchId=branchId;
		this.roleDesc = desc;
	}

	@Id
	@GeneratedValue(generator = "RoleGenerator")    
	@GenericGenerator(name = "RoleGenerator", strategy = "increment")
	@Column(name = "role_id", unique = true, nullable = false)
	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer id) {
		this.roleId = id;
	}
	@Column(name = "role_name", length=100)
	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String name) {
		this.roleName = name;
	}

	@Column(name = "role_desc", length=200)
	public String getRoleDesc() {
		return roleDesc;
	}


	public void setRoleDesc(String desc) {
		this.roleDesc = desc;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade=javax.persistence.CascadeType.PERSIST)
	@JoinColumn(name = "branch_id" , nullable = true)
	public Branch getBranchId() {
		return branchId;
	}


	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}


	@Column(name = "role_pid")
	public String getRolePid() {
		return rolePid;
	}


	public void setRolePid(String rolePid) {
		this.rolePid = rolePid;
	}





	@Override
	public Map<String, Object> formatValue(Object po) {
		Map<String, Object> map=new HashMap<String, Object>();
		Role role=(Role) po;
		
		map.put("branchName", role.getBranchId()==null?"":role.getBranchId().getBranchName());
		map.put("branchId", role.getBranchId()==null?null:role.getBranchId().getBranchId());

		return map;
	}


}