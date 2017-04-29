package com.cxdai.console.lottery.mapper;

import com.cxdai.console.base.mapper.BaseGoodSendInfoMapper;
import com.cxdai.console.lottery.vo.GoodSendInfo;

public interface GoodSendInfoMapper extends BaseGoodSendInfoMapper {

	GoodSendInfo getGoodSendInfoByUseRecordId(Integer useRecordId);

}