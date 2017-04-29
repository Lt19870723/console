package com.cxdai.console.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Menu;
import com.cxdai.console.system.entity.User;
import com.cxdai.console.system.vo.UserCnd;
import com.cxdai.console.system.vo.UserVo;

public interface UserMapper {
	
	public int countUserList(UserCnd userCnd);
	public List<User> selectUserList(UserCnd userCnd, Page page);

	public UserVo selectById(Integer userId);
	public User selectByUserName(String userName);

	public int insert(User user);
	public int insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	public int update(User user);
	public int updateUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
	
	public int updateStatus(Integer userId);

	public List<String> queryPermissionsByUserId(Integer userId);
	public List<String> queryRolesByUserId(Integer userId);
	public List<Menu> queryMenusByUserId(Integer userId);
	
	public int changePwd(UserCnd userCnd);
}