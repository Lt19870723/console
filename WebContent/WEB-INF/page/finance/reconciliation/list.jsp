<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th style="width: 90px;">日期</th>
			<th style="width: 125px;">净提现总额(元)</th>
			<th style="width: 125px;">提现支出总额(元)</th>
			<th style="width: 135px;">实际打款总额(元)</th>
			<th style="width: 155px;">网银在线划出金额(元)</th>
			<th style="width: 90px;">手续费(元)</th>
			<th style="width: 90px;">差异(元)</th>
			<th style="width: 75px;">状态</th>
			<th style="width: 130px;">差异说明</th>
			<th style="width: 90px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}"> 
			<tr>
				<td><fmt:formatDate value="${vo.billDate}" pattern="yyyy-MM-dd"/></td>
				<td align="right"><fmt:formatNumber value="${vo.presentSuccessMoney}" pattern="#,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${vo.takeCashMoney}" pattern="#,##0.00"/></td>
				<td align="right"><fmt:formatNumber value="${vo.actualCashMoney}" pattern="#,##0.00"/></td>
				<td align="right">
					<c:if test="${vo.totalExpenditure == null}">--</c:if>
					<c:if test="${vo.totalExpenditure != null}"><fmt:formatNumber value="${vo.totalExpenditure}" pattern="#,##0.00"/></c:if>
				</td>
				<td align="right">
					<c:if test="${vo.counterFee == null}">--</c:if>
					<c:if test="${vo.counterFee != null}"><fmt:formatNumber pattern="#,##0.00" value="${vo.counterFee}"/></c:if>
				</td>
				<td align="right">
					<c:if test="${vo.difference == null}">--</c:if>
					<c:if test="${vo.difference != null}"><fmt:formatNumber pattern="#,##0.00" value="${vo.difference}"/></c:if>
				</td>
				<td>
					<c:if test="${vo.isSuccess == 1}"><span style="color: green;">对账成功</span></c:if>
					<c:if test="${vo.isSuccess == 2}"><span style="color: red;">保存草稿</span></c:if>
					<c:if test="${vo.isSuccess == 3}"><span style="color: blue;">未对账</span></c:if>
					<c:if test="${vo.isSuccess == 4}"><span style="color: red;">信息不符</span></c:if>
				</td>
				<td title="${vo.remarks}"  style="width:110px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color: blue;"> ${vo.remarks}</td>
				<td>
					<c:if test="${vo.isSuccess == 1}">--</c:if>
					<c:if test="${vo.isSuccess == 2 || vo.isSuccess == 3}"><a href="javascript:void(0);" onclick="edit(${vo.id})">核对</a></c:if>
					<c:if test="${vo.isSuccess == 4}">
						<a href="javascript:void(0);" onclick="updateWithdrawal(${vo.id}, '<fmt:formatDate value="${vo.billDate}" pattern="yyyy-MM-dd"/>');">数据更新</a>
					</c:if>
				</td>						
			</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>