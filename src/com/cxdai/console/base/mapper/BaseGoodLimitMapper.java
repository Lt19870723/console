package com.cxdai.console.base.mapper;

import com.cxdai.console.lottery.entity.BaseGoodLimit;

public interface BaseGoodLimitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseGoodLimit record);

    int insertSelective(BaseGoodLimit record);

    BaseGoodLimit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseGoodLimit record);

    int updateByPrimaryKey(BaseGoodLimit record);
}