package com.cxdai.console.sycee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.reward.entity.MemberAccumulatePoints;
import com.cxdai.console.account.reward.mapper.BaseMemberAccumulatePointsMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.sycee.entity.MemberAccumulatePointsCnd;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountSyceeService {

	@Autowired
	BaseMemberAccumulatePointsMapper accumulatePointsMapper;

	/**
	 * 用户元宝明细分页查询
	 * <p>
	 * Description:这里写描述<br />
	 * </p>
	 * @author huangpin
	 * @version 0.1 2015年10月19日
	 * @param cnd
	 * @param page
	 * @return
	 * @throws Exception Page
	 */
	public Page pageQuery(MemberAccumulatePointsCnd cnd, Page page) throws Exception {
		int totalCount = accumulatePointsMapper.pageQueryCount(cnd);
		page.setTotalCount(totalCount);
		List<MemberAccumulatePoints> list = accumulatePointsMapper.pageQuery(cnd, page);
		page.setResult(list);
		return page;
	}
}
