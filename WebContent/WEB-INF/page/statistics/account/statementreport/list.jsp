<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">注册人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operateStatementVo.totalRegMembers}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">实名认证人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operateStatementVo.totalRealNameMembers}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">VIP认证人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operateStatementVo.totalVIPPassMembers}" pattern="#,##0"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">投资人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operateStatementVo.totalTenders}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">充值金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalRechargeMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">提现金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalCashMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">抵押标金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalDYMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">净值标金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalJZMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">秒标金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalMBMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">信用标金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalTJMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">担保标金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalDBMoney}" pattern="#,##0.00"/>
			</div>
		</div>	
		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">存管提现金额：</span>
			<div style="font-size:14px;">
				￥<fmt:formatNumber value="${operateStatementVo.totalCGMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px"></span>
			<div style="font-size:14px;">
			</div>
		</div>
	</div>
	
</div>
