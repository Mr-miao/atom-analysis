package com.nantian.atom.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

/**
 *Module entity. 模块表
 */
@Entity
@Table(name = "t_module", catalog = "")
@Component("module")
public class Module implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
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
	public Module() {
		
	}



	/** max constructor 
	 * @return */
	public  Module(Integer id, Integer modulePid, String modCnName,String modEnName) {
		super();
		this.id=id;
		this.modulePid=modulePid;
		this.modCnName=modCnName;
		this.modEnName=modEnName;
	}

	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_MODULE_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "ModuleGenerator")    
	@GenericGenerator(name = "ModuleGenerator", strategy = "increment") 
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name = "module_pid")
	public Integer getModulePid() {
		return modulePid;
	}



	public void setModulePid(Integer modulePid) {
		this.modulePid = modulePid;
	}


	@Column(name = "mod_cn_name", length=100)
	public String getModCnName() {
		return modCnName;
	}



	public void setModCnName(String modCnName) {
		this.modCnName = modCnName;
	}


	@Column(name = "mod_en_name", length=100)
	public String getModEnName() {
		return modEnName;
	}


	public void setModEnName(String modEnName) {
		this.modEnName = modEnName;
	}

	@Column(name = "back1", length=10)
	public String getBack1() {
		return back1;
	}



	public void setBack1(String back1) {
		this.back1 = back1;
	}


	@Column(name = "back2", length=50)
	public String getBack2() {
		return back2;
	}



	public void setBack2(String back2) {
		this.back2 = back2;
	}


	@Column(name = "mod_icon", length=200)
	public String getModIcon() {
		return modIcon;
	}



	public void setModIcon(String modIcon) {
		this.modIcon = modIcon;
	}



}