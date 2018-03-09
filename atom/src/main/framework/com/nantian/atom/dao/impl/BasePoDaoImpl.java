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
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.dao.IBasePoDao;
import com.nantian.atom.dao.ProWork;
import com.nantian.atom.generated.po.rolem.Branch;
import com.nantian.atom.util.DynamicDataSource;
import com.nantian.util.hql.HqlDataAuthGeneratorUtil;
import com.nantian.util.str.SysConfigPropertiesUtil;

@Repository("basePoDaoImpl")
public class BasePoDaoImpl implements IBasePoDao {
	protected final static Logger log = LogManager.getLogger(BasePoDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public Session getSession() {
		return this.getCurrentSession();
	}

	@Override
	public void flush() {
		this.getCurrentSession().flush();
	}

	@Override
	public <PO> PO get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			List<PO> l = q.list();
			if (l != null && l.size() > 0) {
				return l.get(0);
			}
		}
		return null;
	}

	@Override
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

	@Override
	public <PO> Serializable save(PO o) {
		 this.getCurrentSession().save(o);
		return (Serializable) o;
	}

	@Override
	public <PO> Object merge(PO o) {
		return this.getCurrentSession().merge(o);
	}

	@Override
	public <PO> void delete(PO o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public <PO> void update(PO o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public <PO> void saveOrUpdate(PO o) {
		this.getCurrentSession().saveOrUpdate(o);
	}

	@Override
	public <PO> PO get(Class<PO> c, Serializable id) {
		return (PO) this.getCurrentSession().get(c, id);
	}

	@Override
	public <PO> PO load(Class<PO> c, Serializable id) {
		return (PO) this.getCurrentSession().load(c, id);
	}

	@Override
	public <PO> List<PO> find(String hql) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		//数据权限由hql控制
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			return q.list();
		}else{
			return null;
		}
	}

	@Override
	public <PO> List<PO> find(String hql, int maxResults) {
		
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			q.setMaxResults(maxResults);
			return q.list();
		}else{
			return null;
		}
	}

	@Override
	public <PO> List<PO> find(String hql, Object... param) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
			}
			return q.list();
		}
		return null;
	}

	@Override
	public <PO> List<PO> find(String hql, Map<String, Object> params) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
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

	@Override
	public <PO> List<PO> find(String hql, int page, int rows) {
/*		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo());
		}*/
		//数据权限由hql控制
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		}
		return null;
	}

	@Override
	public <PO> List<PO> find(String hql, Map<String, Object> params, int page, int rows) {
/*		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo());
		}*/
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
		}
		return null;
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			return (Long) q.uniqueResult();
		}else{
			return null;
		}
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			return (Long) q.uniqueResult();
		}
		
		return null;
	}
	
	public Object sumNoEnBranchNo(String hql, Map<String, Object> params){
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			return q.uniqueResult();
		}
		
		return null;
	}

	@Override
	public int executeHql(String hql) {
		boolean isQuery = hql.toLowerCase().startsWith("select")||hql.toLowerCase().startsWith("from");
/*		if(isQuery){
			//从hql中获取到类名
			String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
			//获取数据权限配置，是否启用数据权限
			if(SysConfigPropertiesUtil.isDataAuth(entityName)){
				hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo());
			}
		}*/
		Query q = this.getCurrentSession().createQuery(hql);
		if(q!=null){
			return q.executeUpdate();
		}
		return 0;
	}


	//根据ids批量删除，id之间使用“#”分割
	@Override
	public <PO> void delete(String delIds, Class<PO> poCls) {
		String[] id = delIds.split("#");
		try {
			for( int i=0; i<id.length; i++){
				 PO newinfo = findById(Integer.parseInt(id[i]),poCls);
				 getSession().delete(newinfo);
			}
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public<PO> PO findById(Object id,Class<PO> poCls) {
		try {
			PO instance = (PO) getSession().get(poCls, (Serializable) id);
			return instance;
		} catch (RuntimeException re) {
			re.printStackTrace();
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
	@Override
	public ResultSet executeProRs(ProWork work) {
		this.getCurrentSession().doWork(work);
		return work.getRs();
	}
	
	/**
	 * 条件查询所有PO
	 */
	@Override
	public <PO> List<PO> findAllByCondition(PO po, Class<PO> poCls) {

		String branchNo="";
		branchNo=DynamicDataSource.getBranchNo()==null?"":DynamicDataSource.getBranchNo();
		Integer branchId=DynamicDataSource.getBranchId();
		Map<EmDaoResult, Object> map = new HashMap<EmDaoResult, Object>();
		List<PO> infoList = new ArrayList<PO>();
		try {
			Criteria listCriteria = getCurrentSession().createCriteria(po.getClass());
			if((SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName()))&&(po.getClass().getSimpleName().equals("Branch"))){//需要加数据权限的
				
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
				
				addLIKEConditions(listCriteria, "p.branchRelationship", "%"+branchNo+"%");
			}else if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的				
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p").createCriteria("branchId","b",JoinType.LEFT_OUTER_JOIN);
				listCriteria.add(Restrictions.eqProperty("p.branchId.branchId", "b.branchId"));
				addLIKEConditions(listCriteria, "b.branchRelationship", "%"+branchNo+"%");
			}else{
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
			}
			
			
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
						if("branchId".equals(field.getName())&&po.getClass().getSimpleName().equals("Media")){
							Branch branch3=(Branch)obj;
							Branch branch1=get(Branch.class, branch3.getBranchId());
							if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的	
								addEQConditions(listCriteria, name, obj);
							}else{
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

						}if("branchId".equals(field.getName())&&!po.getClass().getSimpleName().equals("Media")){
							Branch branch1=null;
							if(obj instanceof Branch){
								Branch branch3=(Branch)obj;
								branch1=get(Branch.class, branch3.getBranchId());
							}else{
								branch1=get(Branch.class, Integer.parseInt(obj.toString()));
							}
							
							
							if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的				
								addLIKEConditions(listCriteria, "b.branchRelationship", "%"+branch1.getBranchNo()+"%");
								
							}else{
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

						}else{
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
			}
			infoList =  (List<PO>) listCriteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return infoList;
	}
/*
 * flag标志1表示全量，0表示机构及机构管辖的机构
 * 
 */
	public  List<Branch> findAllByConditionForBranch(Branch branch) {

		List<Branch> infoList = new ArrayList<Branch>();
		try {
			Criteria listCriteria = getCurrentSession().createCriteria(Branch.class);
			

			listCriteria = getCurrentSession().createCriteria(Branch.class,"p");
	
			Field[] fields = Branch.class.getDeclaredFields();
			String name;
			Method m;
			
			for (Field field : fields) {
				name = field.getName(); // 获取属性的名字
				int modifiers=field.getModifiers();
				if (10 != modifiers && 18 != modifiers && 26 != modifiers) {
					m = Branch.class.getMethod(
							"get" + name.substring(0, 1).toUpperCase()
									+ name.substring(1));

					Object obj = m.invoke(branch);
					if (obj != null) {
						if(obj instanceof Date) {
							listCriteria.add(Restrictions.ge("p."+name, obj )); 
						}else if(obj instanceof String) {
							if (!"".equals((obj.toString()).trim())) {
								addLIKEConditions(listCriteria, "p."+name, "%"+((String) obj).trim()+"%");
							}
						}else {
							addEQConditions(listCriteria, "p."+name, obj);
						}
					}
				}
			}
			infoList =  (List<Branch>) listCriteria.list();
		} catch (Exception e) {
			log.error(e);
		}
		return infoList;
	}



}
