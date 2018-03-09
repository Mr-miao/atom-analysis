package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.po.Module;
import com.nantian.atom.po.Opt;
import com.nantian.atom.po.Transaction;
import com.nantian.atom.service.impl.ModuleServiceImpl;
import com.nantian.atom.service.impl.OptRoleServiceImpl;
import com.nantian.atom.service.impl.OptServiceImpl;
import com.nantian.atom.service.impl.TransactionServiceImpl;
import com.nantian.atom.service.impl.UserRoleServiceImpl;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/_menu")
public class MenuController {
	@Autowired private ModuleServiceImpl iModuleService;
	@Autowired private TransactionServiceImpl iTransactionService;
	@Autowired private UserRoleServiceImpl iUserRoleService;
	@Autowired private OptRoleServiceImpl iOptRoleService;
	@Autowired private OptServiceImpl iOptService;
	/*
	 * 查询所有导航菜单项
	 */
	@RequestMapping(value="/findAllMenu.html", method=RequestMethod.POST)
	public String pagerModual(HttpServletRequest request,ModelMap map){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		try {
			List<Integer> roleIdList = iUserRoleService.findRoleIdsByUserId(user.getId());
			List<Integer> optIdList = iOptRoleService.findOptIdsByRoleID(roleIdList);
			
			List<Integer> transIdList = iOptService.findTransIdsByOpt(optIdList);
			
			List<Transaction> transList = iTransactionService.findTransByIds(transIdList);
			List<Integer> moduleIdList = new ArrayList<Integer>();
			for (Transaction trans : transList) {
				
				if(trans.getModuleId()!=null){
					moduleIdList.add(trans.getModuleId());
				}
//				moduleIdList.add(trans.getModuleId());
			}
			
			List<Module> moduleList = iModuleService.findModuleByIds(moduleIdList,new ArrayList<Module>());
			
			//根据id排序module
			Collections.sort(moduleList, new Comparator<Module>() {

				@Override
				public int compare(Module o1, Module o2) {
					if(o1.getId()>o2.getId()){
						return 1;
					}
					return -1;
				}
			});
			
			//根据id排序tran
			Collections.sort(transList, new Comparator<Transaction>() {

				@Override
				public int compare(Transaction o1, Transaction o2) {
					if(o1.getId()>o2.getId()){
						return 1;
					}
					return -1;
				}
			});
			
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute("moduleList", moduleList);
			map.addAttribute("transList", transList);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
	
	@RequestMapping(value="/genFuncMenu.html", method=RequestMethod.POST)
	public String genFuncMenu(HttpServletRequest request,ModelMap map,String trn){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Integer> roleIds = iUserRoleService.findRoleIdsByUserId(user.getId());
		List<Integer> optIds = iOptRoleService.findOptIdsByRoleID(roleIds);
		List<Opt> optList = iOptRoleService.findOptListByIdsAndSerName(optIds,trn);
		map.addAttribute(AppContext.DATA, optList);
		return AppContext.JSON_VIEW;
	}

}
