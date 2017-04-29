package com.cxdai.console.common;

import static com.cxdai.console.util.Utils.decodeURL;
import static com.cxdai.console.util.Utils.encodeURL;

import org.apache.shiro.SecurityUtils;

import com.cxdai.console.security.ShiroUser;

/**
 * <p>
 * Description:自定义el表达式<br />
 * </p>
 * 
 * @title ELTag.java
 * @package com.cxdai.bbs.tag
 * @author qiongbiao.zhang
 * @version 0.1 2014年6月17日
 */
public class ELTag {
	public static String encode(String input) {
		return encodeURL(input);
	}

	public static String decode(String input) {
		return decodeURL(input);
	}

	public static ShiroUser currentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

	public static boolean isPermitted(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}
	
	public static String desc(String type, String name) {
		return Dictionary.getValue(type, name);
	}
}
