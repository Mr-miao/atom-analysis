package com.nantian.atom.generated.service.parameter.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.dao.impl.CommonDao;
import com.nantian.atom.dao.impl.CommonDaoForAuth;
import com.nantian.atom.generated.po.parameter.ParameterConf;
import com.nantian.atom.service.CommonService;
import com.nantian.atom.util.ConfigUtil;
import com.nantian.util.context.comm.AppContext;


/**
 * 系统参数设置 服务层实现类
 *
 */
@Service("parameterConfServiceImpl")
public class ParameterConfServiceImpl extends CommonService {
	private @Autowired CommonDao dao;
	private @Autowired CommonDaoForAuth daoForAuth;
	private @Autowired ConfigUtil configUtil;
	

	
	public  void deleteParameterConf(ModelMap map,Map dataMap) {
		String delIds=(String) dataMap.get("recordIds");
		super.delete(delIds, ParameterConf.class);
		configUtil.initConfigMap();
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}
	public <PO> void saveParameterConf(ParameterConf parameterConf,ModelMap map) {
		save(parameterConf);
		configUtil.initConfigMap();
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
	}
	public <PO> void updateParameterConf(ParameterConf parameterConf,ModelMap map) {
		update(parameterConf);
		configUtil.initConfigMap();
		map.addAttribute(AppContext.FLAG, AppContext.FLAG_SUCCESS);
		
	}
	/**
	 * 根据参数英文名获取系统参数值
	 * @param paraEnName 参数英文名
	 * @return 返回参数值
	 */
	public String getParaValueByParaEnName(String paraEnName){
		ParameterConf parameterConf = new ParameterConf();
		parameterConf.setParaEnglishName(paraEnName);
		
		List<ParameterConf> parameterConfList = dao.findAllByCondition(parameterConf);
		if(parameterConfList!=null&&!parameterConfList.isEmpty()){
			return parameterConfList.get(0).getParaValue();
		}else{
			return null;
		}
	}

	
}
