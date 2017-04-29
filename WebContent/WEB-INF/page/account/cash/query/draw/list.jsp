<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名称</th>
			<th>转可提金额</th>
			<th>到账金额</th>
			<th>手续费</th>
			<th>申请时间</th>
			<th>状态</th>
			<th>操作IP</th>
			<th>备注</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td>${vo.userName}</td>
				<td>${vo.moneyStr }</td>
				<td>${vo.creditedStr }</td>
				<td>${vo.feeStr }</td>
				<td>${vo.addtimeFMT }</td>
				<td>${vo.statusStr }</td>
				<td>${vo.addIp}</td>
				<td>${vo.remarkStr }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>