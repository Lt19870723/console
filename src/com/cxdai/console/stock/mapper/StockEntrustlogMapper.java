package com.cxdai.console.stock.mapper;

import com.cxdai.console.stock.entity.StockEntrustlog;

public interface StockEntrustlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockEntrustlog record);

    int insertSelective(StockEntrustlog record);

    StockEntrustlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockEntrustlog record);

    int updateByPrimaryKey(StockEntrustlog record);
}