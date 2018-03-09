/*package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.po.Transaction;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.ITransactionService;
import com.nantian.atom.service.impl.TransactionServiceImpl;
import com.nantian.atom.vo.TransactionVO;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/trans")
public class TransactionController extends BaseController {
	@Autowired private ITransactionService iTransactionService;
	
	 * 分页查询
	 
	@RequestMapping(value="/pagerTrans.html", method=RequestMethod.POST)
	public String pagerTrans(ModelMap map){
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			ITransactionService iTransactionService=GetApplicationContext.getBeanByPermission(TransactionServiceImpl.class);
			list = iTransactionService.findAll(Transaction.class);
			
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
			
		} catch (Exception e) {
			e.printStackTrace();
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
	
	 * 根据条件查询
	 
	@RequestMapping(value="/pagerTransByWhere.html", method=RequestMethod.POST)
	public String pagerTransByWhere(ModelMap map,Transaction info,ReqParam reqParam){
		List<TransactionVO> list = new ArrayList<TransactionVO>();
		try {
			ITransactionService iTransactionService=GetApplicationContext.getBeanByPermission(TransactionServiceImpl.class);
			list = iTransactionService.pagerListByPo(info, reqParam, map, Transaction.class, TransactionVO.class);
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
	 
	@RequestMapping(value="/queryTransById.html", method=RequestMethod.POST)
	public String queryTransById(ModelMap map,String recordId){
		log.debug("请求参数 - recordId["+recordId+"]");
		Transaction transaction = iTransactionService.getPO(Transaction.class, Integer.parseInt(recordId));
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, transaction);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 新增模块
	 
	@RequestMapping(value="/addTrans.html", method=RequestMethod.POST)
	public String addTrans(ModelMap map,Transaction transaction){
		log.debug("新增模块请求参数 - addRole["+transaction+"]");
		ITransactionService iTransactionService=GetApplicationContext.getBeanByPermission(TransactionServiceImpl.class);
		iTransactionService.save(transaction);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 更新模块
	 
	@RequestMapping(value="/updateTrans.html", method=RequestMethod.POST)
	public String updateTrans(ModelMap map,Transaction transaction){
		log.debug("修改模块请求参数 - updateRole["+transaction+"]");
		ITransactionService iTransactionService=GetApplicationContext.getBeanByPermission(TransactionServiceImpl.class);
		iTransactionService.update(transaction);
		
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 删除模块
	 
	@RequestMapping(value="/deleTrans.html", method=RequestMethod.POST)
	public String deleTrans(ModelMap map,String recordIds){
		log.debug("删除模块");
		ITransactionService iTransactionService=GetApplicationContext.getBeanByPermission(TransactionServiceImpl.class);
		iTransactionService.delete(recordIds, Transaction.class);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}

}
*/