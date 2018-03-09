package com.nantian.atom.vo;


import com.nantian.atom.po.Role;

public class RoleVo {

	
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
	private String branchName;
	
	/**
	 * 机构id
	 */
	private Integer branchId;

	/**
	 * 上级机构id
	 */
	private String rolePid;
	/**
	 * 上级机构名称
	 */
	private String rolePname;
	
	/** min constructor */
	
	public RoleVo() {
		
	}

	



	/** max constructor 
	 * @return */
	public RoleVo(Integer id, String name,String desc,String branchName,String rolePid,Integer branchId) {
		this.roleId = id;
		this.roleName = name;
		this.roleDesc = desc;
		this.branchName = branchName;
		
		this.rolePid = rolePid;
		this.branchId = branchId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer id) {
		this.roleId = id;
	}
	public String getRoleName() {
		return roleName;
	}



	public void setRoleName(String name) {
		this.roleName = name;
	}

	public String getRoleDesc() {
		return roleDesc;
	}


	public void setRoleDesc(String desc) {
		this.roleDesc = desc;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



	public String getRolePid() {
		return rolePid;
	}

	public void setRolePid(String rolePid) {
		this.rolePid = rolePid;
	}
	
	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}





	public static RoleVo po2vo(Role role){
		RoleVo vo = new RoleVo(role.getRoleId(), 
				role.getRoleName(), 
				role.getRoleDesc(), 
				role.getBranchId()==null?"":role.getBranchId().getBranchName(), 
				role.getRolePid(),
				role.getBranchId()==null?null:role.getBranchId().getBranchId());
		return vo;
	}





	public String getRolePname() {
		return rolePname;
	}





	public void setRolePname(String rolePname) {
		this.rolePname = rolePname;
	}

}
