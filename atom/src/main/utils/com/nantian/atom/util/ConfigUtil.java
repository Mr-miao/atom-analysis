package com.nantian.atom.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nantian.atom.generated.po.parameter.ParameterConf;
import com.nantian.atom.generated.service.parameter.impl.ParameterConfServiceImpl;

/**
 * 配置文件操作类
 * @author 猫小游
 *
 */
@Component("ConfigUtil")
public class ConfigUtil {
	private Logger log = LogManager.getLogger(ConfigUtil.class);
	
	private static Map<String, String> config = new HashMap<String, String>();
	
	@Autowired
	private ParameterConfServiceImpl paramBean;
	
	/**
	 * 根据配置名获取配置值
	 * @param name 配置项名称
	 * @return
	 */
	public static String getConfig(String name){
		String retStr = config.get(name);
		return retStr;
	}
	
	
	@PostConstruct 
	public void initConfigMap(){
		
		config.clear();
		String hql="from ParameterConf";
		
		List<ParameterConf> allConfig = paramBean.findAllByHql(hql, null);
		
		for (ParameterConf conf : allConfig) {
			config.put(conf.getParaEnglishName(), conf.getParaValue());
		}
		log.info("从数据库中初始化了以下全局参数：" + config);
	}
}
