<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>红包类型</th>
			<th>红包金额</th>
			<th>备注</th>
			<th>创建时间</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.userName}</td>
				<td>
				<c:forEach items="${redTypes}" var="redtype">
				    <c:if test="${redtype.name==vo.redType}">${redtype.value}</c:if>
				</c:forEach>
				</td>
				<td>${vo.redMoney}</td>
				<td>${vo.remark}</td>
				<td><fmt:formatDate value="${vo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:if test="${vo.status==0}">未发放</c:if>
					<c:if test="${vo.status==1}">已发放</c:if>
				</td>
				<!--<td><a href="javascript:showAuditLayer('${vo.id }')">元宝奖励</a></td>  -->
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>