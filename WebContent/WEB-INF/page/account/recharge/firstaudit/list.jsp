<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" width="100%">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>充值订单号</th>
			<th>充值类型</th>
			<th>充值金额</th>
			<th>充值时间</th>
			<th>充值银行</th>
			<th>手续费</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.realname}</td>
				<td>${vo.tradeNo}</td>
				<td>${vo.typeStr}</td>
				<td>${vo.moneyStr}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.payment}</td>
				<td>${vo.feeStr}</td>
				<td><a href="javascript:showAuditLayer('${vo.id }')">立即审核</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>