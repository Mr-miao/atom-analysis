package com.nantian.atom.po;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 *pageDsnForFunction entity. 页面设置之功能
 */
@Entity
@Table(name = "t_pagedsn_function", catalog = "")
public class PageDsnForFunction implements java.io.Serializable {
	
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
	 * 模块id
	 */
	private Integer moduleId;


	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 交易名
	 */
	private String tranCnName;
	
	/**
	 * 主键名称
	 */
	private String idComn;
	/**
	 * 主键生成策略
	 */
	private String idStrategy;
	/**
	 * 是否有新增功能,1表示有，0表示无
	 */
	private Integer addFlg;
	/**
	 * 是否有修改功能,1表示有，0表示无
	 */
	private Integer updateFlg;
	/**
	 * 是否有批量修改功能,1表示有，0表示无
	 */
	private Integer mulitUpdateFlg;
	/**
	 * 是否有删除功能,1表示有，0表示无
	 */
	private Integer deleteFlg;
	/**
	 * 是否有批量删除功能,1表示有，0表示无
	 */
//	private Integer mulitDelFlg;

	
	/**
	 * 是否有条件查询功能,1表示有，0表示无
	 */
	private Integer conditionFlg;
	
	/**
	 * 是否有高级条件功能,1表示有，0表示无
	 */
	private Integer advConditionFlg;
	/**
	 * 是否有导入功能,1表示有，0表示无
	 */
	private Integer importFlg;
	/**
	 * 是否有导出功能，1表示有，0表示无
	 */
	private Integer exportFlg;
	/**
	 * 导入方式，1表示清空数据后导入；2表示覆盖重复数据；3表示忽略重复数据导入
	 */
	private Integer importType;
	/**
	 *导出默认文件名
	 */
	private String defaultExportFileName;
	/** min constructor */
	public PageDsnForFunction() {
		
	}
	
	/** max constructor 
	 * @return */

	@Id
/*	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="SEQ_STORE")
	@SequenceGenerator(name="SEQ_STORE", sequenceName="T_PAGEDSN_FUNCTION_ID_SEQ",allocationSize=1)*/
	@GeneratedValue(generator = "TransactionGenerator")    
	@GenericGenerator(name = "TransactionGenerator", strategy = "increment")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public PageDsnForFunction(Integer id, Integer batchId, Integer moduleId, String tableName, String tranCnName,
			String idComn, String idStrategy, Integer addFlg, Integer updateFlg, Integer mulitUpdateFlg,
			Integer deleteFlg, Integer mulitDelFlg, Integer conditionFlg, Integer advConditionFlg, Integer importFlg,
			Integer exportFlg, Integer importType, String defaultExportFileName) {
		super();
		this.id = id;
		this.batchId = batchId;
		this.moduleId = moduleId;
		this.tableName = tableName;
		this.tranCnName = tranCnName;
		this.idComn = idComn;
		this.idStrategy = idStrategy;
		this.addFlg = addFlg;
		this.updateFlg = updateFlg;
		this.mulitUpdateFlg = mulitUpdateFlg;
		this.deleteFlg = deleteFlg;
//		this.mulitDelFlg = mulitDelFlg;
		this.conditionFlg = conditionFlg;
		this.advConditionFlg = advConditionFlg;
		this.importFlg = importFlg;
		this.exportFlg = exportFlg;
		this.importType = importType;
		this.defaultExportFileName = defaultExportFileName;
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

	@Column(name = "moduleId")
	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	
	@Column(name = "table_name")
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "id_comn")
	public String getIdComn() {
		return idComn;
	}

	public void setIdComn(String idComn) {
		this.idComn = idComn;
	}
	@Column(name = "id_strategy")
	public String getIdStrategy() {
		return idStrategy;
	}

	public void setIdStrategy(String idStrategy) {
		this.idStrategy = idStrategy;
	}

	@Column(name = "add_flg")
	public Integer getAddFlg() {
		return addFlg;
	}

	public void setAddFlg(Integer addFlg) {
		this.addFlg = addFlg;
	}
	@Column(name = "update_flg")
	public Integer getUpdateFlg() {
		return updateFlg;
	}

	public void setUpdateFlg(Integer updateFlg) {
		this.updateFlg = updateFlg;
	}
	@Column(name = "mulit_update_flg")
	public Integer getMulitUpdateFlg() {
		return mulitUpdateFlg;
	}

	public void setMulitUpdateFlg(Integer mulitUpdateFlg) {
		this.mulitUpdateFlg = mulitUpdateFlg;
	}
	@Column(name = "delete_flg")
	public Integer getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(Integer deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
//	@Column(name = "mulit_del_flg")
//	public Integer getMulitDelFlg() {
//		return mulitDelFlg;
//	}
//
//	public void setMulitDelFlg(Integer mulitDelFlg) {
//		this.mulitDelFlg = mulitDelFlg;
//	}
	@Column(name = "condition_flg")
	public Integer getConditionFlg() {
		return conditionFlg;
	}

	public void setConditionFlg(Integer conditionFlg) {
		this.conditionFlg = conditionFlg;
	}
	@Column(name = "adv_condition_flg")
	public Integer getAdvConditionFlg() {
		return advConditionFlg;
	}

	public void setAdvConditionFlg(Integer advConditionFlg) {
		this.advConditionFlg = advConditionFlg;
	}
	@Column(name = "import_flg")
	public Integer getImportFlg() {
		return importFlg;
	}

	public void setImportFlg(Integer importFlg) {
		this.importFlg = importFlg;
	}
	@Column(name = "export_flg")
	public Integer getExportFlg() {
		return exportFlg;
	}

	public void setExportFlg(Integer exportFlg) {
		this.exportFlg = exportFlg;
	}
	@Column(name = "import_type")
	public Integer getImportType() {
		return importType;
	}

	public void setImportType(Integer importType) {
		this.importType = importType;
	}
	@Column(name = "default_export_file_name")
	public String getDefaultExportFileName() {
		return defaultExportFileName;
	}

	public void setDefaultExportFileName(String defaultExportFileName) {
		this.defaultExportFileName = defaultExportFileName;
	}
	@Column(name = "tran_cn_name")
	public String getTranCnName() {
		return tranCnName;
	}

	public void setTranCnName(String tranCnName) {
		this.tranCnName = tranCnName;
	}



}