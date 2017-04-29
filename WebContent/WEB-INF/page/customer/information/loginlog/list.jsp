<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>登录时间</th>
			<th>平台来源</th>
			<th>客户端IP</th>
			<th>省份</th>
			<th>城市</th>
			<th>区域</th>
		</tr>
		<c:forEach items="${page.result }" var="loginLog" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${loginLog.username}</td>
				<td>${loginLog.logintimeFmt}</td>
				<td>${loginLog.platformStr}</td>
				<td>${loginLog.addip}</td>
				<td>${loginLog.province}</td>
				<td>${loginLog.city}</td>
				<td>${loginLog.area}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>