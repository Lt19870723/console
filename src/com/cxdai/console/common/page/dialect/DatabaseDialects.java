package com.cxdai.console.common.page.dialect;

/**
 * Typesafe enum of known database dialects
 * 
 * @author Qiongbiao Zhang
 * 
 */
public enum DatabaseDialects {

	DB2, MYSQL, SQLSERVER, ORACLE, CLOUDSCAPE, DERBY, HSQLDB, SYBASE, DB2_MF, INFORMIX;

	public static DatabaseDialects getDatabaseDialect(String database) {
		DatabaseDialects returnValue = null;

		if ("MySQL".equalsIgnoreCase(database)) {
			returnValue = MYSQL;
		} else if ("Oracle".equalsIgnoreCase(database)) {
			returnValue = ORACLE;
		} else if ("DB2".equalsIgnoreCase(database)) {
			returnValue = DB2;
		} else if ("SqlServer".equalsIgnoreCase(database)) {
			returnValue = SQLSERVER;
		} else if ("Cloudscape".equalsIgnoreCase(database)) {
			returnValue = CLOUDSCAPE;
		} else if ("Derby".equalsIgnoreCase(database)) {
			returnValue = DERBY;
		} else if ("HSQLDB".equalsIgnoreCase(database)) {
			returnValue = HSQLDB;
		} else if ("SYBASE".equalsIgnoreCase(database)) {
			returnValue = SYBASE;
		} else if ("DB2_MF".equalsIgnoreCase(database)) {
			returnValue = DB2_MF;
		} else if ("Informix".equalsIgnoreCase(database)) {
			returnValue = INFORMIX;
		}

		return returnValue;
	}
}
