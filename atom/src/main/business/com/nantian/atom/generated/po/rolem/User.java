package com.nantian.atom.generated.po.rolem;

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

import com.nantian.atom.po.BaseInterfaceVO;

@Entity
@Table(name = "t_user", catalog = "")
@Component("user")
public class User extends  BaseInterfaceVO implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	   /**
   * 主键id
   */
   private Integer id;






/**
   * 用户登录帐号名
   */
   private String userLoad;
   /**
   * 用户密码，登陆使用
   */
   private String userPwd;
   /**
   * 用户姓名
   */
   private String userName;
   /**
   * 移动电话
   */
   private String userPhone;
   /**
   * 座机电话
   */
   private String userCall;
   /**
   * 邮箱
   */
   private String userEmail;
   /**
   * 职位
   */
   private String userPosition;
   /**
   * 用户所属机构
   */
   private Branch branchId;
   /**
   * 用户性别
   */
   private Integer userSex;
   /**
   * 用户所属角色
   */
   private String userRole;
   
   /**
   * 数据删除状态，0表示已删除，1表示未删除
   */
   private Integer delState;
   /**
    * 用户编号，即柜员编号
    * */
   private String userNumber;
	/** min constructor */
	public User() {
		
	}


	/** max constructor 
	 * @return */
	public User(Integer id, String userLoad, String userPwd, String userName, String userPhone, String userCall,
			String userEmail, String userPosition, Branch branchId, Integer userSex, String userRole,
			 Integer delState, String userNumber) {
		super();
		this.id = id;
		this.userLoad = userLoad;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userCall = userCall;
		this.userEmail = userEmail;
		this.userPosition = userPosition;
		this.branchId = branchId;
		this.userSex = userSex;
		this.userRole = userRole;

		this.delState = delState;
		this.userNumber = userNumber;
	}
	// Property accessors
	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_USER_USER_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "UserGenerator")    
	@GenericGenerator(name = "UserGenerator", strategy = "increment")
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
		@Column(name="user_load" )
	public String getUserLoad() {
		return userLoad;
	}

	public void setUserLoad(String userLoad) {
		this.userLoad = userLoad;
	}
	@Column(name="user_pwd" )
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	@Column(name="user_name" )
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="user_phone" )
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Column(name="user_call" )
	public String getUserCall() {
		return userCall;
	}

	public void setUserCall(String userCall) {
		this.userCall = userCall;
	}
	@Column(name="user_email" )
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Column(name="user_position" )
	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id" , nullable = true)
	public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}
	@Column(name="user_sex" )
	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	@Column(name="user_role")
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	@Column(name="del_state")
	public Integer getDelState() {
		return delState;
	}

	public void setDelState(Integer delState) {
		this.delState = delState;
	}


	@Column(name="user_number")
	public String getUserNumber() {
		return userNumber;
	}


	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}


	@Override
	public Map<String, Object> formatValue(Object po) {
		Map<String, Object> map=new HashMap<String, Object>();
		User user=(User) po;
		
		map.put("userBranchIdBranchId", user.getBranchId()==null?null:user.getBranchId().getBranchId());
		map.put("branchName", user.getBranchId()==null?null:user.getBranchId().getBranchName());

		return map;
	}
	
	

}
