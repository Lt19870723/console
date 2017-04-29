<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th style="width: ;">日期</th>
			<th style="width: ;">金额(元)</th>
			<th style="width: ;">分类</th>
			<th style="width: ;">性质</th>
			<th style="width: ;">明细</th>
			<th style="width: ;">银行卡</th>
			<th style="width: ;">是否有发票</th>
			<th style="width: ;">申请人</th>
			<th style="width: ;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="flow" items="${page.result}"> 
						<tr>
							<td align="center"><fmt:formatDate value="${flow.endTime}" pattern="yyyy-MM-dd"/></td>
							<td align="right">
								<fmt:formatNumber value="${flow.money}" pattern="#,##0.00"/>
							</td>
							<td>
								${flow.typeName}
							</td>
							<td align="right">${flow.namure}</td>
							<td align="right">${flow.detailed}</td>
							<td align="right">${flow.bankCard}</td>
							<td>
								<c:if test="${flow.isInvoice == -1}"><span style="color: ;">否</span></c:if>
								<c:if test="${flow.isInvoice == 1}"><span style="color: ;">是</span></c:if>
							</td>
							<td align="right">${flow.applicant}</td>
							<td align="right">
								<%-- <c:if test="${flow.optType == 0 }"> --%>
									<input  type="button"  onclick="updateFlow('${flow.id}');" value="编辑" />
									<input  type="button"  onclick="deleteFlow('${flow.id}');" value="作废" />
								<%-- </c:if>
								<c:if test="${flow.optType != 0 }">
									--
								</c:if> --%>
							</td>
						</tr>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>