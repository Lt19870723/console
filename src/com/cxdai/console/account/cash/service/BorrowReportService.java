package com.cxdai.console.account.cash.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.cash.mapper.BorrowReportMapper;

/**
 * <p>
 * Description:借款统计信息业务类实现<br />
 * </p>
 * 
 * @title BorrowReportServiceImpl.java
 * @package com.cxdai.console.account.service.impl
 * @author justin.xu
 * @version 0.1 2014年6月26日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BorrowReportService {

	@Autowired
	private BorrowReportMapper borrowReportMapper;

	public BigDecimal queryRepaymentAccountTotalByMemberIdAndBorrowType(Integer memberId, Integer borrowType) throws Exception {
		BigDecimal result = borrowReportMapper.queryRepaymentAccountTotalByMemberIdAndBorrowType(memberId, borrowType);
		if (null != result) {
			return result;
		}
		return new BigDecimal(0);
	}
}
