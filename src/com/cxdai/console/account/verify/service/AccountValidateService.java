package com.cxdai.console.account.verify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.verify.mapper.AccountValidateMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.statistics.account.vo.AccountValidateCnd;

/**
 * 
 * <p>
 * Description:资金验证<br />
 * </p>
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AccountValidateService{
	
	@Autowired
	private AccountValidateMapper accountValidateMapper;

	public Page queryAccountValidateListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageAccountValidateListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountAccountValidateListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryCollectionValidateListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageCollectionValidateListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountCollectionValidateListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryRepaymentValidateListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageRepaymentValidateListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountRepaymentValidateListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryTenderRepaymentAccountListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageTenderRepaymentAccountListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountTenderRepaymentAccountListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryCollectionAdvanceYAListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageCollectionAdvanceYAListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountCollectionAdvanceYAListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryAccountAndLogListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageAccountAndLogListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountAccountAndLogListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryTenderCollectionRepayAListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageTenderCollectionRepayAListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountTenderCollectionRepayAListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryCRSumCapitalToOtherListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageCRSumCapitalToOtherListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountCRSumCapitalToOtherListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryCRSumCapitalListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageCRSumCapitalListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountCRSumCapitalListByCnd(accountValidateCnd));
		return page;
	}

	public Page queryCSumCapitalToTenderListByCnd(AccountValidateCnd accountValidateCnd, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(accountValidateMapper.queryPageCSumCapitalToTenderListByCnd(accountValidateCnd, page));
		page.setTotalCount(accountValidateMapper.queryCountCSumCapitalToTenderListByCnd(accountValidateCnd));
		return page;
	}

}
