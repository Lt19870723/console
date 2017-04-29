<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width: 99%">
		<tr>
			<th width="3%">序号</th>
			<th width="10%">用户名</th> 
			<th width="6%">真实姓名</th>
			<th width="14%">时间</th> 
			<th width="7%">元宝</th>
			<th width="8%">结余元宝</th>
			<th width="8%">荣誉值</th>
			<th width="9%">类型</th>
			<th width="13%">明细</th>
			<th>备注说明</th>
		</tr>
		<c:forEach items="${page.result}" var="s" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${s.username}</td>
				<td>${s.realname}</td>
				<td><fmt:formatDate value="${s.gainDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td><fmt:formatNumber value="${s.accumulatePoints }" pattern="###,###" /></td>
				<td><fmt:formatNumber value="${s.sycee }" pattern="###,###" /></td>
				<td><fmt:formatNumber value="${s.honor }" pattern="###,###" /></td>
				<td>${s.typeName }</td>
				<td>${s.detail }</td>
				<td>${s.remark }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
