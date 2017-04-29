<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号  </th>
			<th>用户名  </th>
			<th>可用余额   </th>
			<th>可提现金额 </th>
			<th>受限金额 </th>
			<th>冻结金额  </th>
			<th>待收总额  </th>
			<th>投标直通车余额 </th>
			<th>账户总额  </th>
			<th>操作 </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td>${vo.username}</td>
				<td>${vo.useMoneyStr }</td>
				<td>${vo.drawMoneyStr }</td>
				<td>${vo.noDrawMoneyStr }</td>
				<td>${vo.noUseMoneyStr }</td>
				<td>${vo.collectionStr }</td>
				<td>${vo.firstBorrowUseMoneyStr}</td>
				<td>${vo.totalStr}</td>
				<td><a href="javascript:showdraw('${vo.userId }')">转可提</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>