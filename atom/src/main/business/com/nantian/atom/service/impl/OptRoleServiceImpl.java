package com.nantian.atom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.po.Opt;
import com.nantian.atom.po.OptRole;
import com.nantian.atom.service.CommonService;
import com.nantian.util.context.comm.AppContext;

@Service("optRoleServiceImpl")
public class OptRoleServiceImpl extends CommonService {
	public void batchInsertOptRole(OptRole[] optRoles){
		
		
		
		
		//批量添加权限操作前先删除该角色所有权限操作信息
		Integer key = optRoles[0].getRoleId();
		//删除
		deleteByRoleId(key);
		int size = optRoles.length;
		String sqls[] = new String[size];
		for (int i=0;i<size;i++) {
			int optId = optRoles[i].getOptId();
			int roleId = optRoles[i].getRoleId();
			String back1 = optRoles[i].getOptRoleBack1();
			String back2 = optRoles[i].getOptRoleBack2();
			String back3 = optRoles[i].getOptRoleBack3();
			OptRole optRole=new OptRole();
			optRole.setOptId(optId);
			optRole.setRoleId(roleId);
			optRole.setOptRoleBack1(back1);
			optRole.setOptRoleBack2(back2);
			optRole.setOptRoleBack3(back3);
			save(optRole);
/*			String sql = "INSERT INTO t_opt_role(opt_id,role_id,back1,back2,back3) values(";
			sql += optId+","+roleId+","+"'"+back1+"',"+"'"+back2+"',"+"'"+back3+"')";
			sqls[i] = sql;*/
		}
//		return mysqlDao.batchInsert(sqls);
	}
	public void listOptRole(Map dataMap,ModelMap map){
		String roleId=(String) dataMap.get("roleId");
		List<OptRole> list = new ArrayList<OptRole>();
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("roleId", Integer.parseInt(roleId));
		try {
			list = findAllByHql("FROM OptRole WHERE roleId=:roleId", reqParam);
			
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
	}
	public void deleteByRoleId(Integer roleId){
		String sql = "SELECT id from t_opt_role WHERE role_id="+roleId;
		
		List<Integer> ids = findAllBySql(sql, null);
		if(ids==null||ids.isEmpty()){
			return;
		}
		String key = "";
		for(int i=0;i<ids.size();i++){
			key += ids.get(i)+"#";
		}
		this.delete(key, OptRole.class);
	}

	public List<Integer> findOptIdsByRoleID(List<Integer> roleIdList) {
		if(roleIdList==null||roleIdList.isEmpty()){
			return null;
		}
		String hql = "SELECT DISTINCT optId FROM OptRole WHERE ";
		for(int i=0;i<roleIdList.size();i++){
			if(i==roleIdList.size()-1){
				hql += "roleId="+roleIdList.get(i);
			}else{
				hql += "roleId="+roleIdList.get(i)+" OR ";
			}
		}
		List<Integer> list = findAllByHql(hql, null);
		return list;
	}
	
	public List<Opt> findOptListByIdsAndSerName(List<Integer> ids,String serName){
		if(ids==null||ids.isEmpty()){
			return null;
		}
		
		String hql = " select  o  FROM Opt o,Transaction t WHERE o.optServerId=t.id and t.serEnName=:optServerName AND o.optId IN (";
		
		for (int i=0; i<ids.size();i++) {
			if(i==ids.size()-1){
				hql += ids.get(i)+") ";
			}else{
				hql += ids.get(i)+",";
			}
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("optServerName", serName);
		List<Opt> optList=new ArrayList<Opt>();
/*		if("mediaAudio".equals(serName)||"mediaVideo".equals(serName)||"mediaImage".equals(serName)){
			List<String> lists=find(hql, map);
			
			for(String methodName:lists){
				Opt opt=new Opt();
				opt.setOptMethod(methodName);
				optList.add(opt);
			}
		}else{*/
		
			optList=findAllByHql(hql, map);
		/*}*/

		
		return optList;
	}
	
	
	public List<Integer> findTransIdsByOptIds(List<Integer> ids){
		if(ids==null||ids.isEmpty()){
			return null;
		}
		String hql = "SELECT DISTINCT optServerId FROM OptRole WHERE ";
		for (int i=0; i<ids.size();i++) {
			if(i==ids.size()-1){
				hql += ids.get(i)+") ";
			}else{
				hql += ids.get(i)+",";
			}
		}
		return findAllByHql(hql, null);
	}

}
