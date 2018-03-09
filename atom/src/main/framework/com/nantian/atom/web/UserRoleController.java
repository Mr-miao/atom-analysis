/*package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.po.UserRole;
import com.nantian.atom.po.UserRoleList;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.IUserRoleService;
import com.nantian.atom.service.impl.UserRoleServiceImpl;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/userRole")
public class UserRoleController extends BaseController{
	
	 * 分页查询
	 
	
	@RequestMapping(value="/listUserRole.html", method=RequestMethod.POST)
	public String listUserRole(ModelMap map){
		List<UserRole> list = new ArrayList<UserRole>();
		try {
			IUserRoleService iUserRoleService=GetApplicationContext.getBeanByPermission(UserRoleServiceImpl.class);
			list = iUserRoleService.findAll(UserRole.class);
			
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
	
	 * 根据id查询
	 
	@RequestMapping(value="/queryUserRoleById.html", method=RequestMethod.POST)
	public String queryUserRoleById(ModelMap map,Integer recordId){
		log.debug("请求参数 - recordId["+recordId+"]");
		IUserRoleService iUserRoleService=GetApplicationContext.getBeanByPermission(UserRoleServiceImpl.class);
		String hql="FROM UserRole WHERE userId=:userId";
	    Map<String,Object> reqmap=new HashMap<String,Object>();
	    reqmap.put("userId", recordId);
	    List<UserRole> userRoles=iUserRoleService.find(hql,reqmap);
	    String roleIds ="";
	    if(userRoles.size()!=0){
	    	for(int i=0;i<userRoles.size();i++){
		    	  roleIds+=userRoles.get(i).getRoleId().toString()+",";
		    }
		    roleIds=roleIds.substring(0, roleIds.length()-1);
		    String hql1="update User t set t.userRole ='"+roleIds+"'  where userId ="+recordId+"";  
		    iUserRoleService.executeHql(hql1);
	    }
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, userRoles);
		return AppContext.JSON_VIEW;
	}
	*//**
	/*
	 * 新增权限操作
	 *//*
	@RequestMapping(value="/addUserRole.html", method=RequestMethod.POST)
	public String addOptRole(ModelMap map,UserRole userRole){
		log.debug("新增模块请求参数 - addRole["+userRole+"]");
		IUserRoleService iUserRoleService=GetApplicationContext.getBeanByPermission(UserRoleServiceImpl.class);
		iUserRoleService.save(userRole);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	*//**
	 * 分配权限操作
	 * *//*
	@RequestMapping(value="/allocateUserRole.html", method=RequestMethod.POST)
	public String allocateOptRole(ModelMap map,UserRoleList userRoleList){
		log.debug("权限分配请求参数 - addRole["+userRoleList+"]");
		IUserRoleService iUserRoleService=GetApplicationContext.getBeanByPermission(UserRoleServiceImpl.class);
		iUserRoleService.allocateUserRole(userRoleList);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
}
*/