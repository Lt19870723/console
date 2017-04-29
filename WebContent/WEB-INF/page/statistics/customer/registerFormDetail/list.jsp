<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 103%;">
		<tr>
			<th>用户名</th>
			<th>用户状态</th>
			<th>真实姓名</th>
			<th>邮箱</th>
			<th>手机</th>
			<th>注册时间</th>
			<th>来源</th>
			<th>资产总额</th>
			<th>是否实名认证</th>
			<th>是否手机认证</th>
			<th>是否邮箱认证</th>
<!-- 			<th>是否VIP</th> -->
			<th>是否充值</th>
			<th>充值金额</th>
			<th>是否投标</th>
			<th>投标金额</th>
			<th>是否提现</th>
			<th>提现金额</th>
			<th>充值和提现差值</th>
		</tr>
		<c:forEach items="${page.result }" var="obj" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${obj.username }</td>
				<td>${obj.status }</td>
				<td>${obj.realname }</td>
				<td>${obj.email }</td>
				<td>${obj.mobile }</td>
				<td>${obj.registerTime }</td>
				<td>${obj.source }</td>
				<td><fmt:formatNumber value="${obj.total }" pattern="#,##0"/></td>
				<td>${obj.realPassed }</td>
				<td>${obj.mobilePassed }</td>
				<td>${obj.emailPassed }</td>
<%-- 				<td>${obj.vipPassed }</td> --%>
				<td>${obj.rechargePassed }</td>
				<td><fmt:formatNumber value="${obj.rechargeMoney}" pattern="#,##0"/></td>
				<td>${obj.tenderPassed }</td>
				<td><fmt:formatNumber value="${obj.tenderMoney}" pattern="#,##0"/></td>
				<td>${obj.cashPassed }</td>
				<td><fmt:formatNumber value="${obj.cashMoney}" pattern="#,##0"/></td>
				<td><fmt:formatNumber value="${obj.diffMoney}" pattern="#,##0"/></td>				
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>