/*package com.nantian.atom.web;

import java.lang.reflect.Array;
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
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.IModuleService;
import com.nantian.atom.service.IOptRoleService;
import com.nantian.atom.service.IOptService;
import com.nantian.atom.service.ITransactionService;
import com.nantian.atom.service.impl.ModuleServiceImpl;
import com.nantian.atom.service.impl.OptRoleServiceImpl;
import com.nantian.atom.service.impl.OptServiceImpl;
import com.nantian.atom.service.impl.TransactionServiceImpl;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController{
	@Autowired private IModuleService iModuleService;
	
	 * 分页查询
	 
	@RequestMapping(value="/pagerModual.html", method=RequestMethod.POST)
	public String pagerModual(HttpServletRequest request,ModelMap map,String pRole){
		IModuleService iModuleService=GetApplicationContext.getBeanByPermission(ModuleServiceImpl.class);
		ITransactionService iTransactionService = GetApplicationContext.getBeanByPermission(TransactionServiceImpl.class);
		IOptService iOptService = GetApplicationContext.getBeanByPermission(OptServiceImpl.class);
		IOptRoleService iOptRoleService = GetApplicationContext.getBeanByPermission(OptRoleServiceImpl.class);
		//获取到上级角色id,多个时以英文逗号分隔
//		if(pRole==null){//null表示新增，非null表示编辑
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			pRole = user.getUserRole();
//		}
		if(pRole.equals("0")){
			List<Module> modules = iModuleService.findAll(Module.class);
			List<Transaction> transactions = iTransactionService.findAll(Transaction.class);
			List<Opt> opts = iOptService.findAll(Opt.class);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute("moduleList", modules);
			map.addAttribute("transList", transactions);
			map.addAttribute("optList", opts);
			return AppContext.JSON_VIEW;
			
		}
		String[] pRoles = pRole.split(",");
		List<Integer> roleIdList = new ArrayList<Integer>();
		for (String pRoleId : pRoles) {
			roleIdList.add(Integer.parseInt(pRoleId));
		}
		
		try {
			List<Integer> optIdList = iOptRoleService.findOptIdsByRoleID(roleIdList);
			
			List<Integer> transIdList = iOptService.findTransIdsByOpt(optIdList);
			List<Opt> optList = iOptService.findOptsByIds(optIdList);
			List<Transaction> transList = iTransactionService.findTransByIds(transIdList);
			List<Integer> moduleIdList = new ArrayList<Integer>();
			for (Transaction trans : transList) {
				moduleIdList.add(trans.getModuleId());
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
			map.addAttribute("optList", optList);
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
	
	
	 * 根据id查询
	 
	@RequestMapping(value="/queryModuleById.html", method=RequestMethod.POST)
	public String queryModualById(ModelMap map,String recordId){
		log.debug("请求参数 - recordId["+recordId+"]");
		Module module = iModuleService.getPO(Module.class, Integer.parseInt(recordId));
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, module);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 新增模块
	 
	@RequestMapping(value="/addModule.html", method=RequestMethod.POST)
	public String addModual(ModelMap map,Module module){
		log.debug("新增模块请求参数 - addRole["+module+"]");
		IModuleService iModuleService=GetApplicationContext.getBeanByPermission(ModuleServiceImpl.class);
		iModuleService.save(module);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 更新模块
	 
	@RequestMapping(value="/updateModule.html", method=RequestMethod.POST)
	public String updateModual(ModelMap map,Module module){
		log.debug("修改模块请求参数 - updateRole["+module+"]");
		IModuleService iModuleService=GetApplicationContext.getBeanByPermission(ModuleServiceImpl.class);
		iModuleService.update(module);
		
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 删除模块
	 
	@RequestMapping(value="/deleModule.html", method=RequestMethod.POST)
	public String deleModual(ModelMap map,String recordIds){
		log.debug("删除模块");
		IModuleService iModuleService=GetApplicationContext.getBeanByPermission(ModuleServiceImpl.class);
		iModuleService.delete(recordIds, Module.class);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
}
*/