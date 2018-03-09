package com.nantian.util.str;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Properties文件操纵类
 * @author xurui
 * @version 1.0
 * @created 2013-10-21 上午11:23:51
 */
public class PropertiesUtil {
	private static final Logger log = LogManager.getLogger(PropertiesUtil.class);
	
	private static PropertiesUtil proUtil ;
	
	private PropertiesUtil(){
		
	}
	
	/** 
     * 获得属性配置文件工具类的实例对象 
     *  
     * @return PropertiesFilesUtils 对象 
     */  
    public static PropertiesUtil getInstance() {
    	if(proUtil == null){
    		proUtil = new PropertiesUtil();
    	}
        return proUtil;  
    }
	
	/**
     * 通过文件名（带路径）得到properties文件，如果没有则创建
     * @author xurui
     * @param proFileName 带路径的文件名
     * @return
     * @throws IOException
     */
    @Deprecated
    public synchronized Properties getProFileOrCreate(String proFileName) throws IOException {  
        if (StringUtil.isEmpty(proFileName)) {  
            throw new IllegalArgumentException("The propertiesFileName is illegal argument!");  
        }
        Properties prop = new Properties();  
        File proFile = new File(proFileName);
        if(!proFile.exists()){
        	proFile.createNewFile();
        }
        InputStream is =  new FileInputStream(proFileName);  
        prop.load(is);  
        return prop;  
    }
    
    public synchronized Properties getProFile(String proFileNamePath, InputStream is) throws IOException {  
        if (StringUtil.isEmpty(proFileNamePath)) {  
            throw new IllegalArgumentException("The propertiesFileName is illegal argument!");  
        }
        File proFile = new File(proFileNamePath);
        if(!proFile.exists()){
        	throw new IllegalArgumentException("The propertiesFileName "+proFileNamePath+" is not exists!"); 
        }
        Properties prop = new Properties();  
        is =  new FileInputStream(proFileNamePath);  
        prop.load(is);  
        return prop;  
    }
    
    /**
     * 读取classpath中的properties文件
     * @param proFileNamePath文件路径
     * @return
     * @throws IOException
     */
    public synchronized Properties getProFile(String proFileNamePath) throws IOException {  
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(proFileNamePath);
    	File proFile = new File(url.getFile());
        if(!proFile.exists()){
        	throw new IllegalArgumentException("The propertiesFileName "+proFileNamePath+" is not exists!"); 
        }
        Properties prop = new Properties();  
        InputStream iStream = new FileInputStream(proFile);
        prop.load(iStream);  
        return prop;  
    }
    
    /** 
     * 将properties文件写到磁盘上 
     * @author xurui
     *  
     * @param properties 
     *            属性文件 
     * @param proFilePath 
     *            要保存的路径 
     * @throws IOException 
     */  
    public void writePropertiesFile(Properties properties, String proFilePath){  
        if (properties == null || proFilePath == null || "".equals(proFilePath))  
            throw new IllegalArgumentException("The props or propertiesFilePath  is illegal argument!");  
        FileWriter fw = null;
        try {
        	fw = new FileWriter(proFilePath);  
            properties.store(fw, null);
            fw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} finally{
			if(fw!=null){
				try {
					fw.close();
				} catch (Exception e2) {
					e2.printStackTrace();
					log.error(e2.getMessage(), e2);
				}
			}
		}
    }  
  
    /** 
     * 更新属性文件的某个属性,并保存到磁盘上 
     * @author xurui
     * 
     * @param properties 
     *            要更新的属性文件 
     * @param propertyName 
     *            属性名称 
     * @param propertyValue 
     *            属性值 
     * @param filePath 
     *            保存的文件路径 
     * @throws IOException 
     */  
    public void updatePropertiesFile(Properties properties, String propertyName, String propertyValue, String filePath)  
            throws IOException {  
        if (properties == null || propertyName == null || "".equals(propertyName) || propertyValue == null  
                || "".equals(propertyValue) || filePath == null || "".equals(filePath))  
            throw new IllegalArgumentException("The parameters  is illegal argument!");  
        properties.setProperty(propertyName, propertyValue);  
        writePropertiesFile(properties, filePath);  
    }  

}
