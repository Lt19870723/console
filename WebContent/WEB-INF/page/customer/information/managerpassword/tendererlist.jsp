<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th width="1%">序号</th>
			<th width="4%">用户名</th>
			<th width="2%">用户类型</th>
			<th width="2%">真实姓名</th>
			<th width="8%">邮箱</th>
			<th width="5%">手机号码</th>
			<th width="8%">重置密码</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>
				    <c:if test="${vo.isFinancialUser == '1'}">理财用户</c:if>
					<c:if test="${vo.isFinancialUser == '0'}">借款用户</c:if>
				</td>
				<td>${vo.realName}</td>
				<td>${vo.email}</td>
				<td>${vo.mobileNum}</td>
				<td align="left">
					<a style="cursor: pointer;" id="btnresetlogpwd" name="btnresetlogpwd" onclick="javascript:initEditPage('login','${el:encode(el:encode(vo.username))}');"> 重置登录密码</a>
					 &nbsp; 
					<a style="cursor: pointer;" id="btnresetlogpwd" name="btnresetlogpwd" onclick="javascript:initEditPage('deal','${el:encode(el:encode(vo.username))}');"> 重置交易密码</a>
				</td>
			</tr>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>