package com.cxdai.console.lottery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.lottery.mapper.ChanceRuleInfoMapper;
import com.cxdai.console.lottery.vo.ChanceRuleInfo;

@Service
@Transactional(rollbackFor = Throwable.class)
public class   ChanceRuleInfoService {

	@Autowired
	ChanceRuleInfoMapper chanceRuleInfoMapper;

	
	public List<ChanceRuleInfo> queryAllChanceRuleInfos() {
		return chanceRuleInfoMapper.queryAllChanceRuleInfos();
	}

	/**
     * 
     * <p>
     * Description:根据Code查询符合当前时间的生效记录<br />
     * </p>
     * @author YangShiJin
     * @version 0.1 2015年4月7日
     * @param code
     * @return
     * ChanceRuleInfo
     */
	public ChanceRuleInfo selectByCode(String code) {
		return chanceRuleInfoMapper.selectByCode(code);
	}


}
