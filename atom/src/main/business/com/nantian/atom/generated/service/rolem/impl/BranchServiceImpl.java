package com.nantian.atom.generated.service.rolem.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.dao.impl.CommonDao;
import com.nantian.atom.generated.po.rolem.Branch;
import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.service.CommonService;
import com.nantian.atom.util.DynamicDataSource;
import com.nantian.util.context.comm.AppContext;

/**
 * 机构管理 服务层实现类
 *
 */
@Service("branchServiceImpl")
public class BranchServiceImpl extends CommonService {
	private @Autowired CommonDao dao;

	/**
	 * 查询当前用户可以查看的所有机构信息
	 * @param user
	 * @param map
	 */
	public void findAllAllocationBranch(User user, ModelMap map) {
		// 查找当前操作用户所属机构下的人员
		Branch branch = new Branch();
		branch.setBranchRelationship(user.getBranchId().getBranchRelationship());

		List<Branch> branchlist = dao.findAllByCondition(branch);

		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute(AppContext.DATA, branchlist);
	}

	public List<Branch> findBranch(String recordId) {
		String hql = "from Branch where branchParentId=" + recordId;
		List<Branch> branchList = dao.findAllByHql(hql, null);
		return branchList;
	}

	public void saveBranch(Branch branch, ModelMap map) {
		try {
			String hql = "FROM Branch WHERE branchId=:id";
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", branch.getBranchParentId());
			List<Branch> branchList = findAllByHql(hql, map1);
			String branchRelationship = null;
			System.out.println(branchList.size());
			if (branchList.size() > 0) {
				branchRelationship = branchList.get(0).getBranchRelationship() + "," + branch.getBranchNo();
			} else {
				branchRelationship = branch.getBranchNo();
			}
			// 在保存之前，对validateParam.getUniqueT所标记的属性对应的值进行唯一性检查

			// 查找branch的
			branch.setBranchRelationship(branchRelationship);
			save(branch);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "添加失败");
		}
	}

	public void updateBranch(Branch branch, ModelMap map) {
		try {
			List<Branch> branchList = null;
			String hql = null;
			boolean flag = true;
			Branch oldRecond = getPO(Branch.class, branch.getBranchId());
			if (oldRecond.getIfLeaf() != branch.getIfLeaf()) {
				hql = "FROM Branch WHERE branchParentId=" + branch.getBranchId();
				branchList = findAllByHql(hql, null);
				if (branchList.size() > 0) {
					map.addAttribute(AppContext.FLAG, -1);
					map.addAttribute(AppContext.EXT_MSG, "该机构下还存在其他机构不能修改为末级机构");
					flag = false;
				}
			}

			if (flag) {
				hql = "FROM Branch WHERE branchId=:id";
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", branch.getBranchParentId());
				branchList = findAllByHql(hql, map1);
				String branchRelationship = null;
				if (branchList.size() > 0) {
					branchRelationship = branchList.get(0).getBranchRelationship() + "," + branch.getBranchNo();
				} else {
					branchRelationship = branch.getBranchNo();
				}

				branch.setBranchRelationship(branchRelationship);
				branch = (Branch) dao.merge(branch);
				update(branch);
				map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);

				// 修改下级机构的层级关系
				String updeteSql = "update Branch set branchRelationship=replace(branchRelationship,'"
						+ branch.getOldBranchNo() + "','" + branch.getBranchNo() + "') ";
				executeHql(updeteSql, null);

				DynamicDataSource.setBranchNo(branch.getBranchNo());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "更新失败");
		}
	}

	public void editSessionCheck(User user, ModelMap map) {
		Branch branch = user.getBranchId();
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		map.addAttribute("branchId", branch.getBranchId());
	}

	public void validateSessioBranch(User sessionObj, ModelMap map) {
		try {
			Branch branch = sessionObj.getBranchId();
			if (branch.getIfLeaf() == 1) {
				map.addAttribute("ifLeaf", 1);
			} else {
				map.addAttribute("ifLeaf", 0);
			}
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG, "验证失败");
		}
	}

	public void findAllEdit(User user, ModelMap map) {

		// 查到当前用户所属机构的上级机构
		Integer branchPId = user.getBranchId().getBranchParentId();
		// 根据机构id去查该机构关联关系
		Branch branch = findBybranchId(branchPId);
		// 查找所有当期对应机构
		if (branchPId != 0) {
			List<Branch> branchList = findALLNoFit(branch.getBranchRelationship());
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, branchList);
		} else {
			String hql = "from Branch";
			List<Branch> branchList = dao.findAllByHql(hql, null);// (Branch.class, BranchVO.class);
			map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			map.addAttribute(AppContext.DATA, branchList);
		}
	}

	public void deleteBranch(ModelMap map, Map dataMap) {
		String recordIds = (String) dataMap.get("recordIds");
		String[] id = recordIds.split("#");
		for (int i = 0; i < id.length; i++) {
			Integer branchId = Integer.parseInt(id[i]);
			// 拼接删除机构提示字符串
			String deleteWarning = "该机构与";

			String hql = "from User where branchId.branchId=" + branchId;
			List<User> userList = dao.findAllByHql(hql, null);
			boolean userN = false;
			if (userList.size() != 0) {// 提醒用户
				deleteWarning += "用户、";
				userN = true;
			}

			// 判断是否是末级机构
			List<Branch> branchList = this.findBranch(id[i]);
			if (branchList.size() != 0) {// 等于0表示叶子机构，不可以删除
				map.addAttribute(AppContext.FLAG, -2);
				map.addAttribute(AppContext.EXT_MSG, "该机构下还有其他机构，请先删除下辖机构");
			} else if (!"该机构与".equals(deleteWarning)) {
				deleteWarning = deleteWarning.substring(0, deleteWarning.length() - 1);
				deleteWarning += "有关联，禁止删除";
				map.addAttribute(AppContext.FLAG, -1);
				map.addAttribute(AppContext.EXT_MSG, deleteWarning);
			} else {
				// 删除机构
				delete(id[i], Branch.class);
				map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
			}
		}
	}

	public Integer findBranchByBranchId(Integer branchId) {
		String hql = "from Branch where branchId=:branchId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("branchId", branchId);
		List<Branch> branchList = dao.findAllByHql(hql, params);
		Integer branchLeaf = 0;
		if (branchList.size() > 0) {
			branchLeaf = branchList.get(0).getIfLeaf();
		}
		return branchLeaf;
	}

	public List<Branch> findALLNoFit(String barnchRelationship) {
		String sql = "select BRANCH_ID,BRANCH_NAME,IF_LEAF from t_branch where branch_relationship like '"
				+ barnchRelationship + "%'";

		List<Object[]> branchList1 = dao.findAllBySql(sql, null);
		List<Branch> branchList = new ArrayList<Branch>();
		for (Object[] obj : branchList1) {
			Branch branch = new Branch();

			Integer i_branch = (Integer) obj[0];
			Integer i_leaf = (Integer) obj[2];
			branch.setBranchId(i_branch);
			branch.setBranchName((String) obj[1]);
			branch.setIfLeaf(i_leaf);
			branchList.add(branch);
		}

		// List<Branch> branchList=iBasePoDao.find(hql);
		return branchList;
	}

	public Branch findBybranchId(Integer branchId) {
		Branch branch = dao.get(Branch.class, branchId);
		return branch;
	}

}
