<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td align="center" >
			${vo.planAccountStr }
		</td>
		<td align="center" >
			${vo.lowestAccountStr }
		</td>
		<td align="center" >
			${vo.mostAccountStr }
		</td>										
		<td align="center" >
			${vo.validTimeStr }
		</td>
		<td align="center" >
			${vo.publishTimeStr }
		</td>
		<td>
			<a href="javascript:;" onclick="toAddModify(${vo.id},'view');">查看</a>
			&nbsp;<a href="javascript:;" onclick="toAddModify(${vo.id},'finalApprove');">审核</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="7">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>