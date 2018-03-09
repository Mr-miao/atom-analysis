package com.nantian.atom.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.framework.IAdvanceQueryService;
import com.nantian.atom.service.framework.impl.AdvanceQueryServiceImpl;
import com.nantian.util.context.comm.AppContext;

/**
 * 高级查询
 * @author xurui
 *
 */
@Controller
@RequestMapping("/_advance")
public class AdvanceQueryController {
	/*
	 * 获取指定表的字段信息
	 * */
	@RequestMapping(value="/_getQueryAdvanceTb.html", method=RequestMethod.POST)
	public String getQueryAdvanceTb(ModelMap map, String tbName){
		AdvanceQueryServiceImpl iAdvanceQueryService = GetApplicationContext.getBean("advanceQueryServiceImpl");
		List list = iAdvanceQueryService.getCumnInfo(tbName);
		map.addAttribute(AppContext.DATA, list);
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		return AppContext.JSON_VIEW;
	}
}
