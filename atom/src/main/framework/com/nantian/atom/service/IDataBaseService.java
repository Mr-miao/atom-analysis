//package com.nantian.atom.service;
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.ui.ModelMap;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.nantian.atom.context.ReqParam;
//import com.nantian.atom.dao.impl.EmDaoResult;
//
///**
// * 服务层统一父接口
// * @author antkm
// *
// */
//public interface IDataBaseService {
//	
//	/**
//	 * 根据唯一主键获取PO对象
//	 * @param clsPO
//	 * @param key主键值
//	 * @return
//	 */
//	public <PO> PO getPO(Class<PO> clsPO, Serializable key) ;
//	
//	/**
//	 * 根据唯一主键获取VO对象，一般用于页面查询
//	 * @param clsPO
//	 * @param clsVO
//	 * @param key
//	 * @return
//	 */
//	public <PO, VO> VO getVO(Class<PO> clsPO, Class<VO> clsVO, Serializable key);
//	
//	/**
//	 * 带分页的VO数据
//	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
//	 * @param params hql语句中参数名称及参数值
//	 * @param currentPage 要查询的页码
//	 * @param map 用于存储pager供前端使用
//	 * @return
//	 */
//	public <VO, PO> List<VO> pagerList(String hql, Map<String, Object> params, 
//			ReqParam reqParam, ModelMap map, Class<PO> clsPO, Class<VO> clsVO);
//
//	/**
//	 * 带分页的PO数据
//	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
//	 * @param params hql语句中参数名称及参数值
//	 * @param currentPage 要查询的页码
//	 * @param map 用于存储pager供前端使用
//	 * @return
//	 */
//	public <PO> List<PO> pagerListPo(String hql,
//			Map<String, Object> params, ReqParam reqParam, ModelMap map,
//			Class<PO> clsPO) ;
//
//	/**
//	 * 带条件查询的分页逻辑，将PO映射为VO
//	 * @param po
//	 * @param reqParam
//	 * @param map
//	 * @param clsPO
//	 * @param clsVO
//	 * @return
//	 * @throws Exception
//	 */
//	public <VO, PO> List<VO> pagerListByPo(PO po, ReqParam reqParam, ModelMap map,
//			Class<PO> clsPO, Class<VO> clsVO);
//	
//	
//	/**
//	 * 保存单个对象到数据库
//	 * @param po 要保存的PO对象
//	 * @return
//	 */
//	public <PO> Serializable save(PO po);
//	
//	/**
//	 * 立即保存单个对象到数据库
//	 * @param po
//	 * @return
//	 */
//	public <PO> Object merge(PO po);
//
//	/**
//	 * 删除指定po对象，条件即为po的属性值
//	 * @param po
//	 */
//	public <PO> void delete(PO po);
//	
//	/**
//	 * 批量删除，根据#分割
//	 * @param delIds
//	 * @param poCls
//	 */
//	public <PO> void delete(String delIds,Class<PO> poCls);
//
//	/**
//	 * 更新指定po到数据库
//	 * @param po
//	 */
//	public <PO> void update(PO po);
//
//	/**
//	 * 更新指定po到数据库，如果该po对应数据库不存在，则执行插入操作
//	 * @param po
//	 */
//	public <PO> void saveOrUpdate(PO po);
//
//	/**
//	 * 根据主键延迟加载对应对象
//	 * @param poCls
//	 * @param key 主键值
//	 * @return
//	 */
//	public <PO> PO load(Class<PO> poCls, Serializable key);
//	
//	/**
//	 * 根据指定hql语句查询对应唯一PO
//	 * @param hql
//	 * @return
//	 */
//	public <PO> PO get(String hql);
//
//	/**
//	 * 根据指定hql语句（带参数）查询对应唯一PO
//	 * @param hql
//	 * @return
//	 */
//	public <PO> PO get(String hql, Map<String, Object> params);
//	
//	/**
//	 * 根据指定HQL语句查询集合
//	 * @param hql
//	 * @return
//	 */
//	public <PO> List<PO> find(String hql);
//	
//	/**
//	 * 根据HQL语句查询，查询结果仅返回前maxResults条记录
//	 * @param hql
//	 * @param maxResults
//	 * @return
//	 */
//	public <PO> List<PO> find(String hql, int maxResults);
//	
//	/**
//	 * 根据指定HQL语句查询集合，带参数
//	 * @param hql
//	 * @param param
//	 * @return
//	 */
//	public <PO> List<PO> find(String hql, Object... param);
//
//	/**
//	 * 根据指定HQL语句查询集合，带参数
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	public <PO> List<PO> find(String hql, Map<String, Object> params);
//
//	/**
//	 * PO查询，带分页
//	 * @param hql
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	public <PO> List<PO> find(String hql, int page, int rows);
//
//	/**
//	 * 带参数的PO查询，带分页
//	 * @param hql
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	public <PO> List<PO> find(String hql, Map<String, Object> params, int page, int rows);
//	
//	/**
//	 * 查询所有
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	public <PO> List<PO> findAll(Class<PO> cls);
//	
//	/**
//	 * 查询所有
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	public <PO, VO> List<VO> findAll(Class<PO> clsPo, Class<VO> clsVo);
//	
//	/**
//	 * 聚合函数，包括count、sum、max、min、avg等
//	 * @param hql
//	 * @return
//	 */
//	public Long count(String hql);
//
//	/**
//	 * 带参数的聚合函数，包括count、sum、max、min、avg等
//	 * @param hql
//	 * @return
//	 */
//	public Long count(String hql, Map<String, Object> params);
//	
//	/**
//	 * 执行特定hql语句，返回影响记录数
//	 * @param hql
//	 * @return
//	 */
//	public int executeHql(String hql);
//	
//	
//	//BaseParseDao
//	public <PO> Map<EmDaoResult, Object> findByCondition(PO po, ReqParam reqParam)  throws Exception;
//	
//
//	public <PO, VO> VO getVO(String hql, Class<PO> poCls, Class<VO> voCls);
//
//	public <PO, VO> VO getVO(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) ;
//
//	public <PO, VO> List<VO> findVOList(String hql, Class<PO> poCls, Class<VO> voCls) ;
//
//	public <PO, VO> List<VO> findVOList(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls);
//
//	public List getTBColumns(String tableName,String schemaName);
//	
//	/*
//	 * 批量导入
//	 * list：从excel中读取到的整个数据
//	 * keys：系统配置好的支持导入的字段集合
//	 */
//	public void batchInsert(HttpServletRequest request,MultipartFile file,String tableName) throws Exception;
//	
//	/**
//	 * 批量导入
//	 * list：从excel中读取到的整个数据
//	 * keys：系统配置好的支持导入的字段集合
//	 */
//	public void importExcel(HttpServletRequest request,MultipartFile file,String tableName) throws Exception;
//	
//	/**
//	 * 导出excel
//	 */
//	public void exportExcel();
//}
