<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="list" class="main_cent">
	<div>
		<font color="red">结清标的一共有<fmt:formatNumber value="${finishmap.totalCount }" pattern="#,##0" />个, 其中,应还本金<fmt:formatNumber value="${finishmap.totalCapital }" pattern="#,##0.00"/>(直通车金额<fmt:formatNumber value="${finishmap.totalFirstAcount }" pattern="#,##0.00"/>)， 应还利息<fmt:formatNumber value="${finishmap.totalInterest }" pattern="#,##0.00"/>， 应还罚息<fmt:formatNumber value="${finishmap.totalLateInterest }" pattern="#,##0.00"/></font><br/>
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>借款标题</th>
			<th>类型</th>
			<th>借款人</th>
			<th>期数</th>
			<th>应还本金</th>
			<th>应还直通车金额</th>
			<th>可解锁直通车金额</th>
			<th>应还利息</th>
			<th>应还罚息</th>
			<th>应还日期</th>
			<th>还款状态</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td>${vo.name}</td>
				<td>${vo.borrowtypeStr }</td>
				<td>${vo.username }</td>
				<td>${vo.periods }</td>
				<td>${vo.capitalStr }</td>
				<td><fmt:formatNumber value="${vo.firstAccount}" pattern="#,##0.00"/></td>
				<td><fmt:formatNumber value="${vo.unlockFirstAccount}" pattern="#,##0.00"/></td>
				<td>${vo.interestStr }</td>
				<td><fmt:formatNumber value="${vo.lateInterest}" pattern="#,##0.00"/></td>
				<td>${vo.repaymentTimeStr }</td>
				<td>${vo.statusStr }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>