package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.BaseChanceInfo;

public interface BaseChanceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseChanceInfo record);

    int insertSelective(BaseChanceInfo record);

    BaseChanceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseChanceInfo record);

    int updateByPrimaryKey(BaseChanceInfo record);
}