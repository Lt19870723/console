<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>开户人</th>
			<th>银行账户</th>
			<th>银行名称</th>
			<th>银行支行</th>
			<th>提现时间</th>
			<th>提现金额</th>
			<th>手续费</th>
			<th>到账金额</th>
			<th>审核人</th>
			<th>审核时间</th>
			<th>审核备注</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.realname}</td>
				<td>${vo.account}</td>
				<td>${vo.bank}</td>
				<td>${vo.branch}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.totalStr}</td>
				<td>${vo.feeStr}</td>
				<td>${vo.creditedStr}</td>
				<td>${vo.verifyName}</td>
				<td>${vo.verifyTimeYmdhms}</td>
				<td>${vo.verifyRemark}</td>
				<td>${vo.statusStr}</td>
				<td><c:if test="${isCustody==0}"><a href="javascript:showInvalidCash('${vo.id }')">作废提现</c:if><br/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
