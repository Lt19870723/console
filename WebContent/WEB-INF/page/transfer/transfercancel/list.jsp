<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		 <td>${vo.transferName}</td>
		 <td>${vo.username}</td>
		 <td>${vo.account}</td>
		 <td>${vo.coef}</td>
		 <td>${vo.addTimeStr}</td>
		 <td>${vo.cancelTimeStr}</td>
		 <td>${vo.cancelUser!=0?"手动撤销":"自动撤销"}   </td>
		 <td>${vo.cancelUser!=0?vo.cancelUserStr:"系统"}</td>
		 <td>${vo.cancelRemark}</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="9">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>