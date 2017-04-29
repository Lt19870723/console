<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="role" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${role.name}</td>
		<td>${role.desc}</td>
		<td><c:if test="${role.status == 0}">启用</c:if><c:if test="${role.status == 1}">禁用</c:if></td>
		<td>
			<a href="javascript:;" onclick="edit(${role.id});">编辑</a>
			<c:if test="${role.status == 0}">&nbsp;&nbsp;<a href="javascript:;" onclick="updateStatus(${role.id});">禁用</a></c:if>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="4">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>