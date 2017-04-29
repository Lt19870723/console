package com.cxdai.console.finance.virement.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.AccountFlow;
import com.cxdai.console.finance.virement.vo.AccountFlowResponse;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;

public interface AccountFlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountFlow record);

    int insertSelective(AccountFlow record);

    AccountFlow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountFlow record);

    int updateByPrimaryKey(AccountFlow record);
    
    int queryPageCount(QueryPageCheckWithCnd request);
    List<AccountFlow> queryPageList(QueryPageCheckWithCnd request, Page page);
    
    
    List<AccountFlowResponse> queryByDate(QueryPageCheckWithCnd request);
}