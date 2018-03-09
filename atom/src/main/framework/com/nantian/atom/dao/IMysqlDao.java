package com.nantian.atom.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IMysqlDao {
	
	public List<Object[]> find(String sql);
	
	public List find(String sql, Map<String, Object> map);

	public int excuteBySql(String sql,Map<String, Object> map);

	public int[] batchInsert(String[] sql);
	
	public JdbcTemplate getJdbcTemplate();
}
