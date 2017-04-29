<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="40" align="center">序号</th>
			<th width="90" align="center">定期宝编号</th>
			<th width="120" align="center">开放额度(万元)</th>
			<th width="90" align="center">锁定期限</th>
			<th width="80" align="center">转让价格(元)</th>
			<th width="80" align="center">本金(元)</th>
			<th width="80" align="center">还款日期</th>
			<th width="120" align="center">操作</th>
		</tr>
		 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center">${vo.contractNo}</td>
				<td align="center">${vo.planAccountStr}</td>
				<td align="center">${vo.lockLimit}个月</td>
				<td align="center">${vo.zrMoney}</td>
				<td align="center">${vo.capital}</td>
				<td align="center">
					<fmt:formatDate value="${vo.lockEndTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td align="center">
					  <a href="javascript:;" onclick="subsByHand(${vo.id});">手动转让</a>
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