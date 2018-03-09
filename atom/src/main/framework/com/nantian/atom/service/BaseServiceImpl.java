package com.nantian.atom.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.ui.ModelMap;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.dao.IBasePoDao;
import com.nantian.atom.dao.IMysqlDao;
import com.nantian.atom.dao.IViewerDao;
import com.nantian.atom.dao.impl.EmDaoResult;
import com.nantian.atom.generated.po.rolem.Branch;
import com.nantian.atom.po.Role;
import com.nantian.atom.vo.RoleVo;
import com.nantian.util.context.comm.AppContext;
import com.nantian.util.str.VOPOPaserUtil;
import com.xr.util.db.dao.impl.GenHqlUtil;
import com.xr.util.db.dao.impl.Pager;

/**
 * 所有service类的父类，该类为抽象类
 * @author xurui
 *
 */
public abstract class BaseServiceImpl implements IService{
	
	/**
	 * 用于日志输出
	 */
	protected final static Logger log = LogManager.getLogger(BaseServiceImpl.class);
	
	/**
	 * DAO层
	 */
	@Resource
	protected IViewerDao iViewerDao;
	
	@Resource
	protected IMysqlDao iMysqlDao;
	
	@Resource
	protected IBasePoDao iBasePoDao;
	
	/**
	 * 根据唯一主键获取PO对象
	 * @param clsPO
	 * @param key主键值
	 * @return
	 */
	@Override
	public <PO> PO getPO(Class<PO> clsPO, Serializable key) {
		// TODO Auto-generated method stub
		return  iBasePoDao.get(clsPO, key);
	}
	
	/**
	 * 根据唯一主键获取VO对象，一般用于页面查询
	 * @param clsPO
	 * @param clsVO
	 * @param key
	 * @return
	 */
	@Override
	public <PO, VO> VO getVO(Class<PO> clsPO, Class<VO> clsVO, Serializable key) {
		// TODO Auto-generated method stub
		return iViewerDao.get(clsPO, clsVO, key);
	}
	
	/**
	 * 带分页的VO数据
	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
	 * @param params hql语句中参数名称及参数值
	 * @param currentPage 要查询的页码
	 * @param map 用于存储pager供前端使用
	 * @return
	 */
	@Override
	public <VO, PO> List<VO> pagerList(String hql, Map<String, Object> params, 
			int currentPage, Integer pageSize, ModelMap map, Class<PO> clsPO, Class<VO> clsVO){
		Long rowCount = iBasePoDao.count(GenHqlUtil.genCountRowsHql(hql), params);
		if(rowCount==null){
			return null;
		}
		if(pageSize==null){
			pageSize=AppContext.PAGER_SIZE;
		}
		Pager pager = new Pager(rowCount.intValue(), pageSize, currentPage);
		map.addAttribute(AppContext.PAGER, pager);
		List<PO> list =  iBasePoDao.find(hql, params, pager.getCurrentPage(), pager.getPageSize());
		try {
			List<VO> listVo = VOPOPaserUtil.PO2VO(list, clsPO, clsVO);
			return listVo;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}


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
	@Override
	public <VO, PO> List<VO> pagerListByPo(PO po, ReqParam reqParam, ModelMap map,
			Class<PO> clsPO, Class<VO> clsVO) {
		
		Map<EmDaoResult, Object> retmap= iViewerDao.findByCondition(po, clsPO, clsVO, reqParam);

		Integer rowCount = (Integer)retmap.get(EmDaoResult.ROW_COUNT);
		
		Pager pager = new Pager(rowCount.intValue(), reqParam.getPageSize(), reqParam.getCurrentPage());
		map.addAttribute(AppContext.PAGER, pager);
		return (List<VO>) retmap.get(EmDaoResult.VO_LIST);
	}
	
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
	@Override
	public <VO, PO> List<VO> pagerListByPoA(PO po, ReqParam reqParam, ModelMap map,
			Class<PO> clsPO, Class<VO> clsVO,String relationships) {
		List<RoleVo> rlist = new ArrayList<RoleVo>();
		if(relationships==null){
			return (List<VO>)rlist;
		}
		String[] roles=relationships.split(",");
		String str="";
		for(String role:roles){
			str=str+"'"+role+"',";
		}
		str=str.substring(0,str.length()-1);
		int currPage=0;
		int pageSize=10;
		int startNum=0;
		int endNum=10;
		if (reqParam.getCurrentPage()==null||reqParam.getCurrentPage()==0){
			currPage=0;
		}else{
			currPage=reqParam.getCurrentPage();
		}
		
		if (reqParam.getPageSize()==null){
			pageSize=10;
		}else{
			pageSize=reqParam.getPageSize();
		}
		startNum=currPage*pageSize;

		endNum=currPage*pageSize+pageSize;
		
		String hql="from Role where roleId in ("+str+")";
		List<Role> list=iBasePoDao.getSession().createQuery(hql).list();
		
		List<Role> rlistPo=new ArrayList<Role>();
		for(Role role:list){
			rlistPo.add(role);
			rlistPo=pagerListRecursion(role.getRoleId(),rlistPo);
		}
		rlistPo=removeDuplicate(rlistPo);//去掉重复数据
		
		try {
			rlist=VOPOPaserUtil.PO2VO(rlistPo, Role.class, RoleVo.class);
			Integer rowCount = rlist.size();
			Pager pager = new Pager(rowCount.intValue(), pageSize, currPage);
			map.addAttribute(AppContext.PAGER, pager);
			if(startNum>rowCount){
				startNum=rowCount;
				endNum=rowCount;
			}
			if(endNum>rowCount){
				endNum=rowCount;
			}
			rlist=rlist.subList(startNum, endNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (List<VO>) rlist;
	}
	/*
	 * 递归查询
	 */
	public  List<Role> pagerListRecursion(Integer roleId,List<Role> retlist){
		String hql="from Role where rolePid ="+roleId;
		Query q=iBasePoDao.getSession().createQuery(hql);
		List<Role> list=iBasePoDao.getSession().createQuery(hql).list();
		if(list!=null&&list.size()>0){
			retlist.addAll(list);
		}
		return retlist;
		
	}
	 public   static   List  removeDuplicate(List list)  {
		   for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
		    for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
		      if  (list.get(j).equals(list.get(i)))  {
		        list.remove(j);
		      } 
		    } 
		  } 
		  return list;
		}
	/**
	 * 保存单个对象到数据库
	 * @param po 要保存的PO对象
	 * @return
	 */
	@Override
	public <PO> Serializable save(PO po){
		return iBasePoDao.save(po);
	}
	
	public static void main(String[] args) {
		String ss="df,fd,ret,op";
		String[] roles=ss.split(",");
		String sss="";
		for(String role:roles){
			sss=sss+"'"+role+"',";
		}
		sss=sss.substring(0,sss.length()-1);
		System.out.println(sss);
	}
	/**
	 * 批量删除，根据#分割
	 * @param delIds
	 * @param poCls
	 */
	@Override
	public <PO> void delete(String delIds,Class<PO> poCls){
		iBasePoDao.delete(delIds, poCls);
	}

	/**
	 * 更新指定po到数据库
	 * @param po
	 */
	@Override
	public <PO> void update(PO po){
		iBasePoDao.update(po);
	}
	
	/**
	 * 批量更新PO集合到数据库
	 * @param po
	 */
	public <PO> void updateBatch(List<PO> list){
		if(list!=null && !list.isEmpty()){
			for(PO po : list){
				iBasePoDao.update(po);
			}
		}
	}

	/**
	 * 更新指定po到数据库，如果该po对应数据库不存在，则执行插入操作
	 * @param po
	 */
	@Override
	public <PO> void saveOrUpdate(PO po){
		iBasePoDao.saveOrUpdate(po);
	}

	
	/**
	 * 根据指定HQL语句查询集合，带参数
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public <PO> List<PO> find(String hql, Map<String, Object> params){
		return iBasePoDao.find(hql, params);
	}

	/**
	 * 查询所有
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public <PO> List<PO> findAll(Class<PO> cls){
		String hql = "FROM "+cls.getName();
		return iBasePoDao.find(hql);
	}
	/**
	 * 查询所有
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public <PO> List<PO> findForAuth(String hql, Map<String, Object> params,String tableName){
		return iBasePoDao.find(hql, tableName,params);
	}
	/**
	 * 查询所有
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public <PO, VO> List<VO> findAll(Class<PO> clsPo, Class<VO> clsVo){
		String hql = "FROM "+clsPo.getName();
		return iViewerDao.find(hql, clsPo, clsVo);
	}
	
	/**
	 * 带参数的聚合函数，包括count、sum、max、min、avg等
	 * @param hql
	 * @return
	 */
	@Override
	public Long count(String hql, Map<String, Object> params){
		return iBasePoDao.count(hql, params);
	}
	
	/**
	 * 执行特定hql语句，返回影响记录数
	 * @param hql
	 * @return
	 */
	@Override
	public int executeHql(String hql){
		return iBasePoDao.executeHql(hql);
	}
	
	/**
	 * 根据条件查询所有VO，
	 * @param clsVO
	 * @param clsPO
	 * @return返回所有VO
	 */
	@Override
	public <PO,VO> List<VO> findAllByCondition(PO po,Class<VO> clsVO,Class<PO> clsPO){
		Map<EmDaoResult, Object> retmap= iViewerDao.findAllByCondition(po, clsPO, clsVO);
		return (List<VO>) retmap.get(EmDaoResult.VO_LIST);
	}
	
	@Override
	public <PO> List<PO> findAllByCondition(PO po,Class<PO> clsPO){
		return iBasePoDao.findAllByCondition(po, clsPO);
	}
	@Override
	public  List<Branch> findAllByConditionForBranch(Branch branch){
		return iBasePoDao.findAllByConditionForBranch(branch);
	}
}
