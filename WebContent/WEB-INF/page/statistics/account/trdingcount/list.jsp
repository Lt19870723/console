<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="logList" class="main_cent" style="margin:5px 10px 20px 30px;width:100%; height:100%;">
	<table class="table-striped">
		<tr>
		  <td>充值金额【不包含国诚金融、国阳资产账号】：</td>
		  <td><fmt:formatNumber value="${reportVo.rechangeTotalMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提现金额（已打款）：</td>
		  <td><fmt:formatNumber value="${reportVo.cashTotalMoney}" pattern="#,##0.00"/></td>
		</tr>
		<tr>
		  <td>提现金额（已申请）：</td>
		  <td><fmt:formatNumber value="${reportVo.cashTotalApplyMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投资人充值成功总额【不包含国诚金融、国阳资产账号】：</td>
		  <td><fmt:formatNumber value="${reportVo.rechangeTotalByTZ}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>借款人充值总额：</td>
		  <td><fmt:formatNumber value="${reportVo.rechangeTotalByJK}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;净充值金额：</td>
		  <td><fmt:formatNumber value="${reportVo.netRechargeTotal}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>用户资金总额：</td>
		  <td><fmt:formatNumber value="${reportVo.useMoneyTotal}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投标直通车总余额：</td>
		  <td><fmt:formatNumber value="${reportVo.firstBorrowUseMoneyTotal}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>抵押标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.dyAccountMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债转抵押标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.dyTransferAccountMoney}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>净值标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.jzAccountMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债转净值标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.jzTransferAccountMoney}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>抵押标时间加权成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.dybTimeTotal}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;净值标 时间加权交易金额：</td>
		  <td><fmt:formatNumber value="${reportVo.jzbTimeTotal}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>秒标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.mbAccountMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债转秒标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.mbTransferAccountMoney}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>信用标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.tjbAccountMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债转信用标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.tjbTransferAccountMoney}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>担保标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.dbAccountMoney}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;债转担保标成交金额：</td>
		  <td><fmt:formatNumber value="${reportVo.dbTransferAccountMoney}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>已支付抵押标利息总额（即时）：</td>
		  <td><fmt:formatNumber value="${reportVo.dybHavePayInterstTotal}" pattern="#,##0.00"/></td>
		  <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;待支付抵押标利息总额：</td>
		  <td><fmt:formatNumber value="${reportVo.dybUnPayInterstTotal}" pattern="#,##0.00"/></td>
		</tr> 
		<tr>
		  <td>上线活动奖励（即时）：</td>
		  <td><fmt:formatNumber value="${reportVo.activityAwardTotal}" pattern="#,##0.00"/></td>
		 
		</tr> 
	 
	</table>
　
</div>
