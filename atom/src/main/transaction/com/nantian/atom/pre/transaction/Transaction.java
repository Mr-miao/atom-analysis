package com.nantian.atom.pre.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * 交易处理类父类
 * @author 李建兴
 *
 */
public abstract class Transaction {
	private Logger log = LogManager.getLogger(Transaction.class);
	
	public static final String RET_CODE = "RetCode";
	public static final String RET_MESSAGE = "RetMessage";
	public static final String RET_MESSAGE_VALUE = "交易成功";
	public static final String RET_CODE_RESULT = "0000";
	public static final String RET_CODE_SYSERR = "SYSERR";
	public static final String TRAN_CODE = "tranCode";
	
	public JSONObject service(JSONObject jsonObject) {
		
		JSONObject ret = new JSONObject();
		
		try{
			ret = tranHandle(jsonObject);
		}catch(Exception e){
			log.error(e.getMessage(), e);
			ret.put(RET_CODE, RET_CODE_SYSERR);
			ret.put(RET_MESSAGE, e.getMessage());
		}
		
		return ret;
	}
	
	protected abstract JSONObject tranHandle(JSONObject jsonObject) throws Exception;
}
