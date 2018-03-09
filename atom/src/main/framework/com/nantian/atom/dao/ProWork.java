package com.nantian.atom.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.jdbc.Work;

public abstract class ProWork implements Work {
	/**
	 * 存储过程语句
	 */
	private String proSql; 
	/**
	 * 返回结果集
	 */
	private ResultSet rs = null; 
	/**
	 * 存储过程涉及参数
	 */
	private Map pro = new HashMap(); 

	public abstract void execute(Connection conn) throws SQLException;
	
	public ProWork(){
		
	}

	public ProWork(String proSql, Object... params) {
		this.proSql = proSql;
		int i = 1;
		if(params!=null){
			for (Object obj : params) {
				pro.put(i, obj);
				i++;
			}
		}
	}

	public void setParams(Integer key, Object value) {
		pro.put(key, value);
	}

	public String getProSql() {
		return proSql;
	}

	public void setProSql(String proSql) {
		this.proSql = proSql;
	}

	public Object getParams(Integer key) {
		return pro.get(key);
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
}