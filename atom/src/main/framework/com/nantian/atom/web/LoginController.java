package com.nantian.atom.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.service.impl.LoginServiceImpl;
import com.nantian.atom.util.DynamicDataSource;
import com.nantian.util.context.comm.AppContext;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Autowired private LoginServiceImpl  iLoginService;
	
	//接收页面请求
	@RequestMapping(value="/loginValid.html", method=RequestMethod.POST)
	public String queryUserInfo(HttpServletRequest request,String userLoad,String userPwd,ModelMap map){
		//清除ThreadLocal
		DynamicDataSource.clear();
		//获取查询数据库表的sql
		List<User> userList=iLoginService.findUserByNameAndPwd(userLoad, userPwd);
        if(userList!=null&&userList.size()>0){
        	HttpSession session = request.getSession();
        	session.setAttribute("user", userList.get(0));
        	map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
        }else{
        	map.addAttribute(AppContext.FLAG, -1);
        }
       
		return AppContext.JSON_VIEW;
	}

}
