package com.cxdai.console.stock.mapper;

import java.util.List;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.stock.entity.StockEntrust;
import com.cxdai.console.stock.vo.StockEntrustCnd;
import com.cxdai.console.stock.vo.StockEntrustRequest;

public interface StockEntrustMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockEntrust record);

    int insertSelective(StockEntrust record);

    StockEntrust selectByPrimaryKey(Integer id);
    
    StockEntrust selectByPrimaryCnd(StockEntrustCnd entrustCnd);

    int updateByPrimaryKeySelective(StockEntrust record);

    int updateByPrimaryKey(StockEntrust record);
    
    
    int  queryListCount(StockEntrustRequest request);
    List<StockEntrust> queryList(StockEntrustRequest request,Page page);
}