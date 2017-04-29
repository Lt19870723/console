package com.cxdai.console.borrow.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.approve.vo.BorrowVo;
import com.cxdai.console.borrow.manage.mapper.QueryBorrowRocordMapper;
import com.cxdai.console.borrow.manage.vo.BorrowCnd;
import com.cxdai.console.common.page.Page;

/**
 * <p>
 * Description:<br />
 * </p>
 * 
 * @title CashRecordServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author 
 * @version 
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class QueryBorrowRocordService  {
	
	@Autowired
	private QueryBorrowRocordMapper queryBorrowRocordActionMapper;

 
	public Page searchPageBorrowList(BorrowCnd repaymentBorrowCnd,
			Integer curPage, Integer pageSize) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = queryBorrowRocordActionMapper
				.queryBorrowRecordCount(repaymentBorrowCnd);
		page.setTotalCount(totalCount);
		List<BorrowVo> list = queryBorrowRocordActionMapper.queryBorrowRecordList(
				repaymentBorrowCnd, page);
		page.setResult(list);
		return page;
	}
 
	public List<BorrowVo> searchListBorrowList(BorrowCnd repaymentBorrowCnd) throws Exception {
		List<BorrowVo> list = queryBorrowRocordActionMapper.queryBorrowRecordList(
				repaymentBorrowCnd);
		return list;
	}

}
