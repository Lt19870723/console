<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="menuList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>客户状态</th>
			<th>用户名</th>
			<th>用户类型</th>
			<th>真实姓名</th>
			<th>邮箱</th>
			<th>手机号码</th>
			<th>资产总额</th>
			<th>可用余额</th>
			<th>可提现金额</th>
			<th>不可提现金额</th>
			<th>冻结金额</th>
			<th>待收总额</th>
			<th>投标直通车余额</th>
			<th>注册日期</th>
			<th>最后一次登录时间</th>
			<th>累计登录次数</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="memberVo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td><c:if test="${memberVo.MEMBERID > 0}">已注销</c:if> <c:if
						test="${memberVo.MEMBERID == 0}">正常用户</c:if></td>

				<td>${memberVo.username}</td>
				<td><c:if test="${memberVo.isFinancialUser == 1}">理财用户</c:if> <c:if
						test="${memberVo.isFinancialUser == 0}">借款用户</c:if></td>
				<td>${memberVo.realName}</td>
				<td>${memberVo.email}</td>
				<td>${memberVo.mobileNum}</td>
				<td>${memberVo.totalStr}</td>
				<td>${memberVo.useMoneyStr}</td>
				<td>${memberVo.drawMoneyStr}</td>
				<td>${memberVo.noDrawMoneyStr}</td>
				<td>${memberVo.noUseMoneyStr}</td>
				<td>${memberVo.collectionStr}</td>
				<td>${memberVo.firstBorrowUseMoneyStr}</td>
				<td>${memberVo.addtimeStr}</td>
				<td>${memberVo.lastlogintimeStr}</td>
				<td>${memberVo.logintimes}</td>
				<td><a id="clean" href="javascript:clean(${memberVo.id });"
					style="${memberVo.status == 0?'':'display:none'}">注销</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>