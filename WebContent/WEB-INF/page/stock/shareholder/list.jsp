<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>	
				<th style="width: 40px">序号</th>
				<th>用户姓名</th>
				<th >证件号码</th>
				<th>平台用户名</th>
				<th>加入日期</th>
				<th>持有份额（份）</th>
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
				<fmt:formatNumber value="${vo.stockTotal }"  pattern="#,##0"/></td>
				<td>
				   	<a onclick="queryLogInfo('${vo.id}','${vo.userId}')">查看变更</a>
				</td>	
			</tr>
			</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</tbody>