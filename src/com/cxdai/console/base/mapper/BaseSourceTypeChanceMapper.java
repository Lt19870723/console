package com.cxdai.console.base.mapper;

import com.cxdai.console.base.entity.BaseSourceTypeChance;

public interface BaseSourceTypeChanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseSourceTypeChance record);

    int insertSelective(BaseSourceTypeChance record);

    BaseSourceTypeChance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseSourceTypeChance record);

    int updateByPrimaryKey(BaseSourceTypeChance record);
}