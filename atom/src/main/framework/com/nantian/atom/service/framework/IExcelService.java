package com.nantian.atom.service.framework;

import java.util.List;

import com.nantian.atom.po.PageDsnForTable;
import com.nantian.atom.service.mysql.IMysqlService;

/**
 * Excel导入导出
 * 
 *
 */
public interface IExcelService extends IMysqlService {
	public <T> void exportDataToExcel(String path, Class<T> cls)
			throws Exception;

	public <T> void importDataFromExcel(String path, Class<T> cls)
			throws Exception;

	public void genExcelModule(String path, String tableName) throws Exception;

	public List<PageDsnForTable> getPageDsnTableListByTableName(String tableName);

	public <T> String queryExportFileName(Class<T> cls);
}
