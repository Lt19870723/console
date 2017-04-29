<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="sta">
	<tr class="tr_${sta.index%2}">
		<td>${sta.index + 1}</td>
		<td>${vo.transferName}</td>
		<td>复审中</td>
		<td>${vo.capital}</td>
		<td>${vo.interest}</td>
		<td>${vo.manageFee }</td>
		<td>${vo.interestManageFee}</td>
		<td>${vo.interestDiff}</td>
		<td>${vo.account}</td>
		<td>${vo.coef}</td>
		<td>${vo.accountReal}</td>
		<td>${vo.mostAccount}</td>
		<td>${vo.lowestAccount}</td>
		<td>${vo.username}</td>
		<td>${vo.addTimeStr}</td>
		<td>${vo.validTime}</td>
		<td>${vo.tenderTimes}</td>
		<td>
			<a href="javascript:;" onclick="status(${vo.id});">复审</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="18">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>