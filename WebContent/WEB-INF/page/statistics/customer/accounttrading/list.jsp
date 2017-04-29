<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="list" class="main_cent">
	<div>
		<c:if test="${accountLogMap.netMoneyLimit != null}">
			<font color="red">净值额度：<fmt:formatNumber value="${accountLogMap.netMoneyLimit}" pattern="#,##0.00"/></font>
		</c:if>
		<c:if test="${accountLogMap.noRepayTotal != null}">
			<font color="red">待还总额：<fmt:formatNumber value="${accountLogMap.noRepayTotal}" pattern="#,##0.00" /></font>
		</c:if>
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 100%;">
		<tr>
			<th style="width: 4%;">序号  </th>
			<th>用户名  </th>
			<th style="width: 8%;">注册时间 </th>
			<th>交易类型  </th>
			<th>交易金额    </th>
			<th>可用余额   </th>
			<th>可提现金额 </th>
			<th>不可提现金额 </th>
			<th>冻结金额  </th>
			<th>待收总额  </th>
			<th>投标直通车余额 </th>
			<th>账户总额  </th>
			<th style="width: 8%;">交易时间  </th>
			<th>交易明细  </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index + 1}</td>
				<td>${vo.username}</td>
		        <td>${vo.registTime}</td>
				<td>${vo.typeStr}</td>
				<td>${vo.moneyStr}</td>
				<td>${vo.useMoneyStr}</td>
				<td>${vo.drawMoneyStr}</td>
				<td>${vo.noDrawMoneyStr}</td>
				<td>${vo.noUseMoneyStr}</td>
				<td>${vo.collectionStr}</td>
				<td>${vo.firstBorrowUseMoneyStr}</td>
				<td>${vo.accountTotalStr}</td>
				<td>${vo.addtimeStr}</td>
				<td>${vo.remark} 
					<span style="${vo.idType!=null and vo.borrowId!=null and vo.idType==1?'':'display: none'}">
					(直通车：<a title='${vo.borrowName }' href='${portal_path}/zhitongche/${vo.borrowId}.html' target='_blank'> ${fn:substring(vo.borrowName,0,10)}</a>)  
					</span>
					 <span  style="${vo.idType!=null and  vo.borrowId!=null and vo.idType==0?'':'display: none'}"> 
					 (借款标：<a title='${vo.borrowName }' href='${portal_path}/toubiao/${vo.borrowId}.html' target='_blank'> ${fn:substring(vo.borrowName,0,10)}</a>) </span>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>