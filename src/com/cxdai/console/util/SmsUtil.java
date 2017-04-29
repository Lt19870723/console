package com.cxdai.console.util;

import java.util.Map;

/**
 * 
 * <p>
 * Description:短信发送帮助类<br />
 * </p>
 * 
 * @title SmsUtil.java
 * @package com.cxdaiweb.sms.util
 * @author gang.li
 * @version 0.1 2013年9月12日
 */
public class SmsUtil {

	public static String generateParamContent(String smsTempale, Map<String, Object> paramMap) {
		String smsContent = smsTempale;
		if (null != paramMap) {
			for (String key : paramMap.keySet()) {
				if (smsContent.indexOf(key) != -1) {
					smsContent = smsContent.replace("${" + key + "}", String.valueOf(paramMap.get(key)));
				}

			}
		}
		return smsContent;
	}

	/**
	 * 
	 * <p>
	 * Description:获取验证码<br />
	 * </p>
	 * 
	 * @author chenlu
	 * @version 0.1 2014年8月31日
	 * @return String
	 */
	public static String getRandCode() {
		String s = "";
		while (s.length() < 6) {
			s += (int) (Math.random() * 10);
		}
		return s;
	}

}
