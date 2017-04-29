<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="logList" class="main_cent">
	<div>

		<font color="red">提现金额总计:<fmt:formatNumber value="${resultMap.sumTotal}" pattern="#,##0.00"/></font>
		 &nbsp;&nbsp;
		<font color="red">到账金额总计:<fmt:formatNumber value="${resultMap.sumCredited}" pattern="#,##0.00"/></font>
		 &nbsp;&nbsp;
		<font color="red">手续费总计:<fmt:formatNumber value="${resultMap.sumFee}" pattern="#,##0.00"/></font>
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>用户类型</th>
			<th>开户人</th>
			<th>银行账户</th>
			<th>银行名称</th>
			<th>银行支行</th>
			<th>提现时间</th>
			<th>提现金额</th>
			<th>手续费</th>
			<th>到账金额</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.isFinancialUserStr}</td>
				<td>${vo.realname}</td>
				<td>${vo.account}</td>
				<td>${vo.bank}</td>
				<td>${vo.branch}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.totalStr}</td>
				<td>${vo.feeStr}</td>
				<td>${vo.creditedStr}</td>
				<td><a href="javascript:showAuditLayer('${vo.id }')">审核</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>