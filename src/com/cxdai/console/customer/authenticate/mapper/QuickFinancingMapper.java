package com.cxdai.console.customer.authenticate.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.authenticate.entity.QuickFinancing;
import com.cxdai.console.customer.authenticate.vo.QuickFinancingCnd;

public interface QuickFinancingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuickFinancing record);

    int insertSelective(QuickFinancing record);

    QuickFinancing selectByPrimaryKey(Integer id);
    
    QuickFinancing selectFinance(Integer id);

    int updateByPrimaryKeySelective(QuickFinancing record);

    int updateByPrimaryKey(QuickFinancing record);
    
    List<QuickFinancing> queryQuickFianFinancing(QuickFinancingCnd cnd,Page page);
    
    Integer countQuickFianFinancing(QuickFinancingCnd cnd);
}