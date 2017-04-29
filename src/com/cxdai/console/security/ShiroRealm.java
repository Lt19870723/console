package com.cxdai.console.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxdai.console.system.entity.User;
import com.cxdai.console.system.mapper.UserMapper;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;

	public ShiroRealm() {
		setAuthenticationTokenClass(UsernamePasswordToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;

		User user = userMapper.selectByUserName(usernamePasswordToken.getUsername());
		if (user == null) {
			throw new UnknownAccountException("Unknown username [" + usernamePasswordToken.getUsername() + "]");
		}
		if (!user.getStatus().equals(0)) {
			throw new DisabledAccountException("Disabled account [" + usernamePasswordToken.getUsername() + "]");
		}

		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUserName());
		shiroUser.setUserName(user.getName());
		shiroUser.setUserId(user.getId());
		return new SimpleAuthenticationInfo(shiroUser, user.getPassword(), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(userMapper.queryRolesByUserId(shiroUser.getUserId()));
		info.addStringPermissions(userMapper.queryPermissionsByUserId(shiroUser.getUserId()));
		return info;
	}
}
