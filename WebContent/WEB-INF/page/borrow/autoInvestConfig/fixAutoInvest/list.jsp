<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table id="dataTable" class="fulltable" style="width: 98%;">
		<tr>
			<th width="50">序号</th>
			<th>用户名</th>
			<th width="100">排队号</th>
			<th width="170">排队编号</th>
			<th width="70">状态</th>
			<th width="140">投宝方式</th>
			<th>投宝额度（元）</th>
			<th>定期宝期限</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.userName}</td>
				<td><c:if test="${vo.rownum != null&& vo.rownum != 0}">第${vo.rownum}位</c:if></td>
				<td>${vo.uptime}</td>
				<td>
					<c:if test="${vo.status=='0'}">未启用</c:if> 
					<c:if test="${vo.status=='1'}">已启用</c:if> 
					<c:if test="${vo.status=='-1'}">已删除</c:if>
				</td>
				<td>
					<c:if test="${vo.autoTenderType==1}">按金额投宝</c:if> 
					<c:if test="${vo.autoTenderType==2}">按账户余额投宝</c:if>
				</td>
				<td><fmt:formatNumber value="${vo.limitMoney}" pattern="###,###"/></td>
				<td>${vo.fixLimitTemp}</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="text-align: left;" colspan="8">
				<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
			</td>
		</tr>
	</table>
</div>