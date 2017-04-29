<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.userName}</td>
		<td>${vo.interestTotal}</td>
		<td>${vo.interestYesterday}</td>
		<td>${vo.noUseMoney}</td>
		<td>${vo.useMoney}</td>
		<td>${vo.total}</td>
		<td>
			<a href="javascript:;" onclick="detail(${vo.id});">查看</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="8">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>