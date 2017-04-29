package com.cxdai.console.util;


/**
 * @author WangQianJin
 * @title 银行卡常量类
 * @date 2016年6月14日
 */
public interface BankConstants {
	
	public static final String bank_id_url = PropertiesUtil.getValue("bank_id_url");
	public static final String bank_user_id = PropertiesUtil.getValue("bank_user_id");
	public static final String bank_aesKey = PropertiesUtil.getValue("bank_aesKey");
	public static final String bank_mKey = PropertiesUtil.getValue("bank_mKey");
	
}
