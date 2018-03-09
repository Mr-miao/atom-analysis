/*package com.nantian.atom.web;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.context.ValidateParam;
import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.generated.service.rolem.IUserService;
import com.nantian.atom.generated.service.rolem.impl.UserServiceImpl;
import com.nantian.atom.generated.vo.rolem.UserVO;
import com.nantian.atom.po.OptRole;
import com.nantian.atom.po.OptRoleList;
import com.nantian.atom.po.Role;
import com.nantian.atom.po.UserRole;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.IRoleService;
import com.nantian.atom.service.IUserRoleService;
import com.nantian.atom.service.impl.RoleServiceImpl;
import com.nantian.atom.service.impl.UserRoleServiceImpl;
import com.nantian.atom.vo.RoleVo;
import com.nantian.util.context.comm.AppContext;
import com.nantian.util.str.CollectionUtil;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	@Autowired private IRoleService iRoleService;
	
	 * 角色管理页
	 * 
	@RequestMapping(value="/_visit.html", method=RequestMethod.GET)
	public String getRole(){
		return "auth/rolemanager";
	}
	
	
	 * 分页查询
	 
	@RequestMapping(value="/pagerRole.html", method=RequestMethod.POST)
	public String pagerRole(HttpServletRequest request,ModelMap map,Role role,String currentPage,ReqParam reqParam){
		List<RoleVo> list = new ArrayList<RoleVo>();
		try {
			IRoleService iRoleService=GetApplicationContext.getBeanByPermission(RoleServiceImpl.class);
			//获取当前登陆用户所属角色
			User sessionUser=(User)request.getSession().getAttribute("user");
			String roleRelationships=sessionUser.getUserRole();
			//获取登陆用户所属角色id
//			for(int i=0;i<roleRelationships.length;i++){
				list = iRoleService.pagerListByPoA(role, reqParam, map, Role.class, RoleVo.class,roleRelationships);
//			}
			if(list!=null&&!list.isEmpty()){
				if(list.size()==1){
					map.addAttribute("size", 1);
				}
				for (RoleVo roleVo : list) {
					String pRoleName = "";
					if(roleVo.getRolePid()!=null){
						String[] ids = roleVo.getRolePid().split(",");
						for (int i=0;i<ids.length;i++) {
							Role temp = iRoleService.getPO(Role.class, Integer.parseInt(ids[i]));
							if(temp!=null){
								if(i<ids.length-1){
									pRoleName += temp.getRoleName()+",";
								}else{
									pRoleName += temp.getRoleName();
								}
							}else{
								if(ids[i].equals("0")){
									if(i<ids.length-1){
										pRoleName += "顶级角色"+",";
									}else{
										pRoleName += "顶级角色";
									}
								}else{
									if(i<ids.length-1){
										pRoleName += "未找到角色"+",";
									}else{
										pRoleName += "未找到角色";
									}
								}
							}
						}
					}
					roleVo.setRolePname(pRoleName);
				}
			}
			
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
		} catch (Exception e) {
			log.error("", e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
	
	
	 * 根据id查询
	 
	@RequestMapping(value="/queryRoleById.html", method=RequestMethod.POST)
	public String queryRoleById(HttpServletRequest request,ModelMap map,String recordId,String currentPage){
		log.debug("请求参数 - recordId["+recordId+"]");
		RoleVo vo = iRoleService.getVO(Role.class, RoleVo.class, Integer.parseInt(recordId));
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		if(vo.getRolePid().equals("0")){
			map.addAttribute("rolePId", "0");
			map.addAttribute("roleName", vo.getRoleName());
		}else{
			map.addAttribute("rolePId", "1");
		}
		//查找当前登陆用户
		User sessionObj=(User)request.getSession().getAttribute("user");
		String[] userRoleIds=sessionObj.getUserRole().split(",");
		for(int i=0;i<userRoleIds.length;i++){
			if(vo.getRoleId().toString().equals(userRoleIds[i])){
				map.addAttribute("sessionRole", 1);
				map.addAttribute("sessionRoleName", vo.getRoleName());
			}
		}
		map.addAttribute(AppContext.DATA, vo);
		return AppContext.JSON_VIEW;
	}
	
	 * 根据id查询其角色及其下级角色
	 
	@RequestMapping(value="/queryRoleByIdAndfollow.html", method=RequestMethod.POST)
	public String queryRoleByIdAndfollow(HttpServletRequest request,ModelMap map,String recordId,String currentPage){
		log.debug("请求参数 - recordId["+recordId+"]");
		//RoleVo vo = iRoleService.getVO(Role.class, RoleVo.class, Integer.parseInt(recordId));
		//查找当钱选中用户的角色
		User sessionObj=(User)request.getSession().getAttribute("user");
		//UserVO vo=iuserService.getVO(User.class, UserVO.class,Integer.parseInt(recordId));
		if(sessionObj!=null){
			String[] userRoles=sessionObj.getUserRole().split(","); 
			Set<Role> roleSet=new HashSet<Role>();
			for(int i=0;i<userRoles.length;i++){
				Role role=iRoleService.findById(Integer.parseInt(userRoles[i]));
				//查找当前选中角色的子角色
				List<Role> roleListA=iRoleService.findByPId(userRoles[i]);
				if(role!=null){
					roleListA.add(role);
					for(Role roleA:roleListA){
						roleSet.add(roleA);
					} 
				}
			}
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, roleSet);
		}
		
		return AppContext.JSON_VIEW;
	}
	
	 * 新增角色
	 
	@RequestMapping(value="/addRole.html", method=RequestMethod.POST)
	public String addRole(HttpServletRequest request,ModelMap map,Role role,OptRoleList optRoleList, ValidateParam validateParam){
		log.debug("新增角色请求参数 - addRole["+role+"]");
		IRoleService iRoleService=GetApplicationContext.getBeanByPermission(RoleServiceImpl.class);
		if(iValidateParamService.validateUnique(role, validateParam, map)){
			User temp = (User) request.getSession().getAttribute("user");
			//新增角色时上级角色为当前登录的用户的角色
			role.setRolePid(temp.getUserRole()); 
			//拼接用户的关联关系
			iRoleService.saveOrUpdate(role,optRoleList);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		}
		return AppContext.JSON_VIEW;
	}
	
	
	 * 更新角色
	 
	@RequestMapping(value="/updateRole.html", method=RequestMethod.POST)
	public String updateRole(ModelMap map,Role role,OptRoleList optRoleList,ValidateParam validateParam){
		log.debug("修改角色请求参数 - updateRole["+role+"]");
		IRoleService iRoleService=GetApplicationContext.getBeanByPermission(RoleServiceImpl.class);
		if(iValidateParamService.validateUnique(role, validateParam, map)){
			//编辑角色时不改变角色原有的上级角色和关联关系
			Role oldRole = iRoleService.getPO(Role.class, role.getRoleId());
			role.setRolePid(oldRole.getRolePid());
			//更新t_user_role表中的角色名字
			IUserRoleService iUserRoleService=GetApplicationContext.getBeanByPermission(UserRoleServiceImpl.class);
			List<UserRole> userRoleList=iUserRoleService.findByRoleId(role.getRoleId());
			if(!userRoleList.isEmpty()&&userRoleList.size()>0){
				for(int i=0;i<userRoleList.size();i++){
					//更新t_user_role
					UserRole userRole=userRoleList.get(i);
					userRole.setRoleName(role.getRoleName());
					iUserRoleService.updateUserRole(userRole);
				}
			}
			iRoleService.saveOrUpdate(role,optRoleList);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		}
		return AppContext.JSON_VIEW;
	}
	
	
	 * 删除角色
	 
	@RequestMapping(value="/deleRole.html", method=RequestMethod.POST)
	public String deleRole(HttpServletRequest request,ModelMap map,String recordIds){
		log.debug("删除角色");
		IRoleService iRoleService=GetApplicationContext.getBeanByPermission(RoleServiceImpl.class);
		String[] ids=recordIds.split("#");
		//禁止删除信息拼接
		String deleteWarning="";
		for(int i=0;i<ids.length;i++){
			//判断该角色是否为顶级角色，如果是禁止删除
			Role role=iRoleService.findById(Integer.parseInt(ids[i]));
			if(role.getRolePid().equals("0")){
				deleteWarning+=role.getRoleName()+"为顶级角色，禁止删除;";
			}
			//获取当前session用的所属角色，判断是否为当前用户所属角色
			User sessionObj=(User)request.getSession().getAttribute("user");
			String[] userRoles=sessionObj.getUserRole().split(",");
			for(int j=0;j<userRoles.length;j++){
				if(userRoles[j].equals(ids[i])){
					deleteWarning+=role.getRoleName()+"为当前操作用户所属角色，禁止删除;";
				}
			}
			//获取可以删除的角色进行删除
			if(!deleteWarning.equals("")){
				map.addAttribute(AppContext.FLAG, -1);
				map.addAttribute(AppContext.EXT_MSG, deleteWarning.substring(0, deleteWarning.length()-1));
			}else{
				iRoleService.deleteById(ids[i]);
				
				String hql = "DELETE OptRole t WHERE t.roleId=" + ids[i];
				iRoleService.executeHql(hql);
				
				map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			}
		}
		return AppContext.JSON_VIEW;
	}
	*//**
	 * 查询所有对象
	 *//*
	@RequestMapping(value="/_findAll.html", method=RequestMethod.POST)
	public String findAllObj(HttpServletRequest request, ModelMap map){
		try {
			IRoleService iRoleService=GetApplicationContext.getBeanByPermission(RoleServiceImpl.class);
			List<RoleVo> list =  iRoleService.findAll(Role.class,RoleVo.class);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
		} catch (Exception e) {
			log.error(null, e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		return AppContext.JSON_VIEW;
	}
}
*/