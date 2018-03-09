package com.nantian.atom.generated.service.rolem.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.dao.impl.CommonDao;
import com.nantian.atom.dao.impl.CommonDaoForAuth;
import com.nantian.atom.generated.po.rolem.Branch;
import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.po.UserRole;
import com.nantian.atom.po.UserRoleList;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.CommonService;
import com.nantian.atom.service.impl.UserRoleServiceImpl;
import com.nantian.util.context.comm.AppContext;

/**
 * 用户管理 服务层实现类
 *
 */
@Service("userServiceImpl")
public class UserServiceImpl extends CommonService {
	private @Autowired CommonDao dao;
	private @Autowired CommonDaoForAuth daoForAuth;

	public void deleteByBranchId(String recordId) {
		String hql = "update User t set t.delState = 0 where branchId ="
				+ recordId + "";
		dao.executeHql(hql, null);
	}

	public void updateUser(User user, ModelMap map, HttpServletRequest request) {
		try {
			User vo = getPO(User.class, user.getId());
			String userRole = vo.getUserRole();
			// 在更新之前，对validateParam.getUniqueT所标记的属性对应的值进行唯一性检查
			// if(iValidateParamService.validateUnique(user, validateParam,
			// map)){
			Branch branch = getPO(Branch.class, user.getBranchId()
					.getBranchId());
			user.setBranchId(branch);
			user.setUserRole(userRole);// 可能出现重复角色
			user = (User) dao.merge(user);
			update(user);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			// 判断当前重置的的用户对象是否是session对象
			Integer reLoad = 0;
			User sessionObj = (User) request.getSession().getAttribute("user");
			if (user.getId().equals(sessionObj.getId())) {
				reLoad = 1;
			}
			map.addAttribute("reLoad", reLoad);
			// }
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "更新失败");
		}
	}

	public void deleteUser(Map dataMap, ModelMap map, HttpServletRequest request) {
		try {
			String recordIds = (String) dataMap.get("recordIds");
			User sessionObj = (User) request.getSession().getAttribute("user");
			if ("1".equals(recordIds) || "1#".equals(recordIds)) {
				map.addAttribute(AppContext.FLAG, -1);
				map.addAttribute(AppContext.EXT_MSG, "该用户为超级管理员，不允许删除");
			} else if (recordIds.equals(sessionObj.getId())
					|| recordIds.equals(sessionObj.getId() + "#")) {
				map.addAttribute(AppContext.FLAG, -1);
				map.addAttribute(AppContext.EXT_MSG, "不能删除自己");
			} else {
				delete(recordIds, User.class);
				String userIds = recordIds.replace("#", ",");

				userIds = userIds.substring(0, userIds.length() - 1);
				String hql = "DELETE UserRole t WHERE t.userId IN(" + userIds
						+ ")";
				executeHql(hql, null);
				map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "删除失败");
		}

	}

	public void allocateUserRole(ModelMap map, UserRoleList userRoleList) {
		UserRoleServiceImpl userRoleServiceImpl = GetApplicationContext
				.getBean("userRoleServiceImpl");
		userRoleServiceImpl.deleteByUserId(userRoleList.getUserRoles().get(0)
				.getUserId());
		for (UserRole userRole : userRoleList.getUserRoles()) {
			save(userRole);
		}
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}

	public void findAll(ModelMap map) {
		try {
			String hql = "from User";

			List<User> userList = findAllByHql(hql, null);
			// User sessionObj=(User)request.getSession().getAttribute("user");
			// //查找当前操作用户所属机构下的人员
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, userList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
	}

	public String findUserNumberByName(String userName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		List<User> userlist = dao.findAllByHql(
				"From User WHERE userName=:userName", params);
		String userPhone = "";
		if (userlist.size() != 0) {
			userPhone = userlist.get(0).getUserPhone();
		}
		return userPhone;
	}

	public List<User> findByUserNumber(String userNumber) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userNumber", userNumber);
		List<User> userList = dao.findAllByHql(
				"From User WHERE userNumber=:userNumber", params);
		return userList;
	}

	public void findByBranchId(Map dataMap, ModelMap map) {

		String recordId = (String) dataMap.get("recordId");
		if (recordId.isEmpty() || "".equals(recordId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			String hql = "from User ";
			List<User> userList = dao.findAllByHql(hql, null);

			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, userList);
		} else {
			Integer branchId = Integer.parseInt(recordId);
			Map<String, Object> params = new HashMap<String, Object>();
			Branch branch = new Branch();
			branch.setBranchId(branchId);
			String hql = "from User where branchId=:branchId";
			params.put("branchId", branch);
			List<User> userList = dao.findAllByHql(hql, params);

			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, userList);
		}

	}

	public User findByUserId(String userId) {

		User user = dao.get(User.class, Integer.parseInt(userId));
		return user;
	}

	public void resetPassword(HttpServletRequest request, ModelMap map,
			Map<String, Object> dataMap) {
		String recordIds = (String) dataMap.get("recordIds");
		User user = (User) request.getSession().getAttribute("user");
		try {
			String[] ids = recordIds.split("#");
			Integer reLoad = 0;
			for (String id : ids) {

				String hql = "update User t set t.userPwd ='000000' where id ="
						+ id + "";
				dao.executeHql(hql, null);

				// 判断当前重置的的用户对象是否是session对象
				if (id.equals(user.getId().toString())) {
					reLoad = 1;
				}
			}
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.EXT_MSG, "密码已重置为6个0!");
			map.addAttribute("reLoad", reLoad);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "重置密码失败!");
		}

	}

	public void changePassword(HttpServletRequest request, ModelMap map,
			Map<String, Object> dataMap) {
		String userPwd = (String) dataMap.get("recordId");

		User user = (User) request.getSession().getAttribute("user");

		try {
			// 获取当前登陆用户
			user.setUserPwd(userPwd);
			dao.update(user);

			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "修改密码失败!");
		}

	}
}
