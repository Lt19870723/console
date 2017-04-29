<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;float: left;">

		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">国诚系统充值总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.gcjrRechargeTotal}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">浙商银行充值总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.czbankRechargeMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankRechargeMoney == null }">0.00</c:if>
			</div>
		</div>
        <div style="clear:both;"></div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">国诚系统提现总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.gcjrTakeCashTotal}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">浙商银行提现总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.czbankTakeCashMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankTakeCashMoney == null }">0.00</c:if>
			</div>
		</div>
        <div style="clear:both;"></div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">国诚系统投标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.sysInvestMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">浙商银行投标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.czbankInvestMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankInvestMoney == null }">0.00</c:if>
			</div>
		</div>
        <div style="clear:both;"></div>
        <div class="left1_input_ts" style="float: left;">
			<span class="input_span">国诚投标成功总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.sysInvestSuccessMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">浙商投标成功总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.czbankInvestSuccessMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankInvestSuccessMoney == null }">0.00</c:if>
			</div>
		</div>
        <div style="clear:both;"></div>
	   	<div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.rechargeCompareResult=='true' || checkMap.rechargeCompareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>国诚系统充值总额:<fmt:formatNumber value="${checkMap.gcjrRechargeTotal}" pattern="#,##0.00"/><font color="blue">=</font>
		    	浙商银行充值总额:<fmt:formatNumber value="${checkMap.czbankRechargeMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankRechargeMoney == null }">0.00</c:if>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.rechargeCompareResult=='true' || checkMap.rechargeCompareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.rechargeCompareResult=='true' || checkMap.rechargeCompareResult==null)?'':'注意:充值对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>
        <div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.takeCashCompareResult=='true' || checkMap.takeCashCompareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>国诚系统提现总额:<fmt:formatNumber value="${checkMap.gcjrTakeCashTotal}" pattern="#,##0.00"/><font color="blue">=</font>
		    	浙商银行提现总额:<fmt:formatNumber value="${checkMap.czbankTakeCashMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankTakeCashMoney == null }">0.00</c:if>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.takeCashCompareResult=='true' || checkMap.takeCashCompareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.takeCashCompareResult=='true' || checkMap.takeCashCompareResult==null)?'':'注意:提现对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>
        <div style="clear:both;">&nbsp;</div>
	   	<div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.investMoneyCompareResult=='true' || checkMap.investMoneyCompareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>国诚投标总额:<fmt:formatNumber value="${checkMap.sysInvestMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	浙商投标总额:<fmt:formatNumber value="${checkMap.czbankInvestMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankInvestMoney == null }">0.00</c:if>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.investMoneyCompareResult=='true' || checkMap.investMoneyCompareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.investMoneyCompareResult=='true' || checkMap.investMoneyCompareResult==null)?'':'注意:投标总额以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>
        <div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.investSuccessMoneyCompareResult=='true' || checkMap.investSuccessMoneyCompareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>国诚投标成功总额:<fmt:formatNumber value="${checkMap.sysInvestSuccessMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	浙商投标成功总额:<fmt:formatNumber value="${checkMap.czbankInvestSuccessMoney}" pattern="#,##0.00"/>
                <c:if test="${ checkMap.czbankInvestSuccessMoney == null }">0.00</c:if>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.investSuccessMoneyCompareResult=='true' || checkMap.investSuccessMoneyCompareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.investSuccessMoneyCompareResult=='true' || checkMap.investSuccessMoneyCompareResult==null)?'':'注意:投标成功总额以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>
	</div>
	
</div>
