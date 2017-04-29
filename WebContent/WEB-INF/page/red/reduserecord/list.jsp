<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>红包ID</th>
			<th>红包金额</th>
			<th>用户名</th>
			<th>类型</th>
			<th>所投标的</th>
			<th>投标金额</th>
			<th>发放日期</th>
			<th>使用日期</th>
			<th>红包来源</th>
			<th>注册来源</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.redId}</td>
				<td>${vo.money}</td>
				<td>${vo.userName}</td>
				<td>
				    <c:if test="${vo.bizType==1}">定期宝</c:if>
					<c:if test="${vo.bizType==2}">直通车</c:if>
					<c:if test="${vo.bizType==3}">手动投标</c:if>
                </td>
                <td>${vo.usebizNo}</td>
                <td>${vo.account}</td>
				<td><fmt:formatDate value="${vo.addTime}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${vo.useTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${vo.source}</td>
				<td>${vo.memberSource}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>