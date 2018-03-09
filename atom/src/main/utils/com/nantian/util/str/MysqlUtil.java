package com.nantian.util.str;

public class MysqlUtil {
	
	public static final String COPY_DATA_TO_TABLE_SQL = 
			"INSERT INTO ##tableName## SELECT * FROM temp_table";
	
	public static final String CREATE_TEMP_TABLE_SQL = 
			"CREATE TABLE temp_table (SELECT * FROM ##tableName## WHERE 1=2)";
	
	public static final String DROP_TEMP_SQL = "DROP TABLE IF EXISTS temp_table";
	
	public static final String DELETE_TABLE_DATA_SQL = 
			"DELETE FROM ##tableName## "
					+"WHERE CONCAT(##uniqueArr##) "
					+"IN (SELECT CONCAT(##uniqueArr##) FROM temp_table)";
	
	public static final String DELETE_TEMP_DATA_SQL = 
			"DELETE FROM temp_table "
					+"WHERE CONCAT(##uniqueArr##) "
					+"IN (SELECT CONCAT(##uniqueArr##) FROM ##tableName##)";
	
	public static final String QUERY_PARA_VALUE_HQL = "SELECT paraValue FROM Paramitem GROUP BY paraValue";
	
	public static final String DELETE_OLD_DATA_HQL = "DELETE FROM ##tableName##";
	
	public static final String INSERT_FROM_TEMP_SQL = "INSERT INTO ##tableName## SELECT * FROM temp_table";
	
	public static final String EXPORT_FILE_NAME_HQL = "SELECT defaultExportFileName FROM PageDsnForFunction "
			+"WHERE batchId=(SELECT MAX(batchId) FROM PageDsnForFunction WHERE tableName=:tableName)";
	
	public static final String QUERY_ROLEID_BY_USERID_HQL = "SELECT roleId FROM UserRole "
			+"WHERE userId=:userId";
	
	//格式化sql语句
	public static String formatSql(String srcSql,String src,String des){
		return srcSql.replaceAll(src, des);
	}
	
	 
			

}
