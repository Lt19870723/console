package com.cxdai.console.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.index.vo.UserMenuInfo;
import com.cxdai.console.system.mapper.UserMapper;
import com.cxdai.console.system.vo.UserCnd;
import com.cxdai.console.system.vo.UserVo;
import com.cxdai.console.util.MD5;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserService {
	@Autowired
	private UserMapper userMapper;

	@Cacheable(value = "userMenuCache", key = "#userId")
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public UserMenuInfo queryMenusByUserId(Integer userId) {
		return new UserMenuInfo(userMapper.queryMenusByUserId(userId));
	}
	
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page selectUserPage(UserCnd userCnd, Page page) {
		page.setTotalCount(userMapper.countUserList(userCnd));
		if (page.getTotalPage() >= page.getPageNo()) {
			page.setResult(userMapper.selectUserList(userCnd, page));
		}
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public UserVo selectById(Integer userId) {
		return userMapper.selectById(userId);
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void saveOrUpdate(UserVo user) throws Exception {
		if (user.getId() == null) {
			user.setStatus(0);
			user.setPassword(MD5.toMD5("123456"));
			userMapper.insert(user);
			userMapper.insertUserRole(user.getId(), user.getRoleId());
		} else {
			userMapper.update(user);
			userMapper.updateUserRole(user.getId(), user.getRoleId());
		}
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void updateStatus(Integer userId) throws Exception {
		userMapper.updateStatus(userId);
	}
	
	public void changePwd(UserCnd userCnd) throws Exception {
		userMapper.changePwd(userCnd);
	}
}
