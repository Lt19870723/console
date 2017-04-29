package com.cxdai.console.stock.mapper;

import java.util.List;

import com.cxdai.console.stock.entity.StockApprove;

public interface StockApproveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockApprove record);

    int insertSelective(StockApprove record);

    StockApprove selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockApprove record);

    int updateByPrimaryKey(StockApprove record);
    
    public List<StockApprove> selectApproveList(StockApprove record);
    
    
}