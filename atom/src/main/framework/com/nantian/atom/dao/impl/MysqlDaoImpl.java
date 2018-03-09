package com.nantian.atom.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nantian.atom.dao.IMysqlDao;

@Repository("mysqlDaoImpl")
public class MysqlDaoImpl implements IMysqlDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}


	@Override
	public List find(String sql) {
		List list = getCurrentSession().createSQLQuery(sql).list();    
        return list;    
	}

	@Override
	public int excuteBySql(String sql,Map<String, Object> map) {
		 SQLQuery query = this.getCurrentSession().createSQLQuery(sql);  
	        if(query!=null){
	        	if (map != null && !map.isEmpty()) {
					for (String key : map.keySet()) {
						query.setParameter(key, map.get(key));
					}
				}
				return query.executeUpdate(); 
			}
	        return -1;     
	}



	@Override
	public int[] batchInsert(String[] sql) {
		return jdbcTemplate.batchUpdate(sql);
	}


	@Override
	public List find(String sql, Map<String, Object> map) {
		 SQLQuery query = this.getCurrentSession().createSQLQuery(sql);  
	        if(query!=null){
	        	if (map != null && !map.isEmpty()) {
					for (String key : map.keySet()) {
						query.setParameter(key, map.get(key));
					}
				}
				return query.list(); 
			}
	        return null;    
	}


	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	

}
