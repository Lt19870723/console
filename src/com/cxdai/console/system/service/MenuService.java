package com.cxdai.console.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.system.entity.Menu;
import com.cxdai.console.system.mapper.MenuMapper;
import com.cxdai.console.system.vo.MenuCnd;
import com.cxdai.console.system.vo.MenuVo;

/**
 * <p>
 * Description:基础资源管理-菜单资源管理服务实现类<br />
 * </p>
 * 
 * @title MenuManagerServiceImpl.java
 * @package com.cxdai.console.system.service.impl
 * @author hujianpan
 * @version 0.1 2015年3月4日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Map<String, List<Menu>> selectMenuTree() {
		Map<String, List<Menu>> ret = new HashMap<String, List<Menu>>();
		
		List<Menu> menus = menuMapper.selectMenuList(new MenuCnd(0), Page.DEFAULT);
		for (Menu menu : menus) {
			String key = String.valueOf(menu.getPid());
			if (!ret.containsKey(key)) {
				ret.put(key, new ArrayList<Menu>());
			}
			ret.get(key).add(menu);
		}
		return ret;
	}
	
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page selectMenuPage(MenuCnd menuCnd, Page page) {
		page.setTotalCount(menuMapper.countMenuList(menuCnd));
		if (page.getTotalPage() >= page.getPageNo()) {
			page.setResult(menuMapper.selectMenuList(menuCnd, page));
		}
		return page;
	}

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public MenuVo selectById(Integer menuId) {
		return menuMapper.selectById(menuId);
	}
	
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<Menu> selectByPid(Integer pid) {
		return menuMapper.selectByPid(pid);
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void saveOrupdate(Menu menu) throws Exception {
		if (menu.getId() == null) {
			menu.setStatus(0);
			menuMapper.insert(menu);
		} else {
			menuMapper.update(menu);
		}
	}

	@CacheEvict(value = "userMenuCache", allEntries = true)
	public void updateStatus(Integer menuId) {
		menuMapper.updateStatus(menuId);
		// menuMapper.deletePermissionResourcesByMenuId(menuId);
	}
}
