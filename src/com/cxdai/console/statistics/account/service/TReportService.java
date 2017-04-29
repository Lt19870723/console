package com.cxdai.console.statistics.account.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cxdai.console.common.page.Page;
import com.cxdai.console.common.statics.BusinessConstants;
import com.cxdai.console.fix.mapper.RockyAccountLogMapper;
import com.cxdai.console.statistics.account.mapper.TReportMapper;
import com.cxdai.console.statistics.account.vo.ReportStatementCnd;
import com.cxdai.console.statistics.account.vo.TotalReportVo;
import com.cxdai.console.statistics.customer.entity.NewInterestMember;
import com.cxdai.console.util.DateUtils;

@Service
@Transactional(rollbackFor = Throwable.class)
public class TReportService {

	@Autowired
	private TReportMapper tReportMapper;
	@Autowired
	private RockyAccountLogMapper rockyAccountLogMapper;

	public TotalReportVo queryTReport(ReportStatementCnd reportStatementCnd) throws Exception {

		TotalReportVo vo = new TotalReportVo();
		//获取报表数据
		vo=getTotalReportVo(vo,reportStatementCnd);
		// 查询流失投资者人数
		int losePersonsTotal = 0;
		Date date = new Date();
		String dateFmt = DateUtils.format(date, DateUtils.YMD_DASH);
		String nowTime = DateUtils.dayOffset(DateUtils.parse(dateFmt, DateUtils.YMD_DASH), 1).getTime() / 1000 + "";

		if (Integer.parseInt(reportStatementCnd.getEndTimeSec()) == Integer.parseInt(nowTime)) {
			losePersonsTotal = tReportMapper.queryLosePersonsTotal1(reportStatementCnd);
		} else {
			losePersonsTotal = tReportMapper.queryLosePersonsTotal2(reportStatementCnd);
		}
		vo.setLosePersonsTotal(losePersonsTotal);
		// 净充值金额：
		BigDecimal netRechargeTotal = vo.getRechangeTotalMoney().subtract(vo.getCashTotalMoney());
		vo.setNetRechargeTotal(netRechargeTotal.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 网银在线充值费用
		// 旧账号，已失效的账号网银在线充值费用：
//		reportStatementCnd.setMerchantNo(BusinessConstants.ONLINE_PAY_CHINABANK_INVALID_SHOPNO);
//		// <!-- onlinetype等于2中的网银在线总额-->
//		reportStatementCnd.setOnlinetype(BusinessConstants.ONLINE_TYPE_CHINABANK);
//		BigDecimal onLineIntypeTwo = tReportMapper.queryPayFeeTotalOnline(reportStatementCnd);
//		BigDecimal invalidChinaBankFeeTotal = tReportMapper.queryPayFeeTotal(reportStatementCnd);
		// invalidChinaBankFeeTotal =
		// invalidChinaBankFeeTotal.add(onLineIntypeTwo);
		// 新账号网银在线充值费用：
//		reportStatementCnd.setMerchantNo(BusinessConstants.ONLINE_PAY_CHINABANK_SHOPNO);
//		BigDecimal onLineIntypeTwoOld = tReportMapper.queryPayFeeTotalOnline(reportStatementCnd);
//		BigDecimal chinaBankFeeTotal = tReportMapper.queryPayFeeTotal(reportStatementCnd);
		// 加上2中的网银在线的收入
		// chinaBankFeeTotal = chinaBankFeeTotal.add(onLineIntypeTwoOld);
		// 网银在线的手续费比例是万分之八
//		BigDecimal chinaBankFee = (invalidChinaBankFeeTotal.multiply(new BigDecimal(0.0008))).add((chinaBankFeeTotal.multiply(new BigDecimal(0.0008))));
//		// 加上商户号22899911
//		reportStatementCnd.setMerchantNo("22899911");
//		BigDecimal fee22899911 = tReportMapper.queryPayFeeTotal(reportStatementCnd);
//		chinaBankFee = chinaBankFee.add(fee22899911.multiply(new BigDecimal(0.0008)));
		
		//网银在线的手续费比例是万分之八,原来是千分之2.5和千分之2
		reportStatementCnd.setOnlinetype(BusinessConstants.ONLINE_TYPE_CHINABANK);
		reportStatementCnd.setOnlineNo22791329(BusinessConstants.ONLINE_PAY_CHINABANK_INVALID_SHOPNO);
		reportStatementCnd.setOnlineNo22899911("22899911");
		reportStatementCnd.setOnlineNo23234639(BusinessConstants.ONLINE_PAY_CHINABANK_SHOPNO);
		BigDecimal chinaBankFee = tReportMapper.queryPayFeeTotalForOnline(reportStatementCnd);

		// 最终网银在线充值费用
		vo.setChinaBankFeeTotal(chinaBankFee.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 盛付通充值费用 type = 3
		reportStatementCnd.setOnlinetype(BusinessConstants.ONLINE_TYPE_SHENGPAY);
		reportStatementCnd.setMerchantNo(null);
		BigDecimal shengpayFeeTotal = tReportMapper.queryPayFeeTotal(reportStatementCnd);
		BigDecimal shengpayFee = shengpayFeeTotal.multiply(new BigDecimal(0.002));
		vo.setShengpayFeeTotal(shengpayFee.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 国付宝充值费用：= 国付宝充值总额*0.3% type=2
		reportStatementCnd.setOnlinetype(BusinessConstants.ONLINE_TYPE_GOPAY);
		reportStatementCnd.setMerchantNo(null);
		BigDecimal guopayFeeTotal = tReportMapper.queryPayFeeTotal(reportStatementCnd);
		// 减去2中的网银在线的收入

		// guopayFeeTotal.subtract(onLineIntypeTwo).subtract(onLineIntypeTwoOld);

		BigDecimal guopayFee = guopayFeeTotal.multiply(new BigDecimal(0.003));
		vo.setGuopayFeeTotal(guopayFee.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 连连支付充值费用 = PC每笔收取千分之1，如果不足0.1元，收取0.1元  + 微信、IOS、安卓每笔收取千分之2，如果不足0.1元，收取0.1元
		BigDecimal llpayFee = tReportMapper.queryllPayFeeTotal(reportStatementCnd);
		vo.setLlFeeTotal(llpayFee.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 富友支付充值费用 = 千分之1
		BigDecimal fuioupayFee = tReportMapper.queryFuiouPayFeeTotal(reportStatementCnd);
		vo.setFuiouFeeTotal(fuioupayFee.setScale(2, BigDecimal.ROUND_HALF_UP));

        // 浙商存管充值费用 = 万分之8
		BigDecimal czbankCustodyFee = tReportMapper.queryCzbankCustodyTotal(reportStatementCnd);
		vo.setCzbankCustodyTotal(czbankCustodyFee.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 充值费用总和：（3家第三方充值费用总和加上线下充值奖励）
		BigDecimal rechargeTotal = chinaBankFee.add(shengpayFee.setScale(2, BigDecimal.ROUND_HALF_UP)).add(guopayFee.setScale(2, BigDecimal.ROUND_HALF_UP))
				.add(vo.getSinaFeeTotal().setScale(2, BigDecimal.ROUND_HALF_UP)).add(vo.getOffLineRechargeAwardTotal().setScale(2, BigDecimal.ROUND_HALF_UP))
				.add(llpayFee.setScale(2, BigDecimal.ROUND_HALF_UP)).add(fuioupayFee.setScale(2, BigDecimal.ROUND_HALF_UP))
                .add(czbankCustodyFee.setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setRechargeTotal(rechargeTotal.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 论坛活动奖励 最下面
		BigDecimal forumAwardTotal = tReportMapper.queryForumAwardTotal(reportStatementCnd);
		String lastEndTime = DateUtils.dateTime2TimeStamp("2014-03-31 23:59:59");

		if (reportStatementCnd.getBeginTimeSec().compareTo(lastEndTime) > 0) {
			reportStatementCnd.setBeginTimeSecForforum(lastEndTime);
		}
		if (reportStatementCnd.getEndTimeSec().compareTo(lastEndTime) > 0) {
			reportStatementCnd.setEndTimeSecForforum(lastEndTime);
		}
		BigDecimal activityAwardTotal = tReportMapper.queryActivityAwardTotal(reportStatementCnd);

		BigDecimal forumActivityAwardTotal = forumAwardTotal.add(activityAwardTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		vo.setForumActivityAwardTotal(forumActivityAwardTotal.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 活期宝已支付投资人利息
		BigDecimal curInterestTotal = tReportMapper.queryCurInterestTotal(reportStatementCnd);
		vo.setCurInterestTotal(curInterestTotal);
		// 红包支出
		BigDecimal redOutTotal = tReportMapper.queryCurRedOutTotal(reportStatementCnd);
		vo.setRedOutTotal(redOutTotal);
		// 共享奖支出
		BigDecimal gxjOutTotal = tReportMapper.queryGongxiangJiangTotal(reportStatementCnd);
		vo.setGxjOutTotal(gxjOutTotal);
		// 支出总和：generalizeRegisterTotal
		BigDecimal payTotal = vo.getRealnamePassAwardTotal().add(vo.getGeneralizeRegisterTotal()).add(vo.getMonthFirstAwardTotal())
				.add(vo.getForumActivityAwardTotal()).add(rechargeTotal).add(curInterestTotal).add(vo.getRedOutTotal()).add(vo.getGxjOutTotal());
		vo.setPayTotal(payTotal.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 收入总和
		BigDecimal incomeTotal = vo.getVipIncomeTotal().add(vo.getTakeCashFeeTotal()).add(vo.getInterestFeeTotal()).add(vo.getNetBorrowFeeTotal())
				.add(vo.getMoneyLineReseive()).add(vo.getSinaLineReseive()).add(vo.getTransferManagerFree()).add(vo.getFirstTransferManagerFree())
				.add(vo.getLlFeeReseive().add(vo.getChangeDrawMoneyFee()));
		vo.setIncomeTotal(incomeTotal.setScale(2, BigDecimal.ROUND_HALF_UP));

		// 净收益：（收入总和—支出总和）
		BigDecimal netEarningTotal = incomeTotal.subtract(payTotal);
		vo.setNetEarningTotal(netEarningTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		return vo;

	}
	/**
	 * 新投资人管理-查询新投资人列表
	 * @author liutao
	 * @Date 2016-03-22
	 */
	public Page queryNewInterestMember(ReportStatementCnd reportStatementCnd,int pageSize, int curPage) throws Exception {
		Page page = new Page(curPage, pageSize);
		int totalCount = tReportMapper.queryNewInterestMemberCount(reportStatementCnd);
		page.setTotalCount(totalCount);
		List<NewInterestMember> list = tReportMapper.queryNewInterestMemberList(reportStatementCnd, page);
		page.setResult(list);
		return page;
	}
	/**
	 * 新投资人管理-查询新投资人列表导出
	 * @author liutao
	 * @Date 2016-03-22
	 */
	public List<NewInterestMember> queryNewInterestMemberExport(ReportStatementCnd reportStatementCnd) throws Exception {
		return tReportMapper.queryNewInterestMemberList(reportStatementCnd);
	}
	
	/**
	 * 所选时间段，展示推荐投资用户现金奖励金额
	 * @param type
	 * @author wushaoling
	 * @return 总的奖励金额
	 */
	public BigDecimal querySumRockyAccountLog(ReportStatementCnd reportStatementCnd){
		return rockyAccountLogMapper.querySumRockyAccountLog(reportStatementCnd);
	}
	/**
	 * 所选时间段，论坛活动现金支出
	 * @param type
	 * @author jingbinbin
	 * @return 论坛活动现金支出
	 */
	public BigDecimal queryAwardAccountLog(ReportStatementCnd reportStatementCnd){
		return rockyAccountLogMapper.queryAwardAccountLog(reportStatementCnd);
	}
	private TotalReportVo getTotalReportVo(TotalReportVo vo ,ReportStatementCnd reportStatementCnd){
		TotalReportVo voTemp =new TotalReportVo();
		vo = tReportMapper.queryTReport(reportStatementCnd);
		voTemp = tReportMapper.queryTReport1(reportStatementCnd);
		vo.setRealnamePassAwardTotal(voTemp.getRealnamePassAwardTotal());
		vo.setGeneralizeRegisterTotal(voTemp.getGeneralizeRegisterTotal());
		vo.setMonthFirstAwardTotal(voTemp.getMonthFirstAwardTotal());
		vo.setOffLineRechargeAwardTotal(voTemp.getOffLineRechargeAwardTotal());
		vo.setSinaFeeTotal(voTemp.getSinaFeeTotal());
		vo.setVipIncomeTotal(voTemp.getVipIncomeTotal());
		vo.setTakeCashFeeTotal(voTemp.getTakeCashFeeTotal());
		vo.setInterestFeeTotal(voTemp.getInterestFeeTotal());
		vo.setNetBorrowFeeTotal(voTemp.getNetBorrowFeeTotal());
		vo.setMoneyLineReseive(voTemp.getMoneyLineReseive());
		vo.setSinaLineReseive(voTemp.getSinaLineReseive());
		vo.setDbAccountMoney(voTemp.getDbAccountMoney());
		vo.setDyTransferAccountMoney(voTemp.getDyTransferAccountMoney());
		vo.setJzTransferAccountMoney(voTemp.getJzTransferAccountMoney());
		vo.setMbTransferAccountMoney(voTemp.getMbTransferAccountMoney());
		vo.setTjbTransferAccountMoney(voTemp.getTjbTransferAccountMoney());
		vo.setDbTransferAccountMoney(voTemp.getDbTransferAccountMoney());
		vo.setLlFeeReseive(voTemp.getLlFeeReseive());
		vo.setTransferManagerFree(voTemp.getTransferManagerFree());
		vo.setFirstTransferManagerFree(voTemp.getFirstTransferManagerFree());
		vo.setChangeDrawMoneyFee(voTemp.getChangeDrawMoneyFee());
		vo.setNetBorrowOverdueFee(voTemp.getNetBorrowOverdueFee());
		vo.setAddInvestPersonsTotal(tReportMapper.queryAddInvestPersonsTotal(reportStatementCnd));
		vo.setInvestPersonsTotal(tReportMapper.queryInvestPersonsTotal(reportStatementCnd));
		vo.setNowInvestPersonsTotal(tReportMapper.queryNowInvestPersonsTotal(reportStatementCnd));
		return vo;
	}
}
