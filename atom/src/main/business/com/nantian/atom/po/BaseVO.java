package com.nantian.atom.po;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * 公共VO类
 * 
 * @author 猫小游 2017年8月14日
 */
public class BaseVO {
	protected Logger log = LogManager.getLogger(BaseVO.class);

	public static JSONObject po2vo(Object po, Map<String, Object> formatMap) {
		JSONObject json = IgnoreNullFieldForBean(po);

		for (Map.Entry entry : formatMap.entrySet()) {
			json.put((String) entry.getKey(), entry.getValue());
		}
		return json;
	}

	public static JSONArray IgnoreNullFieldForList(Object obj) {

		// 设置过滤json格式
		JsonConfig jsonConfig = new JsonConfig();
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				if (fieldValue instanceof List) {
					List<Object> list = (List<Object>) fieldValue;
					if (list.size() == 0) {
						return true;
					}
				}
				return null == fieldValue || "".equals(fieldValue);
			}
		};
		jsonConfig.setJsonPropertyFilter(filter);

		JSONArray jsonArray = JSONArray.fromObject(obj, jsonConfig);
		return jsonArray;
	}

	public static JSONObject IgnoreNullFieldForBean(Object obj) {

		// 设置过滤json格式
		JsonConfig jsonConfig = new JsonConfig();
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				if (fieldValue instanceof List) {
					List<Object> list = (List<Object>) fieldValue;
					if (list.size() == 0) {
						return true;
					}
				}
				return null == fieldValue || "".equals(fieldValue);
			}
		};
		jsonConfig.setJsonPropertyFilter(filter);

		JSONObject jsonObject = JSONObject.fromObject(obj, jsonConfig);
		return jsonObject;
	}


	public static <PO extends  BaseInterfaceVO>   JSONArray  po2voForList(List list,Class poClass){
		
		JSONArray jsonArray= new JSONArray();
		try{
			jsonArray=IgnoreNullFieldForList(list);
		}catch(Exception e){
			e.printStackTrace();
			JsonConfig config = new JsonConfig();	
			config.setExcludes(new String[]{"branchId"});//除去emps属性
			jsonArray=JSONArray.fromObject(list,config); 
		}
        JSONArray retjsonArray = new JSONArray();
        Object[] objj=jsonArray.toArray();
        for(Object obj:objj){
        	JSONObject json=IgnoreNullFieldForBean(obj);
        	Map map=json;
        	PO po=(PO) json.toBean(json,poClass);
            Map<String, Object> formatMap=po.formatValue(po);
    		for(Map.Entry entry:formatMap.entrySet()){
    			map.put((String) entry.getKey(), entry.getValue());
    		}
    		retjsonArray.add(map);
        }
        System.out.println("retjsonArray:----"+retjsonArray);
		return retjsonArray;
	}
}
