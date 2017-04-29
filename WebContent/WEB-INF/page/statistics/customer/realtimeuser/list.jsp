<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">总资产：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.total}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">待还净值：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.repayNetMoney}" pattern="#,##0.00" />
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">实际资产：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.actualMoney}" pattern="#,##0.00" />
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">可用余额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.userMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">冻结金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.noUserMoney}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">待收总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.collection}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">可提现金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.drawMoney}" pattern="#,##0.00" />
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">受限金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${realtimeUserAccountVO.noDrawMoney}" pattern="#,##0.00" />
			</div>
		</div>
		<div style="float: left;color: red;display: block;">注：该统计数据包含所有用户（理财用户，借款用户）</div>
	</div>
	
</div>
