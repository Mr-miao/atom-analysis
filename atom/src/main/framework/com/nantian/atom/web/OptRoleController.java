/*package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.po.OptRole;
import com.nantian.atom.po.OptRoleList;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.IOptRoleService;
import com.nantian.atom.service.impl.OptRoleServiceImpl;
import com.nantian.atom.vo.OptRoleVo;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/optRole")
public class OptRoleController extends BaseController {
	@Autowired private IOptRoleService iOptRoleService;
	
	 * 分页查询
	 
	@RequestMapping(value="/listOptRole.html", method=RequestMethod.POST)
	public String listOptRole(ModelMap map,Integer roleId){
		List<OptRole> list = new ArrayList<OptRole>();
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("roleId", roleId);
		try {
			IOptRoleService iOptRoleService=GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
			list = iOptRoleService.find("FROM OptRole WHERE roleId=:roleId", reqParam);
			
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
	 
	@RequestMapping(value="/queryOptRoleById.html", method=RequestMethod.POST)
	public String queryOptRoleById(ModelMap map,String recordId){
		log.debug("请求参数 - recordId["+recordId+"]");
		OptRole optRole = iOptRoleService.getPO(OptRole.class, recordId);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, optRole);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 新增权限操作
	 
	@RequestMapping(value="/addOptRole.html", method=RequestMethod.POST)
	public String addOptRole(ModelMap map,OptRole optRole){
		log.debug("新增模块请求参数 - addRole["+optRole+"]");
		IOptRoleService iOptRoleService=GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
		iOptRoleService.save(optRole);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 更新权限操作
	 
	@RequestMapping(value="/updateOptRole.html", method=RequestMethod.POST)
	public String updateOptRole(ModelMap map,OptRole optRole){
		log.debug("修改模块请求参数 - updateRole["+optRole+"]");
		IOptRoleService iOptRoleService=GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
		iOptRoleService.update(optRole);
		
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 删除权限操作
	 
	@RequestMapping(value="/deleOptRole.html", method=RequestMethod.POST)
	public String deleOptRole(ModelMap map,String recordIds){
		log.debug("删除权限操作");
		IOptRoleService iOptRoleService=GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
		iOptRoleService.delete(recordIds, OptRole.class);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 批量增加权限操作
	 
	@RequestMapping(value="/batchInsertOptRole.html", method=RequestMethod.POST)
	public String batchInsertOptRole(ModelMap map, OptRoleList optRoleList){
		log.debug("批量增加权限操作");
		List<OptRole> list = optRoleList.getOptRoles();
		int size = list.size();
		OptRole[] optRoles = new OptRole[size];
		list.toArray(optRoles);
		IOptRoleService iOptRoleService=GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
		//批量添加
		iOptRoleService.batchInsertOptRole(optRoles);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	@RequestMapping(value="/pagerOptRole.html", method=RequestMethod.POST)
	public String pagerOptRole(ModelMap map, OptRole optRole,ReqParam reqParam){
		IOptRoleService iOptRoleService=GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
		try {
			List<OptRoleVo> list = iOptRoleService.pagerListByPo(optRole, reqParam, map, OptRole.class, OptRoleVo.class);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(AppContext.FLAG, -1);
		}
		return AppContext.JSON_VIEW;
	}
}
*/