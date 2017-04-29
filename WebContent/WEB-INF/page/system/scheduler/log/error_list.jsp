<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<tbody>
	<c:forEach var="log" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td><fmt:formatDate value="${log.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>${log.message}</td>
		<td>
			<textarea id="detail_${log.id}" name="detail_${log.id}" style="width: 800px; height: 450px;display: none;" readonly="readonly">${log.exception}</textarea>
			<a href="javascript:;" onclick="detail('detail_${log.id}');">详情</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="3">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>