package com.xr.util.db.dao.impl;

import com.nantian.util.str.StringUtil;

/**
 * 生成指定的HQL语句
 * @author antkm
 *
 */
public class GenHqlUtil {
	
	
	/**
	 * 生成根据给定hql语句，进行count(*)查询
	 * @param hql 形如：FROM HenAuthUser WHERE ...
	 * @return
	 */
	public static final String genCountRowsHql(String hql){
		//从 FROM之后截
		if(!StringUtil.isEmpty(hql)){
			String hqlTmp = hql.toUpperCase();
			int position = hqlTmp.indexOf("FROM");
			if(position>0){//如果hql不以FROM开头，则进行截断
				return "SELECT count(*) " + hql.substring(position, hql.length());
			}else{
				return "SELECT count(*) "+hql;
			}
			
		}else{
			return hql;
		}
	}
	
	/**
	 * 加上权限拦截之后，只能查看自己权限下的数据
	 * @param hql
	 * @param branchRelation
	 * @return
	 */
	public static final String genAuthDataHql(String hql, String branchRelation){
		return " branchno like '" + branchRelation +"%' ";
	}
	public static final String deleteEndAnd(String hql){
		if(!StringUtil.isEmpty(hql)){
			String newHql = hql.toUpperCase().trim(); 
			if(newHql.endsWith("AND")){
				return hql.substring(0, newHql.length()-3);
			}else{
				return hql;
			}
		}else{
			return hql;
		}
	}
}
