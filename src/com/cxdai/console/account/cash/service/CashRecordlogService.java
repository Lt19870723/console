package com.cxdai.console.account.cash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.account.cash.entity.CashRecordlog;
import com.cxdai.console.account.cash.mapper.CashRecordlogMapper;

/**
 * 提现操作日志业务实现类
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class CashRecordlogService {

	@Autowired
	private CashRecordlogMapper cashRecordlogMapper;
	
	/**
	 * 保存提现操作日志
	 */
	public Integer saveCashRecordlog(CashRecordlog cashRecordlog)
			throws Exception {
		return cashRecordlogMapper.insertCashRecordlog(cashRecordlog);
	}

}
