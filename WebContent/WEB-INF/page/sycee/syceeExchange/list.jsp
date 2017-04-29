<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width:100%">
		<tr> 
		    <th width="4%"> 序号</th>
			<th width="10%">商品名称</th>
			<th width="6%">兑换数量</th>
			<th width="8%">用户名称</th> 
			<th width="7%">真实姓名</th>
			<th width="9%">用户手机</th>
			<th width="15%">兑换时间</th> 
			<th width="7%">处理结果</th>
			<th width="8%">处理人</th>
			<th width="15%">处理时间</th>
			<th width="8%">操作</th>
		</tr>
		<c:forEach items="${page.result}" var="s" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${s.name}</td>
				<td>${s.num}</td>
				<td>${s.username}</td>
				<td>${s.realname}</td>
				<td>${s.mobilenum}</td>
				<td><fmt:formatDate value="${s.exchangeTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td style="${s.dealStatus==0?'':'display:none;'}">未处理</td>
				<td style="${s.dealStatus==1?'':'display:none;'}">已处理</td>
				<td>${s.dealUsername }</td>
			    <td><fmt:formatDate value="${s.dealTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
				<td>
					<c:if test="${s.firstClass==2 and s.dealStatus==0}">
						<a href="javascript:;" onclick="handle('${s.id}','handle');">处理</a>
					</c:if>
					<c:if test="${s.dealStatus!=0}">
						<a href="javascript:void();" onclick="handle('${s.id}','view');">查看</a>
					</c:if>
					<c:if test="${s.firstClass==2 and s.secondClass==3}">
						<br><a href="javascript:void();" onclick="sendMsg('${s.id}');">发送短信</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
