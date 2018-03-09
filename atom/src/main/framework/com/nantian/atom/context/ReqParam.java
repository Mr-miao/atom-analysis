package com.nantian.atom.context;

/**
 * HTTP请求参数封装
 * @author xurui
 *
 */
public class ReqParam {
	/**
	 * 排序字段名
	 */
	private String orderName;
	/**
	 * 正序还是倒叙
	 */
	private String orderFlag;
	public static final String DESC = "desc";
	public static final String ASC = "asc";
	
	/**
	 * 请求当前页
	 */
	private Integer currentPage;
	/**
	 * 每页显示记录数
	 */
	private Integer pageSize;
	
	
	public ReqParam() {
		super();
	}
	public ReqParam(String orderName, String orderFlag, Integer currentPage,
			Integer pageSize) {
		super();
		this.orderName = orderName;
		this.orderFlag = orderFlag;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderFlag() {
		return orderFlag;
	}
	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}
	public Integer getCurrentPage() {
		if(currentPage==null){
			return 0;
		}
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		if(currentPage==null){//默认分页为10
			return 10;
		}
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "ReqParam [orderName=" + orderName + ", orderFlag=" + orderFlag
				+ ", currentPage=" + currentPage + ", pageSize=" + pageSize
				+ "]";
	}
	
	
}
