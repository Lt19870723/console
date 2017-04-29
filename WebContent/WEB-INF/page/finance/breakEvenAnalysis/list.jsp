<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 98%;">
	<thead>
		<tr>	
			<th>日期</th>
			<th>收入</th>
			<th>支出</th>
			<th>净收益</th>
			<th>充值</th>
			<th>提现</th>
			<th>净流量</th>
			<th>收入增长率</th>
			<th>支出增长率</th>
			<th>充值增长率</th>
			<th>提现增长率</th>
			<th>到期本息(包含直通车)</th>
			<th>到期本息（直通车）</th>
			<th>预期提现率</th>
			<th>预期提现金额</th>
			<th>实际提现率</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}"> 
			<tr>
					<td>
					<c:if test="${vo.type == 1}"><span style="color: ;"><fmt:formatDate value="${vo.time}" pattern="MM月" />平均</span></c:if>
					<c:if test="${vo.type == 2}"><span style="color: ;"><fmt:formatDate value="${vo.time}" pattern="MM月dd日"/></span></c:if>
					</td>
				<td>${vo.income}</td>
				<td>${vo.pay}</td>
				<td>${vo.netProceeds}</td>
				<td>${vo.recharge}</td>
				<td>${vo.advance}</td>
				<td>${vo.netFlow}</td>
				<td>${vo.incomeRate}</td>
				<td>${vo.payRate}</td>
				<td>${vo.rechargeRate}</td>
				<td>${vo.recordeRate}</td>
				<td>
					<c:if test="${vo.type == 1}"></c:if>
					<c:if test="${vo.type == 2}">${vo.allPrincipal}</c:if>
				</td>
				<td>
				<c:if test="${vo.type == 1}"></c:if>
					<c:if test="${vo.type == 2}">${vo.throughPrincipal}</c:if>
				</td>
				<td>${vo.expectRate}</td>
				<td>${vo.expectCash}</td>
				<td>${vo.actualRate}</td>
				<td><c:if test="${vo.type == 1}"></c:if>
					<c:if test="${vo.type == 2}"><a href="javascript:void(0);" onclick="edit(${vo.id})">计算预期</a></c:if>
				</td>
				
			</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>