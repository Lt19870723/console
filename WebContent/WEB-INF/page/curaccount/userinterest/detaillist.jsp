<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.interestDateStr}</td>
		<td>${vo.money}</td>
		<td>${vo.noUseMoney}</td>
		<td>${vo.total}</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="8">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>