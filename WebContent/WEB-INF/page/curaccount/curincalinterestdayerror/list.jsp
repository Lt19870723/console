<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.username}</td>
		<td>${vo.account}</td>
		<td>${vo.drawMoney}</td>
		<td>${vo.noDrawMoney}</td>
		<td>${vo.actualMoney}</td>
		<td>${vo.actualDrawMoney}</td>
		<td>${vo.actualNoDrawMoney}</td>
		<td><fmt:formatDate value="${vo.calInterestDay}" pattern="yyyy-MM-dd" /></td>
		<td><fmt:formatDate value="${vo.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="10">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>