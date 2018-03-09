package com.nantian.util.str;

import java.util.List;
import java.util.Map;

/**
 * 集合类实体信息格式化输出
 * @author xurui
 *
 */
public class SimpleCollectionFormat {
	
	public static <T> String formatList(List<T> list){
		if(list == null){
			return null;
		}else if(list.size()==0){
			return list.toString();
		}else{
			StringBuffer sb = new StringBuffer();
			for(T t : list){
				sb.append("\n");
				sb.append(t);
			}
			return sb.toString();
		}
	}
	
	public static <K, V> String formatMap(Map<K, V> map){
		if(map==null){
			return null;
		}else if(map.size()==0){
			return map.toString();
		}else{
			StringBuffer sb = new StringBuffer();
			for(K key : map.keySet()){
				sb.append("\n");
				sb.append("key-" + key +": " + map.get(key));
			}
			return sb.toString();
		}
	}
	
	public static String replaceTags(String input, String rex){
	    if ((input == null) || (input.length() == 0)) {
	      return input;
	    }
	    
	    return input.replaceAll("\n", rex);
	  }
}
