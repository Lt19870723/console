package com.cxdai.console.finance.virement.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.CheckRecharge;
import com.cxdai.console.finance.virement.vo.CheckAccountRequestCnd;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;


public interface CheckRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckRecharge record);

    int insertSelective(CheckRecharge record);

    CheckRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckRecharge record);

    int updateByPrimaryKey(CheckRecharge record);
    
    int queryPageCheckRechargeCount(QueryPageCheckWithCnd request);
    List<CheckRecharge> queryPageCheckRecharge(QueryPageCheckWithCnd request , Page page);
    
    
    List<CheckRecharge> queryByDate(QueryPageCheckWithCnd request);
    
    int saveRecharge(CheckAccountRequestCnd checkInfo);
}