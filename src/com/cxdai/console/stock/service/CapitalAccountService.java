package com.cxdai.console.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxdai.console.curaccount.entity.CurAccount;
import com.cxdai.console.curaccount.entity.CurAccountlog;
import com.cxdai.console.statistics.account.vo.AccountVo;
import com.cxdai.console.stock.mapper.CapitalAccountMapper;
import com.cxdai.console.stock.vo.AccountLogRequest;


/**
 * 
 * <p>
 * Description:资金操作类<br />
 * </p>
 * @title CapitalAccountServiceImpl.java
 * @package com.cxdai.console.stock.service 
 * @author xiaofei.li
 * @version 0.1 2016-6-2
 */
@Service
public class CapitalAccountService {
	@Autowired
	private CapitalAccountMapper capitalAccountMapper;

	public void saveCurAccountlog(CurAccount cur, CurAccountlog curLog) {
		
		curLog.setUserId(cur.getUserId());
		curLog.setTotal(cur.getTotal());
		curLog.setUseMoney(cur.getUseMoney());
		curLog.setNoUseMoney(cur.getNoUseMoney());
		curLog.setInterestTotal(cur.getInterestTotal());
		curLog.setInterestYesterday(cur.getInterestYesterday());
		capitalAccountMapper.saveCurAccountlog(curLog);
	}
	
	public void saveAccountlog(AccountVo account, AccountLogRequest accountLog){
		accountLog.setUserId(account.getUserId());
		accountLog.setTotal(account.getTotal());
		accountLog.setUseMoney(account.getUseMoney());
		accountLog.setNoUseMoney(account.getNoUseMoney());
		accountLog.setCollection(account.getCollection());
		accountLog.setFirstBorrowUseMoney(account.getFirstBorrowUseMoney());
		accountLog.setDrawMoney(account.getDrawMoney());
		accountLog.setNoDrawMoney(account.getNoDrawMoney());
		capitalAccountMapper.saveAccountlog(accountLog);
	}

}
