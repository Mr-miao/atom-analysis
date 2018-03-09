package com.nantian.atom.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.nantian.atom.dao.IViewerDao;
import com.nantian.atom.generated.po.rolem.Branch;
import com.nantian.atom.util.DynamicDataSource;
import com.nantian.util.hql.HqlDataAuthGeneratorUtil;
import com.nantian.util.str.SysConfigPropertiesUtil;
import com.nantian.util.str.VOPOPaserUtil;

@Repository("viewerDaoImpl")
public class ViewerDaoImpl implements IViewerDao {
	protected final static Logger log = LogManager.getLogger(ViewerDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BasePoDaoImpl basePoDaoImpl;
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public <PO, VO> VO get(Class<PO> poCls, Class<VO> voCls, Serializable id) {
		PO po = (PO) this.getCurrentSession().get(poCls, id);
		if (po != null) {
			try {
				return VOPOPaserUtil.PO2VO(po, poCls, voCls);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public <PO, VO> VO get(String hql, Class<PO> poCls, Class<VO> voCls) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			List<PO> l = q.list();
			if (l != null && l.size() > 0) {
				try {
					return VOPOPaserUtil.PO2VO(l.get(0), poCls, voCls);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public <PO, VO> VO get(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			List<PO> l = q.list();
			if (l != null && l.size() > 0) {
				try {
					return VOPOPaserUtil.PO2VO(l.get(0), poCls, voCls);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	@Override
	public <PO, VO> List<VO> find(String hql, Class<PO> poCls, Class<VO> voCls) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			try {
				return VOPOPaserUtil.PO2VO(q.list(), poCls, voCls);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public <PO, VO> List<VO> find(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		Query q = this.getCurrentSession().createQuery(hql);
		this.getCurrentSession().getNamedQuery("name").list();		
		if (q != null) {
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			try {
				return VOPOPaserUtil.PO2VO(q.list(), poCls, voCls);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public <PO, VO> List<VO> find(String hql, Map<String, Object> params, int currentPage, int rows, Class<PO> poCls,
			Class<VO> voCls) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			try {
				return VOPOPaserUtil.PO2VO(q.setFirstResult((currentPage - 1) * rows)
						.setMaxResults(rows).list(), poCls, voCls);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	@Override
	public <PO, VO> List<VO> find(String hql, int page, int rows, Class<PO> poCls, Class<VO> voCls) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			try {
				return VOPOPaserUtil.PO2VO(q.setFirstResult((page - 1) * rows)
						.setMaxResults(rows).list(), poCls, voCls);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	@Override
	public <PO, VO> List<VO> find(String hql, Class<PO> poCls, Class<VO> voCls, Object... param) {
		//从hql中获取到类名
		String entityName = HqlDataAuthGeneratorUtil.getEntityName(hql);
		//获取数据权限配置，是否启用数据权限
		if(SysConfigPropertiesUtil.isDataAuth(entityName)){
			hql = HqlDataAuthGeneratorUtil.genAuthBranch(hql,DynamicDataSource.getBranchNo(),entityName);
		}
		Query q = this.getCurrentSession().createQuery(hql);
		if (q != null) {
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					q.setParameter(i, param[i]);
				}
			}
			try {
				return VOPOPaserUtil.PO2VO(q.list(), poCls, voCls);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		return null;
	}

	@Override
	public <PO, VO> Map<EmDaoResult, Object> findByCondition(PO po, Class<PO> poCls, Class<VO> voCls, ReqParam reqParam) {
		//判断是否需要数据权限
		Map<EmDaoResult, Object> map = new HashMap<EmDaoResult, Object>();
		List<PO> infoList = new ArrayList<PO>();
		Criteria listCriteria =null;
		Criteria countCriteria=null;
		int infoListCount = 0;
		String branchRelationShip="";
		branchRelationShip=DynamicDataSource.getBranchNo()==null?"":DynamicDataSource.getBranchNo();
		try {
			if((SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName()))&&(po.getClass().getSimpleName().equals("Branch"))){//需要加数据权限的
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
				countCriteria= getCurrentSession().createCriteria(po.getClass(),"p");
				addLIKEConditions(listCriteria, "p.branchRelationship", "%"+branchRelationShip+"%");
				addLIKEConditions(countCriteria, "p.branchRelationship", "%"+branchRelationShip+"%");
			}else if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的				
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p").createCriteria("branchId","b",JoinType.LEFT_OUTER_JOIN);
				listCriteria.add(Restrictions.eqProperty("p.branchId.branchId", "b.branchId"));
				addLIKEConditions(listCriteria, "b.branchRelationship", "%"+branchRelationShip+"%");
				
				countCriteria = getCurrentSession().createCriteria(po.getClass(),"p").createCriteria("branchId","b",JoinType.LEFT_OUTER_JOIN);
				countCriteria.add(Restrictions.eqProperty("p.branchId.branchId", "b.branchId"));
				addLIKEConditions(countCriteria, "b.branchRelationship", "%"+branchRelationShip+"%");
			}else{
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
				countCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
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
							Branch branch1=basePoDaoImpl.get(Branch.class, branch3.getBranchId());
							if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的	
								addEQConditions(listCriteria, name, obj);
								addEQConditions(countCriteria, name, obj);
							}else{
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

						}if("branchId".equals(field.getName())&&!po.getClass().getSimpleName().equals("Media")){
							Branch branch3=(Branch)obj;
							Branch branch1=basePoDaoImpl.get(Branch.class, branch3.getBranchId());
							if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的				
								addLIKEConditions(listCriteria, "b.branchRelationship", "%"+branch1.getBranchNo()+"%");
								
								addLIKEConditions(countCriteria, "b.branchRelationship", "%"+branch1.getBranchNo()+"%");
							}else{
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

						}else{
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
			}
			infoListCount=criteriaCount(countCriteria);
			infoList =  (List<PO>) criteriaList(listCriteria, reqParam);
			
			map.put(EmDaoResult.VO_LIST, VOPOPaserUtil.PO2VO(infoList, poCls, voCls));
			map.put(EmDaoResult.ROW_COUNT, infoListCount);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return map;
	}
	
	/**
	 * 条件查询所有VO
	 * @param po
	 * @param poCls
	 * @param voCls
	 * @return返回结果集
	 */
	@Override
	public <PO, VO> Map<EmDaoResult, Object> findAllByCondition(PO po, Class<PO> poCls, Class<VO> voCls) {
		//判断是否需要数据权限
		Map<EmDaoResult, Object> map = new HashMap<EmDaoResult, Object>();
		List<PO> infoList = new ArrayList<PO>();
		String branchRelationShip="";
		branchRelationShip=DynamicDataSource.getBranchNo()==null?"":DynamicDataSource.getBranchNo();
		try {
			Criteria listCriteria = getCurrentSession().createCriteria(po.getClass());
			if((SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName()))&&(po.getClass().getSimpleName().equals("Branch"))){//需要加数据权限的
				
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p");
				
				addLIKEConditions(listCriteria, "p.branchRelationship", "%"+branchRelationShip+"%");
			}else if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要加数据权限的				
				listCriteria = getCurrentSession().createCriteria(po.getClass(),"p").createCriteria("branchId","b",JoinType.LEFT_OUTER_JOIN);
				listCriteria.add(Restrictions.eqProperty("p.branchId.branchId", "b.branchId"));
				addLIKEConditions(listCriteria, "b.branchRelationship", "%"+branchRelationShip+"%");
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
			map.put(EmDaoResult.VO_LIST, VOPOPaserUtil.PO2VO(infoList, poCls, voCls));
			map.put(EmDaoResult.ROW_COUNT, infoList.size());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return map;
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
	private void addEQConditions(Criteria criteria, String str, Object object) {
		criteria.add(Restrictions.eq(str, object));

	}
	
	/**
	 * 
	 * 统计数目的方法
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	private int criteriaCount(Criteria criteria) {
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
	private void addLIKEConditions(Criteria criteria, String str, Object object) {
		criteria.add(Restrictions.like(str, object));
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
			criteria.addOrder(Order.asc("p."+reqParam.getOrderName()));
		}else if(ReqParam.DESC.equals(reqParam.getOrderFlag())){
			criteria.addOrder(Order.desc("p."+reqParam.getOrderName()));
		}
		return criteria.list();
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
	private List<?> criteriaListA(Criteria criteria, ReqParam reqParam) {
		criteria.setFirstResult(reqParam.getCurrentPage()*reqParam.getPageSize());
		criteria.setMaxResults(reqParam.getPageSize());
		if(ReqParam.ASC.equals(reqParam.getOrderFlag())){
			criteria.addOrder(Order.asc(reqParam.getOrderName()));
		}else if(ReqParam.DESC.equals(reqParam.getOrderFlag())){
			criteria.addOrder(Order.desc(reqParam.getOrderName()));
		}
		return criteria.list();
	}
	@Override
	public <PO, VO> Map<EmDaoResult, Object> findByConditionA(PO po, Class<PO> poCls, Class<VO> voCls,
			ReqParam reqParam,String relationship) {
			Map<EmDaoResult, Object> map = new HashMap<EmDaoResult, Object>();
			List<PO> infoList = new ArrayList<PO>();
			int infoListCount = 0;
			try {
				Criteria listCriteria = getCurrentSession().createCriteria(po.getClass());
				Criteria countCriteria = getCurrentSession().createCriteria(po.getClass());
				Field[] fields = po.getClass().getDeclaredFields();
				String name;
				Method m;
				
				for (Field field : fields) {
					name = field.getName(); // 获取属性的名字
					if(name.equals("roleRelationship")){
					
						listCriteria.add(Restrictions.or(Restrictions.eq("roleId", Integer.parseInt(relationship)),Restrictions.like(name, "%"+relationship+"%")));
						countCriteria.add(Restrictions.or(Restrictions.eq("roleId", Integer.parseInt(relationship)),Restrictions.like(name, "%"+relationship+"%")));
					}
				}
				infoListCount = criteriaCount(countCriteria);
				infoList =  (List<PO>) criteriaListA(listCriteria, reqParam);
				map.put(EmDaoResult.VO_LIST, VOPOPaserUtil.PO2VO(infoList, poCls, voCls));
				map.put(EmDaoResult.ROW_COUNT, infoListCount);
			} catch (Exception e) {
				log.error(e);
			}
			return map;
	}

}
