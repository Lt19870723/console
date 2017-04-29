package com.cxdai.console.borrow.approve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.borrow.approve.mapper.BorrowApprovedMapper;
import com.cxdai.console.borrow.approve.vo.BorrowApprovedVo;

 
/**
 * 
 * <p>
 * Description:借款标审核业务实现类<br />
 * </p>
 * @title BorrowApprovedService.java
 * @package com.cxdai.console.borrow.approve.service 
 * @author Administrator
 * @version 0.1 2015年6月25日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class BorrowApprovedService {
  
	@Autowired
	private BorrowApprovedMapper borrowApprovedMapper;

 
	public BorrowApprovedVo selectByBorrowIdForUpdate(Integer borrowId)
			throws Exception {
		return borrowApprovedMapper.selectByBorrowIdForUpdate(borrowId);
	}
}
