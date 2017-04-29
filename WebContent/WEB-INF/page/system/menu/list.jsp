<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="menu" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td><c:if test="${menu.level == 1}">一级</c:if><c:if test="${menu.level == 2}">二级</c:if><c:if test="${menu.level == 3}">三级</c:if></td>
		<td>${menu.name}</td>
		<td>${menu.url}</td>
		<td>${menu.desc}</td>
		<td>${menu.order}</td>
		<td><c:if test="${menu.status == 0}">启用</c:if><c:if test="${menu.status == 1}">禁用</c:if></td>
		<td>
			<a href="javascript:;" onclick="edit(${menu.id});">编辑</a>
			<c:if test="${menu.status == 0}">&nbsp;&nbsp;<a href="javascript:;" onclick="updateStatus(${menu.id});">禁用</a></c:if>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="7">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>