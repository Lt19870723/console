<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="用户在时间段内注册并且充值成功的总额" style="width:250px">新注册用户充值总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.topupTotalNewRegister}" pattern="#,##0.00" />
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="用户在时间段内注册并且提现成功的总额" style="width:250px">新注册用户提现总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.withDrawTotalNewRegister}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="用户在时间段内注册并且借款标满标的投标总额" style="width:250px">新用户投资总额【不含债转认购】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.tenderTotalNewRegister}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="之前有充值并投标超过100【包含100元】，截止到查询结束时间为止，资产总额小于100元的用户总额" style="width:250px">流失投资者人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.losePersonsTotal}" pattern="#,##000"/>
			</div>
		</div>
	</div>
	
</div>
