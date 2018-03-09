package com.nantian.atom.service.framework;

import org.springframework.ui.ModelMap;

import com.nantian.atom.context.ValidateParam;
import com.nantian.atom.service.IService;

/**
 * 验证框架
 * @author xurui
 *
 */
public interface IValidateParamService extends IService{
	
	/**
	 * 对validateParam.getUniqueT所标记的属性对应的值进行唯一性检查
	 */
	public  <PO>  boolean  validateUnique(PO po,ValidateParam validateParam, ModelMap map) ;
}
