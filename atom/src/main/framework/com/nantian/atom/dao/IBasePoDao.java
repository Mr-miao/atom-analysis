package com.nantian.atom.dao;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.nantian.atom.generated.po.rolem.Branch;

/**
 * 操作PO
 * @author xurui
 *
 */
public interface IBasePoDao {
	
	public <PO> Serializable save(PO po);
	
	public <PO> Object merge(PO po);

	public <PO> void delete(PO po);
	
	/**
	 * 批量删除，根据#分割
	 * @param delIds
	 * @param poCls
	 */
	public <PO> void delete(String delIds,Class<PO> poCls);

	public <PO> void update(PO po);

	public <PO> void saveOrUpdate(PO po);

	/**
	 * 根据主键值立即获取对象
	 * @param poCls
	 * @param key 主键值
	 * @return
	 */
	public <PO> PO get(Class<PO> poCls, Serializable key);
	
	/**
	 * 根据主键延迟加载对应对象
	 * @param poCls
	 * @param key 主键值
	 * @return
	 */
	public <PO> PO load(Class<PO> poCls, Serializable key);
	
	public <PO> PO get(String hql);

	public <PO> PO get(String hql, Map<String, Object> params);
	
	public <PO> List<PO> find(String hql);
	
	public <PO> List<PO> find(String hql, int maxResults);
	
	public <PO> List<PO> find(String hql, Object... param);

	public <PO> List<PO> find(String hql, Map<String, Object> params);

	public <PO> List<PO> find(String hql, int page, int rows);

	public <PO> List<PO> find(String hql, Map<String, Object> params, int page, int rows);
	
	/**
	 * 聚合函数，包括count、sum、max、min、avg等
	 * @param hql
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 带参数的聚合函数，包括count、sum、max、min、avg等
	 * @param hql
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);
	
	public int executeHql(String hql);
	
	/**
	 * 获取hibernate会话
	 * @return
	 */
	public Session getSession();
	
	/**
	 * flush 数据流，立即执行未执行的sql
	 */
	public void flush();
	
	/**
	 * 执行存储过程
	 * @param work
	 * @return
	 */
	public ResultSet executeProRs(ProWork work);

	/**
	 * 条件查询所有PO
	 */
	public <PO> List<PO> findAllByCondition(PO po, Class<PO> poCls);

	public  List<Branch> findAllByConditionForBranch(Branch branch);

	
}
