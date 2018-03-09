package com.nantian.util.str;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.nantian.atom.po.PageDsnForTable;

/**
 * @author xurui
 * @version 1.0
 * @created 29-9-2013 10:26:39
 */
public class StringUtil {
	/**
	 * 判断字符串是否为空，包括空字符串和null值
	 * @author xurui
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		if(null==value || "".equals(value.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getNotNullStr(String value){
		return StringUtil.isEmpty(value) ? "" : value.trim();
	}
	
	public static String getNotNullInt(String value){
		return StringUtil.isEmpty(value) ? "0" : value.trim();
	}
	
	public static String getNotNullString(Object object){
		if(object==null || object.toString().trim().equals("")){
			return "";
		}else{
			return object.toString().trim();
		}
	}
	
	/**
	 * 将形如c:/BQMC/version/file.文件后缀名 解析为文件名和文件路径
	 * @author xurui
	 * @created 2013-11-25
	 * @param fileNamePath
	 * @return	String[0] 文件名-String[1] 文件路径
	 */
	public static String[] getFileNamePathByStr(String fileNamePath, String fileSuffix){
		if(!fileSuffix.startsWith(".")){
			fileSuffix += ".";
		}
		if(!StringUtil.isEmpty(fileNamePath) && fileNamePath.endsWith(fileSuffix)){
			String filePath = fileNamePath.substring(0, fileNamePath.lastIndexOf("/")+1);
			String fileName = fileNamePath.substring(fileNamePath.lastIndexOf("/")+1);
			return new String[]{fileName, filePath};
		}else{
			return null;
		}
	}
	
	/** 
     * 获取字符串的长度，中文占两个字符,英文数字占一个字符
     * @param value  指定的字符串
     * @return 字符串的长度
     */  
    public static int length(String value) {
    	if(value==null){
    		return 0;
    	}
        int valueLength = 0;  
        String chinese = "[\u4e00-\u9fa5]";  
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1  
        for (int i = 0; i < value.length(); i++) {  
            // 获取一个字符  
            String temp = value.substring(i, i + 1);  
            // 判断是否为中文字符  
            if (temp.matches(chinese)) {  
                // 中文字符长度为1  
                valueLength += 2;  
            } else {  
                // 其他字符长度为0.5  
                valueLength += 1;  
            }  
        }  
        return  valueLength;  
    } 
    
    public static String[] split(String value, String flag){
    	if(value==null){
    		return null;
    	}else{
    		String[] args = value.split(flag);
    		if(args==null){
    			return null;
    		}else{
    			return args;
    		}
    	}
    }
    
    public static Float getNotNullFloat(Object o){
    	if(o==null){
    		return 0f;
    	}else{
    		return (Float)o;
    	}
    }
	/**
	 * 将map转换为json格式数据
	 */
	public String map2json(Map map){
		JSONArray json = JSONArray.fromObject(map); 
		String jsonString=json.toString();
		//去掉“[”与“]”
		jsonString=jsonString.substring(jsonString.indexOf("[")+1, jsonString.lastIndexOf("]"));
		return jsonString;
	}
    /*
     * list对象转json格式字符串
     */
	public String list2json(List list){
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
    /*
     * 数组转json格式字符串
     */
	public String array2json(Object[] arr){

		JSONArray jsonarray = JSONArray.fromObject(arr);
		return jsonarray.toString();
	}
    /*
     * json格式字符串转map对象
     */
	public static Map<String, Object> json2map(String jsonString){
		JSONObject jsonObject = JSONObject.fromObject(jsonString.trim());
		Iterator<String> keyIter = jsonObject.keys();
		String key;
		Object value;
		Map<String, Object> valueMap = new HashMap<String, Object>();

		while (keyIter.hasNext()) {
			key = (String) keyIter.next();
			value = jsonObject.get(key);
			valueMap.put(key, value);
		}
		return valueMap;
	}
   
	/**
     * 
     * @param list:excel中读取到的数据
     * @param tableName：要插入数据的表名
     * @param pageDsnForTableList：系统配置
     * @return
     */
    public static String[] genSqlFromExcel(List<List<Object>> list,String tableName,List<PageDsnForTable> pageDsnForTableList){
		int size = list.size();
    	String[] sqls = new String[size];
    	int index = 0;
		for (List<Object> obj : list) {
			String sql="INSERT INTO "+tableName+"(";
			for (int i=0;i<pageDsnForTableList.size();i++) {
				if(i==pageDsnForTableList.size()-1){
					sql += pageDsnForTableList.get(i).getCumnName();
				}else{
					sql += pageDsnForTableList.get(i).getCumnName()+",";
				}
			}
			sql += ") VALUES(";
			
			/**
			 * 根据值的来源和类型赋值，并判断是否需要给值添加单引号
			 * 1期所有的值都从excel取，所以case中的所有取值都一样
			 * 2期有可能按照1 2 3 4的定义动态取值
			 */
			int step = 0;
			for (int i=0;i<pageDsnForTableList.size();i++) {
				//判断值的类型是否为int和decimal，如果是：值不需要加单引号，不是：要加单引号
				if("int".equals(pageDsnForTableList.get(i).getCumnType())||"decimal".equals(pageDsnForTableList.get(i).getCumnType())){
					switch (pageDsnForTableList.get(i).getImportSource()) {
					//1表示从excel取值
					case 1:
						if(i==pageDsnForTableList.size()-1){
							sql += obj.get(step)+")";
						}else{
							sql += obj.get(step)+",";
							step++;
						}
						break;
					//2表示常量
					case 2:
//    						if(i==importSource.size()-1){
//    							sql += "const"+")";
//    						}else{
//    							sql += "const"+",";
//    						}
						if(i==pageDsnForTableList.size()-1){
							sql += obj.get(step)+")";
						}else{
							sql += obj.get(step)+",";
							step++;
						}
						break;
					//3表示从session取值
					case 3:
//    						if(i==importSource.size()-1){
//    							sql += "session"+")";
//    						}else{
//    							sql += "session"+",";
//    						}
						if(i==pageDsnForTableList.size()-1){
							sql += obj.get(step)+")";
						}else{
							sql += obj.get(step)+",";
							step++;
						}
						break;
					//4表示调用方法取值
					case 4:
//    						if(i==importSource.size()-1){
//    							sql += "method"+")";
//    						}else{
//    							sql += "method"+",";
//    						}
						if(i==pageDsnForTableList.size()-1){
							sql += obj.get(step)+")";
						}else{
							sql += obj.get(step)+",";
							step++;
						}
						break;
					default:
						break;
					}
				}else{
					switch (pageDsnForTableList.get(i).getImportSource()) {
					//1表示从excel取值
					case 1:
						if(i==pageDsnForTableList.size()-1){
							sql += "'"+obj.get(step)+"')";
						}else{
							sql += "'"+obj.get(step)+"',";
							step++;
						}
						break;
					case 2:
//    						if(i==importSource.size()-1){
//    							sql += "'"+"const"+"')";
//    						}else{
//    							sql += "'"+"const"+"',";
//    						}
						if(i==pageDsnForTableList.size()-1){
							sql += "'"+obj.get(step)+"')";
						}else{
							sql += "'"+obj.get(step)+"',";
							step++;
						}
						break;
					case 3:
//    						if(i==importSource.size()-1){
//    							sql += "'"+"session"+"')";
//    						}else{
//    							sql += "'"+"session"+"',";
//    						}
						if(i==pageDsnForTableList.size()-1){
							sql += "'"+obj.get(step)+"')";
						}else{
							sql += "'"+obj.get(step)+"',";
							step++;
						}
						break;
					case 4:
//    						if(i==importSource.size()-1){
//    							sql += "'"+"method"+"')";
//    						}else{
//    							sql += "'"+"method"+"',";
//    						}
						if(i==pageDsnForTableList.size()-1){
							sql += "'"+obj.get(step)+"')";
						}else{
							sql += "'"+obj.get(step)+"',";
							step++;
						}
						break;
					default:
						break;
					}
				}
				
			}
			step = 0;
			System.out.println(sql);
			sqls[index] = sql;
			index++;
		}
    	return sqls;
	}
}
