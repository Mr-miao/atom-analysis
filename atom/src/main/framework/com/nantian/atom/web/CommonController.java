package com.nantian.atom.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.util.WebUtils;

import com.nantian.atom.context.ReqParam;
import com.nantian.atom.context.ValidateParam;
import com.nantian.atom.generated.po.rolem.User;
import com.nantian.atom.po.OptRoleList;
import com.nantian.atom.po.UserRoleList;
import com.nantian.atom.power.GetApplicationContext;
import com.nantian.atom.service.CommonService;
import com.nantian.atom.service.framework.IValidateParamService;
import com.nantian.util.context.comm.AppContext;
import com.nantian.util.paser.json.JsonUtil;

@Controller
public class CommonController {

	@Autowired
	protected IValidateParamService iValidateParamService;
	protected Logger log = LogManager.getLogger(CommonController.class);

	/**
	 * 请求参数封装 将前缀为reqParam.的属性绑定到 名称为reqParam的对象
	 * 
	 * @param binder
	 */
	@InitBinder("reqParam")
	public void initBinderReqParam(WebDataBinder binder) {

		binder.setFieldDefaultPrefix("reqParam.");
	}

	/***
	 * 根据方法名称取得反射方法的参数类型(没有考虑同名重载方法使用时注意)
	 * 
	 * @param obj 类实例
	 * 
	 * @param methodName 方法名
	 * 
	 * @return
	 * 
	 * @throws ClassNotFoundException
	 */

	public static Class[] getMethodParamTypes(Class objclass, String methodName)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Class[] paramTypes = null;

		Class<?> clazz = Class.forName(objclass.getName());
		Object obj = objclass.newInstance();

		Method[] methods = clazz.getDeclaredMethods();// 获取本类中的所有方法，包括私有的(private、protected、默认以及public)的方法
		for (int i = 0; i < methods.length; i++) {
			if (methodName.equals(methods[i].getName())) {// 和传入方法名匹配
				Class[] params = methods[i].getParameterTypes();
				paramTypes = new Class[params.length];
				for (int j = 0; j < params.length; j++) {
					paramTypes[j] = Class.forName(params[j].getName());
				}
				break;
			}
		}

		if (paramTypes == null) {// 如果为空，获取父类方法
			methods = obj.getClass().getMethods();// 获取本类以及父类或者父接口中所有的公共方法(public修饰符修饰的)
			for (int i = 0; i < methods.length; i++) {
				if (methodName.equals(methods[i].getName())) {// 和传入方法名匹配
					Class[] params = methods[i].getParameterTypes();
					paramTypes = new Class[params.length];
					for (int j = 0; j < params.length; j++) {
						paramTypes[j] = Class.forName(params[j].getName());
					}
					break;
				}
			}
		}
		return paramTypes;
	}

	@RequestMapping(value = "/**/*.html", method = RequestMethod.POST)
	public String entrance(HttpServletRequest request, ModelMap map,
			 ValidateParam validateParam,
			ReqParam reqParam, OptRoleList optRoleList,UserRoleList userRoleList) throws Exception {
		String reqUrl=request.getServletPath();
		if(reqUrl.contains("?")){
			reqUrl=reqUrl.split("\\?")[0];
		}
		CommonService service = GetApplicationContext.getBean("commonService");
		// 根据请求地址获取server名、server对应的方法名、一直操作对象
		String opthql = "select  t.serEnName,o.optMethod ,o.poName FROM Opt o,Transaction t WHERE   o.optServerId=t.id and  o.reqUrl = '"
				+ reqUrl + "'";
		System.out.println("request.getServletPath():-----"
				+ request.getServletPath());
		Map<String, Object> params = new HashMap<String, Object>();
		List<Object[]> serverInfo = service.findAllByHql(opthql, params);
		if (serverInfo == null || serverInfo.size() <= 0) {
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG,
					"该请求地址非法,url:" + request.getRequestURI());
			System.out.println("--------------------该请求地址非法,url:"
					+ request.getRequestURI());
			return AppContext.JSON_VIEW;
		}
		if(!service.validPower(request.getServletPath())){//是否有权限
			System.out.println("没有访问" + request.getServletPath() + "的权限"); 
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG,
					"没有访问" + request.getServletPath() + "的权限");
			return AppContext.JSON_VIEW;
		}
		String serverName = (String) serverInfo.get(0)[0];// 服务名---对应@Service

		String methodName = (String) serverInfo.get(0)[1];// 服务名中方法
		String poName = (String) serverInfo.get(0)[2];// 服务操作对象
		Object server = GetApplicationContext.getBean(serverName);// 获取服务实例
		

		System.out.println("request:"+request.getParameterMap());
		System.out.println("server:"+serverName+",methodName "+methodName);
		
		Class<? extends Object> classForServer = server.getClass();
		Class[] paramTypes = getMethodParamTypes(classForServer, methodName);// 调用方法参数类型列表
		Method method = classForServer
				.getDeclaredMethod(methodName, paramTypes);// 获取到方法
		Object[] obj = new Object[paramTypes.length];
		// 是否对象请求
		Object bean = null;
		Map objMap=getT( poName, request);// 对象赋值
		bean =objMap.get("bean") ;// 对象赋值
		Map paraMap=(Map) objMap.get("paraMap") ;// 对象赋值
		// 在保存之前，对validateParam.getUniqueT 所标记的属性对应的值进行唯一性检查

		if (!iValidateParamService.validateUnique(bean, validateParam, map)) {

			return AppContext.JSON_VIEW;
		}


		// 参数传值
		for (int i = 0; i < paramTypes.length; i++) {// 参数类型,目前只支持三种类型，对象，ModelMap，ReqParam,String约定是主键，并且名称必须是recordId
			Class<?> paramType = paramTypes[i];
			System.out.println("参数类型：---" + paramType.getName());
			String typeName=paramType.getName();
			if (paramType.getName().equals(bean.getClass().getName())
					|| paramType.getName().equals("com.nantian.atom.po.BaseInterfaceVO")) {

				    String contentType = request.getContentType();
				    if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {//上传
				        MultipartHttpServletRequest multipartRequest =
				                WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
				    Map<String,String[]> parmMap= multipartRequest.getParameterMap();
				     
	
					     obj[i] =JsonUtil.mapToBean(paraMap, bean) ;
				    }else{
				    	obj[i] =bean;
				    }
					
					
			} else if (paramType.getName().equals(
					"org.springframework.ui.ModelMap")) {
				obj[i] = map;
			} else if (paramType.getName().equals("java.util.Map")
					|| paramType.getName().equals("java.util.HashMap")) {
				obj[i] = getReqParam( request);
			} else if (paramType.getName()
					.equals(reqParam.getClass().getName())) {
				obj[i] = reqParam;
			} else if (paramType.getName().equals("java.lang.String")) {
				System.out.println("paramType.getName():"+paramType.getName());
				System.out.println("bean.name():"+bean.toString());
				System.out.println("request:"+request.getParameterMap());
				System.out.println("server:"+serverName+",methodName "+methodName);
				//		String methodName = (String) serverInfo.get(0)[1];// 服务名中方法
//				String poName = (String) serverInfo.get(0)[2];// 服务操作对象
				if (bean.toString().endsWith("#")) {
					obj[i] = bean.toString().substring(0,
							bean.toString().lastIndexOf("#"));
				} else {
					obj[i] = bean;
				}
			} else if (paramType.getName().equals("java.lang.Class")) {
				obj[i] = GetApplicationContext.getBean(poName).getClass();
			} else if (paramType.getName().equals("java.io.Serializable")) {
				if (paraMap != null && paraMap instanceof Map) {
//					Map<String,Object> m = (Map<String,Object>) bean;
					Iterator<?> it = paraMap.entrySet().iterator();
					Map.Entry entry = (Map.Entry) it.next();
					String value=(String) entry.getValue();
					if(value!=null&&!"".equals(value)&&!"undefined".equals(value)){
						obj[i] = Integer.parseInt(value);
					}else{
						obj[i] =0;
					}
					
				}
			} else if (paramType.getName().equals(
					"javax.servlet.http.HttpServletRequest")) {
				obj[i] = request;
			}else if (paramType.getName().equals(
					"com.nantian.atom.generated.po.rolem.User")) {
				User user = (User) request.getSession().getAttribute("user");
				obj[i] = user;
			} else if (paramType.getName().equals(
					"com.nantian.atom.po.OptRoleList")) {
				obj[i] = optRoleList;
			}else if (paramType.getName().equals(
					"com.nantian.atom.po.UserRoleList")) {
				obj[i] = userRoleList;
			}else if (paramType.getName().equals(
					"org.springframework.web.multipart.MultipartFile")) {
				
			    String contentType = request.getContentType();
			    if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			        MultipartHttpServletRequest multipartRequest =
			                WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
			     Map<String, MultipartFile> filemap=   multipartRequest.getFileMap();
			     for (Map.Entry<String, MultipartFile> entry : filemap.entrySet()) {
//				        MultipartFile file = multipartRequest.getFile("file");   
				        obj[i] = entry.getValue();
			    	   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			     }

			    }else{
			    	obj[i] =null;
			    }
				
				
			}else if (paramType.getName().equals(
					"[Lorg.springframework.web.multipart.MultipartFile;")) {
				
			    String contentType = request.getContentType();
			    if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			        MultipartHttpServletRequest multipartRequest =
			                WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
			     Map<String, MultipartFile> filemap=   multipartRequest.getFileMap();
			    
			     
				     for (Map.Entry<String, MultipartFile> entry : filemap.entrySet()) {
				    	 
				    List<MultipartFile> mfList=	 multipartRequest.getFiles(entry.getKey());
				    MultipartFile[] mfs= new MultipartFile[mfList.size()];
				    for(int m=0 ;m<mfList.size();m++){
				    	MultipartFile mf=mfList.get(m);
				    	mfs[m]=mf;
				    }
				   
				   

				    obj[i] = mfs;
				     }
				     
			    }else{
			    	obj[i] =null;
			    }
				
				
			}
		}

		Object retobj = method.invoke(server, obj);

		return AppContext.JSON_VIEW;
	}

	@RequestMapping(value = "/**/_*visit*.html", method = RequestMethod.GET)
	public String visitPage(HttpServletRequest request, ModelMap map) {
		CommonService service = GetApplicationContext.getBean("commonService");
		// 根据请求地址获取server名、server对应的方法名、一直操作对象
		String opthql = "select  o.optMethod ,o.poName,o.respUrl FROM Opt o WHERE   o.reqUrl='"
				+ request.getServletPath() + "'";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Object[]> serverInfo = service.findAllByHql(opthql, params);
		if (serverInfo == null || serverInfo.size() <= 0) {
			map.addAttribute(AppContext.FLAG, -1);
			map.addAttribute(AppContext.EXT_MSG,
					"该请求地址非法,url:" + request.getRequestURI());
			return AppContext.JSON_VIEW;
		}
		String respUrl = (String) serverInfo.get(0)[2];// 服务名中方法
		return respUrl;
	}

	/*
     * 
     */

	public Map<String,Object> getT( String poName, HttpServletRequest request)
			throws Exception {
		Map<String,Object> retMap=new HashMap<String,Object>();
		Object po = GetApplicationContext.getBean(poName);
		Class clazz = po.getClass();
		Object bean = null;
		bean = clazz.newInstance();


		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map map = request.getParameterMap();
		if(map!=null){
			for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
				Map.Entry element = (Map.Entry) iter.next();
				// key值
				String proName = (String) element.getKey();
				// value,数组形式
				String[] values = (String[]) element.getValue();
//				dataMap.put((String) strKey, value[0]);
//				System.out.print(strKey.toString() + "=");
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					if("".equals(value)||value.isEmpty()){
						continue;
					}
					try {
//						if(!"pageSize".equals(proName)){
							Field field = clazz.getDeclaredField(proName);// 可以访问私有属性
							field.setAccessible(true); // 设置些属性是可以访问的
							System.out.println("name:" + field.getName() + "\t value = "
									+ value);
							String type = field.getType().toString();// 得到此属性的类型
							if (type.startsWith("class")) {
								type = type.substring(5);
								type = type.trim();
							}
							if ("java.lang.Integer".equals(type) || "int".equals(type)) {
								field.set(bean, Integer.parseInt(value));
							} else if ("java.lang.String".equals(type)) {
								field.set(bean, value);
							} else if ("java.util.Date".equals(type)) {
								Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
								field.set(bean, date);
							} else if ("char".equals(type)) {
								char[] cha = value.toCharArray();
								field.set(bean, cha);
							} else if ("boolean".equals(type)) {
								if ("true".equals(value)) {
									field.set(bean, true);
								} else {
									field.set(bean, false);
								}
							} else if ("short".equals(type)) {
								Short shortvalule = new Short(value);
								field.set(bean, shortvalule);
							} else if ("long".equals(type)) {
								field.set(bean, Long.parseLong(value));
							} else if ("byte".equals(type)) {
								byte[] bytes = value.getBytes();
								field.set(bean, bytes);
							} else if ("double".equals(type)) {
								field.set(bean, Double.parseDouble(value));
							} else if ("float".equals(type)) {

								field.set(bean, Float.parseFloat(value));

							} else {// 其他未外键关联
								System.out.println("---------");
							}
//						}

					} catch (NoSuchFieldException e) {// 外键关联属性
						if (proName.contains(".") && !proName.contains("validateParam")
								&& !proName.contains("reqParam")
								&& !proName.contains("optRoles")
								&& !proName.contains("userRoles")) {
							String proName_fgk = proName.split("\\.")[0];

							String proName_id = proName.split("\\.")[1];
							Field field = clazz.getDeclaredField(proName_fgk);
							
							
							field.setAccessible(true); // 设置些属性是可以访问的
							String type = field.getType().toString();// 得到外键对应的类型
							if (type.startsWith("class")) {
								type = type.substring(5);
								type = type.trim();
							}
							CommonService service = GetApplicationContext
									.getBean("commonService");
							Class poClass = Class.forName(type);
							String IdType = poClass.getDeclaredField(proName_id)
									.getType().toString().substring(5).trim();// z
							Object po_fpk = null;
							if ("java.lang.Integer".equals(IdType)
									|| "int".equals(IdType)) {

								po_fpk = service
										.getPO(poClass, Integer.parseInt(value));// 查询外键对象

							} else if ("java.lang.String".equals(IdType)) {
								try{
									po_fpk = service.getPO(poClass, Integer.parseInt(value));// 查询外键对象
								}catch(Exception ex){
							
									String methodName = "set"+proName_id.substring(0, 1).toUpperCase()+ proName_id.substring(1);
									 Method m1 = poClass.getDeclaredMethod(methodName,String.class);
									 po_fpk=poClass.newInstance();
									 m1.invoke(po_fpk, value);
									
//									ex.printStackTrace();
								}
							} else if ("java.util.Date".equals(IdType)) {
								Date date = new SimpleDateFormat("yyyy-MM-dd")
										.parse(value);
								po_fpk = service.getPO(poClass, date);// 查询外键对象
							} else if ("char".equals(IdType)) {
								char[] cha = value.toCharArray();
								po_fpk = service.getPO(poClass, cha);// 查询外键对象
							} else if ("boolean".equals(IdType)) {
								if ("true".equals(value)) {
									po_fpk = service.getPO(poClass, true);// 查询外键对象
								} else {
									po_fpk = service.getPO(poClass, false);// 查询外键对象
								}
							} else if ("short".equals(IdType)) {
								Short shortvalule = new Short(value);
								po_fpk = service.getPO(poClass, shortvalule);// 查询外键对象
							} else if ("long".equals(IdType)) {
								po_fpk = service.getPO(poClass, Long.parseLong(value));// 查询外键对象
							} else if ("byte".equals(IdType)) {
								byte[] bytes = value.getBytes();
								po_fpk = service.getPO(poClass, bytes);// 查询外键对象
							} else if ("double".equals(IdType)) {
								po_fpk = service.getPO(poClass,
										Double.parseDouble(value));// 查询外键对象
							} else if ("float".equals(IdType)) {

								po_fpk = service
										.getPO(poClass, Float.parseFloat(value));// 查询外键对象
							}

							field.set(bean, po_fpk);// 对象赋值
						} else {
							
							if (!proName.contains("validateParam")) {
								dataMap.put(proName, value);
							}
							System.out.println("属性名：" + proName);

						}
					}
					
					
					//----
				}
			
			}
		}
		retMap.put("bean", bean);
		if (!dataMap.isEmpty() ) {
			retMap.put("paraMap", dataMap);
			
		}
		return retMap;

	}
	

	
	public void typeConversion(Object bean,Field field){
		
	}
	
	
	public static void main(String[] args) {}

	/**
	 * 将前缀为validateParam.的属性绑定到 名称为reqParam的对象
	 * 
	 * @param binder
	 */
	@InitBinder("validateParam")
	public void initBinderValidateParam(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("validateParam.");
	}
	/*
	 * 从request中获得参数Map，并返回可读的Map
	 */
	public Map getReqParam( HttpServletRequest request) {
	    // 参数Map
	    Map properties = request.getParameterMap();
	    // 返回值Map
	    Map returnMap = new HashMap();
	    Iterator entries = properties.entrySet().iterator();
	    Map.Entry entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Map.Entry) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i=0;i<values.length;i++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else{
	            value = valueObj.toString();
	        }
	        returnMap.put(name, value);
	    }
	    return returnMap;
	}

	protected void responseJson(Object object, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		if (object != null) {
			try {
				out = response.getWriter();
				out.write(object.toString());
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			} finally {
				if (out != null) {
					out.close();
				}
			}
		} else {
			try {
				out = response.getWriter();
				out.append("");
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			} finally {
				if (out != null) {
					out.close();
				}
			}
		}
	}
}
