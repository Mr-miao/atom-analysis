package com.nantian.util.str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClassPathAnalyzer {
	private static ClassPathAnalyzer classPathAnalyzer;
	
	private ClassPathAnalyzer(){
		
	}
	
	public static ClassPathAnalyzer getInstance(){
		if(classPathAnalyzer == null){
			classPathAnalyzer = new ClassPathAnalyzer();
		}
		return classPathAnalyzer;
	}
	
	/**
	 * 获取类加载路径（或Jar包）中的文件
	 * @author xurui
	 * @created 2013-11-29
	 * @param path
	 * @param is
	 * @param encode
	 * @return
	 * @throws IOException 
	 */
	public String readClassPathFile(String path, InputStream is, String encode) throws IOException{
		//返回读取指定资源的输入流  
        is = this.getClass().getResourceAsStream(path);   
        BufferedReader br=new BufferedReader(new InputStreamReader(is, encode));  
        StringBuffer sb = new StringBuffer();
        String line = null;
        while((line=br.readLine()) != null){
        	sb.append(line);
        }
        return sb.toString();
	}
	
	/**
	 * 获取类加载路径（或Jar包）中的文件流
	 * @author xurui
	 * @created 2013-11-29
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public InputStream readClassPathFile(String path) throws IOException{
		//返回读取指定资源的输入流  
		InputStream is = this.getClass().getResourceAsStream(path);   
        return is;
	}
}
