package com.cxdai.console.finance.virement.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.finance.virement.entity.CheckRecharge;
import com.cxdai.console.finance.virement.entity.RechargeType;
import com.cxdai.console.finance.virement.entity.WithdrawalsStatus;
import com.cxdai.console.finance.virement.mapper.CheckRechargeMapper;
import com.cxdai.console.finance.virement.vo.CheckAccountRequestCnd;
import com.cxdai.console.finance.virement.vo.CheckAccountResponseCnd;
import com.cxdai.console.finance.virement.vo.CheckWithCnd;
import com.cxdai.console.finance.virement.vo.QueryPageCheckWithCnd;
import com.cxdai.console.finance.virement.vo.UpdateCheckWithCnd;
import com.cxdai.console.statistics.reconciliation.entity.AccountCheckCnd;
import com.cxdai.console.statistics.reconciliation.mapper.AccountCheckMapper;
import com.cxdai.console.util.DateUtils;
import com.cxdai.console.util.StringUtils;

@Service
public class CheckRechargeService {

	@Autowired
	private CheckRechargeMapper checkRechargeMapper;

	/**
	 * 分页获取每日充值对账汇总列表
	 * 
	 * @param request
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public Page queryPageCheckRecharge(QueryPageCheckWithCnd request, Integer curPage, Integer pageSize) {
		Page page = new Page(curPage, pageSize);
		if (StringUtils.isNotEmpty(request.getStartDate())) {
			request.setStartDate(request.getStartDate() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			request.setEndDate(request.getEndDate() + " 23:59:59");
		}
		Integer count = checkRechargeMapper.queryPageCheckRechargeCount(request);
		page.setTotalCount(count);
		List<List<CheckRecharge>> checkRecharge = this.assembleList(checkRechargeMapper.queryPageCheckRecharge(request, page));
		page.setResult(checkRecharge);
		return page;
	}
	
	public List<CheckRecharge> queryByDate(String date){
		QueryPageCheckWithCnd request = new QueryPageCheckWithCnd();
		if (StringUtils.isNotEmpty(date)) {
			request.setStartDate(date + " 00:00:00");
			request.setEndDate(date + " 23:59:59");
		}
		List<CheckRecharge> list = checkRechargeMapper.queryByDate(request);
		 return list;
	}
	
	
	/**
	 * 
	 * <p>
	 * Description:充值核对<br />
	 * </p>
	 * @author xiaofei.li
	 * @version 0.1 2016-4-18
	 * @param request
	 * @return
	 * Map<String,Object>
	 */
	public List<CheckAccountResponseCnd> checkAccount(List<CheckAccountRequestCnd> request){
		List<CheckAccountResponseCnd> response = new ArrayList<CheckAccountResponseCnd>();
		String date = request.get(0).getDate();
		if(StringUtils.isNotEmpty(date)){
			List<CheckRecharge> list = this.queryByDate(request.get(0).getDate());
				for(CheckAccountRequestCnd cnd : request ){
					for(CheckRecharge recharge : list){
						if(cnd.getType() == recharge.getType()){
								//充值成功总额 =  实际充值总额+实际手续费
							CheckAccountResponseCnd ch = new CheckAccountResponseCnd();
								BigDecimal subtract = recharge.getSuccessMoney().subtract(cnd.getCheckFee().add(cnd.getReceiveMoney()));
								BigDecimal fee = recharge.getCalculationFee().subtract(cnd.getCheckFee());
								ch.setType(cnd.getType());
								ch.setDifferenceFee(fee);
								ch.setDifferenceTotal(subtract);
								response.add(ch);
						}
					}
			}
		}
		return response;
	}
	
	@Transactional
	public int saveRecharge(List<CheckAccountRequestCnd> request,Integer userId,String ip){
		int result=0;
		for(CheckAccountRequestCnd checkInfo : request){
			checkInfo.setUpdateUser(userId);
			checkInfo.setUpdateIp(ip);
			result += checkRechargeMapper.saveRecharge(checkInfo);
		}
		return result;
	}
	
	
	
	public List<CheckRecharge> exportRechargeList(QueryPageCheckWithCnd request) {
		Page page = new Page();
		if (StringUtils.isNotEmpty(request.getStartDate())) {
			request.setStartDate(request.getStartDate() + " 00:00:00");
		}
		if (StringUtils.isNotEmpty(request.getEndDate())) {
			request.setEndDate(request.getEndDate() + " 23:59:59");
		}
		Integer count = checkRechargeMapper.queryPageCheckRechargeCount(request);
		page.setPageSize(count);
		List<CheckRecharge> checkRecharge = checkRechargeMapper.queryPageCheckRecharge(request, page);
		return checkRecharge;
	}
	
	/**
	 * 针对查询列表按照日期分组 并统计汇总
	 * @param checkRecharge
	 * @return
	 */
	private List<List<CheckRecharge>> assembleList(List<CheckRecharge> checkRecharge){
		List<List<CheckRecharge>> newList = new ArrayList<List<CheckRecharge>>();
		//日期判断标示，是否统计过
		List<Long> mark = new ArrayList<Long>();
		for(CheckRecharge recharge : checkRecharge){
			Long group1 = recharge.getDate().getTime();
			boolean isMark = mark.contains(group1);
			if(!isMark){
				mark.add(group1);
				//汇总每日渠道统计
				List<CheckRecharge> list = new ArrayList<CheckRecharge>();
				//每日所有渠道充值汇总
				CheckRecharge totalRecharge = new CheckRecharge();
				BigDecimal totalMoney = BigDecimal.ZERO;
				BigDecimal successMoney = BigDecimal.ZERO;
				BigDecimal checkSuccessMoney = BigDecimal.ZERO;
				BigDecimal calculationFee = BigDecimal.ZERO;
				BigDecimal checkFee = BigDecimal.ZERO;
				BigDecimal differenceFee = BigDecimal.ZERO;
				BigDecimal receiveMoney = BigDecimal.ZERO;
				BigDecimal fictitiousRecharge = BigDecimal.ZERO;
				BigDecimal differenceToatl = BigDecimal.ZERO;
				for(CheckRecharge recharge1 : checkRecharge){
						if(group1 == recharge1.getDate().getTime()){
							if(list.size() > 5)break;
							list.add(recharge1);
							if(RechargeType.ALIPAY.getCode() != recharge1.getType()){
							totalMoney = totalMoney.add(recharge1.getTotalMoney() != null ? recharge1.getTotalMoney() : BigDecimal.ZERO);
							successMoney = successMoney.add(recharge1.getSuccessMoney() != null ? recharge1.getSuccessMoney() : BigDecimal.ZERO);
							checkSuccessMoney = checkSuccessMoney.add(recharge1.getCheckSuccessMoney() != null ? recharge1.getCheckSuccessMoney() : BigDecimal.ZERO);
							calculationFee = calculationFee.add(recharge1.getCalculationFee() != null ? recharge1.getCalculationFee() : BigDecimal.ZERO);
							checkFee = checkFee.add(recharge1.getCheckFee() != null ? recharge1.getCheckFee() : BigDecimal.ZERO);
							differenceFee = differenceFee.add(recharge1.getDifferenceFee() != null ? recharge1.getDifferenceFee() : BigDecimal.ZERO);
							receiveMoney = receiveMoney.add(recharge1.getReceiveMoney() != null ? recharge1.getReceiveMoney() : BigDecimal.ZERO);
							fictitiousRecharge = fictitiousRecharge.add(recharge1.getFictitiousRecharge() != null ? recharge1.getFictitiousRecharge() : BigDecimal.ZERO);
							differenceToatl = differenceToatl.add(recharge1.getDifferenceTotal() != null ? recharge1.getDifferenceTotal() : BigDecimal.ZERO);
							}
						}
				}
				totalRecharge.setType(8);
				totalRecharge.setTotalMoney(totalMoney);
				totalRecharge.setSuccessMoney(successMoney);
				totalRecharge.setCheckSuccessMoney(checkSuccessMoney);
				totalRecharge.setCalculationFee(calculationFee);
				totalRecharge.setCheckFee(checkFee);
				totalRecharge.setDifferenceFee(differenceFee);
				totalRecharge.setReceiveMoney(receiveMoney);
				totalRecharge.setFictitiousRecharge(fictitiousRecharge);
				totalRecharge.setDifferenceTotal(differenceToatl);
				list.add(totalRecharge);
				newList.add(list);
			}
			continue;
		
		}
		return newList;
		
	}
}
