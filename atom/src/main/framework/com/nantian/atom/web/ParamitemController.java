/*package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.po.Paramitem;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.IParamitemService;
import com.nantian.atom.service.impl.ParamitemServiceImpl;
import com.nantian.atom.vo.ParamitemVO;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/paramitem")
public class ParamitemController extends BaseController{

	*//**
	 * 访问该增删改查页面
	 * @return
	 *//*
	@RequestMapping(value="/toParamitem.html", method=RequestMethod.GET)
	public String toParamitem(){
		return "generated/paramitem";
	}
	*//**
	 * 分页查询
	 * @param request
	 * @param map
	 * @param student
	 * @param currentPage
	 * @return
	 *//*
	@RequestMapping(value="/_pagerParamitem.html", method=RequestMethod.POST)
	public String pagerParamitem(HttpServletRequest request, ModelMap map,Paramitem info,ReqParam reqParam){
		List<ParamitemVO> list = new ArrayList<ParamitemVO>();
		try {
			IParamitemService iParamitemService=GetApplicationContext.getBeanByPermission(ParamitemServiceImpl.class);
			
			list = iParamitemService.pagerListByPo(info, reqParam, map, Paramitem.class, ParamitemVO.class);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
		} catch (Exception e) {
			log.error(null, e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
	*//**
	 * 查询单条信息
	 * @param request
	 * @param map
	 * @param recordId
	 * @return
	 *//*
	@RequestMapping(value="/_queryParamitemById.html", method=RequestMethod.POST)
	public String queryParamitemById(HttpServletRequest request, ModelMap map, String recordId){
		IParamitemService iParamitemService=GetApplicationContext.getBeanByPermission(ParamitemServiceImpl.class);
		ParamitemVO vo = iParamitemService.getVO(Paramitem.class, ParamitemVO.class, Integer.parseInt(recordId));
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, vo);
		return AppContext.JSON_VIEW;	
	}
	
	*//**
	 * 单笔添加
	 * @param map
	 * @param info
	 * @return
	 *//*
	@RequestMapping(value="/_addParamitem.html", method=RequestMethod.POST)
	public String addParamitem(ModelMap map,Paramitem info){
		IParamitemService iParamitemService=GetApplicationContext.getBeanByPermission(ParamitemServiceImpl.class);
		try {
			iParamitemService.save(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
	
	
	*//**
	 * 单笔修改
	 * @param map
	 * @param info
	 * @return
	 *//*
	@RequestMapping(value="/_updateParamitem.html", method=RequestMethod.POST)
	public String updateParamitem(ModelMap map,Paramitem info){
		log.debug("修改设备请求参数 - updateParamitem["+info+"]");
		IParamitemService iParamitemService=GetApplicationContext.getBeanByPermission(ParamitemServiceImpl.class);
		try {
			iParamitemService.update(info);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		} catch (Exception e) {
			log.error(null, e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "更新失败");
		}
		return AppContext.JSON_VIEW;
	}
	
	 * 删除设备信息
	 * 
	@RequestMapping(value="/_deleParamitem.html", method=RequestMethod.POST)
	public String deleteParamitem(HttpServletRequest request,ModelMap map,String recordIds){
		IParamitemService iParamitemService=GetApplicationContext.getBeanByPermission(ParamitemServiceImpl.class);
		try {
			iParamitemService.delete(recordIds, Paramitem.class);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		} catch (Exception e) {
			log.error(null, e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "删除失败");
		}
		return AppContext.JSON_VIEW;
	}
	 
	
}
*/