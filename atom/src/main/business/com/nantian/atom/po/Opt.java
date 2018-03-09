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
 *Opt entity. 操作表
 */
@Entity
@Table(name = "t_opt", catalog = "")
@Component("opt")
public class Opt implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer optId;
	
	private Integer optServerId;
	
	private String poName ;
	

	private String optName;
	
	private String optExpression;
	
	private Integer optState;
	
	private String optMethod;
	
	private String optDesc;
	
	private String optBack1;
	
	private String optBack2;
	
	private String reqUrl;
	private String respUrl;




	public Opt(Integer optId, Integer optServerId, String poName, String optName, String optExpression,
			Integer optState, String optMethod, String optDesc, String optBack1, String optBack2,String reqUrl,String respUrl) {
		super();
		this.optId = optId;
		this.optServerId = optServerId;
		this.poName = poName;
		this.optName = optName;
		this.optExpression = optExpression;
		this.optState = optState;
		this.optMethod = optMethod;
		this.optDesc = optDesc;
		this.optBack1 = optBack1;
		this.optBack2 = optBack2;
		this.reqUrl=reqUrl;
		this.respUrl=respUrl;
	}

	public Opt() {
		super();
	}

	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_OPT_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "OptGenerator")    
	@GenericGenerator(name = "OptGenerator", strategy = "increment") 
	@Column(name = "id", unique = true, nullable = false)
	public Integer getOptId() {
		return optId;
	}

	public void setOptId(Integer optId) {
		this.optId = optId;
	}

	@Column(name = "server_id")
	public Integer getOptServerId() {
		return optServerId;
	}

	public void setOptServerId(Integer optServerId) {
		this.optServerId = optServerId;
	}

	@Column(name = "PO_NAME")
	public String getPoName() {
		return poName;
	}

	public void setPoName(String poName) {
		this.poName = poName;
	}


	@Column(name = "opt_name")
	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	@Column(name = "expression")
	public String getOptExpression() {
		return optExpression;
	}

	public void setOptExpression(String optExpression) {
		this.optExpression = optExpression;
	}

	@Column(name = "state")
	public Integer getOptState() {
		return optState;
	}

	public void setOptState(Integer optState) {
		this.optState = optState;
	}

	@Column(name = "method")
	public String getOptMethod() {
		return optMethod;
	}

	public void setOptMethod(String optMethod) {
		this.optMethod = optMethod;
	}

	@Column(name = "opt_desc")
	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
	}

	@Column(name = "back1")
	public String getOptBack1() {
		return optBack1;
	}

	public void setOptBack1(String optBack1) {
		this.optBack1 = optBack1;
	}

	@Column(name = "back2")
	public String getOptBack2() {
		return optBack2;
	}

	public void setOptBack2(String optBack2) {
		this.optBack2 = optBack2;
	}
	@Column(name = "REQ_URL")
	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}
	@Column(name = "RESP_URL")
	public String getRespUrl() {
		return respUrl;
	}

	public void setRespUrl(String respUrl) {
		this.respUrl = respUrl;
	}
}
