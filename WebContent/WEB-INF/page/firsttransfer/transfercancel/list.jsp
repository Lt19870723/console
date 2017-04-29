<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${vo.transferName}</td>
		<td>${vo.userName}</td>
		<td>${vo.accountReal}</td>
		<td>${vo.addTimeStr}</td>
		<td>${vo.cancleTimeStr}</td>
		<td>${vo.lastUpdateName!="系统自动"?"手动撤销":"自动撤销"}</td>
		<td>${vo.lastUpdateName}</td>
		<td>${vo.remark}</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="8">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>