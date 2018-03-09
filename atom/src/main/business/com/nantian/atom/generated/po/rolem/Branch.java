package com.nantian.atom.generated.po.rolem;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.nantian.atom.po.BaseInterfaceVO;

@Entity
@Table(name = "t_branch", catalog = "")
@Component("branch")
public class Branch extends  BaseInterfaceVO implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
   /**
   * 机构id
   */
   private Integer branchId;
   /**
   * 机构编号
   */
   private String branchNo;
   /**
   * 修改之前旧机构编号
   */
   private String oldBranchNo;
   @Column(name="OLD_BRANCH_NO" )
   public String getOldBranchNo() {
	   return oldBranchNo;
	}
	public void setOldBranchNo(String oldBranchNo) {
		this.oldBranchNo = oldBranchNo;
	}

/**
   * 机构级别,1表示墨末级机构，0表示非末级机构
   */
   private Integer ifLeaf;
   /**
   * 机构名字
   */
   private String branchName;
   /**
   * 机构英文名
   */
   private String branchEnName;
   /**
   * 机构简称
   */
   private String branchShortName;
   /**
   * 机构联系人
   */
   private String branchContactPeople;
   /**
   * 机构联系电话
   */
   private String branchContactNumber;
   /**
   * 父机构id
   */
   private Integer branchParentId;
   /**
   * 机构关联关系
   */
   private String branchRelationship;
   /**
   * 机构地址
   */
   private String branchAddr;
   /**
   * 数据删除状态，0表示删除，1表示未删除
   */
   private Integer delState;


	/** min constructor */
	public Branch() {
		
	}


	/** max constructor 
	 * @return */
	public  Branch(Integer branchId,String branchNo,Integer ifLeaf,String branchName,String branchEnName,String branchShortName,String branchContactPeople,String branchContactNumber,Integer branchParentId,String branchRelationship,String branchAddr,Integer delState,String oldBranchNo) {
		super();
		this.branchId=branchId;
		this.branchNo=branchNo;
		this.ifLeaf=ifLeaf;
		this.branchName=branchName;
		this.branchEnName=branchEnName;
		this.branchShortName=branchShortName;
		this.branchContactPeople=branchContactPeople;
		this.branchContactNumber=branchContactNumber;
		this.branchParentId=branchParentId;
		this.branchRelationship=branchRelationship;
		this.branchAddr=branchAddr;
		this.delState=delState;
		this.oldBranchNo=oldBranchNo;
	}

	
	// Property accessors
	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_BRANCH_BRANCH_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "BranchGenerator")    
	@GenericGenerator(name = "BranchGenerator", strategy = "increment")
	@Column(name = "branch_id", unique = true, nullable = false)
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
		@Column(name="branch_no" )
	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}
	@Column(name="if_leaf" )
	public Integer getIfLeaf() {
		return ifLeaf;
	}

	public void setIfLeaf(Integer ifLeaf) {
		this.ifLeaf = ifLeaf;
	}
	@Column(name="branch_name" )
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name="branch_en_name" )
	public String getBranchEnName() {
		return branchEnName;
	}

	public void setBranchEnName(String branchEnName) {
		this.branchEnName = branchEnName;
	}
	@Column(name="branch_short_name" )
	public String getBranchShortName() {
		return branchShortName;
	}

	public void setBranchShortName(String branchShortName) {
		this.branchShortName = branchShortName;
	}
	@Column(name="branch_contact_people")
	public String getBranchContactPeople() {
		return branchContactPeople;
	}

	public void setBranchContactPeople(String branchContactPeople) {
		this.branchContactPeople = branchContactPeople;
	}
	@Column(name="branch_contact_number" )
	public String getBranchContactNumber() {
		return branchContactNumber;
	}

	public void setBranchContactNumber(String branchContactNumber) {
		this.branchContactNumber = branchContactNumber;
	}
	@Column(name="branch_parent_id" )
	public Integer getBranchParentId() {
		return branchParentId;
	}

	public void setBranchParentId(Integer branchParentId) {
		this.branchParentId = branchParentId;
	}
	@Column(name="branch_relationship" )
	public String getBranchRelationship() {
		return branchRelationship;
	}

	public void setBranchRelationship(String branchRelationship) {
		this.branchRelationship = branchRelationship;
	}
	@Column(name="branch_addr" )
	public String getBranchAddr() {
		return branchAddr;
	}

	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}
	@Column(name="del_state" ,updatable=false)
	public Integer getDelState() {
		return delState;
	}

	public void setDelState(Integer delState) {
		this.delState = delState;
	}
	@Override
	public Map<String, Object> formatValue(Object po) {
		Map<String, Object> map=new HashMap<String, Object>();
		return map;
	}

}
