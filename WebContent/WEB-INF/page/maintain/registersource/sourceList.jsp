<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<tbody>
	<c:forEach var="SourceListVo" items="${page.result}" varStatus="i">
		<tr>
			<td><input type="checkbox" name="checkbox" value="${SourceListVo.sourceNo}" /></td>
			<td>${i.index+1}</td>
			<td>${SourceListVo.sourceNo }</td>
			<td>${SourceListVo.sourceName}</td>
			<td><fmt:formatDate value="${SourceListVo.startTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/></td>
			<td><fmt:formatDate value="${SourceListVo.endTime}"    pattern="yyyy-MM-dd HH:mm:ss"   type="both"/></td>
			<td>${SourceListVo.updateUserName}</td>
			<td><fmt:formatDate value="${SourceListVo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"  type="both"/></td>
			<td>${SourceListVo.remark}</td>
		</tr>
	</c:forEach>
</tbody>
