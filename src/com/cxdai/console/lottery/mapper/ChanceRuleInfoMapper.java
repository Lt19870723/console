package com.cxdai.console.lottery.mapper;

import java.util.List;

import com.cxdai.console.base.mapper.BaseChanceRuleInfoMapper;
import com.cxdai.console.lottery.vo.ChanceRuleInfo;

public interface ChanceRuleInfoMapper extends BaseChanceRuleInfoMapper {

	List<ChanceRuleInfo> queryAllChanceRuleInfos();
	
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
    public ChanceRuleInfo selectByCode(String code);
}