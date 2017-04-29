package com.cxdai.console.stock.mapper;

import java.util.List;

import com.cxdai.console.stock.entity.ShareholderRoster;
import com.cxdai.console.stock.entity.StockAccount;
import com.cxdai.console.stock.vo.StockAccountRequest;

public interface StockAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockAccount record);

    int insertSelective(StockAccount record);

    StockAccount selectByPrimaryKey(Integer id);
    
    StockAccount selectByPrimaryCnd(StockAccountRequest request);

    int updateByPrimaryKeySelective(StockAccount record);

    int updateByPrimaryKey(StockAccount record);
    
    StockAccount selectByUserId(Integer userid);
    
    List<ShareholderRoster> selectShareholderList();
    
    List<ShareholderRoster> outShareholdList();
    
    List<ShareholderRoster> addShareholdList();
    
    
    
}