package com.nantian.atom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.dao.impl.CommonDao;
import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.po.OptRole;
import com.nantian.atom.po.OptRoleList;
import com.nantian.atom.po.Role;
import com.nantian.atom.po.UserRole;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.CommonService;
import com.nantian.util.context.comm.AppContext;
import com.xr.util.db.dao.impl.Pager;

@Service("roleServiceImpl")
public class RoleServiceImpl extends CommonService {
	private @Autowired CommonDao dao;
	
	@Autowired private OptRoleServiceImpl optRoleService;
	@Autowired private UserRoleServiceImpl userRoleService;
	public static void main(String[] args) {
		String  ss="12,23";
		if(ss.contains(",")){
			System.out.println("1234554");
			String[] sss=ss.split(",");
			for(String ss1:sss){
				System.out.println(ss1);
			}
		}else if(!ss.isEmpty()&&!"".equals(ss)){ 
			System.out.println("67898");
		}
	}
	public void saveOrUpdateRole(Role role,OptRoleList optRoleList,User user,ModelMap map){
		
		log.debug("新增角色请求参数 - addRole["+role+"]");
			//新增角色时上级角色为当前登录的用户的角色
			role.setRolePid(user.getUserRole()); 
			//拼接用户的关联关系
			role=(Role) dao.merge(role);
			
			String userRole=user.getUserRole();
			if(userRole.contains(",")){
				String[] roleIds=userRole.split(",");
				for(String roleId:roleIds){
					String sql="insert into t_role_prole(role_id,role_pid)  select "+role.getRoleId()+",role_pid from t_role_prole where role_id ="+roleId;
					dao.executeSql(sql, null);
					 sql="insert into t_role_prole(role_id,role_pid) values ("+role.getRoleId()+","+roleId+")";
					 dao.executeSql(sql, null);
				}
			}else if(!userRole.isEmpty()&&!"".equals(userRole)){ 
				String sql="insert into t_role_prole(role_id,role_pid)  select "+role.getRoleId()+",role_pid from t_role_prole where role_id ="+userRole;
				dao.executeSql(sql, null);
				 sql="insert into t_role_prole(role_id,role_pid) values ("+role.getRoleId()+","+userRole+")";
				 dao.executeSql(sql, null);
			}
			

			
			List<OptRole> list = optRoleList.getOptRoles();
			for (OptRole optRole : list) {
				optRole.setRoleId(role.getRoleId());
			}
			int size = list.size();
			OptRole[] optRoles = new OptRole[size];
			list.toArray(optRoles);
			//批量添加
			optRoleService.batchInsertOptRole(optRoles);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		
	}
	

	
	public void updateRole(Role role,User user,ModelMap map,OptRoleList optRoleList){
		
//		OptRoleList optRoleList=(OptRoleList) dataMap.get("optRoles");
		log.debug("修改角色请求参数 - updateRole["+role+"]");
			//编辑角色时不改变角色原有的上级角色和关联关系
			Role oldRole = getPO(Role.class, role.getRoleId());
			role.setRolePid(oldRole.getRolePid());
			//更新t_user_role表中的角色名字
			UserRoleServiceImpl userRoleService=GetApplicationContext.getBean("userRoleServiceImpl");
			List<UserRole> userRoleList=userRoleService.findByRoleId(role.getRoleId());
			if(!userRoleList.isEmpty()&&userRoleList.size()>0){
				for(int i=0;i<userRoleList.size();i++){
					//更新t_user_role
					UserRole userRole=userRoleList.get(i);
					userRole.setRoleName(role.getRoleName());
					userRoleService.updateUserRole(userRole);
				}
			}
			saveOrUpdateRole(role,optRoleList,user,map);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		
	}
	public void deleteRole(User user,ModelMap map,Map dataMap){
		String recordIds=(String) dataMap.get("recordIds");
		String[] ids=recordIds.split("#");
		//禁止删除信息拼接
		String deleteWarning="";
		for(int i=0;i<ids.length;i++){
			//判断该角色是否为顶级角色，如果是禁止删除
			Role role=findById(Integer.parseInt(ids[i]));
			if(role.getRolePid().equals("0")){
				deleteWarning+=role.getRoleName()+"为顶级角色，禁止删除;";
			}
			//获取当前session用的所属角色，判断是否为当前用户所属角色
			String[] userRoles=user.getUserRole().split(",");
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
				deleteById(ids[i]);
				
				String hql = "DELETE OptRole t WHERE t.roleId=" + ids[i];
				executeHql(hql,null);
				
				String sql = "DELETE from t_role_prole  WHERE role_id=" + ids[i];
				executeSql(sql, null);
				
				map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			}
		}
		
	}
	public void findAllObj(ModelMap map){
		
		try {
			String hql="from Role";
			List<Role> list =  findAllByHql(hql, null);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		
	}
	public void queryRoleById(ModelMap map,User user,Map dataMap){
		String recordId=(String) dataMap.get("recordId");
		Role po = getPO(Role.class,  Integer.parseInt(recordId));
		
		if(po.getRolePid().equals("0")){
			map.addAttribute("rolePId", "0");
			map.addAttribute("roleName", po.getRoleName());
		}else{
			map.addAttribute("rolePId", "1");
		}
		//查找当前登陆用户
		String[] userRoleIds=user.getUserRole().split(",");
		for(int i=0;i<userRoleIds.length;i++){
			if(po.getRoleId().toString().equals(userRoleIds[i])){
				map.addAttribute("sessionRole", 1);
				map.addAttribute("sessionRoleName", po.getRoleName());
			}
		}
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, po);
		
	}
	
	public void queryRoleByIdAndfollow(String recordId,ModelMap map,User user){
		//RoleVo vo = iRoleService.getVO(Role.class, RoleVo.class, Integer.parseInt(recordId));
		//查找当钱选中用户的角色
		//UserVO vo=iuserService.getVO(User.class, UserVO.class,Integer.parseInt(recordId));
		if(user!=null){
			String[] userRoles=user.getUserRole().split(","); 
			Set<Role> roleSet=new HashSet<Role>();
			for(int i=0;i<userRoles.length;i++){
				Role role=findById(Integer.parseInt(userRoles[i]));
				//查找当前选中角色的子角色
				List<Role> roleListA=findByPId(userRoles[i]);
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
		
	}
	public void pagerRole(Role role,ModelMap map,User user,ReqParam reqParam){
		List<Role> list = new ArrayList<Role>();
		try {
			//获取当前登陆用户所属角色
			String roleRelationships=user.getUserRole();
			String sql="select ROLE_ID,ROLE_NAME,ROLE_DESC from t_role where (ROLE_ID in (select ROLE_ID from t_role_prole where role_pid in ("+roleRelationships+")) or ROLE_ID in ("+roleRelationships+")) ";
			if(role.getRoleName()!=null&&!"".equals(role.getRoleName())){
				sql=sql+" and ROLE_NAME like '%"+role.getRoleName()+"%'";
			}
			
			List<Object[]> listo=(List<Object[]>) dao.getSession().createSQLQuery(sql).setFirstResult(reqParam.getCurrentPage()*reqParam.getPageSize()).setMaxResults(reqParam.getPageSize()).list();
			int cn=dao.findAllBySql(sql, null).size();

			
			if(listo!=null&&!listo.isEmpty()){
				for (Object[] objs : listo) {
					Role nrole=new Role();
					nrole.setRoleDesc((String)objs[2]);
					nrole.setRoleId((Integer)objs[0]);
					nrole.setRoleName( (String)objs[1]);
					list.add(nrole);
				}
			}
		
		/*	//获取登陆用户所属角色id
			Map retMap=pageByCondition(role, reqParam);
			list = (List<Role>) retMap.get(EmDaoResult.VO_LIST);*/
/*			if(list!=null&&!list.isEmpty()){
				if(list.size()==1){
					map.addAttribute("size", 1);
				}
				for (Role role1 : list) {
					
//					JSONObject jsonObj=JSONObject.fromObject(role1);
//					JSONObject jsonObj = new JSONObject(role1);
					String text=JSONObject.toJSONString(role1);
					JSONObject jsonObj=JSONObject.parseObject(text);
					String pRoleName = "";
					if(role1.getRolePid()!=null){
						String[] ids = role1.getRolePid().split(",");
						for (int i=0;i<ids.length;i++) {
							Role temp = getPO(Role.class, Integer.parseInt(ids[i]));
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
					jsonObj.put("rolePname", pRoleName);
					jsonObj.put("branchId", "");
					jsonArr.add(jsonObj);
//					role1.setRolePname(pRoleName);
				}
			}*/
			
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
//			map.put(AppContext.DATA, jsonArr);
			map.addAttribute(AppContext.DATA, list);
			Pager pager = new Pager(cn, reqParam.getPageSize(), reqParam.getCurrentPage());
			map.put(AppContext.PAGER, pager);
		} catch (Exception e) {
			log.error("", e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "查询数据失败");
		}
		
	}

	public void delete(String ids){
		dao.delete(ids, Role.class);
		String[] idArr = ids.split("#");
		for (String key : idArr) {
			optRoleService.deleteByRoleId(Integer.parseInt(key));
		}
	}


	public Role findById(Integer roleId) {
		Role role=getPO(Role.class, roleId);
		return role;
	}


	public List<Role> findByPId(String rolePId) {
		String hql="from Role where rolePid=:rolePid";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("rolePid", rolePId);
		
		List<Role> roleList=findAllByHql(hql, params);
		return roleList;
	}


	public void deleteById(String recordId) {
       String hql="delete from Role where roleId='"+recordId+"'";
       executeHql(hql, null);
//      String sql="delete from ";
       //删除t_opt_role
       optRoleService.deleteByRoleId(Integer.parseInt(recordId));
       //删除t_user_role
       userRoleService.deleteByRoleId(Integer.parseInt(recordId));
	}
}
