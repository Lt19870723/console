<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div id="list" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>帖子标题</th>
			<th>发帖人</th>
			<th>发帖时间</th>
			<th>回帖数</th>
			<th>总积分</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td><a target = "_blank" href = "${bbsPath}/posts/${vo.id}/1">${vo.title}</a></td>
				<td>${vo.username}</td>
				<td><fmt:formatDate value="${vo.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${vo.notecount }</td>
				<td><a href="javascript:;" onclick="query(${vo.id});">${vo.integral}</a></td>
</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>