<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table id="dataTable" class="fulltable" style="width: 99%;">
		<tr>
			<th width="35">序号</th>
			<th>用户名</th>
			<th width="60">排队号</th>
			<th width="170">排队编号</th>
			<th width="50">状态</th>
			<th width="120">投宝方式</th>
			<th>投宝额度(元)</th>
			<th>实际投宝(元)</th>
<!-- 			<th>定期宝期限</th> -->
			<th width="100">记录时间</th>
			<th width="70">记录类型</th>
			<th width="90">所投宝编号</th>
			<th width="40">详情</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.userName}</td>
				<td><c:if test="${vo.rownum != null&& vo.rownum != 0}">${vo.rownum}</c:if></td>
				<td>${vo.uptime}</td>
				<td>
					<c:if test="${vo.status==0}">未启用</c:if> 
					<c:if test="${vo.status==1}">已启用</c:if> 
					<c:if test="${vo.status==-1}">已删除</c:if>
				</td>
				<td>
					<c:if test="${vo.autoTenderType==1}">按金额投宝</c:if> 
					<c:if test="${vo.autoTenderType==2}">按账户余额投宝</c:if>
				</td>
				<td><fmt:formatNumber value="${vo.limitMoney}" pattern="###,###" /></td>
				<td><fmt:formatNumber value="${vo.recordType==6?vo.autoTenderMoney:null}" pattern="###,###" /></td>
<%-- 				<td>${vo.fixLimitTemp}</td> --%>
				<td><fmt:formatDate value="${vo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${vo.recordType==1}">设置</c:if> 
					<c:if test="${vo.recordType==2}">修改</c:if> 
					<c:if test="${vo.recordType==3}">停用 </c:if> 
					<c:if test="${vo.recordType==4}">启用</c:if> 
					<c:if test="${vo.recordType==5}">删除</c:if>
					<c:if test="${vo.recordType==6}">投宝</c:if> 
					<c:if test="${vo.recordType==7}">流宝</c:if> 
					<c:if test="${vo.recordType==8}">撤宝</c:if> 
					<c:if test="${vo.recordType==9}">重新排队</c:if>
				</td>
				<td>
					<c:if test="${vo.recordType==6 or vo.recordType==7 or vo.recordType==8}">
						<a href="${portalPath }/dingqibao/${vo.fixId}.html" target="_blank">${vo.fixNo}</a>
					</c:if>
				</td>
				<td><a href="javascript:;" onclick="fordetail(${vo.id});">详情</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td style="text-align: left;" colspan="12">
				<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
			</td>
		</tr>
	</table>
</div>