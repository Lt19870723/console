<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>投资类型</th>
			<th>投资金额</th>
			<th>利率</th>
			<th>投资时间</th>
			<th>账户总额</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.userName}</td>
			<td>${vo.tenderType}</td>
		    <td><fmt:formatNumber value="${vo.tenderMoney}" pattern="#,##0.00"/></td>
			<td><c:if test="${vo.apr!=null}">${vo.apr}</c:if></td>
			<td>${vo.tenderDate}</td>
		    <td><fmt:formatNumber value="${vo.accountTotal}" pattern="#,##0.00"/></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>