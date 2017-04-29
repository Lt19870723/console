package com.cxdai.console.stock.mapper;

import com.cxdai.console.stock.entity.StockAccountlog;

public interface StockAccountlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockAccountlog record);

    int insertSelective(StockAccountlog record);

    StockAccountlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockAccountlog record);

    int updateByPrimaryKey(StockAccountlog record);
}