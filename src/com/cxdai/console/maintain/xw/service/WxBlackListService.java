package com.cxdai.console.maintain.xw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.maintain.xw.entity.BlackList;
import com.cxdai.console.maintain.xw.entity.BlackListVo;
import com.cxdai.console.maintain.xw.entity.WxBlackListCnd;
import com.cxdai.console.maintain.xw.entity.WxBlackListVo;
import com.cxdai.console.maintain.xw.mapper.WxBlackListMapper;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.ShiroUtils;

/**
 * 
 * <p>
 * Description:黑名单业务处理方法<br />
 * </p>
 * 
 * @title BlackListServiceImpl.java
 * @package com.cxdai.console.member.service.impl
 * @author yangshijin
 * @version 0.1 2015年1月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class WxBlackListService{

	@Autowired
	private WxBlackListMapper blackListMapper;
	@Autowired
	private MemberMapper memberMapper;

	
	public Page queryPageListByCnd(WxBlackListCnd blackListCnd, int curPage, int pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = 0;
		totalCount = blackListMapper.queryBlackListCountForWeiXin(blackListCnd);
		page.setTotalCount(totalCount);
		if (page.getTotalPage() < page.getPageNo()) {
			page.setPageNo(page.getTotalPage());
		}
		List<BlackListVo> list = new ArrayList<BlackListVo>();
		list = blackListMapper.queryBlackListForWeiXin(blackListCnd, page);
		page.setResult(list);
		return page;
	}

	
	public String updateBlackList(WxBlackListVo blackListVo, String ip) throws Exception {
		String remark = blackListVo.getUpdateRemark();
		if (blackListVo.getId() == null) {
			return "该记录不存在，请刷新页面后重试！";
		}
		blackListVo = blackListMapper.queryByIdForUpdate(blackListVo.getId());
		if (blackListVo == null || blackListVo.getId() == null) {
			return "该记录不存在，请刷新页面后重试！";
		}
		blackListVo.setStatus(-1); // 失效
		ShiroUser shiroUser = ShiroUtils.currentUser();
		int userid = shiroUser.getUserId();
		blackListVo.setUpdateId(userid);
		blackListVo.setUpdateIp(ip);
		blackListVo.setUptime(new Date());
		blackListVo.setUpdateRemark(remark);
		BlackList blackList = new BlackList();
		BeanUtils.copyProperties(blackListVo, blackList);
		blackListMapper.updateEntity(blackList);
		return "success";
	}

}
