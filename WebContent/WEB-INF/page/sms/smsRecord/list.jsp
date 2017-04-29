<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width: 100%">
		<tr>
			<th width="4%">序号</th>
			<th width="9%">用户名</th> 
			<th width="10%">手机号码</th> 
			<th>短信内容</th> 
			<th width="6%">模板编号</th> 
			<th width="6%";>发送状态</th> 
			<th width="8%";>发送时间</th> 
			<th width="6%";>平台来源</th> 
			<th width="9%";>最近更新时间</th> 
			<th width="8%";>供应商类型</th>
		</tr>
		<c:forEach items="${page.result}" var="s" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${s.username}</td>
				<td>${s.mobile}</td>
				<td style="text-align: left;">${s.content}</td>
				<td>${s.smsType}</td>
				<td>${s.invokingStatus}</td>
				<td><fmt:formatDate value="${s.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
				<c:if test="${s.platform==1}">网页</c:if>
				<c:if test="${s.platform==2}">微信</c:if>
				</td>
				<td><fmt:formatDate value="${s.lastmodifytime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
				<c:if test="${s.vendorType==1}">漫道</c:if>
				<c:if test="${s.vendorType==0}">港奥资迅</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
