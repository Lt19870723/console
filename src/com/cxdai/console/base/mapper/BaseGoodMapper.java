package com.cxdai.console.base.mapper;

import com.cxdai.console.lottery.entity.BaseGood;

public interface BaseGoodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseGood record);

    int insertSelective(BaseGood record);

    BaseGood selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseGood record);

    int updateByPrimaryKey(BaseGood record);
}