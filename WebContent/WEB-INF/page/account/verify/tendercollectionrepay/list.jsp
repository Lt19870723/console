<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<div>
		待收的应收总额是否等于投标的应收总额，以标为单位
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		 >
		<tr>
			 <th>借款标题</th>
		     <th>投标表待收总额</th>					 
			 <th>待收表应收总额</th>				 
			 <th>差值</th> 
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${vo.name}</td>
				<td>${vo.sumRepaymentAccountStr}</td>
				<td>${vo.sumRepayAccountStr}</td>
				<td>${vo.diffStr}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>