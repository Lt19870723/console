<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td align="center" >
			${vo.borrowName }
		</td>
		<td align="center" >
			${vo.contractNo }
		</td>							
		<td align="center" >
			${vo.parentContractNo }
		</td>
		<td align="center" >
			<fmt:formatNumber value="${vo.account}" pattern="#,##0.00" />
		</td>
		<td align="center" >
			${vo.tenderTime}
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="6">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>