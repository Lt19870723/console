<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		
		<div  id="sidebar" >
				   <div class="listzent">网站支出数据统计</div>
  					
					<div style="margin-top:10px;font-size:14px;">
						支出总和:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.payTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						实名认证奖励:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.realnamePassAwardTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						推广注册奖励： （充值1000，推荐人和被推荐人各奖励10元）:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.generalizeRegisterTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						提现补帖奖励：（待收超过5000，月初奖励10元）:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.monthFirstAwardTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						论坛活动奖励:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.forumActivityAwardTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						线下充值奖励:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.offLineRechargeAwardTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						网银在线充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.chinaBankFeeTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						盛付通充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.shengpayFeeTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						国付宝充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.guopayFeeTotal}" pattern="#,##0.00"/>
					</div>
					<div style="margin-top:10px;font-size:14px;">
						新浪支付充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.sinaFeeTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						连连支付充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.llFeeTotal}" pattern="#,##0.00" />
					</div>		
					<div style="margin-top:10px;font-size:14px;">
						富友支付充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.fuiouFeeTotal}" pattern="#,##0.00" />
					</div>
                    <div style="margin-top:10px;font-size:14px;">
                        浙商银行存管充值费用:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.czbankCustodyTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						充值费用总和：（第三方充值费用总和加上线下充值奖励）:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.rechargeTotal}" pattern="#,##0.00"/>
					</div>
					<div style="margin-top:10px;font-size:14px;">
						活期宝已支付投资人利息:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.curInterestTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						红包支出:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.redOutTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						共享奖奖励:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.gxjOutTotal}" pattern="#,##0.00" />
					</div>
  					<div style="margin-top:10px;font-size:14px;">
						推荐活动现金支出:&nbsp;&nbsp;<fmt:formatNumber value="${totalMoney}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						论坛活动现金支出:&nbsp;&nbsp;<fmt:formatNumber value="${awardMoney}" pattern="#,##0.00" />
					</div>
  				</div>
  					
  					
  						<div  id="content" >
  				  <div class="listzent">网站收入数据统计</div>
  				 
  					<div style="margin-top:10px;font-size:14px;">
						收入总和:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.incomeTotal}" pattern="#,##0.00"/>
					</div>
					<div style="margin-top:10px;font-size:14px;">
						VIP费用收入:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.vipIncomeTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						提现手续费:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.takeCashFeeTotal}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						利息管理费:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.interestFeeTotal}" pattern="#,##0.00" />
					</div>
					
					<div style="margin-top:10px;font-size:14px;">
						转让管理费:&nbsp;&nbsp; <fmt:formatNumber value="${reportVo.transferManagerFree}" pattern="#,##0.00" />
					</div>
					
					<div style="margin-top:10px;font-size:14px;">
						直通车转让管理费:&nbsp;&nbsp; <fmt:formatNumber value="${reportVo.firstTransferManagerFree}" pattern="#,##0.00" />
					</div>
					
					<div style="margin-top:10px;font-size:14px;">
						净值标借款管理费:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.netBorrowFeeTotal}" pattern="#,##0.00" />
					</div>
  					<div style="margin-top:10px;font-size:14px;">
						网银在线充值手续费收入:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.moneyLineReseive}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						新浪支付充值手续费收入:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.sinaLineReseive}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						连连支付充值手续费收入:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.llFeeReseive}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						不可提转可提手续费收入:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.changeDrawMoneyFee}" pattern="#,##0.00" />
					</div>
					<div style="margin-top:10px;font-size:14px;">
						净值标逾期罚息收入:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.netBorrowOverdueFee}" pattern="#,##0.00" />
					</div>
  					<div style="margin-top:10px;font-size:14px;">
						净收益：（收入总和—支出总和）:&nbsp;&nbsp;<fmt:formatNumber value="${reportVo.netEarningTotal}" pattern="#,##0.00" />
					</div>
					
					
  				</div>
		
	</div>
	
</div>
