package com.nantian.atom.power;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nantian.atom.dao.impl.CommonDao;
import com.nantian.atom.util.DynamicDataSource;

 
 /*面向切面编程, 动态代理. Aspect声明切面, Component初始化. */
   
@Aspect  
@Component  
public class PowerInterceptor  
{  
	/**
	 * DAO层
	 */
	protected @Autowired CommonDao dao;
	

    //这个可用来替代以后重复出现的. 直接在后面的Before("myMethod()")就行了.  
//    @Pointcut("execution(public * com.nantian.atom.service.*.*(..))")  
	@Pointcut("execution(* com.nantian.atom.web.CommonController.*.*(..))")
    public void myMethod(){
    };  
      
    //下面用到的是织入点语法, 看文档里面有. 就是指定在该方法前执行  
//    @Before("execution(public void com.dao.impl.UserDAOImp.save(com.model.User))")  
    //记住下面这种通用的, *表示所有  com.nantian.atom.service.demo.impl
//    @Pointcut("execution(public * com.nantian.atom.service.*.*(..))")  
//  	@Pointcut("execution(public * com.nantian.atom.service..*.*(..))")
    public void beforeMethod()  
    {  
        System.out.println("save start......");  
    }  
      
    //正常执行完后  
//    @AfterReturning("execution(public * com.nantian.atom.service.*.*(..))")  
    public void afterReturnning()  
    {  
        System.out.println("after save......");  
    }  
      
    //抛出异常时才调用  
//    @AfterThrowing("myMethod()")  
    public void afterThrowing()  
    {  
        System.out.println("after throwing......");  
    }  
      
    //环绕, 这个特殊点.  
    @Around("myMethod()")  
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable
    {  
    	Object result = null;
    	
        //加逻辑的时候, 不要依赖执行的的先后顺序  
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String smethod=signature.getMethod().getName();
        //判断功能权限
    	if(ifOptByRoleFromUrl(smethod)){//是否有权限操作
			result = pjp.proceed();
        }else{
       	 	System.out.println("没有执行" + DynamicDataSource.getServerName() + "." + smethod + "()方法的权限"); 
        }
        return result;
    }  
    
     /* 
      * 根据角色判断是否有方法操作权限
      * */
     
    public boolean ifOptByRoleAndMethod(String methodName){
    	//获取roleId
    	List<Integer> roleId=DynamicDataSource.getRoleId();
    	String temp = "(";
    	for (int i=0;i<roleId.size();i++) {
    		if(i==roleId.size()-1){
    			temp += roleId.get(i)+")";
    		}else{
    			temp += roleId.get(i) + ",";
    		}
		}
    	String server_name=DynamicDataSource.getServerName();
    	server_name = server_name.replaceAll("ServiceImpl", "");
    	String sql="select 1 from t_opt_role t,t_opt o where t.opt_id=o.id and  t.role_id IN"+temp+" and o.method='"+methodName+"' and o.server_name='"+server_name+"'";
    	List list=dao.findAllBySql(sql, null);
    	return list.size()>0?true:false;
    }
    public boolean ifOptByRoleFromUrl(String methodName){
    	//获取roleId
    	List<Integer> roleId=DynamicDataSource.getRoleId();
    	String temp = "(";
    	for (int i=0;i<roleId.size();i++) {
    		if(i==roleId.size()-1){
    			temp += roleId.get(i)+")";
    		}else{
    			temp += roleId.get(i) + ",";
    		}
		}
    	String server_name=DynamicDataSource.getServerName();
    	server_name = server_name.replaceAll("ServiceImpl", "");
    	String sql="select 1 from t_opt_role t,t_opt o where t.opt_id=o.id and  t.role_id IN"+temp+" and o.method='"+methodName+"' and o.server_name='"+server_name+"'";
    	List list=dao.findAllBySql(sql, null);
    	return list.size()>0?true:false;
    }
   /* 获取表达式*/
     
    public String getExpression(String methodName){
    	//获取roleId
    	String server_name=DynamicDataSource.getServerName();
    	String sql="select o.expression from t_opt o where  o.method='"+methodName+"' and o.server_name='"+server_name+"'";
    	List list=dao.findAllBySql(sql, null);
    	return list.get(0).toString();
    }
}  