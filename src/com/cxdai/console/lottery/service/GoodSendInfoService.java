package com.cxdai.console.lottery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.lottery.mapper.GoodSendInfoMapper;
import com.cxdai.console.lottery.vo.GoodSendInfo;
import com.cxdai.console.lottery.vo.UseRecord;

@Service
@Transactional(rollbackFor = Throwable.class)
public class  GoodSendInfoService {

	@Autowired
	GoodSendInfoMapper goodSendInfoMapper;

	@Autowired
	UseRecordService useRecordService;

	
	public GoodSendInfo getGoodSendInfoByUseRecordId(Integer goodId) {
		return goodSendInfoMapper.getGoodSendInfoByUseRecordId(goodId);
	}

	
	public void saveGoodSendInfo(GoodSendInfo goodSendInfo, Integer useRecordStatus) {
		goodSendInfoMapper.updateByPrimaryKeySelective(goodSendInfo);

		if (useRecordStatus == 2) {
			System.out.println("=========================");
			// 更新 使用记录的 状态
			Integer useRecordId = goodSendInfo.getLotteryUseRecordId();
			System.out.println("========"+useRecordId);
			UseRecord useRecord = new UseRecord();
			useRecord.setId(useRecordId);
			useRecord.setStatus(3); // 派发中
			useRecordService.updateUseRecordByEntry(useRecord);
		}

	}


}
