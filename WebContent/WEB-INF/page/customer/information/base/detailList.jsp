<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>红包ID</th>
			<th>红包金额</th>
			<th>用户名</th>
			<th>真实姓名</th>
			<th>红包发放日期</th>
			<th>红包到期日期</th>
			<th>贵宾特权红包开启时间</th>
			<th>使用时间</th>
			<th>红包来源</th>
			<th>红包状态</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.id}</td>
				<td>${vo.money}</td>
				<td><a href="javascript:forrecorddetail('${vo.userId }')">${vo.userName}</a></td>
				<td>${vo.realName}</td>
				<td><fmt:formatDate value="${vo.addTime}" type="date"/></td>
				<td><fmt:formatDate value="${vo.endTime}" type="date"/></td>
				<td><fmt:formatDate value="${vo.openTime}" type="date"/></td>
				<td><fmt:formatDate value="${vo.useTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
				${vo.source}
				</td>
				<td>
					<c:if test="${vo.status==1}">未开启</c:if>
					<c:if test="${vo.status==2}">未使用</c:if>
					<c:if test="${vo.status==3}">已冻结</c:if>
					<c:if test="${vo.status==4}">已使用</c:if>
					<c:if test="${vo.status==5}">已过期</c:if>
				</td>
				<!--<td><a href="javascript:showAuditLayer('${vo.id }')">元宝奖励</a></td>  -->
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>