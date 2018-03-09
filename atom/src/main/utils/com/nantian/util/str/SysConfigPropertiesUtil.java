package com.nantian.util.str;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SysConfigPropertiesUtil {
	protected final static Logger log = LogManager.getLogger(SysConfigPropertiesUtil.class);
	
	public static boolean isDataAuth(String entityName){
		Properties properties = null;
		boolean isDataAuth = false;
		try {
			properties = PropertiesUtil.getInstance().getProFile("sys-config.properties");
			if(properties.getProperty(entityName)!=null){
				isDataAuth = properties.getProperty(entityName).equals("1")?true:false;
			}else{
				isDataAuth = false;
			}
		} catch (IOException e) {
			log.error("读取数据权限配置文件错误！", e);
			isDataAuth = false;
		}
		return isDataAuth;
	}
	
	/**
	 * 是否立即更新配置项
	 * @param property
	 * @return
	 */
	public static boolean isImmediately(){
		Properties properties = null;
		boolean isImmediately = false;
		try {
			properties = PropertiesUtil.getInstance().getProFile("sys-config.properties");
			if(properties.getProperty("immediately")!=null){
				isImmediately = properties.getProperty("immediately").equals("1")?true:false;
			}else{
				isImmediately = false;
			}
		} catch (IOException e) {
			log.error("读取数据权限配置文件错误！", e);
			isImmediately = false;
		}
		return isImmediately;
	}
	
	/**
	 * 获取数据库类型
	 * @return
	 */
	public static String getDataBaseType(){
		Properties properties = null;
		String dataBaseType = "";
		try {
			properties = PropertiesUtil.getInstance().getProFile("sys-config.properties");
			if(properties.getProperty("database")!=null){
				dataBaseType = properties.getProperty("database");
			}else{
				dataBaseType = "mysql";
			}
		} catch (IOException e) {
			log.error("读取数据权限配置文件错误！", e);
			dataBaseType = "mysql";
		}
		return dataBaseType;
	}
	
	/**
	 * 读取指定属性的值
	 * @param keyName 配置项名称
	 * @return 配置项值
	 */
	public static String getPropertiesValue(String keyName){
		Properties properties = null;
		String propertiesValue = null;
		try {
			properties = PropertiesUtil.getInstance().getProFile("sys-config.properties");
			if(properties.getProperty(keyName)!=null){
				propertiesValue = properties.getProperty(keyName);
			}else{
				propertiesValue = "";
			}
		} catch (IOException e) {
			log.error("读取数据权限配置文件错误！", e);
			propertiesValue = "";
		}
		return propertiesValue;
	}
}
