package com.cxdai.console.red.util;
public class WDWUtil {
	    public static boolean isExcel2003(String filePath)  {  
	        return filePath.matches("^.+\\.(?i)(xls)$");  
	    }  
	    public static boolean isExcel2007(String filePath)  {  
	        return filePath.matches("^.+\\.(?i)(xlsx)$");  
	    }  
}
