package com.nantian.atom.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.dao.ProWork;

@Repository("commonDao")
public class CommonDao {
	protected final static Logger log = LogManager.getLogger(BasePoDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public Session getSession() {
		return this.getCurrentSession();
	}

	public void flush() {
		this.getCurrentSession().flush();
	}
	public int[] batchInsert(String[] sql) {
		return jdbcTemplate.batchUpdate(sql);
	}
	/*
	 * 根据hql查询对象
	 */
	public <PO> PO get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			List<PO> l = q.list();
			if (l != null && l.size() > 0) {
				return l.get(0);
			}
		}		
		return null;
	}
	/*
	 * 新增对象
	 */
	public <PO> Serializable save(PO o) {
		 this.getCurrentSession().save(o);
		return (Serializable) o;
	}
	/*
	 * 新增对象
	 */
	public <PO> Object merge(PO o) {
		return this.getCurrentSession().merge(o);
	}
	/*
	 * 删除单笔对象
	 */
	public <PO> void delete(PO o) {
		this.getCurrentSession().delete(o);
	}
	/*
	 * 更新对象
	 */
	public <PO> void update(PO o) {
		this.getCurrentSession().update(o);
	}
	/*
	 * 新增或更新对象
	 */
	public <PO> void saveOrUpdate(PO o) {
		this.getCurrentSession().saveOrUpdate(o);
	}
	/*
	 * 根据id获取对象
	 */
	public <PO> PO get(Class<PO> c, Serializable id) {
		return (PO) this.getCurrentSession().get(c, id);
	}
	/*
	 * 根据id获取对象
	 */
	public <PO> PO load(Class<PO> c, Serializable id) {
		return (PO) this.getCurrentSession().load(c, id);
	}
	/*
	 * 根据hql查询全部list对象,没有权限
	 */
	public <PO> List<PO> findAllByHql(String hql,Map<String, Object> params) {
		//数据权限由hql控制
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			return q.list();
		}else{
			return null;
		}
	}
	/*
	 * 根据hql查询分页list对象,没有权限
	 */
	public <PO> List<PO> findPageByHql(String hql,Map<String, Object> params,ReqParam reqParam) {
		//数据权限由hql控制
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			Integer currpage=0;
			Integer pageSize=10;
			if(reqParam!=null&&reqParam.getCurrentPage()!=null){
				currpage=reqParam.getCurrentPage();
			}
			
			if(reqParam!=null&&reqParam.getPageSize()!=null){
				pageSize=reqParam.getPageSize();
			}
			return q.setFirstResult((currpage - 1) * pageSize).setMaxResults(pageSize).list();
		}else{
			return null;
		}
	}
	/*
	 * 根据hql统计数目
	 */
	public Long count(String hql,Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			return (Long) q.uniqueResult();
		}
		return null;
	}
	/*
	 * 执行hql语句
	 */
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			return q.executeUpdate();
		}else{
			return 0;
		}
		
	}
	/*
	 * 执行sql语句
	 */
	public int executeSql(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			return q.executeUpdate();
		}else{
			return 0;
		}
		
	}
	
	/*
	 * 根据sql查询结果
	 */
	public List<Object[]> findAllBySql(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			return q.list();
		}else{
			return null;
		}
		
	}
	/*
	 * 根据hql查询分页list对象,没有权限
	 */
	public <PO> List<PO> findPageBySql(String sql,Map<String, Object> params,ReqParam reqParam) {
		//数据权限由hql控制
		Query q = this.getCurrentSession().createSQLQuery(sql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					Object obj = params.get(key);  
	                //这里考虑传入的参数是什么类型，不同类型使用的方法不同  
	                if(obj instanceof Collection<?>){  
	                    q.setParameterList(key, (Collection<?>)obj);  
	                }else if(obj instanceof Object[]){  
	                    q.setParameterList(key, (Object[])obj);  
	                }else{  
	                	q.setParameter(key, params.get(key)); 
	                }  
					
				}
			}
			Integer currpage=0;
			Integer pageSize=10;
			if(reqParam!=null&&reqParam.getCurrentPage()!=null){
				currpage=reqParam.getCurrentPage();
			}
			
			if(reqParam!=null&&reqParam.getPageSize()!=null){
				pageSize=reqParam.getPageSize();
			}
			return q.setFirstResult((currpage - 1) * pageSize).setMaxResults(pageSize).list();
		}else{
			return null;
		}
	}
	/*
	 * 批量删除
	 */
	public <PO> void delete(String delIds, Class<PO> poCls) {
		String[] id = delIds.split("#");
		try {
			for( int i=0; i<id.length; i++){
				Integer i_id=Integer.parseInt(id[i]);
				PO instance = (PO) getSession().get(poCls, i_id);
				 getSession().delete(instance);
			}
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	
	/**
	 * 
	 * 多条件查询完全匹配的方法，两个Criteria分别用于查询记录，统计记录数目
	 * 
	 * @param criteria
	 *            第一个Criteria
	 * @param str
	 *            POJO内的字段名
	 * @param object
	 *            需要匹配的对象
	 * 
	 */
	public void addEQConditions(Criteria criteria, String str, Object object) {
		criteria.add(Restrictions.eq(str, object));

	}
	/**
	 * 
	 * 查询记录的方法
	 * 
	 * @param criteria
	 * @param start
	 *            数据库查询开始值
	 * @param limit
	 *            数据库查询限量值
	 * @return
	 * @throws Exception
	 */
	private List<?> criteriaList(Criteria criteria, ReqParam reqParam) {
		criteria.setFirstResult(reqParam.getCurrentPage()*reqParam.getPageSize());
		criteria.setMaxResults(reqParam.getPageSize());
		if(ReqParam.ASC.equals(reqParam.getOrderFlag())){
			criteria.addOrder(Order.asc(reqParam.getOrderName()));
		}else if(ReqParam.DESC.equals(reqParam.getOrderFlag())){
			criteria.addOrder(Order.desc(reqParam.getOrderName()));
		}
		return criteria.list();
	}
	/**
	 * 
	 * 统计数目的方法
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public int criteriaCount(Criteria criteria) {
		return Integer.parseInt(criteria.setProjection(Projections.rowCount()).list().get(0).toString());
	}
	/**
	 * 
	 * 多条件查询模糊查询的方法，两个Criteria分别用于查询记录，统计记录数目
	 * 
	 * @param criteria
	 *            第一个Criteria
	 * @param str
	 *            POJO内的字段名
	 * @param object
	 *            需要匹配的对象
	 * 
	 */
	public void addLIKEConditions(Criteria criteria, String str, Object object) {
		criteria.add(Restrictions.like(str, object));
	}

	/**
	 * 执行存储过程
	 */
	public ResultSet executeProRs(ProWork work) {
		this.getCurrentSession().doWork(work);
		return work.getRs();
	}	
	/**
	 * 条件查询所有PO
	 */
	public <PO> List<PO> findAllByCondition(PO po) {
		List<PO> infoList = new ArrayList<PO>();
		try {
			Criteria listCriteria = getCurrentSession().createCriteria(po.getClass());
			listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
			Field[] fields = po.getClass().getDeclaredFields();
			String name;
			Method m;
			for (Field field : fields) {
				name = field.getName(); // 获取属性的名字
				int modifiers=field.getModifiers();
				if (10 != modifiers && 18 != modifiers && 26 != modifiers) {
					m = po.getClass().getMethod(
							"get" + name.substring(0, 1).toUpperCase()
									+ name.substring(1));
					Object obj = m.invoke(po);
					if (obj != null) {
						name="p."+name;
							if(obj instanceof Date) {
								listCriteria.add(Restrictions.ge(name, obj )); 
							}else if(obj instanceof String) {
								if (!"".equals((obj.toString()).trim())) {
									addLIKEConditions(listCriteria, name, "%"+((String) obj).trim()+"%");
								}
							}else {
								addEQConditions(listCriteria, name, obj);
							}

					}
				}
			}
			infoList =  (List<PO>) listCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return infoList;
	}
	/**
	 * 条件查询当前页PO
	 */
	public <PO> Map<EmDaoResult, Object> findPageByCondition(PO po,ReqParam reqParam) {
		Map<EmDaoResult, Object> map = new HashMap<EmDaoResult, Object>();
//		Map<String,Object> retMap=new HashMap<String, Object>();
		List<PO> infoList = new ArrayList<PO>();
		try { 
			Criteria listCriteria = getCurrentSession().createCriteria(po.getClass());
			Criteria countCriteria = getCurrentSession().createCriteria(po.getClass());
			countCriteria=getCurrentSession().createCriteria(po.getClass(),"p");
			listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
			Field[] fields = po.getClass().getDeclaredFields();
			String name;
			Method m;
			for (Field field : fields) {
				name = field.getName(); // 获取属性的名字
				int modifiers=field.getModifiers();
				if (10 != modifiers && 18 != modifiers && 26 != modifiers) {
					m = po.getClass().getMethod(
							"get" + name.substring(0, 1).toUpperCase()
									+ name.substring(1));
					Object obj = m.invoke(po);
					if (obj != null) {
						name="p."+name;
							if(obj instanceof Date) {
								listCriteria.add(Restrictions.ge(name, obj )); 
								countCriteria.add(Restrictions.ge(name, obj )); 
							}else if(obj instanceof String) {
								if (!"".equals((obj.toString()).trim())) {
									addLIKEConditions(listCriteria, name, "%"+((String) obj).trim()+"%");
									addLIKEConditions(countCriteria, name, "%"+((String) obj).trim()+"%");
								}
							}else {
								addEQConditions(listCriteria, name, obj);
								addEQConditions(countCriteria, name, obj);
							}

					}
				}
			}
			int cn=criteriaCount(countCriteria);
			infoList =  (List<PO>) criteriaList(listCriteria,  reqParam);
			
			map.put(EmDaoResult.VO_LIST, infoList);
			map.put(EmDaoResult.ROW_COUNT, cn);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put(EmDaoResult.VO_LIST, new ArrayList());
			map.put(EmDaoResult.ROW_COUNT, 0);
			
			log.error(e);
		}
		return map;
	}
	
	
}
