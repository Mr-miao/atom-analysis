package com.nantian.atom.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.dao.impl.EmDaoResult;

/**
 * PO转VO，主要用于查询类的页面显示
 * @author xurui
 *
 */
public interface IViewerDao {
	
	public <PO, VO> VO get(Class<PO> poCls, Class<VO> voCls, Serializable key) ;

	public <PO, VO> VO get(String hql, Class<PO> poCls, Class<VO> voCls);

	public <PO, VO> VO get(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) ;

	public <PO, VO> List<VO> find(String hql, Class<PO> poCls, Class<VO> voCls) ;

	public <PO, VO> List<VO> find(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) ;
	
	public <PO, VO> List<VO> find(String hql, int currentPage, int pageSize, Class<PO> poCls, Class<VO> voCls);

	public <PO, VO> List<VO> find(String hql, Map<String, Object> params, int currentPage, int pageSize, 
			Class<PO> poCls, Class<VO> voCls) ;
	
	public <PO, VO> List<VO> find(String hql, Class<PO> poCls, Class<VO> voCls, Object... param) ;
	
	public <PO, VO> Map<EmDaoResult, Object> findByCondition(PO po, Class<PO> poCls, Class<VO> voCls, ReqParam reqParam);
	
	public <PO, VO> Map<EmDaoResult, Object> findByConditionA(PO po, Class<PO> poCls, Class<VO> voCls, ReqParam reqParam,String relationship);
	
	public <PO, VO> Map<EmDaoResult, Object> findAllByCondition(PO po, Class<PO> poCls, Class<VO> voCls);
	
}
