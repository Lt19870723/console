<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
<c:if test="${headers!=null && headers!='[]'}">
	<table style="border-spacing: 0;border-collapse: collapse;TABLE-LAYOUT: fixed;min-width: 1000px;">
		<tr>
			<th style="min-width:40px;">序号</th>
			<th style="min-width:70px;">用户名</th>
			<c:forEach items="${headers}" var="obj">
				<th style="min-width:80px;">${obj}</th>
			</c:forEach>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<c:forEach items="${vo}" var="vb">
					<td>${vb }</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</c:if>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>