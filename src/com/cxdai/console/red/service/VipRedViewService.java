package com.cxdai.console.red.service;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.reward.mapper.BaseMemberAccumulatePointsMapper;
import com.cxdai.console.base.mapper.BaseMemberMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.red.entity.VipRedView;
import com.cxdai.console.red.mapper.VipRedViewMapper;
import com.cxdai.console.red.vo.VipRedImportCnd;
import com.cxdai.console.security.ShiroUser;
import com.cxdai.console.util.ShiroUtils;

/**
 * 红包管理-贵族特权红包查看
 * @author liutao
 * @Date 2015-11-11
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class VipRedViewService {
	private final static Logger logger = Logger.getLogger(RedRecordLogService.class);
	@Autowired
	private BaseMemberAccumulatePointsMapper baseMemberAccumulatePointsMapper;
	@Autowired
	private BaseMemberMapper baseMemberMapper;
	@Autowired
	private VipRedViewMapper vipRedViewMapper;
	
	/**
	 * 红包管理-查询贵族特权红包查看集合
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryVipRedViewVoList(VipRedImportCnd vipRedImportCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = vipRedViewMapper.queryVipRedViewVoCount(vipRedImportCnd);
		page.setTotalCount(totalCount);
		List<VipRedView> list = vipRedViewMapper.queryVipRedViewVoList(vipRedImportCnd, page);
		page.setResult(list);
		return page;
	}
	/**
	 * 红包管理-新增贵族特权红包日志表
	 * @author liutao
	 * @Date 2015-11-11
	 */
	public int add(VipRedView vipRedView) throws Exception {
		ShiroUser shiroUser = ShiroUtils.currentUser();
		vipRedView.setAddUserId(shiroUser.getUserId());
		vipRedView.setAddTime(new Date());
		return vipRedViewMapper.add(vipRedView);
	}
	/**
	 * 红包管理-根据红包ID查询贵族特权红包
	 * @author liutao
	 * @Date 2015-11-11
	 */
	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public List<VipRedView> queryRedByRedId(String id) throws Exception {
		List<VipRedView> list = vipRedViewMapper.queryRedByRedId(id);
		return list;
	}
}
