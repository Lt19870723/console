<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 103%;">
		<tr>
			<th width="4%;">序号</th>
			<th>用户来源渠道</th>
			<th>注册人数</th>
			<th>实名数量</th>
			<th>vip数量</th>
			<th>充值人数</th>
			<th>投资人数</th>
			<th>流失人数</th>
			<th>投资金额</th>
			<th>充值金额</th>
			<th>来源渠道开始时间</th>
			<th>结束时间</th>
			<th>备注</th>
		</tr>
		<c:forEach items="${recommendCountList }" var="obj" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${obj.sourceName }</td>
				<td><fmt:formatNumber value="${obj.registNum }" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.realNameNum}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.vipNum}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.rechargeNum}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.investNum}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.lostNum}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.investMoney}" pattern="#,##0.00"/></td>
				<td><fmt:formatNumber value="${obj.rechargeMoney}" pattern="#,##0.00"/></td>
				<td><fmt:formatDate value="${obj.sourceFrom}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${obj.sourceEndTime}" pattern="yyyy-MM-dd"/></td>
				<td>${obj.remark }</td>
			</tr>
		</c:forEach>
	</table>
</div>