package com.cxdai.console.base.mapper;

import java.util.List;

import com.cxdai.console.base.entity.AccountError;
import com.cxdai.console.borrow.check.vo.AccountErrorCnd;
import com.cxdai.console.common.page.Page;


public interface AccountErrorMapper {
   
	public int insert(AccountError accountError);
	
	public List<AccountError> findAccountErrorByUserId(Integer userId);
	
	public int findAccountErrorByUserIdCount(Integer userId);
	
	public List<AccountError> findAccountError(AccountErrorCnd accountErrorCnd,Page page);
	
	public Integer findAccountErrorCount(AccountErrorCnd accountErrorCnd);
	
	public Integer updateByPrimaryKeySelective(AccountError accountError);
}