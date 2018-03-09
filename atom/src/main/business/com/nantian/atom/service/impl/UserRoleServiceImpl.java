package com.nantian.atom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.dao.IBasePoDao;
import com.nantian.atom.dao.IMysqlDao;
import com.nantian.atom.po.UserRole;
import com.nantian.atom.po.UserRoleList;
import com.nantian.atom.service.CommonService;
import com.nantian.util.context.comm.AppContext;
import com.nantian.util.str.MysqlUtil;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl extends CommonService {
	public List<Integer> findRoleIdsByUserId(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		
		List<Integer> list = findAllByHql(MysqlUtil.QUERY_ROLEID_BY_USERID_HQL, map);
		return list;
	}

	public void queryUserRoleById(Map dataMap,ModelMap map) {
		String srecordId=(String) dataMap.get("recordId");
		Integer recordId=Integer.parseInt(srecordId);
		String hql="FROM UserRole WHERE userId=:userId";
	    Map<String,Object> reqmap=new HashMap<String,Object>();
	    reqmap.put("userId", recordId);
	    List<UserRole> userRoles=findAllByHql(hql,reqmap);
	    String roleIds ="";
	    if(userRoles.size()!=0){
	    	for(int i=0;i<userRoles.size();i++){
		    	  roleIds+=userRoles.get(i).getRoleId().toString()+",";
		    }
		    roleIds=roleIds.substring(0, roleIds.length()-1);
		    String hql1="update User t set t.userRole ='"+roleIds+"'  where id ="+recordId+"";  
		    executeHql(hql1,null);
	    }
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, userRoles);
	}
	 
	public void deleteByUserId(Integer userId){
		String sql = "SELECT id from t_user_role WHERE user_id="+userId;
		List<Integer> ids = findAllBySql(sql, null);
		String key = "";
		if(!(ids.size()>0)){
			return;
		}
		for(int i=0;i<ids.size();i++){
			key += ids.get(i)+"#";
		}
		this.delete(key, UserRole.class);
	}

	public void updateUserRole(UserRole userRole) {
		update(userRole);
	}

	public List<UserRole> findByRoleId(Integer roleId) {
		String hql="from UserRole where roleId=:roleId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<UserRole> userRoleList=findAllByHql(hql, map);
		return userRoleList;
	}

	public void deleteByRoleId(Integer roleId) {
		String sql = "SELECT id from t_user_role WHERE role_id="+roleId;
		List<Integer> ids = findAllBySql(sql, null);
		String key = "";
		if(!(ids.size()>0)){
			return;
		}
		for(int i=0;i<ids.size();i++){
			key += ids.get(i)+"#";
		}
		this.delete(key, UserRole.class);
	}


}