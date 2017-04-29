package com.cxdai.console.customer.information.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.UnusualAccountMapper;
import com.cxdai.console.customer.information.vo.UnusualAccountCnd;
import com.cxdai.console.customer.information.vo.UnusualAccountVo;

/**
 * <p>
 * Description:客户信息 - 异常账户服务类<br />
 * </p>
 * 
 * @title UnusualAccountService.java
 * @package com.cxdai.console.customer.information.service
 * @author hujianpan
 * @version 0.1 2015年3月11日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class UnusualAccountService {
	@Autowired
	private UnusualAccountMapper unusualAccountMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryAccountUnusualForPage(UnusualAccountCnd unusualAccountCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = unusualAccountMapper.queryAccountUnusualForCounts(unusualAccountCnd);
		page.setTotalCount(totalCount);
		List<UnusualAccountVo> list = unusualAccountMapper.queryAccountUnusualForPage(unusualAccountCnd, page);
		page.setResult(list);
		return page;
	}
}
