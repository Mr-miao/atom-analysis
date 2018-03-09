package com.nantian.util.context.comm;

public class AppContext {
	/**
	 * 以json方式返回给前台的是否成功标识 0-成功
	 */
	public static final String WEBPATH="E:/workspace/atom-standard-v1";
	public static final String DB_NAME="db_atom"; 
	public static final String FLAG = "flag";
	public static final String FLAG_SUCCESS = "0";
	public static final String FLAG_ERROR = "99";
	public static final String DATA = "data";
	public static final String PAGER = "pager";
	public static final int PAGER_SIZE = 10;
	public static final String TOTAL = "total";

	
	public static final String FILETYPE_MEDIA = "media";
	public static final String FILETYPE_SOFT = "soft";
	public static final String PAGE_TOTAL = "total";
	public static final String PAGE_DATA = "data";
	public static final String PAGE_CURRENT_PAGE = "currentPage";
	public static final String PAGE_LAST_PAGE = "lastPage";//最后一页
	public static final String PAGE_HAS_PREV = "hasPrev";
	public static final String PAGE_HAS_LAST = "hasLast";
	public static final String PAGE_TOTAL_PAGE = "totalPage";
	
	public static final String EXT_SUCCESS = "success";
	public static final String EXT_MSG = "msg";
	public static final String EXT_DATA = "data";
	public static final String EXT_TOTAL = "total";
	
	public static final String SESSION_USER = "user";
	public static final String SESSION_ADMIN = "admin";
	public static final String SESSION_CHECKCODE = "checkcode";
	
	
	public static final String JSON_VIEW = "_json";
	public static final String EXCEL_COMN_LIST = "excelcomnList";
	public static final String KEY_SEQ = "keySeq";
	public static final String FILE_UPLOAD_PATH = "WEB-INF/re/upload/";
	
	public static final String FILE_DOWNLOAD_PATH = "WEB-INF/re/download/";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_UPLOAD_PDF_PATH = "WEB-INF/re/upload/pdf/";
	
	public static final int LONG_CONNECTION_PORT = 7000;
	
	public static final String teller_path = "download/";
	/**
	 * Agent端口
	 */
	public static final String AGENT_LOCAL_PORT_NAME = "AgentPort";
	public static final int AGENT_LOCAL_PORT = 55002;
	
}
