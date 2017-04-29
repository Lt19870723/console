<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<c:forEach var="item" items="${leftMenus}">
<h3>${item.key.name}</h3>
<div>
	<c:forEach var="menu" items="${item.value}">
	<a href="javascript:;" class="menuchage" id="menu${menu.id}" onclick="changeSrc('${path}/${menu.url}','menu${menu.id}');">${menu.name}</a>
	</c:forEach>
</div>
</c:forEach>