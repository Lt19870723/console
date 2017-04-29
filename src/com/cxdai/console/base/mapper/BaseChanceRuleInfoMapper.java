package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.BaseChanceRuleInfo;

public interface BaseChanceRuleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseChanceRuleInfo record);

    int insertSelective(BaseChanceRuleInfo record);

    BaseChanceRuleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseChanceRuleInfo record);

    int updateByPrimaryKey(BaseChanceRuleInfo record);
}