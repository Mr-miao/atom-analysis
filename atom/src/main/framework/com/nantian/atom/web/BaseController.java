package com.nantian.atom.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.nantian.atom.service.framework.IValidateParamService;

/**
 * web层所有action的父类
 * @author antkm
 *
 */
public class BaseController {
	protected Logger log = LogManager.getLogger(BaseController.class);
	
	@Autowired
	protected IValidateParamService iValidateParamService;
	
	/**
	 * 请求参数封装
	 * 将前缀为reqParam.的属性绑定到 名称为reqParam的对象
	 * @param binder
	 */
    @InitBinder("reqParam")
    public void initBinderReqParam(WebDataBinder binder){
        binder.setFieldDefaultPrefix("reqParam.");
    }
    
   /**
    * 将前缀为validateParam.的属性绑定到 名称为reqParam的对象
    * @param binder
    */
    @InitBinder("validateParam")
    public void initBinderValidateParam(WebDataBinder binder){
        binder.setFieldDefaultPrefix("validateParam.");
    }
	
	protected void responseJson(Object object, HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    if(object!=null){
	    	try {  
		        out = response.getWriter();  
		        out.write(object.toString());  
		    } catch (IOException e) {  
		    	log.error(e.getMessage(), e);
		    } finally {  
		        if (out != null) {  
		            out.close();  
		        }  
		    }  
	    }else{
	    	try {  
		        out = response.getWriter();  
		        out.append("");  
		    } catch (IOException e) {  
		        log.error(e.getMessage(), e);
		    } finally {  
		        if (out != null) {  
		            out.close();  
		        }  
		    }  
	    }
	}
}
