package com.nantian.util.hql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nantian.util.str.StringUtil;

public class HqlDataAuthGeneratorUtil {
	protected final static Logger log = LogManager.getLogger(HqlDataAuthGeneratorUtil.class);
	
	public HqlDataAuthGeneratorUtil() {
		super();
	}
	
	/**
	 * 为hql增加机构权限限制的新hql
	 * 
	 * @param hql
	 * @return
	 */
	public static String genAuthBranch(String hql, String branchNo,String entityName){
		if(!StringUtil.isEmpty(hql)){
			String hqlCompare = hql.toLowerCase();
			int whereFlag = hqlCompare.indexOf("where");
			if(whereFlag==-1){//不存在where子句
				int pos = indexCondition(hqlCompare);
				if(pos!=-1){
					String prefix = hql.substring(0, pos);
					String suffix = hql.substring(pos, hql.length());
					//hql = prefix + " " + " where branchrelation like '"+branchrelation+"%' " + suffix;
					hql = prefix + "" + genAuthBranchHql(entityName,branchNo, true) +" "+ suffix;
				}else{
					hql += genAuthBranchHql(entityName,branchNo, true);
				}
			}else{//如果存在where字句，直接在where字句后添加
				whereFlag += 5;
				String prefix = hql.substring(0, whereFlag);
				String suffix = hql.substring(whereFlag, hql.length());
				hql = prefix + "" + genAuthBranchHql(entityName,branchNo, false) + " and " + suffix;
			}
		}	
		return hql;
	}
	
	public static String genAuthBranchHql(String entityName,String branchNo, boolean whereFlag){
		StringBuffer hql = null;
		if(branchNo!=null){
			if(whereFlag){
				hql = new StringBuffer(" where branchId in (select branchId from Branch where branchRelationship like '%"+branchNo+"%') ");
			}else{
				hql = new StringBuffer(" branchId in (select branchId from Branch where branchRelationship like '%"+branchNo+"%')  ");
			}
			return hql.toString();
		}else{
			return " 1=1 ";
		}
		
	}
	
	/**
	 * 判断给定HQL中是否存在order by、group by、limit等字句，并得到位置最小的值
	 * @return
	 */
	private static int indexCondition(String hql){
		int pos = -1;
		int orderByFlag = hql.indexOf("order by");
		if(orderByFlag!=-1){
			pos = orderByFlag;
		}
		int groupByFlag = hql.indexOf("group by");
		if(groupByFlag!=-1){
			pos = orderByFlag>pos?pos:groupByFlag;
		}
		int limitByFlag = hql.indexOf("limit");
		if(limitByFlag!=-1){
			pos = limitByFlag>pos?pos:limitByFlag;
		}
		return pos;
	}
	
	/**
	 * 获取hql中类名
	 * @param hql
	 * @return 返回类名的字符串
	 */
	public static String getEntityName(String hql){
		String entityName = "";
		String hqlCompare = hql.toLowerCase();
		int whereFlag = hqlCompare.indexOf("where");
		if(whereFlag==-1){//不存在where子句
			int pos = indexCondition(hqlCompare);
			if(pos!=-1){
				entityName = hql.substring(hqlCompare.indexOf("from")+4, pos);
			}else{
				entityName = hql.substring(hqlCompare.indexOf("from")+4, hqlCompare.length());
			}
		}else{//如果存在where字句，直接在where字句后添加
			entityName = hql.substring(hqlCompare.indexOf("from")+4, whereFlag);
		}
		if(entityName.lastIndexOf(".")!=-1){
			return entityName.substring(entityName.lastIndexOf(".")).trim();
		}
		return entityName.trim();
	}
}
