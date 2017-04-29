<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	&nbsp;定期宝可用余额：
	<fmt:formatNumber value="${fixUseMoneyTotal}" pattern="#,##0.00" />
	元
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="40">序号</th>
			<th>借款标题</th>
			<th width="75">借款类型</th>
			<th width="105">借款用户</th>
			<th>合同编号</th>
			<th width="75">年化利率</th>
			<th>借款总额 (元)</th>
			<th>已投金额 (元)</th>
			<th>剩余金额 (元)</th>
			<th width="85">发布时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.name}</td>
				<td><c:if test="${vo.borrowtype==1}">信用标</c:if> <c:if test="${vo.borrowtype==2}">抵押标</c:if> <c:if
						test="${vo.borrowtype==5}">担保标</c:if></td>
				<td>${vo.userName}</td>
				<td>${vo.contractNo}</td>
				<td>${vo.apr}%</td>
				<td><fmt:formatNumber value="${vo.account}" pattern="#,##0.00" /></td>
				<td><fmt:formatNumber value="${vo.accountYes}" pattern="#,##0.00" /></td>
				<td><fmt:formatNumber value="${vo.account - vo.accountYes}" pattern="#,##0.00" /></td>
				<td>${vo.publishTimeStr}</td>
				<td><a href="javascript:;" onclick="showFix('${vo.id}','${vo.account - vo.accountYes}');">手动定期宝投标</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td style="text-align: left;" colspan="11">
				<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
			</td>
		</tr>
	</table>
</div>