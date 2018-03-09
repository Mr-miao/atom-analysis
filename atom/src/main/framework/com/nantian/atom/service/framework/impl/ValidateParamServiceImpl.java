package com.nantian.atom.service.framework.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Table;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.nantian.atom.context.ValidateParam;
import com.nantian.atom.service.BaseServiceImpl;
import com.nantian.atom.service.framework.IValidateParamService;
import com.nantian.util.context.comm.AppContext;

/**
 * 验证框架
 * @author xurui
 *
 */
@Service("validateParamServiceImpl")
public class ValidateParamServiceImpl extends BaseServiceImpl implements IValidateParamService{
	
	/**
	 * 对validateParam.getUniqueT所标记的属性对应的值进行唯一性检查
	 */
	@Override
	public  <PO> boolean validateUnique(PO po,ValidateParam validateParam, ModelMap map) {
		//获取id的值
		Object id = new Object();
		try{
			Method idMethod1 = po.getClass().getMethod("getId");
			id = idMethod1.invoke(po);
		}catch(Exception e){
			//log.error("获取主键的方法不是getId()", e);
			try {
				Method idMethod2 = po.getClass().getMethod("get"+po.getClass().getSimpleName()+"Id");
				id = idMethod2.invoke(po);
			} catch (Exception e1) {
				log.error("获取主键的方法不是get"+po.getClass().getName()+"Id()", e);
				map.put(AppContext.EXT_MSG, "操作失败");
				map.put(AppContext.FLAG, -1);
				return false;
			}
		}
		//首先得到属性名
			String[] names = validateParam.getUniqueT();
			if(names!=null && names.length>0){
				for(String fieldName : names){
					try {
						Method m = po.getClass().getMethod(
								"get" + fieldName.substring(0, 1).toUpperCase()
											+ fieldName.substring(1));
						Object obj=m.invoke(po);
						String poClassName = po.getClass().getName();
						String hql = "";
						Map<String, Object> params = new HashMap<String, Object>(1);
						if(id==null){
							hql = "SELECT COUNT(1) FROM "+poClassName+" WHERE "+fieldName+" =:value";
							params.put("value", obj);
						}else{
							hql = "SELECT COUNT(1) FROM "+poClassName+" WHERE "+fieldName+" =:value AND id NOT IN(:notValue)";
							params.put("value", obj);
							params.put("notValue", id);
						}
						Long cnt = iBasePoDao.count(hql, params);
						
						if(cnt>0){//存在重复数据
							log.info(fieldName+"字段，值为"+obj+"存在重复数据");
							map.put(AppContext.EXT_MSG, obj+"存在重复数据");
							map.put(AppContext.FLAG, -1);
							return false;
						}
					} catch (Exception e) {
						log.error("操作失败", e);
						map.put(AppContext.EXT_MSG, "操作失败");
						map.put(AppContext.FLAG, -1);
						return false;
					} 
				}
			}
			
			return true;
			
	}
}
