<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="business" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${business.username}</td>
		<td><fmt:formatDate value="${business.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="2">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>