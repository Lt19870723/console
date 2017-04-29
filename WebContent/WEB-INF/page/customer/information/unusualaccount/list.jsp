<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>可用余额</th>
			<th>可提现金额</th>
			<th>受限金额</th>
			<th>冻结金额</th>
			<th>待收总额</th>
			<th>投标直通车余额</th>
			<th>账户总额</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.username}</td>
				<td style="${vo.useMoney < 0 ?'color:red':''}">
					${vo.useMoneyStr}</td>
				<td style="${vo.drawMoney < 0 ?'color:red':''}">${vo.drawMoneyStr}</td>
				<td style="${vo.noDrawMoney < 0 ?'color:red':''}">${vo.noDrawMoneyStr}</td>
				<td style="${vo.noUseMoney < 0 ?'color:red':''}">${vo.noUseMoneyStr}</td>
				<td style="${vo.collection < 0 ?'color:red':''}">${vo.collectionStr}</td>
				<td style="${vo.firstBorrowUseMoney < 0 ?'color:red':''}">${vo.firstBorrowUseMoneyStr}</td>
				<td style="${vo.total < 0 ?'color:red':''}">${vo.totalStr}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>