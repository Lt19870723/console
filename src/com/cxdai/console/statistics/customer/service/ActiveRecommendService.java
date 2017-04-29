package com.cxdai.console.statistics.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.mapper.ActiveRecommendMapper;
import com.cxdai.console.statistics.customer.vo.InvetedUserVo;
import com.cxdai.console.statistics.customer.vo.RecommendAwardCnd;
import com.cxdai.console.statistics.customer.vo.RecommendUserCountCnd;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ActiveRecommendService {

	@Autowired
	ActiveRecommendMapper activeRecommendMapper;

	public Page queryPageRecommendUserCountList(RecommendUserCountCnd recommendAwardCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);

		page.setTotalCount(activeRecommendMapper.queryCountRecommendUserCountList(recommendAwardCnd));
		page.setResult(activeRecommendMapper.queryRecommendUserCountList(recommendAwardCnd, page));

		return page;
	}

	public Page queryPageRecommendAwardList(RecommendAwardCnd recommendAwardCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);

		page.setTotalCount(activeRecommendMapper.queryCountRecommendAwardList(recommendAwardCnd));
		page.setResult(activeRecommendMapper.queryRecommendAwardList(recommendAwardCnd, page));

		return page;
	}

	public Page queryInviteUserPageByUser(RecommendAwardCnd recommendAwardCnd,int pageNo, int pageSize) {
		Page page = new Page(pageNo,pageSize);
		page.setTotalCount(activeRecommendMapper.queryCountInviteUserListByUser(recommendAwardCnd));
		page.setResult(activeRecommendMapper.queryInviteUserListByUser(recommendAwardCnd, page));
		return page;
	}

	public Page queryUsercollectionPageByInviteUser(RecommendAwardCnd recommendAwardCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setTotalCount(activeRecommendMapper.queryCountUsercollectionListByInviteUser(recommendAwardCnd));
		page.setResult(activeRecommendMapper.queryUsercollectionListByInviteUser(recommendAwardCnd, page));
		return page;
	}

	public InvetedUserVo getRecharge() {
		return activeRecommendMapper.getRecharge();
	}
	
	public InvetedUserVo queryDetailInviteUserPageById(int id){
		return activeRecommendMapper.queryDetailInviteUserById(id);
	}

}
