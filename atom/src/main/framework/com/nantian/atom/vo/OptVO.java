package com.nantian.atom.vo;

import com.nantian.atom.po.Opt;


/**
 *表
 */
public class OptVO  {

		/**
   * 操作id
   */
   private Integer id;
	/**
   * 服务id
   */
   private Integer serverId;
	/**
   * 服务名称,注解时名称
   */
   private String serverName;
	/**
   * 操作名称
   */
   private String optName;
	/**
   * 表达式
   */
   private String expression;
	/**
   * 
   */
   private Integer state;
	/**
   * 方法
   */
   private String method;
	/**
   * 描述
   */
   private String optDesc;
	/**
   * 
   */
   private String back1;
	/**
   * 
   */
   private String back2;
   
	private String reqUrl;


		/** min constructor */
	public OptVO() {
		
	}
		/** max constructor 
	 * @return */
	public  OptVO(Integer id,Integer serverId,String serverName,String optName,String expression,Integer state,String method,String optDesc,String back1,String back2,String reqUrl) {
		super();
		this.id=id;
		this.serverId=serverId;
		this.serverName=serverName;
		this.optName=optName;
		this.expression=expression;
		this.state=state;
		this.method=method;
		this.optDesc=optDesc;
		this.back1=back1;
		this.back2=back2;
		this.reqUrl=reqUrl;
	}
	/*
	 * 	public Opt(Integer optId, Integer optServerId, String poName, String optName, String optExpression,
			Integer optState, String optMethod, String optDesc, String optBack1, String optBack2,String reqUrl) {
	 */
	public static OptVO po2vo(Opt info){
		OptVO vo = new OptVO(	info.getOptId(),	info.getOptServerId(),	info.getPoName(),	info.getOptName(),	info.getOptExpression(),	info.getOptState(),	info.getOptMethod(),	info.getOptDesc(),	info.getOptBack1(),	info.getOptBack2(),info.getReqUrl());
		return vo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	public String getOptDesc() {
		return optDesc;
	}

	public void setOptDesc(String optDesc) {
		this.optDesc = optDesc;
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
	public String getReqUrl() {
		return reqUrl;
	}
	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

}
