<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<div id="list" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>加分用户</th>
			<th>加分时间</th>
			<th>积分</th>
			<th>加分理由</th>
		</tr>
		<c:forEach items="${list}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td>${vo.username}</td>
				<td><fmt:formatDate value="${vo.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${vo.integral }</td>
				<td>${vo.reason }</td>
</tr>
		</c:forEach>
	</table>
</div>