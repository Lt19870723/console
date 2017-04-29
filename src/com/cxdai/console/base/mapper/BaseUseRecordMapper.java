package com.cxdai.console.base.mapper;

import com.cxdai.console.lottery.entity.BaseUseRecord;

public interface BaseUseRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseUseRecord record);

    int insertSelective(BaseUseRecord record);

    BaseUseRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseUseRecord record);

    int updateByPrimaryKey(BaseUseRecord record);
}