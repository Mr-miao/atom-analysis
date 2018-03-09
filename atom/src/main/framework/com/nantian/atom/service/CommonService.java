package com.nantian.atom.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.dao.impl.CommonDao;
import com.nantian.atom.dao.impl.CommonDaoForAuth;
import com.nantian.atom.dao.impl.EmDaoResult;
import com.nantian.atom.po.BaseInterfaceVO;
import com.nantian.atom.po.BaseVO;
import com.nantian.atom.po.Opt;
import com.nantian.atom.util.DynamicDataSource;
import com.nantian.util.context.comm.AppContext;
import com.nantian.util.str.SysConfigPropertiesUtil;
import com.xr.util.db.dao.impl.GenHqlUtil;
import com.xr.util.db.dao.impl.Pager;

@Service("commonService")
public class CommonService {
	protected final static Logger log = LogManager.getLogger(CommonService.class);
	/**
	 * DAO层
	 */
	
	@Resource
	protected CommonDao commonDao;
	@Resource
	protected CommonDaoForAuth commonDaoForAuth;
	/*
	 * 单条数据查询(PO)，根据主键
	 */
	public<PO> PO getPO(Class clsPO, Serializable key) {
		// TODO Auto-generated method stub
		return  (PO) commonDao.get(clsPO, key);
	}
	/*
	 * 单条数据查询(PO)，根据主键,用于界面查询
	 */
	public void findById(Class clsPO, Serializable key,ModelMap map) throws Exception {
		System.out.println("class-----"+clsPO.getName()+",key---"+key);
		if(key!=null){
			BaseInterfaceVO retobj= (BaseInterfaceVO)commonDao.get(clsPO, key);
			
			if(retobj!=null){
				Map formatMap=retobj.formatValue(retobj);
				map.put(AppContext.DATA, BaseVO.po2vo(retobj, formatMap));
				map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			}
		}



	}
	/*
	 * 批量删除（po）
	 */
	public < PO extends BaseInterfaceVO> void delete(String delIds,Class<PO> poCls){
		commonDao.delete(delIds, poCls);
	}
	/*
	 * 批量删除（po）
	 */
	public < PO extends BaseInterfaceVO> void deleteForPage(Class<PO> poCls,ModelMap map,Map dataMap){
		String delIds=(String) dataMap.get("recordIds");
		commonDao.delete(delIds, poCls);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}
	/*
	 * 统计数目
	 */
	public Long count(String hql, Map<String, Object> params){
		return commonDao.count(hql, params);
	}
	/*
	 * 单条更新
	 */
	public < PO extends BaseInterfaceVO> void update(PO po){
		commonDao.update(po);
	}
	/*
	 * 单条更新
	 */
	public < PO extends BaseInterfaceVO> void updateForPage(PO po,ModelMap map){
		commonDao.update(po);
		
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}
	/*
	 * 单条新增数据（PO）
	 */
	public < PO extends BaseInterfaceVO> PO save(PO po){
		
		return (PO) commonDao.save(po);
	}
	/*
	 * 单条新增数据（PO）
	 */
	public < PO extends BaseInterfaceVO> void saveForPage(PO po,ModelMap map){
		
		 commonDao.save(po);
		 map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}
	public boolean validPower(String req_url){
		List<Integer> roleId=DynamicDataSource.getRoleId();
		String temp = "(";
		for (int i=0;i<roleId.size();i++) {
			if(i==roleId.size()-1){
				temp += roleId.get(i)+")";
			}else{
				temp += roleId.get(i) + ",";
			}
		}
		String optSql="from Opt where reqUrl='"+req_url+"'";
		List<Opt> optList=findAllByHql(optSql, null);
		
		if(optList.size()==1 ){//请求地址存在
			Opt opt=optList.get(0);
			String powSql="";
			if(opt.getOptState()==1){//需要权限管控
				String sql="select 1 from t_opt_role t,t_opt o where t.opt_id=o.id and  t.role_id IN"+temp+" and o.REQ_URL='"+req_url+"'";
				List list=commonDao.findAllBySql(sql, null);
				return list.size()>0?true:false;
			}else{
				return true;
			}
		}else{//请求地址不存在
			return false;
		}
		
		
		
		
	}
	/**
	 * 带分页的hql查询数据
	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
	 * @param params hql语句中参数名称及参数值
	 * @param reqParam 分页信息
	 * @return
	 */

	public <  PO extends BaseInterfaceVO> Map findPageByHql(String hql, Map<String, Object> params, 
			ReqParam reqParam){
		Long rowCount = commonDao.count(GenHqlUtil.genCountRowsHql(hql), params);
		Map retmap=new HashMap<String, Object>();
		Integer pageSize=10;//默认10条
		Integer currentPage=0;//默认当前页为第一页
		if(rowCount==null){
			return null;
		}
		if(reqParam!=null&&reqParam.getPageSize()==null){
			pageSize=reqParam.getPageSize();
		}
		if(reqParam!=null&&reqParam.getCurrentPage()==null){
			currentPage=reqParam.getCurrentPage();
		}
		Pager pager = new Pager(rowCount.intValue(), pageSize, currentPage);
		retmap.put(AppContext.PAGER, pager);
		List<PO> list =  commonDao.findPageByHql(hql, params,reqParam);
		retmap.put(AppContext.DATA, list);
		return retmap;
	}
	/*
	 * 查询hql结果
	 */
	public List findAllByHql(String hql,Map<String, Object> params){

		List l = commonDao.findAllByHql(hql, params);
		return l;
	}
	
	
	
	
	
	
	/**
	 * 带分页的hql查询数据
	 * @param whereHql 查询 条件语句：形如 FROM HenAuthUser WHERE 条件1=:name1 and 条件2=:name2
	 * @param params hql语句中参数名称及参数值
	 * @param reqParam 分页信息
	 * @return
	 */

	public < PO extends BaseInterfaceVO > Map findPageBySql(String sql, Map<String, Object> params, 
			ReqParam reqParam){
		/*
		 * 没有测试sql是否可以     2017-09-20
		 */
		Long rowCount = commonDao.count(GenHqlUtil.genCountRowsHql(sql), params);
		Map retmap=new HashMap<String, Object>();
		Integer pageSize=10;//默认10条
		Integer currentPage=0;//默认当前页为第一页
		if(rowCount==null){
			return null;
		}
		if(reqParam!=null&&reqParam.getPageSize()==null){
			pageSize=reqParam.getPageSize();
		}
		if(reqParam!=null&&reqParam.getCurrentPage()==null){
			currentPage=reqParam.getCurrentPage();
		}
		Pager pager = new Pager(rowCount.intValue(), pageSize, currentPage);
		retmap.put(AppContext.PAGER, pager);
		List<PO> list =  commonDao.findPageBySql(sql, params,reqParam);
		retmap.put(AppContext.DATA, list);
		return retmap;
	}
	/*
	 * 查询hql结果
	 */
	public List findAllBySql(String sql,Map<String, Object> params){

		List list = commonDao.findAllBySql(sql, params);
		return list;
	}
	/*
	 * 通用分页查询
	 */
	public<PO extends BaseInterfaceVO > void pagerListByPo(PO po, ReqParam reqParam, ModelMap map) throws Exception {
//	public void pagerListByPo(Object po, ReqParam reqParam, ModelMap map) throws Exception {
		Map retmap=null;
		if(SysConfigPropertiesUtil.isDataAuth(po.getClass().getSimpleName())){//需要数据权限
			retmap= commonDaoForAuth.findPageByCondition(po,reqParam);
		}else{ 
			retmap= commonDao.findPageByCondition(po,reqParam);
		}

		Integer rowCount = (Integer)retmap.get(EmDaoResult.ROW_COUNT);
		Integer pageSize=10;
		Integer currPage=1;
		if(reqParam!=null&&reqParam.getPageSize()!=null){
			pageSize=reqParam.getPageSize();
		}
		if(reqParam!=null&&reqParam.getCurrentPage()!=null){
			currPage=reqParam.getCurrentPage();
		}
		Pager pager = new Pager(rowCount.intValue(), reqParam.getPageSize(), reqParam.getCurrentPage());
		map.addAttribute(AppContext.PAGER, pager);
		
		List<PO> list=	(List<PO>) retmap.get(EmDaoResult.VO_LIST);

		
		map.put(AppContext.DATA, BaseVO.po2voForList(list,po.getClass()));
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}
	public <PO> List<PO> findAllByCondition(PO po){
		return commonDao.findAllByCondition(po);
	}
	public <PO> Map<EmDaoResult, Object> pageByCondition(PO po,ReqParam reqParam){
		return commonDao.findPageByCondition(po, reqParam);
	}


	/*
	 * 执行hql语句，更新，新增、删除等操作
	 */
	public int executeHql(String hql,Map<String, Object> params){
		return commonDao.executeHql(hql,params);
	}

	/*
	 * 执行颠三倒Sql语句，更新，新增、删除等操作
	 */
	public int executeSql(String sql,Map<String, Object> params){
		return commonDao.executeSql(sql, params);
	}

}
