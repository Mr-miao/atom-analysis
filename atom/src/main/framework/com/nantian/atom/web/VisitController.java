package com.nantian.atom.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nantian.atom.util.DynamicDataSource;

@Controller
public class VisitController extends BaseController{
	@RequestMapping(value="/login.html")
	public String login(){
		DynamicDataSource.clear();
		return "/auth/login";
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		DynamicDataSource.clear();
		return "/auth/login";
	}
	
	@RequestMapping(value="/main.html")
	public String toMain(){
		return "/index";
	}
	//牛超代码测试部分
	@RequestMapping(value="/testTcp")
	public String visitPage1(){
		return "testTcp";
	}
	@RequestMapping(value="/device")
	public String visitPage2(String skip,ModelMap map){
		map.addAttribute("skip", skip);
		return "auth/device";
	}
	@RequestMapping(value="/deviceIndex")
	public String visitPage3(){
		
		return "auth/deviceIndex";
	}
	
}
