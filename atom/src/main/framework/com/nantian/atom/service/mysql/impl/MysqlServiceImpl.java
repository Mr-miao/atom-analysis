package com.nantian.atom.service.mysql.impl;

import java.util.List;

import com.nantian.atom.service.BaseServiceImpl;
import com.nantian.atom.service.mysql.IMysqlService;


public abstract class MysqlServiceImpl extends BaseServiceImpl implements IMysqlService  {


	/**
	 * 获取表字段信息
	 * 
	 * @param clsPO
	 * @param db
	 * @return
	 */
	@Override
	public <PO> List getCumnInfo(String tableName) {
		return getTBColumns(tableName);
	}

	public List<Object[]> getTBColumns(String tableName) {
		String queryString = "select distinct t2.column_name,t2.data_type,t1.comments from SYS.user_col_comments t1, SYS.user_tab_columns t2 "+
				"where t1.column_name=t2.column_name and t1.table_name='"+tableName.toUpperCase()+"'";
		List<Object[]> list = iMysqlDao.find(queryString);
		
		return list;
	}

	@Override
	public List<Object[]> getListBySql(String sql) {
		// TODO Auto-generated method stub
		return iMysqlDao.find(sql);
	}
}
