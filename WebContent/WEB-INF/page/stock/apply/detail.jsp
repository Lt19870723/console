<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>
<body>
   <form id="approForm" name="" method="post">
   <input type="hidden" value="${applyInfo.id}" name="id"/> 
     		<table>
				<tr>
					<td align="right">平台用户名：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.userName}"/></td>
					
				</tr>
				<tr>
					<td align="right">姓名：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.userRealName}"/></td>
				</tr>
				<tr>
					<td align="right">性别：</td>
					<td><input type="text" disabled="disabled" value="<c:if test="${applyInfo.sex==0}">男</c:if><c:if test="${applyInfo.sex==1}">女</c:if>"/></td>
				</tr>
				<tr>
					<td align="right">证件号码：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.idCard}"/></td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.mobile}"/></td>
				</tr>
				<c:if test="${applyInfo.type==1}">
				<tr>
					<td align="right">用户平台待收（元）：</td>
					<td><input type="text" disabled="disabled" value="${applyInfo.collection}"/></td>
				</tr>	
				</c:if>
		</table>
		
		<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>	
				<th>审核日期</th>
				<th >状态</th>
				<th>操作人</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="stockApproveList" items="${stockApproveList}"> 
			<tr>
				<td><fmt:formatDate value="${stockApproveList.addtime}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:choose>
						<c:when test="${stockApproveList.status==1 }">待审核</c:when>
						<c:when test="${stockApproveList.status==2 }">审核通过</c:when>
						<c:when test="${stockApproveList.status==3 }">审核不通过</c:when>
						<c:when test="${stockApproveList.status==-1 }">已作废</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				<td>${stockApproveList.userRealName }</td>
				<td>${stockApproveList.remark }</td>
			</tr>
			</c:forEach>
	</table>
</form>
</body>

</html>
