package com.cxdai.console.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * <p>
 * Description:MD5工具类<br />
 * </p>
 * 
 * @title MD5.java
 * @package com.framework.util
 * @author gang.li
 * @version 0.1 2013年9月11日
 */
public class MD5 {
	/**
	 * 
	 * <p>
	 * Description:MD5加密（32位）<br />
	 * </p>
	 * 
	 * @author YangShiJin
	 * @version 0.1 2015年6月17日
	 * @param str
	 * @return String
	 */
	public static String toMD5(String str) {
		return DigestUtils.md5Hex(str);
	}

}
