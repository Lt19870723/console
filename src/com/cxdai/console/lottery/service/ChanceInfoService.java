package com.cxdai.console.lottery.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.base.mapper.BaseChanceInfoMapper;
import com.cxdai.console.common.page.Page;
import com.cxdai.console.customer.information.mapper.MemberMapper;
import com.cxdai.console.customer.information.vo.MemberVo;
import com.cxdai.console.lottery.entity.ChanceInfoSignSet;
import com.cxdai.console.lottery.mapper.ChanceInfoMapper;
import com.cxdai.console.lottery.mapper.ChanceRuleInfoMapper;
import com.cxdai.console.lottery.vo.ChanceInfo;
import com.cxdai.console.lottery.vo.ChanceRuleInfo;
import com.cxdai.console.system.mapper.ConfigurationMapper;
/**
 * 
 * <p>
 * Description:抽奖机会信息查询<br />
 * </p>
 * @title ChanceInfoService.java
 * @package com.cxdai.console.lottery.service 
 * @author yubin
 * @version 0.1 2015年7月5日
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ChanceInfoService {
	@Autowired
	private ChanceInfoMapper chanceInfoMapper;
	@Autowired
	private BaseChanceInfoMapper baseChanceInfoMapper;
	@Autowired
	private ChanceRuleInfoMapper ChanceRuleInfoMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	ConfigurationMapper configurationMapper;
	/**
     * 
     * <p>
     * Description:根据机会来源类型和userId查询记录数量<br />
     * </p>
     * @author YangShiJin
     * @version 0.1 2015年4月7日
     * @param code
     * @param userId
     * @return
     * LotteryChanceInfoVo
     */
	public Integer selectCountByCodeAndUserId(String code, Integer userId) {
		return chanceInfoMapper.selectCountByCodeAndUserId(code, userId);
	}

	/**
     * 
     * <p>
     * Description:新增抽奖机会信息记录<br />
     * </p>
     * @author YangShiJin
     * @version 0.1 2015年4月8日
     * @param userId
     * @param code
     * @return
     * @throws Exception
     * String
     */
	public String insertLotteryChanceInfoByCode(Integer userId, String code) throws Exception{
		ChanceRuleInfo lotteryChanceRuleInfoVo = ChanceRuleInfoMapper.selectByCode(code);
		if (lotteryChanceRuleInfoVo != null && lotteryChanceRuleInfoVo.getLotteryNum() != null) {
			MemberVo memberVo = memberMapper.queryMemberById(userId);
			if (memberVo != null && memberVo.getIsFinancialUser() != null && memberVo.getIsFinancialUser().intValue() == 1) {
				ChanceInfo lotteryChanceInfo = new ChanceInfo();
				lotteryChanceInfo.setLotteryChanceRuleInfoId(lotteryChanceRuleInfoVo.getId());
				lotteryChanceInfo.setLotteryNum(lotteryChanceRuleInfoVo.getLotteryNum());
				lotteryChanceInfo.setUseNum(lotteryChanceRuleInfoVo.getLotteryNum());
				lotteryChanceInfo.setUserId(userId);
				lotteryChanceInfo.setAddTime(new Date());
				lotteryChanceInfo.setAddIp("0.0.0.1");
				lotteryChanceInfo.setUsername(memberVo.getUsername());
				if (baseChanceInfoMapper.insert(lotteryChanceInfo) > 0) {
					return "success";
				}
			}
		}
		return "fail";
	}

	/**
	 * Description:新增抽奖机会信息记录
	 * @author wushaoling
	 * @param lotteryChanceRuleInfoId
	 * @param lotteryNum
	 * @param remark
	 * @param username
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String insertLotteryChanceInfo(Integer lotteryChanceRuleInfoId, Integer lotteryNum,String remark,String username,Integer userId) throws Exception{
		ChanceInfo lotteryChanceInfo = new ChanceInfo();
		lotteryChanceInfo.setLotteryChanceRuleInfoId(lotteryChanceRuleInfoId);
		lotteryChanceInfo.setLotteryNum(lotteryNum);
		lotteryChanceInfo.setRemark(remark);
		lotteryChanceInfo.setUseNum(lotteryNum);
		lotteryChanceInfo.setUserId(userId);
		lotteryChanceInfo.setAddTime(new Date());
		lotteryChanceInfo.setAddIp("0.0.0.1");
		lotteryChanceInfo.setUsername(username);
		if (baseChanceInfoMapper.insert(lotteryChanceInfo) > 0) {
			return "奖励抽奖机会成功";
		}
		return "奖励抽奖机会失败";
	}

	
	public Page queryPageChanceInfoList(String userName, int pageNo, int pageSize) {
		Page page = new Page(pageNo, pageSize);
		page.setResult(chanceInfoMapper.queryChanceInfoList(userName, page));
		page.setTotalCount(chanceInfoMapper.queryCountChanceInfoList());
		return page;
	}

	public Integer querySumLotteryNum(Integer userId){
		return chanceInfoMapper.querySumLotteryNum(userId);
	}
	public ChanceInfoSignSet getDiscount() {
		return chanceInfoMapper.getChanceInfoSign();
	}
	public int SetChanceInfoSign(String beginDate, String endDate,Integer awardNum) throws Exception {
		return configurationMapper.updateByType(beginDate, endDate, 1397, awardNum);
	}
}
