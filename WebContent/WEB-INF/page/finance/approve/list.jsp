<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>	
				<th>操作员</th>
				<th>操作金额</th>
				<th>转出方</th>
				<th>转入方</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${page.result}"> 
	
	<tr>
		<td>${vo.operator }</td>
		<td>${vo.operationrMoney }</td>
		<td>${vo.payerName }</td>
		<td>${vo.payeeName }</td>
		<td>
			<c:choose>
				<c:when test="${vo.status==1 }">草稿中</c:when>
				<c:when test="${vo.status==2 }">待审核</c:when>
				<c:when test="${vo.status==3 }">审核通过</c:when>
				<c:when test="${vo.status==4 }">已删除</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td>	
		<td>
		   	<a onclick="showVirement(${vo.id })">查看</a>
		   	<a onclick="approve(${vo.id })">审核</a>
		</td>	
	</tr>
	</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</tbody>
<script type="text/javascript">
	
</script>