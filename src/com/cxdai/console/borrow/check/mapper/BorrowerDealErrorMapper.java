package com.cxdai.console.borrow.check.mapper;

import java.util.List;
import com.cxdai.console.borrow.check.entity.BorrowerDealError;
import com.cxdai.console.borrow.check.vo.BorrowErrorCnd;
import com.cxdai.console.borrow.check.vo.BorrowErrorVo;
import com.cxdai.console.common.page.Page;

public interface BorrowerDealErrorMapper {
   
	public Integer insert(BorrowerDealError borrowerDealError);
	
	public int findBorrowerErrorCount(BorrowErrorCnd borrowErrorCnd);
	
	public List<BorrowErrorVo> findBorrowerError(BorrowErrorCnd borrowErrorCnd,Page page); 
	
	public BorrowerDealError selectByPrimaryKey(Integer id);
	
	public Integer findBorrowErrorCount(Integer borrowId);
	
	public List<BorrowErrorVo> findFBReqAccount(BorrowErrorCnd borrowErrorCnd,Page page);
	
	public int findFBReqAccountCount(BorrowErrorCnd borrowErrorCnd);
	
	public Integer updateByPrimaryKeySelective(BorrowerDealError borrowerDealError);
	
}