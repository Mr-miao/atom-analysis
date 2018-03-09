//package com.nantian.atom.service;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.logging.log4j.Logger;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.nantian.atom.context.ReqParam;
//import com.nantian.atom.dao.IBasePoDao;
//import com.nantian.atom.dao.IMysqlDao;
//import com.nantian.atom.dao.IViewerDao;
//import com.nantian.atom.dao.impl.EmDaoResult;
//import com.nantian.util.context.comm.AppContext;
//import com.nantian.util.str.FileUtil;
//import com.nantian.util.str.StringUtil;
//import com.nantian.util.str.VOPOPaserUtil;
//import com.xr.util.db.dao.impl.GenHqlUtil;
//import com.xr.util.db.dao.impl.Pager;
//
///**
// * 所有service类的父类，该类为抽象类
// * @author xurui
// *
// */
//public abstract class DataBaseServiceImpl implements IDataBaseService{
//	
//	/**
//	 * 用于日志输出
//	 */
//	protected final static Logger log = LogManager.getLogger(DataBaseServiceImpl.class);
//	
//	/**
//	 * DAO层
//	 */
//	@Resource
//	protected IViewerDao iViewerDao;
//	
//	@Resource
//	protected IMysqlDao iMysqlDao;
//	
//	@Resource
//	protected IBasePoDao iBasePoDao;
//	
//	/**
//	 * 根据唯一主键获取PO对象
//	 * @param clsPO
//	 * @param key主键值
//	 * @return
//	 */
//	@Override
//	public <PO> PO getPO(Class<PO> clsPO, Serializable key) {
//		// TODO Auto-generated method stub
//		return  iBasePoDao.get(clsPO, key);
//	}
//	
//	/**
//	 * 根据唯一主键获取VO对象，一般用于页面查询
//	 * @param clsPO
//	 * @param clsVO
//	 * @param key
//	 * @return
//	 */
//	@Override
//	public <PO, VO> VO getVO(Class<PO> clsPO, Class<VO> clsVO, Serializable key) {
//		// TODO Auto-generated method stub
//		return iViewerDao.get(clsPO, clsVO, key);
//	}
//	
//	/**
//	 * 带分页的VO数据
//	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
//	 * @param params hql语句中参数名称及参数值
//	 * @param currentPage 要查询的页码
//	 * @param map 用于存储pager供前端使用
//	 * @return
//	 */
//	@Override
//	public <VO, PO> List<VO> pagerList(String hql, Map<String, Object> params, 
//			ReqParam reqParam, ModelMap map, Class<PO> clsPO, Class<VO> clsVO){
//		Long rowCount = iBasePoDao.count(GenHqlUtil.genCountRowsHql(hql), params);
//		if(rowCount==null){
//			return null;
//		}
//		
//		Pager pager = new Pager(rowCount.intValue(), reqParam.getPageSize(), reqParam.getCurrentPage());
//		map.addAttribute(AppContext.PAGER, pager);
//		List<PO> list =  iBasePoDao.find(hql, params, pager.getCurrentPage(), pager.getPageSize());
//		try {
//			List<VO> listVo = VOPOPaserUtil.PO2VO(list, clsPO, clsVO);
//			return listVo;
//		} catch (Exception e) {
//			log.error(null, e);
//		}
//		return null;
//	}
//
//	/**
//	 * 带分页的PO数据
//	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
//	 * @param params hql语句中参数名称及参数值
//	 * @param currentPage 要查询的页码
//	 * @param map 用于存储pager供前端使用
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> pagerListPo(String hql,
//			Map<String, Object> params, ReqParam reqParam, ModelMap map,
//			Class<PO> clsPO) {
//		Long rowCount = iBasePoDao.count(GenHqlUtil.genCountRowsHql(hql), params);
//		if(rowCount==null){
//			return null;
//		}
//		Pager pager = new Pager(rowCount.intValue(), reqParam.getPageSize(), reqParam.getCurrentPage());
//		map.addAttribute(AppContext.PAGER, pager);
//		List<PO> list =  iBasePoDao.find(hql, params, pager.getCurrentPage(), pager.getPageSize());
//		return list;
//	}
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
//	@Override
//	public <VO, PO> List<VO> pagerListByPo(PO po, ReqParam reqParam, ModelMap map,
//			Class<PO> clsPO, Class<VO> clsVO) {
//		
//		Map<EmDaoResult, Object> retmap= iViewerDao.findByCondition(po, clsPO, clsVO, reqParam);
//
//		Integer rowCount = (Integer)retmap.get(EmDaoResult.ROW_COUNT);
//		
//		Pager pager = new Pager(rowCount.intValue(), reqParam.getPageSize(), reqParam.getCurrentPage());
//		map.addAttribute(AppContext.PAGER, pager);
//		return (List<VO>) retmap.get(EmDaoResult.VO_LIST);
//	}
//	
//	
//	/**
//	 * 保存单个对象到数据库
//	 * @param po 要保存的PO对象
//	 * @return
//	 */
//	@Override
//	public <PO> Serializable save(PO po){
//		return iBasePoDao.save(po);
//	}
//	
//	/**
//	 * 立即保存单个对象到数据库
//	 * @param po
//	 * @return
//	 */
//	@Override
//	public <PO> Object merge(PO po){
//		return iBasePoDao.merge(po);
//	}
//
//	/**
//	 * 删除指定po对象，条件即为po的属性值
//	 * @param po
//	 */
//	@Override
//	public <PO> void delete(PO po){
//		iBasePoDao.delete(po);
//	}
//	
//	/**
//	 * 批量删除，根据#分割
//	 * @param delIds
//	 * @param poCls
//	 */
//	@Override
//	public <PO> void delete(String delIds,Class<PO> poCls){
//		iBasePoDao.delete(delIds, poCls);
//	}
//
//	/**
//	 * 更新指定po到数据库
//	 * @param po
//	 */
//	@Override
//	public <PO> void update(PO po){
//		iBasePoDao.update(po);
//	}
//
//	/**
//	 * 更新指定po到数据库，如果该po对应数据库不存在，则执行插入操作
//	 * @param po
//	 */
//	@Override
//	public <PO> void saveOrUpdate(PO po){
//		iBasePoDao.saveOrUpdate(po);
//	}
//
//	/**
//	 * 根据主键延迟加载对应对象
//	 * @param poCls
//	 * @param key 主键值
//	 * @return
//	 */
//	@Override
//	public <PO> PO load(Class<PO> poCls, Serializable key){
//		return iBasePoDao.load(poCls, key);
//	}
//	
//	/**
//	 * 根据指定hql语句查询对应唯一PO
//	 * @param hql
//	 * @return
//	 */
//	@Override
//	public <PO> PO get(String hql){
//		return iBasePoDao.get(hql);
//	}
//
//	/**
//	 * 根据指定hql语句（带参数）查询对应唯一PO
//	 * @param hql
//	 * @return
//	 */
//	@Override
//	public <PO> PO get(String hql, Map<String, Object> params){
//		return iBasePoDao.get(hql, params);
//	}
//	
//	/**
//	 * 根据指定HQL语句查询集合
//	 * @param hql
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> find(String hql){
//		return iBasePoDao.find(hql);
//	}
//	
//	/**
//	 * 根据HQL语句查询，查询结果仅返回前maxResults条记录
//	 * @param hql
//	 * @param maxResults
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> find(String hql, int maxResults){
//		return iBasePoDao.find(hql, maxResults);
//	}
//	
//	/**
//	 * 根据指定HQL语句查询集合，带参数
//	 * @param hql
//	 * @param param
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> find(String hql, Object... param){
//		return iBasePoDao.find(hql, param);
//	}
//
//	/**
//	 * 根据指定HQL语句查询集合，带参数
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> find(String hql, Map<String, Object> params){
//		return iBasePoDao.find(hql, params);
//	}
//
//	/**
//	 * PO查询，带分页
//	 * @param hql
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> find(String hql, int page, int rows){
//		return iBasePoDao.find(hql, page, rows);
//	}
//
//	/**
//	 * 带参数的PO查询，带分页
//	 * @param hql
//	 * @param page
//	 * @param rows
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> find(String hql, Map<String, Object> params, int page, int rows){
//		return iBasePoDao.find(hql, params, page, rows);
//	}
//	
//	/**
//	 * 查询所有
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	@Override
//	public <PO> List<PO> findAll(Class<PO> cls){
//		String hql = "FROM "+cls.getName();
//		return iBasePoDao.find(hql);
//	}
//	
//	/**
//	 * 查询所有
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	@Override
//	public <PO, VO> List<VO> findAll(Class<PO> clsPo, Class<VO> clsVo){
//		String hql = "FROM "+clsPo.getName();
//		return iViewerDao.find(hql, clsPo, clsVo);
//	}
//	
//	/**
//	 * 聚合函数，包括count、sum、max、min、avg等
//	 * @param hql
//	 * @return
//	 */
//	@Override
//	public Long count(String hql){
//		return iBasePoDao.count(hql);
//	}
//
//	/**
//	 * 带参数的聚合函数，包括count、sum、max、min、avg等
//	 * @param hql
//	 * @return
//	 */
//	@Override
//	public Long count(String hql, Map<String, Object> params){
//		return iBasePoDao.count(hql, params);
//	}
//	
//	/**
//	 * 执行特定hql语句，返回影响记录数
//	 * @param hql
//	 * @return
//	 */
//	@Override
//	public int executeHql(String hql){
//		return iBasePoDao.executeHql(hql);
//	}
//	
//	/**
//	 * 根据PO查询
//	 */
//	@Override
//	public <PO> Map<EmDaoResult, Object> findByCondition(PO po, ReqParam reqParam) throws Exception {
//		return iBasePoDao.findByCondition(po, reqParam);
//	}
//	
//	@Override
//	public <PO, VO> VO getVO(String hql, Class<PO> poCls, Class<VO> voCls){
//		return iViewerDao.get(hql, poCls, voCls);
//	}
//
//	@Override
//	public <PO, VO> VO getVO(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) {
//		return iViewerDao.get(hql, params, poCls, voCls);
//	}
//
//	@Override
//	public <PO, VO> List<VO> findVOList(String hql, Class<PO> poCls, Class<VO> voCls) {
//		return iViewerDao.find(hql, poCls, voCls);
//	}
//
//	@Override
//	public <PO, VO> List<VO> findVOList(String hql, Map<String, Object> params, Class<PO> poCls, Class<VO> voCls) {
//		return iViewerDao.find(hql, params, poCls, voCls);
//	}
//
//	@Override
//	public List getTBColumns(String tableName,String schemaName){
//		String queryString ="select column_name,data_type,column_comment from information_schema.COLUMNS where table_name ='"+tableName+"' and table_schema = '"+schemaName+"'";
//		List list = iMysqlDao.find(queryString);
//		return list;
//	} 	
//	
//	/*
//	 * 批量导入
//	 * list：从excel中读取到的整个数据
//	 * keys：系统配置好的支持导入的字段集合
//	 */
//	@Override
//	public void batchInsert(HttpServletRequest request,MultipartFile file,String tableName) throws Exception{
//		//获取路径，重命名文件
//		String path = request.getSession().getServletContext().getRealPath("/");
//		String genFilepath = path + AppContext.FILE_UPLOAD_PATH + FileUtil.fileRename(file.getOriginalFilename());
//		File uploadFile=new File(genFilepath);
//		//写文件到本地
//		file.transferTo(uploadFile);
//		//读取系统配置好可以进行批量导入的字段
//		String[] keys = {"device_no","device_name","device_factory","device_crt_time","device_desc"};
//		//读取文件
//		List<List<String>> list = FileUtil.readXls(genFilepath);
//		String[] sqls = StringUtil.genSqlFromExcel(list, tableName, keys);
//		//执行批量导入
//		iMysqlDao.batchInsert(sqls);
//		//删除文件
//		FileUtil.deleteFile(genFilepath);
//			
//	}
//	
//	/*
//	 * 批量导入
//	 * list：从excel中读取到的整个数据
//	 * keys：系统配置好的支持导入的字段集合
//	 */
//	@Override
//	public void importExcel(HttpServletRequest request,MultipartFile file,String tableName) throws Exception{
//		//获取路径，重命名文件
//		String path = request.getSession().getServletContext().getRealPath("/");
//		String genFilepath = path + AppContext.FILE_UPLOAD_PATH + FileUtil.fileRename(file.getOriginalFilename());
//		File uploadFile=new File(genFilepath);
//		//写文件到本地
//		file.transferTo(uploadFile);
//		//读取系统配置好可以进行批量导入的字段
//		String[] keys = {"device_no","device_name","device_factory","device_crt_time","device_desc"};
//		//读取文件
//		List<List<String>> list = FileUtil.readXls(genFilepath);
//		String[] sqls = StringUtil.genSqlFromExcel(list, tableName, keys);
//		//执行批量导入
//		iMysqlDao.batchInsert(sqls);
//		//删除文件
//		FileUtil.deleteFile(genFilepath);
//			
//	}
//	
//	@Override
//	public void exportExcel(){
//		
//	}
//
//	
//	
//}
