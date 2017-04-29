<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>

<div id="logList" class="main_cent">
	 <div style="margin-left:20px;line-height:50px;">
		&nbsp;
		定期宝可用金额:${sumUseMoney}
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		定期宝可投金额:${sumUseMoneyYes}元
		&nbsp;
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="40" align="center">序号</th>
			<th width="90" align="center">定期宝编号</th>
			<th width="80" align="center">开放额度(万元)</th>
			<th width="120" align="center">锁定期限</th>
			<th width="80" align="center">待转出金额(万元)</th>
			<th width="150" align="center">操作</th>
		</tr>
		 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center">${vo.fixContractNo }</td>
				<td align="center">${vo.accountRealWan }</td>
				<td align="center">${vo.fixLockLimit }个月</td>
				<td align="center">${vo.dzcMoneyWan }</td>
				<td align="center">
				     <a href="javascript:;" onclick="toTransferCancel(${vo.id});">取消转让</a>
				     &nbsp;&nbsp;
				     <a href="javascript:;" onclick="subsByHand(${vo.id});">立即认购</a>      
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