package com.cxdai.console.stock.mapper;

import java.util.List;
import java.util.Map;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.StockDeal;
import com.cxdai.console.stock.vo.StockDealRequest;

public interface StockDealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockDeal record);

    int insertSelective(StockDeal record);

    StockDeal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockDeal record);

    int updateByPrimaryKey(StockDeal record);
    
    Integer queryListCount(StockDealRequest request);
    List<StockDeal> queryList(StockDealRequest request, Page pag);
    
    List<Map<String,Object>> querySummary1(StockDealRequest request);
    List<Map<String,Object>> querySummary2(StockDealRequest request);
    List<Map<String,Object>> querySummary3(StockDealRequest request);
    
}