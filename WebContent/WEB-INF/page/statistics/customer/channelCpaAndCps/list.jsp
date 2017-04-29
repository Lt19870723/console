<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width: 100%;">
		<tr>
			<c:if test="${source==17 or source==13}">
				<th>用户名</th>
				<th>绑定注册时间</th>
				<th>投资时间</th>
				<th>投资金额</th>
				<th>期限</th>
				<th>是否债转</th>
			</c:if>
			<c:if test="${source==60}">
				<th>用户ID</th>
				<th>投资时间</th>
				<th>投资金额</th>
				<th>期限</th>
				<th>投资ID</th>
				<th>标名称</th>
				<th>标ID</th>				
			</c:if>
			<c:if test="${source==37}">
				<th>用户名</th>
				<th>注册时间</th>
				<th>投资时间</th>
				<th>投资金额</th>
				<th>期限</th>
			</c:if>
			<c:if test="${source==38}">
				<th>来源</th>
				<th>订单编号</th>
				<th>商品编号</th>
				<th>会员名称</th>
				<th>注册时间</th>
				<th>投资时间</th>
				<th>单次投资金额</th>
				<th>单标期限</th>
			</c:if>			
		</tr>
		<c:forEach items="${page.result }" var="obj" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<c:if test="${source==17 or source==13}">
					<td>${obj.username }</td>
					<td>${fn:substring(obj.bingTime, 0, 19)}</td>
					<td>${fn:substring(obj.tenderTime, 0, 19)}</td>
					<td><fmt:formatNumber value="${obj.bidAmount }" pattern="#,##0"/></td>
					<td>${obj.timeLimit }</td>
					<td>${obj.isTransfer }</td>
				</c:if>
				<c:if test="${source==60}">
					<td>${obj.userId }</td>
					<td>${fn:substring(obj.tenderTime, 0, 19)}</td>
					<td><fmt:formatNumber value="${obj.bidAmount }" pattern="#,##0"/></td>
					<td>${obj.timeLimit }</td>
					<td>${obj.tenderId }</td>
					<td>${obj.borrowName }</td>
					<td>${obj.borrowId }</td>					
				</c:if>
				<c:if test="${source==37}">
					<td>${obj.username }</td>
					<td>${fn:substring(obj.bingTime, 0, 19)}</td>
					<td>${fn:substring(obj.tenderTime, 0, 19)}</td>
					<td><fmt:formatNumber value="${obj.bidAmount }" pattern="#,##0"/></td>
					<td>${obj.timeLimit }</td>
				</c:if>
				<c:if test="${source==38}">
					<td>${obj.source }</td>
					<td>${obj.borrowId }</td>
					<td>${obj.tenderId }</td>
					<td>${obj.username }</td>
					<td>${fn:substring(obj.bingTime, 0, 19)}</td>
					<td>${fn:substring(obj.tenderTime, 0, 19)}</td>
					<td><fmt:formatNumber value="${obj.bidAmount }" pattern="#,##0"/></td>
					<td>${obj.timeLimit }</td>
				</c:if>				
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>