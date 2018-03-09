package com.nantian.atom.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




/**
 *module entity. 模块表
 */
@Entity
@Table(name = "t_pagedsn_table", catalog = "")
public class PageDsnForTable  implements java.io.Serializable{

	// Fields

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 批次号
	 */
	private Integer batchId;
	/**
	 * 数据库字段名
	 */
	private String cumnName;

	/**
	 * 字段中文名
	 */
	private String cnName;
	/**
	 * 数据库字段类型
	 */
	private String cumnType;
	/**
	 * java域属性类型
	 */
	private String fieldType;
	/**
	 * 组件类型,
	 * 1:文本框
		2:下拉框
		3:日期控件
		4:金额输入框
		5:单选框
		6:复选框
		7:多行文本框
		8:密码框
	 */
	private Integer inputType;
	/**
	 * 表单校验类型
	 */
	private String checkType;
	/**
	 * 是否允许重复输入，1表示允许重复输入，0表示不可以重复输入
	 */
	private Integer ifRepeat;
	/**
	 * json格式
	 */
	private String jsonType;
	/**
	 * 字段是否在表格中显示，1表示在表格中显示，0表示不在表格中显示
	 */
	private Integer ifGrid;

	
	/**
	 * 导入时是否在excel中存在，1表示该字段需要在导入excel中显示，0表示不显示在excel中
	 */
	private Integer ifImport;
	/**
	 * 是否为外键
	 */
	private Integer ifForeginKey;
	/**
	 * 外键对应的表名
	 */
	private String foreginKeyTable;
	/**
	 * 外键对应的字段名，多个字段用","分割
	 */
	private String foreginKeyTableColums;
	
	/**
	 * 导入时格式化设置
	 */
	private String importFormat;
	
	/**
	 * 导出时格式化设置
	 */
	private String exportFormat;
	/**
	 * 导出时是否在excel中存在，1表示该字段将在导出的excel中，0表示不会在导出excel中
	 */
	private Integer ifExport;
	/**
	 * 是否查询条件字段呢，1表示是，0表示否
	 */
	private Integer ifQuery;
	/**
	 * 显示时是否占用两列，1表示是，0表示否
	 */
	private Integer ifTwice;
	/**
	 * 是否可编辑字段，1表示是，0表示否
	 */
	private Integer ifEdit;
	/**
	 * 导入数据来源，1表示excel取值，2表示
	 */
	private Integer importSource;
	/**
	 * 是否唯一
	 */
	private Integer ifUnique;
	
	@Column(name="ifUnique")
	public Integer getIfUnique() {
		return ifUnique;
	}

	public void setIfUnique(Integer ifUnique) {
		this.ifUnique = ifUnique;
	}

	/** min constructor */
	public PageDsnForTable() {
		
	}
	
	public PageDsnForTable(Integer id, Integer batchId, String cumnName, String cnName, String cumnType,
			String fieldType, Integer inputType, String checkType, Integer ifRepeat, String jsonType, Integer ifGrid,
			Integer ifImport, String importFormat, String exportFormat, Integer ifExport, Integer ifQuery,
			Integer ifTwice, Integer ifEdit, Integer importSource, Integer ifUnique) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.cumnName = cumnName;
		this.cnName = cnName;
		this.cumnType = cumnType;
		this.fieldType = fieldType;
		this.inputType = inputType;
		this.checkType = checkType;
		this.ifRepeat = ifRepeat;
		this.jsonType = jsonType;
		this.ifGrid = ifGrid;
		this.ifImport = ifImport;
		this.importFormat = importFormat;
		this.exportFormat = exportFormat;
		this.ifExport = ifExport;
		this.ifQuery = ifQuery;
		this.ifTwice = ifTwice;
		this.ifEdit = ifEdit;
		this.importSource = importSource;
		this.ifUnique = ifUnique;
	}


	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_PAGEDSN_TABLE_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "PageDsnForTableGenerator")    
	@GenericGenerator(name = "PageDsnForTableGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "batch_id")
	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	@Column(name = "cumn_name")
	public String getCumnName() {
		return cumnName;
	}

	public void setCumnName(String cumnName) {
		this.cumnName = cumnName;
	}
	@Column(name = "cn_name")
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	@Column(name = "cumn_type")
	public String getCumnType() {
		return cumnType;
	}

	public void setCumnType(String cumnType) {
		this.cumnType = cumnType;
	}
	@Column(name = "field_type")
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	@Column(name = "input_type")
	public Integer getInputType() {
		return inputType;
	}

	public void setInputType(Integer inputType) {
		this.inputType = inputType;
	}
	@Column(name = "check_type")
	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	@Column(name = "ifRepeat")
	public Integer getIfRepeat() {
		return ifRepeat;
	}

	public void setIfRepeat(Integer ifRepeat) {
		this.ifRepeat = ifRepeat;
	}
	@Column(name = "json_type")
	public String getJsonType() {
		return jsonType;
	}

	public void setJsonType(String jsonType) {
		this.jsonType = jsonType;
	}
	@Column(name = "ifGrid")
	public Integer getIfGrid() {
		return ifGrid;
	}

	public void setIfGrid(Integer ifGrid) {
		this.ifGrid = ifGrid;
	}
	@Column(name = "ifImport")
	public Integer getIfImport() {
		return ifImport;
	}

	public void setIfImport(Integer ifImport) {
		this.ifImport = ifImport;
	}
	@Column(name = "import_format")
	public String getImportFormat() {
		return importFormat;
	}

	public void setImportFormat(String importFormat) {
		this.importFormat = importFormat;
	}
	@Column(name = "export_format")
	public String getExportFormat() {
		return exportFormat;
	}

	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}
	@Column(name = "ifExport")
	public Integer getIfExport() {
		return ifExport;
	}

	public void setIfExport(Integer ifExport) {
		this.ifExport = ifExport;
	}
	@Column(name = "ifQuery")
	public Integer getIfQuery() {
		return ifQuery;
	}

	public void setIfQuery(Integer ifQuery) {
		this.ifQuery = ifQuery;
	}
	@Column(name = "ifTwice")
	public Integer getIfTwice() {
		return ifTwice;
	}

	public void setIfTwice(Integer ifTwice) {
		this.ifTwice = ifTwice;
	}
	@Column(name = "ifEdit")
	public Integer getIfEdit() {
		return ifEdit;
	}

	public void setIfEdit(Integer ifEdit) {
		this.ifEdit = ifEdit;
	}
	@Column(name = "import_source")
	public Integer getImportSource() {
		return importSource;
	}

	public void setImportSource(Integer importSource) {
		this.importSource = importSource;
	}
	@Column(name = "if_foregin_key")
	public Integer getIfForeginKey() {
		return ifForeginKey;
	}

	public void setIfForeginKey(Integer ifForeginKey) {
		this.ifForeginKey = ifForeginKey;
	}
	@Column(name = "foregin_key_table")
	public String getForeginKeyTable() {
		return foreginKeyTable;
	}

	public void setForeginKeyTable(String foreginKeyTable) {
		this.foreginKeyTable = foreginKeyTable;
	}
	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name="foregin_key_table_colums", columnDefinition="text", nullable=true)
	public String getForeginKeyTableColums() {
		return foreginKeyTableColums;
	}

	public void setForeginKeyTableColums(String foreginKeyTableColums) {
		this.foreginKeyTableColums = foreginKeyTableColums;
	}
	
	

}