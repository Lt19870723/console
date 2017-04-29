<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 98%;">
	<thead>
		<tr>	
			<th width="10%">操作日期</th>
			<th>第三方平台</th>
			<th>借(收入)方(元)</th>
			<th>贷(支出)方(元)</th>
			<th>结存(元)</th>
			<th width="40%">摘要</th>
			<th width="12%">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}"> 
			<tr>
				<td><fmt:formatDate value="${vo.operationDate}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:if test="${vo.thirdPlatform == 1}">网银在线</c:if>
					<c:if test="${vo.thirdPlatform == 2}">国付宝</c:if>
					<c:if test="${vo.thirdPlatform == 3}">盛付通</c:if>
					<c:if test="${vo.thirdPlatform == 4}">新浪支付</c:if>
					<c:if test="${vo.thirdPlatform == 5}">连连支付</c:if>
					<c:if test="${vo.thirdPlatform == 6}">富友支付</c:if>
				</td>
				<td>${vo.debitAmount}</td>
				<td>${vo.creditAmount}</td>
				<td>${vo.balance}</td>
				<td>${vo.remark}</td>
				<td>
		   			<a href="javascript:void(0);" onclick="showJournal(${vo.id})">查看</a>
				</td>						
			</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>