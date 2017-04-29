<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 100%;">
		<tr>
			<th>序号  </th>
			<th>综合排名   </th>
			<th>综合得分 </th>
			<th>用户名  </th>
			<th>真实姓名 </th>
			<th>手机号   </th>
			<th>操作 </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td>${vo.totalRank}</td>
				<td>${vo.totalScore }</td>
				<td>${vo.username }</td>
				<td>${vo.realname }</td>
				<td>${vo.mobilenum }</td>
				<td><a href="javascript:showshareholder('${vo.id }')">查看详情</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>