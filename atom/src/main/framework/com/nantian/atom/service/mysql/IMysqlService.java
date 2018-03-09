package com.nantian.atom.service.mysql;

import java.util.List;
import java.util.Map;

import com.nantian.atom.service.IService;

public interface IMysqlService extends IService{
	/**
	 * 获取表的字段信息
	 * @param clsPO
	 * @param db
	 * @return
	 */
	public <PO> List getCumnInfo(String tableName) ;
	/**
	 * 通过sql获取list对象
	 * @param clsPO
	 * @param db
	 * @return
	 */
	public <PO> List<Map<String,Object>> getListBySql(String sql) ;
}
