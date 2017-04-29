<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>邮箱</th>
			<th>手机号码</th>
			<th>注册日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.realName}</td>
				<td>${vo.email}</td>
				<td>${vo.mobileNum}</td>
				<td>${vo.addtimeStr}</td>
				<td><a href="javascript:showAuditLayer('${vo.id }')">抽奖机会</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>