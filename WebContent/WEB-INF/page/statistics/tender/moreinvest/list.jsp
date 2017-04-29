<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
<c:if test="${moreInvestCountList!=null }">
	<div>
		<font color="red">投资总人数：<fmt:formatNumber value="${total.num }" pattern="#,##0"/></font>
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 1400px;">
		<tr>
			<th>投资次数</th>
			<th>投资人数</th>
		</tr>
		<c:forEach items="${moreInvestCountList }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td><fmt:formatNumber value="${vo.times}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${vo.num}" pattern="#,##0"/></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</div>