package com.nantian.util.context.comm;

/**
 * Result Error Code
 * 该类主要用于下层服务在处理逻辑时，
 * 如果成功则按预期返回预期值，否则返回错误码信息
 * @author xurui
 * @version 1.0
 * @created 2013-9-29 下午2:45:29
 */
public class RECode {
	/**
	 * 目标方法预期要返回的值
	 */
	private Object resultObject;
	
	/**
	 * 逻辑是否执行成功 0-成功，其他根据实际逻辑定义
	 */
	private int flag;
	public static final int FLAG_SUCCESS = 0;
	
	private boolean success;
	
	/**
	 * 错误描述
	 */
	private String errorMsg;

	public RECode() {
		super();
	}

	public RECode(Object resultObject, boolean success, int flag, String errorMsg) {
		super();
		this.resultObject = resultObject;
		this.success = success;
		this.flag = flag;
		this.errorMsg = errorMsg;
	}

	public Object getResultObject() {
		return resultObject;
	}

	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
