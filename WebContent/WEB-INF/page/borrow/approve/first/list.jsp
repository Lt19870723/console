<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		    <th>序号</th>
			<th>借款标题</th>
			<th>类型</th>
			<th>借款用户</th>
			<th>借款总额</th>
			<th>借款期限</th>
			<th>年利率</th>
			<th>还款方式</th>
			<th>发标时间</th>
			<th>是否存管</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td><a href="${portal_path}/toubiao/showBorrowDetail/${vo.id}.html" target="_blank">${vo.name}</a></td>
				<td>${vo.borrowTypeName}</td>
				<td>${vo.username}</td>
				<td>
				<fmt:formatNumber type="currency" value="${vo.account}" currencySymbol="¥" maxFractionDigits="2"/> 
				</td>
				<td>${vo.timeLimit}<c:if test="${borrow.style != 4}">个月</c:if> <c:if
						test="${borrow.style== 4}">天</c:if></td>
				<td>${vo.apr}%</td>
				<td>${vo.styleName }</td>
				<td><fmt:formatDate value="${vo.addTime}" type="both"/></td> 
				<td>
				<c:choose>
		    	<c:when test="${vo.isCustody==1}">
		    	存管标
		    	</c:when>
		    	<c:otherwise>非存管标</c:otherwise>
		   		</c:choose>
		   	 	</td>
				<td>
			    <a href="javascript:;" onclick="showAuditLayer(${vo.id});">审核</a>
		        </td>
				 
			</tr>
		</c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>