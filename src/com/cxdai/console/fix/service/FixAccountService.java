package com.cxdai.console.fix.service;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.fix.mapper.FixAccountMapper;
import com.cxdai.console.fix.vo.FixAccountVo;
import com.cxdai.console.fix.vo.FixBorrowCnd;

/**
 * 定期宝service类
 * <p>
 * Description:<br />
 * </p>
 * 
 * @title FixBorrowService.java
 * @package com.cxdai.console.fix.service
 * @author 陈建国
 * @version 0.1 2015年5月28日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class FixAccountService {

	public Logger logger = Logger.getLogger(FixAccountService.class);

	@Autowired
	private FixAccountMapper fixAccountMapper;

	public BigDecimal queryProfitByBetweenDate(FixBorrowCnd fixBorrowCnd)
			throws Exception {
		BigDecimal result = fixAccountMapper
				.queryProfitByBetweenDate(fixBorrowCnd);
		// 如果查询时间段内的利息收入不为空
		if (result != null) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	public BigDecimal queryPayMentByBetweenDate(FixBorrowCnd fixBorrowCnd)
			throws Exception {
		BigDecimal result = fixAccountMapper
				.queryPayMentByBetweenDate(fixBorrowCnd);
		// 查询时间段内的支出利息不为空
		if (result != null) {
			return result;
		}
		return BigDecimal.ZERO;
	}

	public FixAccountVo queryProfitBycontractNo(FixBorrowCnd fixBorrowCnd)
			throws Exception {
		return fixAccountMapper.queryProfitBycontractNo(fixBorrowCnd);
	}

	public BigDecimal queryFixUseMoneyTotal() throws Exception {
		BigDecimal result;
		result = fixAccountMapper.queryFixUseMoneyTotal();
		// 如果查询定期宝的可用余额的不为空
		if (result != null) {
			return result;
		}
		return BigDecimal.ZERO;
	}

}
