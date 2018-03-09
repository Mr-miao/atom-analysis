package com.nantian.atom.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.service.impl.UserRoleServiceImpl;
import com.nantian.atom.util.DynamicDataSource;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired UserRoleServiceImpl userRoleService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect(request.getContextPath()+"/login.html");
			return false;
		}else{
			User user = (User)session.getAttribute("user");
			DynamicDataSource.setBranchNo(user.getBranchId().getBranchNo());
        	DynamicDataSource.setBranchId(user.getBranchId().getBranchId());
			List<Integer> roleId = userRoleService.findRoleIdsByUserId(user.getId());
        	DynamicDataSource.setRoleId(roleId);
			return true;
		}
		
	}
	

}
