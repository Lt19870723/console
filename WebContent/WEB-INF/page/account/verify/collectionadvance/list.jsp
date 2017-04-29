<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<div>
		待收的垫付金额是否小于本金
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		 >
		<tr>
			 <th>序号</th>
		     <th>用户名</th>					 
			 <th>垫付金额</th>				 
			 <th>本金</th> 
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.advanceYAStr}</td>
				<td>${vo.capitalStr}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>