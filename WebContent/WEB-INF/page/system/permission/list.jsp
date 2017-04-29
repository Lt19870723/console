<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="permission" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${permission.name}</td>
		<td>${permission.desc}</td>
		<td><c:if test="${permission.status == 0}">启用</c:if><c:if test="${permission.status == 1}">禁用</c:if></td>
		<td>
			<a href="javascript:;" onclick="edit(${permission.id});">编辑</a>
			<c:if test="${permission.status == 0}">&nbsp;&nbsp;<a href="javascript:;" onclick="updateStatus(${permission.id});">禁用</a></c:if>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="4">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>