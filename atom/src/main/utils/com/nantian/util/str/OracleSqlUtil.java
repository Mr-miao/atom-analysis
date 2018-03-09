package com.nantian.util.str;

public class OracleSqlUtil {
	public static final String CREATE_TEMP_TABLE_SQL = 
			"CREATE GLOBAL TEMPORARY TABLE temp_table ON COMMIT delete ROWS "+
			"AS "+
			"SELECT * FROM ##tableName##";
	
	public static final String DROP_TEMP_SQL = "call proc_dropifexists('temp_table')";
	
	public static final String INSERT_FROM_TEMP_SQL = "INSERT INTO ##tableName## SELECT * FROM ##tempTableName##";
	
	public static final String DELETE_TEMP_DATA_SQL = 
			"DELETE FROM ##tempTableName## "
					+"WHERE ##uniqueArr## "
					+"IN (SELECT ##uniqueArr## FROM ##tableName##)";
	
	public static final String DELETE_TABLE_DATA_SQL = 
			"DELETE FROM ##tableName## "
					+"WHERE ##uniqueArr## "
					+"IN (SELECT ##uniqueArr## FROM ##tempTableName##)";

}
