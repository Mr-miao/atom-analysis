package com.nantian.atom.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.generated.po.rolem.Branch;

public interface IService {
	/**
	 * 根据唯一主键获取PO对象
	 * @param clsPO
	 * @param key主键值
	 * @return
	 */
	public <PO> PO getPO(Class<PO> clsPO, Serializable key) ;
	
	/**
	 * 根据唯一主键获取VO对象，一般用于页面查询
	 * @param clsPO
	 * @param clsVO
	 * @param key
	 * @return
	 */
	public <PO, VO> VO getVO(Class<PO> clsPO, Class<VO> clsVO, Serializable key) ;
	
	/**
	 * 带分页的VO数据
	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
	 * @param params hql语句中参数名称及参数值
	 * @param currentPage 要查询的页码
	 * @param map 用于存储pager供前端使用
	 * @return
	 */
	public <VO, PO> List<VO> pagerList(String hql, Map<String, Object> params, 
			int currentPage, Integer pageSize, ModelMap map, Class<PO> clsPO, Class<VO> clsVO);


	/**
	 * 带条件查询的分页逻辑，将PO映射为VO
	 * @param po
	 * @param reqParam
	 * @param map
	 * @param clsPO
	 * @param clsVO
	 * @return
	 * @throws Exception
	 */
	public <VO, PO> List<VO> pagerListByPo(PO po, ReqParam reqParam, ModelMap map,
			Class<PO> clsPO, Class<VO> clsVO) ;
	/**
	 * 带条件的分页查询，将PO映射为VO
	 * @param po
	 * @param reqParam
	 * @param map
	 * @param clsPO
	 * @param clsVO
	 * @return
	 */
	public <VO, PO> List<VO> pagerListByPoA(PO po, ReqParam reqParam, ModelMap map,
			Class<PO> clsPO, Class<VO> clsVO,String relationship) ;
	/**
	 * 保存单个对象到数据库
	 * @param po 要保存的PO对象
	 * @return
	 */
	public <PO> Serializable save(PO po);
	
	/**
	 * 批量删除，根据#分割
	 * @param delIds
	 * @param poCls
	 */
	public <PO> void delete(String delIds,Class<PO> poCls);

	/**
	 * 更新指定po到数据库
	 * @param po
	 */
	public <PO> void update(PO po);
	
	/**
	 * 批量更新PO集合到数据库
	 * @param po
	 */
	public <PO> void updateBatch(List<PO> list);

	/**
	 * 更新指定po到数据库，如果该po对应数据库不存在，则执行插入操作
	 * @param po
	 */
	public <PO> void saveOrUpdate(PO po);

	
	/**
	 * 根据指定HQL语句查询集合，带参数
	 * @param hql
	 * @param params
	 * @return
	 */
	public <PO> List<PO> find(String hql, Map<String, Object> params);
	
	/**
	 * 根据指定HQL语句查询集合，带参数
	 * @param hql
	 * @param params
	 * @return
	 */
	public <PO> List<PO> findForAuth(String hql, Map<String, Object> params,String tableName);
	
	/**
	 * 查询所有
	 * @param hql
	 * @param params
	 * @return
	 */
	public <PO> List<PO> findAll(Class<PO> cls);

	/**
	 * 查询所有
	 * @param hql
	 * @param params
	 * @return
	 */
	public <PO, VO> List<VO> findAll(Class<PO> clsPo, Class<VO> clsVo);
	
	/**
	 * 带参数的聚合函数，包括count、sum、max、min、avg等
	 * @param hql
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);
	
	/**
	 * 执行特定hql语句，返回影响记录数
	 * @param hql
	 * @return
	 */
	public int executeHql(String hql);
	
	/**
	 * 根据条件查询，
	 * @param clsVO
	 * @param clsPO
	 * @return返回所有VO
	 */
	public <PO,VO> List<VO> findAllByCondition(PO po,Class<VO> clsVO,Class<PO> clsPO);

	public <PO> List<PO> findAllByCondition(PO po, Class<PO> clsPO);

	public  List<Branch> findAllByConditionForBranch(Branch branch);

}
