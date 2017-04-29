<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 98%;">
	<thead>
		<tr>	
			<th style="width: 120px;">充值排名</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>金额(元)</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}"> 
			<tr>
				<td>${vo.rankno}</td>
				 <td>${vo.username}</td>
				<td>${vo.realname}</td>
				<td><fmt:formatNumber value="${vo.rechargeAmount}" pattern="#,##0.00"/></td>
			</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>