package com.nantian.atom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.service.CommonService;
/**
 * 用户服务层实现类
 * @author niuchao
 *
 */
@Service("loginServiceImpl")
public class LoginServiceImpl extends CommonService {
	
	public List<User> findUserByNameAndPwd(String userload, String pwd) {
		String hql="FROM User WHERE userLoad =:userLoad AND userPwd=:userPwd ";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userLoad", userload);
		params.put("userPwd", pwd);
		
		List<User> userList=findAllByHql(hql, params);
		return userList;
	}

}
