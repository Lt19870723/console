<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">待收总额【最新待收】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.collectionTotal}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">成交总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.successAccountTotal}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">累计投资人数【不含债转认购人】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.investPersonsTotal}" pattern="#,##0"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">手动投标总额【不含债转认购】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.manualTenderMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">自动投标总额【不含债转认购】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.autoTenderMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">直通车投标总额【不含债转认购】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.firstTenderMoney}" pattern="#,##0.00"/>
			</div>
		</div>
	</div>
	
</div>
