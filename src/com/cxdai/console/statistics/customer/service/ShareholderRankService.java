package com.cxdai.console.statistics.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.customer.mapper.ShareholderRankMapper;
import com.cxdai.console.statistics.customer.vo.ShareholderRankCnd;
import com.cxdai.console.statistics.customer.vo.ShareholderRankVo;

/**
 * <p>
 * Description:股东加权业务类实现<br />
 * </p>
 * 
 * @title ShareholderRankServiceImpl.java
 * @package com.cxdai.portal.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年5月21日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ShareholderRankService {

	@Autowired
	private ShareholderRankMapper shareholderRankMapper;

	public Page queryPagesList(ShareholderRankCnd shareholderRankCnd, int pageNo, int pageSize) throws Exception {
		Page page = new Page(pageNo, pageSize);
		String addtime = shareholderRankMapper.findMaxAddtime();
		shareholderRankCnd.setAddtime(addtime);
		int totalCount = shareholderRankMapper.queryShareholderRankVoCount(shareholderRankCnd);
		page.setTotalCount(totalCount);
		if (pageNo <= 1) {
			shareholderRankCnd.set_offset(0);
		} else {
			shareholderRankCnd.set_offset((pageNo - 1) * pageSize);
		}
		shareholderRankCnd.set_limit(pageSize);
		List<ShareholderRankVo> list = shareholderRankMapper.queryShareholderRankVoForPages(shareholderRankCnd);
		page.setResult(list);
		return page;
	}

	public ShareholderRankVo queryById(int id) throws Exception {
		return shareholderRankMapper.queryById(id);
	}
}
