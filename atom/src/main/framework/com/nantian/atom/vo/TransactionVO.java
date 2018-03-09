package com.nantian.atom.vo;

import com.nantian.atom.po.Transaction;


/**
 *表
 */
public class TransactionVO  {

		/**
   * 服务id，自动递增
   */
   private Integer id;
	/**
   * 交易名称
   */
   private String serEnName;
	/**
   * 服务名称,对应程序定义的服务
   */
   private String serCnName;
	/**
   * 模块id
   */
   private Integer moduleId;
	/**
   * 状态
   */
   private Integer state;
	/**
   * 是否检查
   */
   private Integer chkflg;
	/**
   * 描述信息
   */
   private String trnDesc;
	/**
   * 备用1
   */
   private String back1;
	/**
   * 备用2
   */
   private String back2;

		/** min constructor */
	public TransactionVO() {
		
	}
		/** max constructor 
	 * @return */
	public  TransactionVO(Integer id,String serEnName,String serCnName,Integer moduleId,Integer state,Integer chkflg,String trnDesc,String back1,String back2) {
		super();
				this.id=id;
		this.serEnName=serEnName;
		this.serCnName=serCnName;
		this.moduleId=moduleId;
		this.state=state;
		this.chkflg=chkflg;
		this.trnDesc=trnDesc;
		this.back1=back1;
		this.back2=back2;

	}
	public static TransactionVO po2vo(Transaction info){
		TransactionVO vo = new TransactionVO(	info.getId(),	info.getSerEnName(),	info.getSerCnName(),	info.getModuleId(),	info.getState(),	info.getChkflg(),	info.getTrnDesc(),	info.getBack1(),	info.getBack2());
		return vo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		public String getSerEnName() {
		return serEnName;
	}

	public void setSerEnName(String serEnName) {
		this.serEnName = serEnName;
	}
	public String getSerCnName() {
		return serCnName;
	}

	public void setSerCnName(String serCnName) {
		this.serCnName = serCnName;
	}
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getChkflg() {
		return chkflg;
	}

	public void setChkflg(Integer chkflg) {
		this.chkflg = chkflg;
	}
	public String getTrnDesc() {
		return trnDesc;
	}

	public void setTrnDesc(String trnDesc) {
		this.trnDesc = trnDesc;
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


}
