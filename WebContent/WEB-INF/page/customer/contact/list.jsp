<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>手机号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>提交时间</th>
			<th>状态</th>
			<th>审核操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.mobileNum}</td>
				<td>${vo.userName}</td>
				<td>${vo.realName}</td>
				<td>${vo.addTimeStr}</td>
				<td><c:if test="${vo.status == 0}">待解决</c:if> <c:if
						test="${vo.status == 1}">已解决</c:if> <c:if test="${vo.status == 2}">不予解决</c:if>
				</td>
				<td><c:if test="${vo.status == 0}">
						<a onclick="javascript:contact(${vo.id});"
							style="cursor: pointer;">我要联系</a>
					</c:if></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>