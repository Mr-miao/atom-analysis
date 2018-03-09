package com.nantian.atom.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public interface ApplicationContextAware {
	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;  
}
