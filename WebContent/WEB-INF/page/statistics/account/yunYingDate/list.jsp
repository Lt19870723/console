<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">注册人数：</span>
			<div style="font-size:14px;">
				${yunYingData.registeredNumber}人
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">投资人数：</span>
			<div style="font-size:14px;">
				${yunYingData.investmentNumber}人
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">直通车存量总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.trainStockTotal}" pattern="#,##0"/>万
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">直通车下车总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.trainOffTotal}" pattern="#,##0"/>万
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">定期宝存量：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.fixStockTotal}" pattern="#,##0.00"/>万
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px"></span>
			<div style="font-size:14px;">
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">提前还款标个数：</span>
			<div style="font-size:14px;">
				${yunYingData.repaymentEarly}个
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">提前还款标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.repaymentEarlyTotal}" pattern="#,##0.00"/>万
			</div>
		</div>		
	<%-- 	<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">到期结清标个数：</span>
			<div style="font-size:14px;">
				${yunYingData.repaymentMaturity}个
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">到期结清标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.repaymentMaturityTotal}" pattern="#,##0.00"/>万
			</div>
		</div> --%>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">新增借款标个数：</span>
			<div style="font-size:14px;">
				${yunYingData.repaymentAdd}个
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">新增借款标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.repaymentAddTotal}" pattern="#,##0.00"/>万
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">续贷标个数：</span>
			<div style="font-size:14px;">
				${yunYingData.standardContinued}个
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">续贷标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.standardContinuedTotal}" pattern="#,##0.00"/>万
			</div>
		</div>		
			<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">逾期标个数：</span>
			<div style="font-size:14px;">
				${yunYingData.repaymentLate}个
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">逾期标总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${yunYingData.repaymentLateTotal}" pattern="#,##0.00"/>万
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px"></span>
			<div style="font-size:14px;">
			</div>
		</div>
	</div>
</div>
