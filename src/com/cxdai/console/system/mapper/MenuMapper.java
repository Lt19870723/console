package com.cxdai.console.system.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Menu;
import com.cxdai.console.system.vo.MenuCnd;
import com.cxdai.console.system.vo.MenuVo;

public interface MenuMapper {
	
	public int countMenuList(MenuCnd menuCnd);

	public List<Menu> selectMenuList(MenuCnd menuCnd, Page page);

	public MenuVo selectById(Integer menuId);

	public List<Menu> selectByPid(Integer pid);
	
	public int insert(Menu menu) throws Exception;

	public int update(Menu menu) throws Exception;
	
	public int updateStatus(Integer menuId);

	public int deletePermissionResourcesByMenuId(Integer menuId);
}