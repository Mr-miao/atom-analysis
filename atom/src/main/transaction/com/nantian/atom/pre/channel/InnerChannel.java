package com.nantian.atom.pre.channel;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.nantian.atom.pre.transaction.Transaction;
import com.nantian.atom.util.SpringInit;

/**
 * M端向Agent发起交易，或向其他渠道发起交易的Faced类
 * @author 猫小游
 *
 */
@Service("InnerChannel")
public class InnerChannel{
	protected Logger log = LogManager.getLogger(InnerChannel.class);
	
	/**
	 * 发起交易
	 * @param tranCode
	 * @param sendIP
	 * @return
	 */
	public JSONObject trans(String tranCode, String sendIP){
		return trans(tranCode, sendIP, null);
	}
	
	/**
	 * 发起交易
	 * @param tranCode
	 * @param sendIP
	 * @param param
	 * @return
	 */
	public JSONObject trans(String tranCode, String sendIP, JSONObject param){
		JSONObject ret = new JSONObject();
		
		try{
			
			if (param == null) {
				param = new JSONObject();
			}
			
			log.info("M端调用" + tranCode + "交易");
			//获取交易处理类
			ApplicationContext ac = SpringInit.getApplicationContext();
			Transaction trans = (Transaction)ac.getBean(tranCode);
			
			param.put("sendIP", sendIP);
			param.put("tranCode", tranCode);
			
			ret = trans.service(param);
			
		}catch(Exception e){
			log.error(e.getMessage(), e);
			ret.put(Transaction.RET_CODE, Transaction.RET_CODE_SYSERR);
			ret.put(Transaction.RET_MESSAGE, e.getMessage());
		}
		
		log.info("M端调用" + tranCode + "交易，执行结果："  + ret);
		
		return ret;
	}
}
