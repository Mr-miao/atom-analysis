package com.nantian.atom.power;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.nantian.atom.util.DynamicDataSource;
import com.nantian.atom.util.SpringInit;

public class GetApplicationContext {
	private static class ApplicationContextHolder {
		// 单例变量
		private static ApplicationContext AC = SpringInit
				.getApplicationContext();
	}

	// 私有化的构造方法，保证外部的类不能通过构造器来实例化。
	private GetApplicationContext() {
	}

	// 获取单例对象实例
	public static ApplicationContext getSignInstance() {
		if (ApplicationContextHolder.AC == null) {
			ApplicationContextHolder.AC = SpringInit.getApplicationContext();

		}
		return ApplicationContextHolder.AC;
	}

	// 获取单例对象实例
	public static <T> T getBeanByPermission(List<Integer> roleId, Class<T> cls) {
		Service serviceAnno = cls.getAnnotation(Service.class);
		String serverName = serviceAnno.value();

		ApplicationContext context = getSignInstance();
		T info = (T) context.getBean(serverName);
		// 角色赋值
		DynamicDataSource.setRoleId(roleId);
		DynamicDataSource.setServerName(serverName);
		return info;
	}

	// 获取单例对象实例
/*	public static <T> T getBeanByPermission(Class<T> cls) {
		Service serviceAnno = cls.getAnnotation(Service.class);
		String serverName = serviceAnno.value();

		ApplicationContext context = getSignInstance();
		T info = (T) context.getBean(serverName);
		// 角色赋值
		DynamicDataSource.setServerName(serverName);
		return info;
	}*/
	// 获取单例对象实例
	public static <T> T getBean(String serverName) {

		ApplicationContext context = getSignInstance();
		T info = (T) context.getBean(serverName);
		
		return info;
	}
	// // 获取单例对象实例
	// public static<T> T getInfo(String serverName) {
	// ApplicationContext context=getSignInstance();
	// T info = (T) context.getBean(serverName);
	// //角色赋值
	// DynamicDataSource.setServerName(serverName);
	// return info;
	// }
}