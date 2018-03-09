package com.nantian.atom.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.po.Module;
import com.nantian.atom.po.Opt;
import com.nantian.atom.po.Transaction;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.CommonService;
import com.nantian.util.context.comm.AppContext;

@Service("moduleServiceImpl")
public class ModuleServiceImpl extends CommonService {

	public List<Module> findModuleByIds(List<Integer> ids,
			List<Module> resultList) {
		boolean flag = true;
		if (ids == null) {
			return null;
		}
		// 根据交易找到模块
		List<Integer> pidList = new ArrayList<Integer>();
		List<Module> childList = new ArrayList<Module>();
		for (Integer integer : ids) {
			if (this.getPO(Module.class, integer) != null) {
				Module module = getPO(Module.class, integer);
				childList.add(module);
			}
		}
		// 去重
		HashSet<Module> set = new HashSet<Module>(childList);
		childList.clear();
		childList.addAll(set);
		resultList.addAll(childList);
		// 取出id
		for (Module module : childList) {
			if (module != null) {
				pidList.add(module.getModulePid());
			}
		}
		// id去重
		HashSet<Integer> pidset = new HashSet<Integer>(pidList);
		pidList.clear();
		pidList.addAll(pidset);
		for (int i = 0; i < pidList.size(); i++) {
			flag = flag && (pidList.get(i) == 0);
		}
		if (!flag) {
			findModuleByIds(pidList, resultList);
		}
		// module去重
		HashSet<Module> resultSet = new HashSet<Module>(resultList);
		resultList.clear();
		resultList.addAll(resultSet);
		return resultList;
	}

	public void pagerModual(ModelMap map, User user) {

		TransactionServiceImpl iTransactionService = GetApplicationContext
				.getBean("transactionServiceImpl");
		OptServiceImpl iOptService = GetApplicationContext
				.getBean("optServiceImpl");
		OptRoleServiceImpl iOptRoleService = GetApplicationContext
				.getBean("optRoleServiceImpl");
		// 获取到上级角色id,多个时以英文逗号分隔

		String pRole = user.getUserRole();
		if (pRole.equals("0")) {
			String hql = "from Module";
			List<Module> modules = findAllByHql(hql, null);
			hql = "from Transaction";
			List<Transaction> transactions = findAllByHql(hql, null);
			hql = "from Opt";
			List<Opt> opts = findAllByHql(hql, null);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute("moduleList", modules);
			map.addAttribute("transList", transactions);
			map.addAttribute("optList", opts);

		}
		String[] pRoles = pRole.split(",");
		List<Integer> roleIdList = new ArrayList<Integer>();
		for (String pRoleId : pRoles) {
			roleIdList.add(Integer.parseInt(pRoleId));
		}

		try {
			List<Integer> optIdList = iOptRoleService
					.findOptIdsByRoleID(roleIdList);

			List<Integer> transIdList = iOptService
					.findTransIdsByOpt(optIdList);
			List<Opt> optList = iOptService.findOptsByIds(optIdList);
			List<Transaction> transList = iTransactionService
					.findTransByIds(transIdList);
			List<Integer> moduleIdList = new ArrayList<Integer>();
			for (Transaction trans : transList) {
				if(trans.getModuleId()!=null){
					moduleIdList.add(trans.getModuleId());
				}
				
			}

			List<Module> moduleList = findModuleByIds(moduleIdList,
					new ArrayList<Module>());

			// 根据id排序module
			Collections.sort(moduleList, new Comparator<Module>() {

				@Override
				public int compare(Module o1, Module o2) {
					if (o1.getId() > o2.getId()) {
						return 1;
					}
					return -1;
				}
			});

			// 根据id排序tran
			Collections.sort(transList, new Comparator<Transaction>() {

				@Override
				public int compare(Transaction o1, Transaction o2) {
					if (o1.getId() > o2.getId()) {
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
	}
}
