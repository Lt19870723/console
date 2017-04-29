<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div>
	<table id="" class="" style="width: 700px;">
		<colgroup>
			<col width="60%" />
			<col width="40%" />
		</colgroup>
		<thead>
			<tr>
				<th>统计项</th>
				<th>数值</th>
			</tr>
		</thead>
		<tr>
			<td>转让挂单内转份额（份）</td>
			<td><fmt:formatNumber value="${record.sellerEntrustTotal }" pattern="#,##0"/></td>
		</tr>
		<tr>
			<td>认购挂单内转份额（份）</td>
			<td><fmt:formatNumber value="${record.buyerEntrustTotal }" pattern="#,##0"/></td>
		</tr>
		<tr>
			<td>内转成交数量</td>
			<td><fmt:formatNumber value="${record.totalDealAmount }" pattern="#,##0"/></td>
		</tr>
		<tr>
			<td>转让方主动成交份额（份）</td>
			<td><fmt:formatNumber value="${record.sellerDealAmount }" pattern="#,##0"/></td>
		</tr>
		<tr>
			<td>认购方主动成交份额（份）</td>
			<td><fmt:formatNumber value="${record.buyerDealAmount }" pattern="#,##0"/></td>
		</tr>
		<tr>
			<td>认购挂单内转均价（元/份）</td>
			<td><fmt:formatNumber value="${record.buyerPrice }" pattern="#,##0.00"/></td>
		</tr>
		<tr>
			<td>转让挂单内转均价（元/份）</td>
			<td><fmt:formatNumber value="${record.sellerPrice }" pattern="#,##0.00"/></td>
		</tr>
		<tr>
			<td>成交内转均价（元/份）</td>
			<td><fmt:formatNumber value="${record.totalMoney/record.totalDealAmount }" pattern="#,##0.00"/></td>
		</tr>
		<tr>
			<td>成交总金额（元）</td>
			<td><fmt:formatNumber value="${record.totalMoney }" pattern="#,##0.00"/></td>
		</tr>
		<tr>
			<td>交易服务费（元）</td>
			<td><fmt:formatNumber value="${record.totalFree }" pattern="#,##0.00"/></td>
		</tr>
		<tr>
			<td>转让方挂单成交百分比</td>
			<td><fmt:formatNumber value="${record.sellerDealPercentage*100 }" maxFractionDigits="0"/>%</td>
		</tr>
		<tr>
			<td>认购方挂单成交百分比</td>
			<td><fmt:formatNumber value="${record.buyerDealPercentage*100 }" maxFractionDigits="0"/>%</td>
		</tr>
	     
</table>
</div>
