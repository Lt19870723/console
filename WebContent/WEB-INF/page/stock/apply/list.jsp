<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>	
				<th style="width: 40px">序号</th>
				<th>申请人姓名</th>
				<th >证件号码</th>
				<th>平台用户名</th>
				<th>申请时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${page.result}" varStatus="status"> 
			<tr>
				<td>${status.index+1}</td>
				<td>${vo.userRealName }</td>
				<td>${vo.idCard }</td>
				<td>${vo.userName }</td>
				<td><fmt:formatDate value="${vo.addtime}" pattern="yyyy-MM-dd"/></td>
				<td>
					<c:choose>
						<c:when test="${vo.status==1 }">待审核</c:when>
						<c:when test="${vo.status==2 }">审核通过</c:when>
						<c:when test="${vo.status==3 }">审核不通过</c:when>
						<c:when test="${vo.status==-1 }">已作废</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>	
				<td>
				   	<a onclick="detail(${vo.id},${vo.type})">查看</a>
				   	<c:if test="${vo.status==1}">
				   	<a onclick="approve(${vo.id},${vo.userId})">审核</a>
				   	</c:if>
				</td>	
			</tr>
			</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</tbody>