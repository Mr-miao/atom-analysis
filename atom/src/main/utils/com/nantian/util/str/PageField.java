package com.nantian.util.str;

public class PageField {
	public static final String VALID_EMAIL_MSG = "邮箱地址格式有误";
	public static final String VALID_NUMBER_MSG = "必须为纯数字";
	public static final String VALID_NUMBER5_MSG = "必须为##n##位数字";
	public static final String VALID_CHINESE_MSG = "请填写中文";
	public static final String VALID_MOBILE_MSG = "手机号码格式有误";
	public static final String VALID_IDCARD_MSG = "身份证号码格式有误";
	public static final String VALID_DATEFORMAT_MSG = "日期格式不正确";
	public static final String VALID_FLOAT_MSG = "必须为小数";
	public static final String VALID_MONEY_MSG = "金额格式输入错误";
	public static final String VALID_REQUIRED_MSG = "不能为空！";

	public static final String VALID_EMAIL = "email";
	public static final String VALID_NUM = "num";
	public static final String VALID_NUM5 = "num5";
	public static final String VALID_CHINESE = "chinese";
	public static final String VALID_MOBILE = "mobile";
	public static final String VALID_IDCARD = "idcard";
	public static final String VALID_DATEFORMAT = "dateformat";
	public static final String VALID_FLOAT_REQ = "float";
	public static final String VALID_MONEY = "money";
	public static final String VALID_REQUIRED = "required";
	public static final String VALID_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String VALID_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String CUMN_TYPE_DATE = "date";
	public static final String CUMN_TYPE_CHAR = "char";
	public static final String CUMN_TYPE_TEXT = "text";

	public static final Integer INPUTTYPE_TEXT = 1;// 文本框
	public static final Integer INPUTTYPE_SELECT = 2;// 下拉框
	public static final Integer INPUTTYPE_DATE = 3;// 日期控件
	public static final Integer INPUTTYPE_MONEY = 4;// 金额输入框
	public static final Integer INPUTTYPE_RADIO = 5;// 单选框
	public static final Integer INPUTTYPE_CHECKBOX = 6;// 复选框
	public static final Integer INPUTTYPE_TEXTAREA = 7;// 多行文本框
	public static final Integer INPUTTYPE_PWD = 8;// 密码框
	public static final Integer INPUTTYPE_TREE = 9;// 树状菜单
	public static final Integer INPUTTYPE_FILEUPLOAD = 10;// 文本上传
	public static final Integer INPUTTYPE_ADVSELECT = 11;// 高级下拉框
	public static final Integer INPUTTYPE_SEARCHSELECT = 12;// 快捷搜索式下拉
	// 表格域字段
	public static final String TB_DATA = "					'<td>'+notNull(ele[\"##field##\"])+'</td>'+" + "\n";
	/*
	 * 以下为查询区域
	 */
	// --------------查询区域-------start------
	public static final String QUERY_LABEL = "					<label for=\"##field##-query\">##chinese##</label>"
			+ "\n";
	// text类型
	public static final String TEXT_QUERY = "					<input type=\"text\" class=\"form-control\"  id=\"##field##-query\"  >"
			+ "\n";
	// 金额框查询
	public static final String MONEY_QUERY = "					<input type=\"text\" class=\"form-control\" form-type=\"money\" id=\"##field##-query\"  >"
			+ "\n";
	// 下拉框查询
	public static final String SELECT_QUERY = "					<select class=\"form-control\" id=\"##field##-query\"></select>"
			+ "\n";
	// 日期查询
	public static final String DATE_QUERY = "					<input type=\"text\" class=\"form-control\" form-type=\"date\" id=\"##field##-query\"  >"
			+ "\n";
	// 单选查询
	public static final String RADIO_QUERY = "					<div id=\"##field##-query\"  name=\"##field##-query\"></div>"
			+ "\n";
	// 多选查询
	public static final String CHECKBOX_QUERY = "					<div id=\"##field##-query\"  name=\"##field##-query\"></div>"
			+ "\n";
	// textarea
	public static final String TEXTAREA_QUERY = "					<textarea id=\"##field##-query\" class=\"form-control\" rows=\"3\">"
			+ "\n";
	// 密码
	public static final String PWD_QUERY = "					<input type=\"password\" class=\"form-control\" id=\"##field##-query\" name=\"##field##-query\" >"
			+ "\n";

	// --------------查询区域-------end------

	// 表格编辑
	public static final String TABLE_GRID = "				<th class=\"tb-sort\" col=\"##field##\">##chinese##</th>"
			+ "\n";
	public static final String CHINESE_20LABLE_REQUESTED = "				<th width=\"20%\">##chinese##<span>*</span></th>"
			+ "\n";
	public static final String CHINESE_20LABLE_NOREQUESTED = "				<th width=\"20%\">##chinese##</th>" + "\n";
	public static final String CHINESE_40LABLE_REQUESTED = "				<th >##chinese##<span>*</span></th>" + "\n";
	public static final String CHINESE_40LABLE_NOREQUESTED = "				<th >##chinese##</th>" + "\n";

	public static final String TDWIDTH30 = "<td width=\"30%\">" + "\n";
	public static final String TDWIDTH60 = "<td width=\"60%\">" + "\n";
	// --------------编辑区域-------start------
	// text类型
	public static final String TEXT_TYPE = "				<input type=\"text\" class=\"form-control\"  id=\"##field##\" name=\"##field##\" vtype=\"##vtype##\" vmsg=\"##vmsg##\" ##editabled## ##unique## ##foreign_key##>"
			+ "\n";
	// 金额框查询
	public static final String MONEY_TYPE = "				<input type=\"text\" class=\"form-control\" form-type=\"money\" id=\"##field##\" name=\"##field##\" vtype=\"##vtype##\" vmsg=\"##vmsg##\" ##editabled## ##unique## ##foreign_key##>"
			+ "\n";
	// 下拉框查询
	public static final String SELECT_TYPE = "				<select class=\"form-control\" id=\"##field##\" name=\"##field##\" ##editabled## ##unique## ##foreign_key##></select>"
			+ "\n";
	// 日期查询
	public static final String DATE_TYPE = "				<input type=\"text\" class=\"form-control\" form-type=\"date\" id=\"##field##\" name=\"##field##\" vtype=\"##vtype##\" vmsg=\"##vmsg##\" ##editabled## ##unique## ##foreign_key## >"
			+ "\n";

	// 单选查询
	public static final String RADIO_TYPE = "				<div id=\"##field##\"  name=\"##field##\"></div>" + "\n";
	// 多选查询
	public static final String CHECKBOX_TYPE = "				<div id=\"##field##\"  name=\"##field##\"></div>"
			+ "\n";
	// textarea
	public static final String TEXTAREA_TYPE = "				<textarea id=\"##field##\" name=\"##field##\" class=\"form-control\" rows=\"3\" ##editabled## ##unique## ##foreign_key##></textarea>"
			+ "\n";
	// 密码
	public static final String PWD_TYPE = "					<input type=\"password\" class=\"form-control\" id=\"##field##\" name=\"##field##\" vtype=\"##vtype##\" vmsg=\"##vmsg##\" ##editabled## ##unique## ##foreign_key##>"
			+ "\n";
			// --------------编辑区域-------end------

	// 初始化
	public static final String genSelectFromPara = "			genSelect(webPath+\"paramitem/_pagerParamitem.html\",{\"paraValue\":\"##json##\"},\"##elemId##\");";// 从数据字典中获取
	public static final String genSelectFromFun = "				genSelect(webPath+\"##jsonUrl##\",,\"##elemId##\");";// 从数据字典中获取

	public static final String genRadioFromPara = "				genRadio(webPath+\"paramitem/_pagerParamitem.html\",{\"paraValue\":\"##json##\"},\"##elemId##\");";// 从数据字典中获取
	public static final String genRadioFromFun = "				genRadio(webPath+\"##jsonUrl##\",,\"##elemId##\");";// 从数据字典中获取

	public static final String genCheckboxFromPara = "			genCheckbox(webPath+\"paramitem/_pagerParamitem.html\",{\"paraValue\":\"##json##\"},\"##elemId##\");";// 从数据字典中获取
	public static final String genCheckboxFromFun = "			genCheckbox(webPath+\"##jsonUrl##\",,\"##elemId##\");";// 从数据字典中获取

}
