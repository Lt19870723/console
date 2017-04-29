<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th style="width: 40px">序号</th>
			<th style="width: ;">委托转让方</th>
			<th style="width: ;">委托认购方</th>
			<th style="width: ;">转让委托价（元）</th>
			<th style="width: ;">认购委托价（元）</th>
			<th style="width: ;">成交价（元）</th>
			<th style="width: ;">成交份额（份）</th>
			<th style="width: ;">成交总价（元）</th>
			<th style="width: ;">交易总服务费（元）</th>
			<th style="width: ;">成交时间</th>
			<th style="width: ;">成交类型</th>
			<th style="width: ;">状态</th>
			<th style="width: ;">操作</th>
		</tr>
	</thead>
	<tbody>
				<c:forEach var="stock" items="${page.result }" varStatus="vIndex" >
						<tr>
							<td>${vIndex.index+1}</td>
							<td align="right">${stock.sellerName }</td>
							<td align="right">${stock.buyerName }</td>
							<td>
								<fmt:formatNumber value="${stock.entrustPrice }" pattern="#,##0.00"/>
							</td>
							<td>
								<fmt:formatNumber value="${stock.buyerPrice }" pattern="#,##0.00"/>
							</td>
							<td>
								<fmt:formatNumber value="${stock.turnoverPrice }" pattern="#,##0.00"/>
							</td>
							<td>
								<fmt:formatNumber value="${stock.turnoverAmount }" pattern="#,##0"/>	
							</td>
							<td>
								<fmt:formatNumber value="${stock.turnoverTotalPrice }" pattern="#,##0.00"/>
							</td>
							<td>
								<fmt:formatNumber value="${stock.serviceCharge }" pattern="#,##0.00"/>
							</td>
							<td>
								<fmt:formatDate value="${stock.addtime }" pattern="yyyy-MM-dd HH:ss"/>
							</td>
							<td>
								<c:if test="${stock.dealType == 1}"><span style="color: ;">主动转让</span></c:if>
								<c:if test="${stock.dealType == 2}"><span style="color: ;">主动认购</span></c:if>
							</td>
							<td>
								<c:if test="${stock.status == 1}"><span style="color: ;">交易处理中</span></c:if>
								<c:if test="${stock.status == 2}"><span style="color: ;">交易完成</span></c:if>
							</td>
							<td align="right">
								<input  type="button"  onclick="queryDealInfoById('${stock.id}');" value="详情" />
							</td>
						</tr>
				</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>