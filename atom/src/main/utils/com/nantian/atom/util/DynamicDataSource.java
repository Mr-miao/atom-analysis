package com.nantian.atom.util;

import java.util.List;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author xurui
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {  
  
	//线程本地环境
    private static final ThreadLocal<List<Integer>> roleId = new ThreadLocal<List<Integer>>();  
    private static final ThreadLocal<String> serverName = new ThreadLocal<String>();  
    private static final ThreadLocal<String> branchNo = new ThreadLocal<String>();
    private static final ThreadLocal<Integer> branchId = new ThreadLocal<Integer>();
    //获取角色信息
    public static List<Integer> getRoleId() {  
        return  roleId.get();  
    }  
  
    public static String getServerName() {
		return serverName.get();
	}

	//设置角色信息 
    public static void setRoleId(List<Integer> id) {  
    	roleId.set(id);  
    } 
    
	//设置角色信息 
    public static void setServerName(String serverName1) {  
    	serverName.set(serverName1);  
    }
   //清除角色信息
    public static void clearRoleId() {
    	roleId.remove();
    }
    
    
  
    public static String getBranchNo() {
		return branchNo.get();
	}
    
    public static void setBranchNo(String value){
    	branchNo.set(value);
    }
    
    public static Integer getBranchId() {
		return branchId.get();
	}
    
    public static void setBranchId(Integer value){
    	branchId.set(value);
    }

	/* 
     * 这是AbstractRoutingDataSource类中的一个抽象方法，
     * 而它的返回值是你所要用的数据源dataSource的key值，
     * 有了这个key值，resolvedDataSource（这是个map,
     * 由配置文件中设置好后存入的）就从中取出对应的DataSource，
     * 如果找不到，就用配置默认的数据源。
     *  
     * @see 
     * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource# 
     * determineCurrentLookupKey() 
     */  
    @Override  
    protected Object determineCurrentLookupKey() {  
        return getRoleId();  
    }  
    
    public static void clear(){
    	roleId.remove();
    	serverName.remove();
    	branchId.remove();
    	branchNo.remove();
    }
  
}  
