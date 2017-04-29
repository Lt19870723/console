package com.cxdai.console.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

 

public class WebServiceMD5 {
	/**
	 * <p>
	 * Description:验证传进来的参数map值与验签的字符串是否匹配<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月28日
	 * @param params
	 * @param md5SignMsg
	 * @return boolean
	 */
	public static boolean validateServiceKey(Map<String, Object> paramsMap, String md5SignMsg) {
		if (null == paramsMap || paramsMap.size() == 0 || null == md5SignMsg || "".equals(md5SignMsg.trim())) {
			return false;
		}
		if (null == PropertiesUtil.getValue("webservicekey") || "".equals(PropertiesUtil.getValue("webservicekey"))) {
			return false;
		}
		String validateMsg = generateToMd5String(paramsMap);
		if (!MD5.toMD5(validateMsg).equals(md5SignMsg)) {
			return false;
		}
		return true;
	}

	/**
	 * <p>
	 * Description:加密验签字符串<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月28日
	 * @param params
	 * @return String
	 */
	public static String encodeParams(Map<String, Object> params) {
		if (null == params || params.size() == 0) {
			return "";
		}
		return MD5.toMD5(generateToMd5String(params));
	}

	/**
	 * <p>
	 * Description:生成webservice加密字符串<br />
	 * </p>
	 * 
	 * @author justin.xu
	 * @version 0.1 2014年11月28日
	 * @param needMd5String
	 * @return String
	 */
	private static String generateToMd5String(Map<String, Object> params) {
		StringBuffer content = new StringBuffer();
		// 按照key做首字母升序排列
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = String.valueOf(params.get(key));
			// 空串不参与签名
			if (null == value || value.equalsIgnoreCase("null") || value.equals("")) {
				continue;
			}
			content.append((i == 0 ? "" : "&") + key + "=" + value);
		}
		// 添加webserviceKey
		content.append("&serviceKey=" + PropertiesUtil.getValue("webservicekey"));
		String signSrc = content.toString();
		if (signSrc.startsWith("&")) {
			signSrc = signSrc.replaceFirst("&", "");
		}
		return signSrc;
	}
}
