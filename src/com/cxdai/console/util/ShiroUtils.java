package com.cxdai.console.util;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;

import com.cxdai.console.security.ShiroUser;

public class ShiroUtils {

	public static ShiroUser currentUser() {
		return (ShiroUser) SecurityUtils.getSubject().getPrincipal();
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}

	public static boolean hasAnyRoles(String... roles) {
		Subject subject = SecurityUtils.getSubject();
		if (CollectionUtils.isEmpty(subject.getPrincipals())) {
			return false;
		}
		for (String role : roles) {
			if (subject.hasRole(role)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasAllRoles(String... roles) {
		if (ArrayUtils.isEmpty(roles)) {
			return false;
		}
		return SecurityUtils.getSubject().hasAllRoles(Arrays.asList(roles));
	}

	public static boolean isPermitted(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}

	public static boolean isPermittedAny(String... permissions) {
		if (ArrayUtils.isEmpty(permissions)) {
			return false;
		}
		Subject subject = SecurityUtils.getSubject();
		if (CollectionUtils.isEmpty(subject.getPrincipals())) {
			return false;
		}
		for (String permission : permissions) {
			if (subject.isPermitted(permission)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPermittedAll(String... permissions) {
		if (ArrayUtils.isEmpty(permissions)) {
			return false;
		}
		return SecurityUtils.getSubject().isPermittedAll(permissions);
	}
}
