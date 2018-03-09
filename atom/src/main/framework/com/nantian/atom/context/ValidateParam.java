package com.nantian.atom.context;

import com.nantian.util.str.StringUtil;

/**
 * 前端验证参数封装
 * @author xurui
 *
 */
public class ValidateParam {
	/**
	 * 唯一性检查，如果uniqueObj某属性不为null，则对该属性的值进行唯一性检查
	 * 属性间用","号分割
	 */
	private String uniqueT;
	
	public ValidateParam() {
		super();
	}

	/**
	 * 
	 * @param uniqueT 属性间用","号分割
	 */
	public ValidateParam(String uniqueT) {
		super();
		this.uniqueT = uniqueT;
	}


	public String[] getUniqueT() {
		if(!StringUtil.isEmpty(uniqueT)){
			if(uniqueT.endsWith(",")){
				String newStr = uniqueT.substring(0, uniqueT.length()-1);
				return newStr.split("\\,");
			}else{
				return uniqueT.split("\\,");
			}
		}
		return null;
	}
	

	/**
	 * 属性间用","号分割
	 * @param uniqueT
	 */
	public void setUniqueT(String uniqueT) {
		this.uniqueT = uniqueT;
	}
	
	

}
