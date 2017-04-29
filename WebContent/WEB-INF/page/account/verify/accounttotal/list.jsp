<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<div>
		 账户总额是否等于可用金额+冻结金额+待收金额+直通车可用余额+存管可用金额+存管冻结金额+存管待收金额
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		 >
		<tr>
			 <th>序号</th>
		     <th>用户名</th>					 
			 <th>账户总额</th>				 
			 <th>可用余额</th> 
			 <th>冻结金额</th>
			 <th>待收总额</th> 
			 <th>优先计划总可用金额</th>
			 <th>存管可用金额</th>
			 <th>存管冻结金额</th>
			 <th>存管待收金额</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td>${vo.totalStr}</td>
				<td>${vo.useMoneyStr}</td>
				<td>${vo.noUseMoneyStr}</td>
				<td>${vo.collectionStr}</td>
				<td>${vo.firstBorrowUseMoneyStr}</td>
				<td>${vo.eUseMoneyStr}</td>
				<td>${vo.eFreezeMoneyStr}</td>
				<td>${vo.eCollectionStr}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>