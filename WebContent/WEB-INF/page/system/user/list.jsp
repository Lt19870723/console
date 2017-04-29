<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="user" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${user.userName}</td>
		<td>${user.name}</td>
		<td>${user.email}</td>
		<td>${user.mobile}</td>
		<td>${user.tel}</td>
		<td>${user.dept}</td>
		<td>${user.position}</td>
		<td>${user.roleName}</td>
		<td><c:if test="${user.status == 0}">启用</c:if><c:if test="${user.status == 1}">禁用</c:if></td>
		<td>
			<a href="javascript:;" onclick="edit(${user.id});">编辑</a>
			<c:if test="${user.status == 0}">&nbsp;&nbsp;<a href="javascript:;" onclick="updateStatus(${user.id});">禁用</a></c:if>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="10">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>