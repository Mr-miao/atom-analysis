package com.nantian.atom.pre.channel;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nantian.atom.pre.transaction.Transaction;
import com.nantian.atom.util.SpringInit;

/**
 * agent请求到m端的接口
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/interface")
public class HttpChannel {
	protected Logger log = LogManager.getLogger(HttpChannel.class);
	
	@RequestMapping(value = "/agent.json")
	public void trans(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		
		InputStream is = null;
		JSONObject ret = new JSONObject();
		
		try {
			is = request.getInputStream();
			byte[] buff = new byte[1024 * 10];
			StringBuffer sb = new StringBuffer();
			while ((is.read(buff)) != -1) {
				sb.append(new String(buff, 0, buff.length, "UTF-8").trim());
			}
			
			log.info("[AM]收到AGENT请求报文[" + sb.toString().trim() + "]");
			
			
			
			JSONObject jsObj = JSONObject.fromObject(sb.toString());
			
			String tranCode = (String) jsObj.get("tranCode");// 交易码
			
			
			//获取交易处理类
			ApplicationContext ac = SpringInit.getApplicationContext();
			Transaction trans = (Transaction)ac.getBean(tranCode);
			
			ret = trans.service(jsObj);			
			
			if (ret == null) {
				ret = new JSONObject();
				ret.put(Transaction.RET_CODE, Transaction.RET_CODE_SYSERR);
				ret.put(Transaction.RET_MESSAGE, tranCode + "交易返回空指针结果对象");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			ret.put(Transaction.RET_CODE, Transaction.RET_CODE_SYSERR);
			ret.put(Transaction.RET_MESSAGE, e.getMessage());
			
			log.error(e.getMessage(), e);
		} finally {
			if (is != null) {
				try {
					String retStr = ret.toString();
					retStr = retStr.trim();
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(retStr);
					is.close();
					log.info("[AM]向AGENT反馈报文[" + retStr + "]");
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
