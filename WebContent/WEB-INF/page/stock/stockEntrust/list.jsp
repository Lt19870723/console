<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th style="width: 40px">序号</th>
			<th style="width: ;">委托人</th>
			<th style="width: ;">委托类型</th>
			<th style="width: ;">委托时间</th>
			<th style="width: ;">委托价格（元）</th>
			<th style="width: ;">委托挂单数量（份）</th>
			<th style="width: ;">已成交数量（份）</th>
			<th style="width: ;">未成交数量（份）</th>
			<th style="width: ;">状态</th>
			<th style="width: ;">操作</th>
		</tr>
	</thead>
	<tbody>
				<c:forEach var="stock" items="${page.result }" varStatus="vIndex" >
						<tr>
							<td>${vIndex.index+1}</td>
							<td align="right">${stock.userName }</td>
							<td>
								<c:if test="${stock.entrustType == 1}"><span style="color: ;">认购</span></c:if>
								<c:if test="${stock.entrustType == 2}"><span style="color: ;">转让</span></c:if>
							</td>
							<td>
								<fmt:formatDate value="${stock.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<fmt:formatNumber value="${stock.price }" pattern="#,##0.00"/>
							</td>
							<td><fmt:formatNumber value="${stock.amount }" pattern="#,##0"/></td>
							<td><fmt:formatNumber value="${stock.dealAmount }" pattern="#,##0"/></td>
							<td><fmt:formatNumber value="${stock.amount- stock.dealAmount }" pattern="#,##0"/></td>
							<td>
								<c:if test="${stock.status == 1}"><span style="color: ;">已挂单</span></c:if>
								<c:if test="${stock.status == 2}"><span style="color: ;">部分成交</span></c:if>
								<c:if test="${stock.status == 3}"><span style="color: ;">全部成交</span></c:if>
								<c:if test="${stock.status == -1}"><span style="color: ;">已撤销</span></c:if>
							</td>
							<td align="right">
								<input  type="button"  onclick="queryInfoById('${stock.id}');" value="详情" />
								<c:if test="${stock.status == 1 || stock.status == 2}">
									<input  type="button"  onclick="revokeById('${stock.id}','${stock.userId}');" value="撤单" />
								</c:if>								
							</td>
						</tr>
				</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>