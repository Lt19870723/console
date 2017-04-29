package com.cxdai.console.base.mapper;

import com.cxdai.console.lottery.entity.BaseGoodSendInfo;

public interface BaseGoodSendInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseGoodSendInfo record);

    int insertSelective(BaseGoodSendInfo record);

    BaseGoodSendInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseGoodSendInfo record);

    int updateByPrimaryKey(BaseGoodSendInfo record);
}