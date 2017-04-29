package com.cxdai.console.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.entity.IPWhileList;
import com.cxdai.console.base.mapper.BaseIPWhileListMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.system.mapper.IPWhileListMapper;
import com.cxdai.console.system.vo.IPWhileListCnd;
import com.cxdai.console.system.vo.IPWhileListVo;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.ShiroUtils;
/**
 * 
 * <p>
 * Description:IP白名单业务类<br />
 * </p>
 * @title IPWhileService.java
 * @package com.cxdai.console.system.service 
 * @author yubin
 * @version 0.1 2015年7月5日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class IPWhileService {
	@Autowired
	private BaseIPWhileListMapper baseIPWhileListMapper;
	@Autowired
	private IPWhileListMapper iPWhileListMapper;

	/**
	 * 
	 * <p>
	 * Description:IP白名单列表分页<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月5日
	 * @param iPWhileListCnd
	 * @param curPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * Page
	 */
	public Page queryListForPage(IPWhileListCnd iPWhileListCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = iPWhileListMapper.queryListCount(iPWhileListCnd);
		page.setTotalCount(totalCount);
		List<IPWhileListVo> list = iPWhileListMapper.queryListForPage(iPWhileListCnd, page);
		page.setResult(list);
		return page;
	}

	/**
	 * 
	 * <p>
	 * Description:新增或修改白名单<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月5日
	 * @param iPWhileList
	 * @return
	 * @throws Exception
	 * String
	 */
	public String insertOrUpdateIpWhileList(IPWhileList iPWhileList) throws Exception {
		if (iPWhileList.getId() != null && iPWhileList.getId().intValue() > 0) { // 修改
			IPWhileList iPWhileList_Old = baseIPWhileListMapper.queryById(iPWhileList.getId());
			iPWhileList.setStatus(iPWhileList_Old.getStatus());
			iPWhileList.setAddStaffId(iPWhileList_Old.getAddStaffId());
			iPWhileList.setAddtime(iPWhileList_Old.getAddtime());

			ShiroUser shiroUser = ShiroUtils.currentUser();
			iPWhileList.setUpdateTime(DateUtils.getCurrentTimeStamp());
			iPWhileList.setUpdateStaffId(shiroUser.getUserId());
			if (baseIPWhileListMapper.updateEntity(iPWhileList) > 0) {
				return "success";
			} else {
				return "修改失败";
			}
		} else { // 新增
			iPWhileList.setAddtime(DateUtils.getCurrentTimeStamp());
			ShiroUser shiroUser = ShiroUtils.currentUser();
			iPWhileList.setAddStaffId(shiroUser.getUserId());
			if (baseIPWhileListMapper.insertEntity(iPWhileList) > 0) {
				return "success";
			} else {
				return "新增失败";
			}
		}
	}

	/**
	 * 
	 * <p>
	 * Description:删除白名单<br />
	 * </p>
	 * @author yubin
	 * @version 0.1 2015年7月5日
	 * @param id
	 * @return
	 * @throws Exception
	 * String
	 */
	public String delIpWhileList(int id) throws Exception {
		IPWhileList iPWhileList = baseIPWhileListMapper.queryById(id);
		if (iPWhileList != null) {
			iPWhileList.setStatus(1);
			ShiroUser shiroUser = ShiroUtils.currentUser();
			iPWhileList.setUpdateStaffId(shiroUser.getUserId());
			iPWhileList.setUpdateTime(DateUtils.getCurrentTimeStamp());
			if (baseIPWhileListMapper.updateEntity(iPWhileList) > 0) {
				return "删除成功";
			}
			;
		}
		return "删除失败";
	}

	public IPWhileList queryById(int id) throws Exception {
		return baseIPWhileListMapper.queryById(id);
	}
}
