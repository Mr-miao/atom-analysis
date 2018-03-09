package com.nantian.atom.vo;

import com.nantian.atom.po.Module;

/**
 *module entity. 模块表
 */
public class ModuleVO  {

	// Fields

	/**
	 * 模块id
	 */
	private Integer id;
	
	/**
	 * 上级模块id
	 */
	private Integer modulePid;
	/**
	 * 模块名称
	 */
	private String modCnName;
	/**
	 * 模块英文名称
	 */
	private String modEnName;
	/**
	 * 模块图标
	 */
	private String modIcon;
	/**
	 * 备用1
	 */
	private String back1;
	/**
	 * 备用2
	 */
	private String back2;
	// Constructors




	/** min constructor */
	public ModuleVO() {
		
	}

	
	/** max constructor */
	public ModuleVO(Integer id, Integer modulePid, String modCnName,String modEnName,String modIcon) {
		super();
		this.id=id;
		this.modulePid=modulePid;
		this.modCnName=modCnName;
		this.modEnName=modEnName;
		this.modIcon=modIcon;
	}



	public static ModuleVO po2vo(Module module){
		ModuleVO vo = new ModuleVO(module.getId(), 
				module.getModulePid(), 
				module.getModCnName(),
				module.getModEnName(),
				module.getModIcon());
		return vo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getModulePid() {
		return modulePid;
	}


	public void setModulePid(Integer modulePid) {
		this.modulePid = modulePid;
	}


	public String getModCnName() {
		return modCnName;
	}


	public void setModCnName(String modCnName) {
		this.modCnName = modCnName;
	}


	public String getModEnName() {
		return modEnName;
	}


	public void setModEnName(String modEnName) {
		this.modEnName = modEnName;
	}


	public String getBack1() {
		return back1;
	}


	public void setBack1(String back1) {
		this.back1 = back1;
	}


	public String getBack2() {
		return back2;
	}


	public void setBack2(String back2) {
		this.back2 = back2;
	}


	public String getModIcon() {
		return modIcon;
	}


	public void setModIcon(String modIcon) {
		this.modIcon = modIcon;
	}
	
	

}