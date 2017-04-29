package com.cxdai.console.finance.virement.mapper;

import java.util.List;

import com.cxdai.console.finance.virement.entity.AccountBalance;
import com.cxdai.console.finance.virement.vo.AccountBalanceResponseCnd;

public interface AccountBalanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountBalance record);

    int insertSelective(AccountBalance record);

    AccountBalance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountBalance record);

    int updateByPrimaryKey(AccountBalance record);
    
    List<AccountBalanceResponseCnd> queryAccountInfo(String date,String bankType);
    
    List<AccountBalanceResponseCnd> queryAccountInfoOther(String date);  
    
}