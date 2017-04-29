<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" width="100%">
		<tr>
			<th width="40">序号</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>用户类型</th>
			<th>充值订单号</th>
			<th>充值类型</th>
			<th>充值金额</th>
			<th>充值时间</th>
			<th>充值银行</th>
			<th>手续费</th>
			<th>状态</th>
			<th>第三方支付平台</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.realname}</td>
				<td><c:if test="${vo.isFinancialUser==0}">借款用户</c:if> <c:if test="${vo.isFinancialUser==1}">理财用户</c:if></td>
				<td>${vo.tradeNo}</td>
				<td>${vo.typeStr}</td>
				<td>${vo.moneyStr}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.payment }</td>
				<td>${vo.feeStr}</td>
				<td>
					<c:if test="${vo.status==0}">充值审核中</c:if> 
					<c:if test="${vo.status==1}">充值成功</c:if> 
					<c:if test="${vo.status==2}">在线充值待付款</c:if> 
					<c:if test="${vo.status==3}">初审成功</c:if>
					<c:if test="${vo.status==9}">充值失败</c:if>
                    <c:if test="${vo.status==10}">处理中</c:if>
                    <c:if test="${vo.status==40}">可疑</c:if>
				</td>
				<td>
					<c:if test="${vo.onlinetype==1}">网银在线</c:if> 
					<c:if test="${vo.onlinetype==2}">充国付宝</c:if> 
					<c:if test="${vo.onlinetype==3}">盛付通</c:if> 
					<c:if test="${vo.onlinetype==4}">新浪支付</c:if> 
					<c:if test="${vo.onlinetype==5}">连连支付</c:if>
					<c:if test="${vo.onlinetype==6}">富友支付</c:if>
                    <c:if test="${vo.onlinetype==8}">浙商存管支付</c:if>
				</td>
				<td><a href="javascript:toLoseOrder('${vo.bizNo}')">补单</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>