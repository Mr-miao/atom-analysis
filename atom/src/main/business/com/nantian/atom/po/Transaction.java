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

/**
 *Device entity. 设备表
 */
@Entity
@Table(name = "t_transaction", catalog = "")
public class Transaction implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	/**
	 * 交易id
	 */
	private Integer id;
	
	/**
	 * 模块id
	 */
	private Integer moduleId;
	/**
	 * 英文名称
	 */
	private String serEnName;
	/**
	 * 中文名称
	 */
	private String serCnName;
	
	/**
	 * 是否检查
	 */
	private Integer chkflg;
	
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 描述信息
	 */
	private String trnDesc;
	/**
	 * 页面跳转url
	 */
	private String trnUrl;
	/**
	 * 描述信息
	 */
	private String back1;
	/**
	 * 描述信息
	 */
	private String back2;
	// Constructors 




	/** min constructor */
	public Transaction() {
		
	}

	/** max constructor 
	 * @return */
	public  Transaction(Integer id,Integer moduleId, String serEnName, String serCnName,Integer chkflg,
			 Integer state, String trnDesc) {
		super();
		this.id = id;
		this.moduleId = moduleId;
		this.serEnName = serEnName;
		this.serCnName = serCnName;
		this.chkflg = chkflg;
		this.state = state;
		this.trnDesc = trnDesc;
	}
	// Property accessors
	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_TRANSACTION_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "TransactionGenerator")    
	@GenericGenerator(name = "TransactionGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "module_id")
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	@Column(name = "ser_en_name")
	public String getSerEnName() {
		return serEnName;
	}

	public void setSerEnName(String serEnName) {
		this.serEnName = serEnName;
	}
	@Column(name = "ser_cn_name")
	public String getSerCnName() {
		return serCnName;
	}

	public void setSerCnName(String serCnName) {
		this.serCnName = serCnName;
	}
	@Column(name = "trn_desc")
	public String getTrnDesc() {
		return trnDesc;
	}

	public void setTrnDesc(String trnDesc) {
		this.trnDesc = trnDesc;
	}



	@Column(name = "back1")
	public String getBack1() {
		return back1;
	}

	public void setBack1(String back1) {
		this.back1 = back1;
	}
	@Column(name = "back2")
	public String getBack2() {
		return back2;
	}

	public void setBack2(String back2) {
		this.back2 = back2;
	}


	@Column(name = "chkflg" )
	public Integer getChkflg() {
		return chkflg;
	}

	public void setChkflg(Integer chkflg) {
		this.chkflg = chkflg;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "trn_url")
	public String getTrnUrl() {
		return trnUrl;
	}

	public void setTrnUrl(String trnUrl) {
		this.trnUrl = trnUrl;
	}


}