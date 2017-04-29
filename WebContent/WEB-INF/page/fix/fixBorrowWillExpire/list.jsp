<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div style="padding:10px 20px;">
	<font color="red">到期资金总额：<fmt:formatNumber value="${fixStatic.capital+fixStatic.profit}" pattern="#,##0.00" /> 元</font>
</div>
<table class="fulltable" style="width: 100%;">
	<tr>
		<th width="40" align="center">序号</th>
		<th width="120" align="center">定期宝编号</th>
		<th width="120" align="center">开放额度(万元)</th>
		<th width="90" align="center">锁定期限</th>
		<th width="150" align="center">待支出总利息(元)</th>
		<th width="150" align="center">宝还款日</th>
		<th width="150" align="center">可用余额(元)</th>
		<th width="120" align="center">投标债权</th>
		<th width="120" align="center">操作</th>
	</tr>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}"  >
		<td>${status.index + 1}</td>
		<td align="center">${vo.contractNo }</td>
		<td align="center">${vo.planAccountStr }</td>
		<td align="center">${vo.lockLimit }个月 </td>
		<td align="center">${vo.interest }</td>
		<td align="center">${vo.lockEndtimeStr }</td>
		<td align="center">${vo.useMoney }</td>
		<td align="center">
			<a href="javascript:;" onclick="detail(${vo.id});">查看</a>
		</td>
		<td align="center">
			<a href="javascript:;" onclick="toAddModifyQuickPub(${vo.id});">一键发宝</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="9">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</table>