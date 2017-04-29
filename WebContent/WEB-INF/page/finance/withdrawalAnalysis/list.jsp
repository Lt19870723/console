<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div>
	<font color="red">提现金额总计:<fmt:formatNumber value="${resultMap.totalWithdrawalAmount}" pattern="#,##0.00"/></font>&nbsp;&nbsp;
	<font color="red">正常提现金额总计:<fmt:formatNumber value="${resultMap.totalNomalWithdrawal}" pattern="#,##0.00"/></font>&nbsp;&nbsp;
	<font color="red">撤资倾向金额总计:<fmt:formatNumber value="${resultMap.totalDivestmentTendency}" pattern="#,##0.00"/></font>
	<font color="red">撤资金额总计:<fmt:formatNumber value="${resultMap.totalDisinvestment}" pattern="#,##0.00"/></font>
</div>
<table id="dataTable" class="fulltable" style="width: 99%;">
	<thead>
		<tr>	
			<th>提现排名</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>金额(元)</th>
			<th>备注</th>
			<th>资产(元)</th>
			<th>提现比率</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}"> 
			<tr>
				<td
					 <c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
					 <c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>
				>${vo.rankno}</td>
			    <td 
			    	<c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
			    	<c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>
			    >${vo.username}</td>
				<td 
					<c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
					<c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>	
				>${vo.realname}</td>
				<td 
					<c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
					<c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>
					><fmt:formatNumber value="${vo.withdrawalAmount}" pattern="#,##0.00"/></td>
				<td 
					<c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
					<c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>
				>${vo.withdrawalType}</td>
				<td 
					<c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
					<c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>
				><fmt:formatNumber value="${vo.assetAmount}" pattern="#,##0.00"/></td>
				<td 
					<c:if test="${vo.withdrawalType == '撤资倾向' }">style="background-color: #ffd966;"</c:if>
					<c:if test="${vo.withdrawalType == '撤资' }">style="background-color: #ee1d24;"</c:if>	
				><%-- <fmt:formatNumber value=" --%>${vo.withdrawalRate}<!-- " pattern="###0.00"/> -->%</td>
				
			</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>