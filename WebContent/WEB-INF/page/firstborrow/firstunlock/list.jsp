<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td align="center" >
			${vo.username }
		</td>
		<td align="center" >
			${vo.accountStr }
		</td>
		<td align="center" >
			${vo.useMoneyStr }
		</td>										
		<td align="center">
			${vo.rateStr}%
		</td>
		<td>
			<a href="javascript:;" onclick="showApprovePopup('${vo.id}','${vo.userId}');">解锁审核</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="6">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>