package com.cxdai.console.customer.bankcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.bankcard.entity.MemberBankCardInfoCnd;
import com.cxdai.console.customer.bankcard.mapper.MemberBankCardInfoMapper;
/**
 * 
 * @author hujianpan
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class MemberBankCardService {

	@Autowired
	private MemberBankCardInfoMapper memberBankCardInfoMapper;

	@Transactional(rollbackFor = Throwable.class,readOnly = true)
	public Page queryMemberBankCardListWithPage(MemberBankCardInfoCnd memberBankCardInfoCnd, int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = memberBankCardInfoMapper.queryListCount(memberBankCardInfoCnd);
		page.setTotalCount(totalCount);
		page.setResult(memberBankCardInfoMapper.queryListForPage(memberBankCardInfoCnd, page));
		return page;
	}
	
	 

}
