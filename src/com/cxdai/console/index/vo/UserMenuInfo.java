package com.cxdai.console.index.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.cxdai.console.system.entity.Menu;

public class UserMenuInfo implements Serializable {
	private static final long serialVersionUID = 3960227714081611107L;

	public UserMenuInfo() {
	}

	public UserMenuInfo(List<Menu> menus) {
		addMenus(menus);
	}

	private Integer userId;
	// 菜单列表<pid,children>
	private Map<Integer, List<Menu>> menuMap = new LinkedHashMap<Integer, List<Menu>>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Menu> getTopMenus() {
		List<Menu> menus = menuMap.get(0);
		return sort(menus);
	}

	public Map<Menu, List<Menu>> getLeftMenus(Integer pid) {
		Map<Menu, List<Menu>> ret = new LinkedHashMap<Menu, List<Menu>>();

		List<Menu> leftMenus = sort(menuMap.get(pid));
		for (Menu menu : leftMenus) {
			ret.put(menu, sort(menuMap.get(menu.getId())));
		}
		return ret;
	}

	public void addMenus(List<Menu> menus) {
		for (Menu menu : menus) {
			addMenu(menu);
		}
	}

	public void addMenu(Menu menu) {
		Integer pid = menu.getPid();
		if (!menuMap.containsKey(pid)) {
			menuMap.put(pid, new ArrayList<Menu>());
		}
		menuMap.get(pid).add(menu);
	}

	private List<Menu> sort(List<Menu> menus) {
		if (CollectionUtils.isEmpty(menus)) {
			return Collections.emptyList();
		}
		Collections.sort(menus, new Comparator<Menu>() {
			public int compare(Menu m1, Menu m2) {
				return m1.getOrder().compareTo(m2.getOrder());
			}
		});
		return menus;
	}
}
