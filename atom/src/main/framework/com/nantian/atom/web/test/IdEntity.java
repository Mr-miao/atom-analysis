package com.nantian.atom.web.test;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
* 功能说明：统一定义id的entity基类
*     基类统一定义id的属性名称、数据类型、列名映射及生成策略
*     子类可重载getId()函数重定义id的列名映射和生成策略
*     本系统采用UUID主键策略
* @author ducc
* @created 2014年6月12日 下午3:06:02
* @updated
*/
//JPA 基类的标识
@MappedSuperclass
public abstract class IdEntity {

/*	@Id
	@GeneratedValue(generator = "paymentableGenerator")    
	@GenericGenerator(name = "paymentableGenerator", strategy = "increment") 
//	@Column(name = "id", unique = true, nullable = false)
protected Integer id;
public Integer getId() {

return id;

}
public void setId(Integer id) {

this.id = id;

}*/
/**dataTable 全局搜索值，用户封装，不持久化到数据库*/
/*@Transient
private String searchValue;
public String getSearchValue() {

return searchValue;

}
public void setSearchValue(String searchValue) {

this.searchValue = searchValue;

}*/


}