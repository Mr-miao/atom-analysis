package com.nantian.atom.vo;

import com.nantian.atom.po.OptRole;

public class OptRoleVo {
	private Integer optRoleID;
	
	private Integer optId;
	
	private Integer roleId;
	
	private String optRoleBack1;
	
	private String optRoleBack2;
	
	private String optRoleBack3;

	public OptRoleVo() {
	}

	public OptRoleVo(Integer optRoleID, Integer optId, Integer roleId, String optRoleBack1, String optRoleBack2,
			String optRoleBack3) {
		super();
		this.optRoleID = optRoleID;
		this.optId = optId;
		this.roleId = roleId;
		this.optRoleBack1 = optRoleBack1;
		this.optRoleBack2 = optRoleBack2;
		this.optRoleBack3 = optRoleBack3;
	}

	public Integer getOptRoleID() {
		return optRoleID;
	}

	public void setOptRoleID(Integer optRoleID) {
		this.optRoleID = optRoleID;
	}

	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getOptRoleBack1() {
		return optRoleBack1;
	}

	public void setOptRoleBack1(String optRoleBack1) {
		this.optRoleBack1 = optRoleBack1;
	}

	public String getOptRoleBack2() {
		return optRoleBack2;
	}

	public void setOptRoleBack2(String optRoleBack2) {
		this.optRoleBack2 = optRoleBack2;
	}

	public String getOptRoleBack3() {
		return optRoleBack3;
	}

	public void setOptRoleBack3(String optRoleBack3) {
		this.optRoleBack3 = optRoleBack3;
	}
	
	public static OptRoleVo po2vo(OptRole optRole){
		OptRoleVo vo = new OptRoleVo(optRole.getId(),
				optRole.getOptId(),
				optRole.getRoleId(), 
				optRole.getOptRoleBack1(),
				optRole.getOptRoleBack2(),
				optRole.getOptRoleBack3());
		return vo;
	}
}
