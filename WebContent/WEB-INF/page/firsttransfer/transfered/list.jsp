<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
	
		<td>${vo.transferName}</td>
		<td>${vo.userName}</td>
		<td>${vo.accountReal}</td>
		<td>${vo.tenderRealAccount}</td>
		<td>${vo.interest}</td>
		<td>${vo.interestDiff}</td>
		<td>${vo.manageFee}</td>
		<td>${vo.addTimeStr}</td>
		<td>${vo.successTimeStr}</td>
		<td>
			<a href="javascript:;" onclick="detail(${vo.id});">认购列表</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="10">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>