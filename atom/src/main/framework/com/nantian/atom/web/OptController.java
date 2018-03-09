/*package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.po.Opt;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.IOptService;
import com.nantian.atom.service.impl.OptServiceImpl;
import com.nantian.atom.vo.OptVO;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/opt")
public class OptController extends BaseController {
	@Autowired private IOptService iOptService;
	
	 * 分页查询
	 
	@RequestMapping(value="/pagerOpt.html", method=RequestMethod.POST)
	public String pagerOpt(ModelMap map){
		List<Opt> list = new ArrayList<Opt>();
		try {
			
			IOptService iOptService=GetApplicationContext.getBeanByPermission(OptServiceImpl.class);
			list = iOptService.findAll(Opt.class);
			
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
	 
	@RequestMapping(value="/pagerOptByWhere.html", method=RequestMethod.POST)
	public String pagerOptByWhere(ModelMap map,Opt info,ReqParam reqParam){
		List<OptVO> list = new ArrayList<OptVO>();
		try {
			IOptService iOptService=GetApplicationContext.getBeanByPermission(OptServiceImpl.class);
			list = iOptService.pagerListByPo(info, reqParam, map, Opt.class, OptVO.class);
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
	 
	@RequestMapping(value="/queryOptById.html", method=RequestMethod.POST)
	public String queryOptById(ModelMap map,String recordId){
		log.debug("请求参数 - recordId["+recordId+"]");
		
		Opt opt = iOptService.getPO(Opt.class,Integer.parseInt(recordId));
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, opt);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 新增模块
	 
	@RequestMapping(value="/addOpt.html", method=RequestMethod.POST)
	public String addOpt(ModelMap map,Opt opt){
		log.debug("新增模块请求参数 - addRole["+opt+"]");
		IOptService iOptService=GetApplicationContext.getBeanByPermission(OptServiceImpl.class);
		iOptService.save(opt);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 更新模块
	 
	@RequestMapping(value="/updateOpt.html", method=RequestMethod.POST)
	public String updateOpt(ModelMap map,Opt opt){
		log.debug("修改模块请求参数 - updateRole["+opt+"]");
		IOptService iOptService=GetApplicationContext.getBeanByPermission(OptServiceImpl.class);
		iOptService.update(opt);
		
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	 * 删除模块
	 
	@RequestMapping(value="/deleOpt.html", method=RequestMethod.POST)
	public String deleOpt(ModelMap map,String recordIds){
		log.debug("删除模块");
		IOptService iOptService=GetApplicationContext.getBeanByPermission(OptServiceImpl.class);
		iOptService.delete(recordIds, Opt.class);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
}
*/