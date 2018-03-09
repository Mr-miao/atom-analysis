package com.nantian.atom.generated.po.parameter;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.GenerationType;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.nantian.atom.po.BaseInterfaceVO;


@Entity
@Table(name = "t_parameter_conf", catalog = "")
@Component("parameterConf")
public class ParameterConf extends  BaseInterfaceVO implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	   /**
   * 参数ID
   */
   private Integer id;
   /**
   * 参数名称
   */
   private String paraName;
   /**
   * 参数值
   */
   private String paraValue;
   /**
   * 参数描述
   */
   private String paraDesc;
   /**
    * 参数英文名称
    */
    private String paraEnglishName;


	/** min constructor */
	public ParameterConf() {
		
	}


	/** max constructor 
	 * @return */
	public  ParameterConf(Integer id,String paraName,String paraValue,String paraDesc,String paraEnglishName) {
		super();
		this.id=id;
		this.paraName=paraName;
		this.paraValue=paraValue;
		this.paraDesc=paraDesc;
		this.paraEnglishName = paraEnglishName;

	}

	
	// Property accessors
	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_PARAMETER_CONF_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "ParameterConfGenerator")    
	@GenericGenerator(name = "ParameterConfGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
		@Column(name="para_name" )
	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	@Column(name="para_value" )
	public String getParaValue() {
		return paraValue;
	}

	public void setParaValue(String paraValue) {
		this.paraValue = paraValue;
	}
	@Column(name="para_desc" )
	public String getParaDesc() {
		return paraDesc;
	}

	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc;
	}

	@Column(name="para_english_name" )
	public String getParaEnglishName() {
		return paraEnglishName;
	}
	
	
	public void setParaEnglishName(String paraEnglishName) {
		this.paraEnglishName = paraEnglishName;
	}


	@Override
	public Map<String, Object> formatValue(Object po) {
		Map<String, Object> map=new HashMap<String, Object>();
		return map;
	}


}
