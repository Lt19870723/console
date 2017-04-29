<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th>开户名</th>
			<th >账号</th>
			<th>银行</th>
			<th>日期</th>
			<th>余额(元)</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${companyList}" varStatus="status"> 
						<tr>
							<td align="center">${vo.payName}</td>
							<td>${vo.cardNo}</td>
							<td align="right">${vo.bankName}</td>
							<td align="right"><fmt:formatDate value="${vo.endTime}" pattern="yyyy-MM-dd HH:mm"/>止</td>
							<td align="right" class="J_companysum"><fmt:formatNumber value="${vo.balance}" pattern="##0.00"/></td>
							<td>
							<c:if test="${vo.optType==1}">
								<a href="javascript:void(0);" onclick="updateBalance(${vo.id});">编辑</a>
								<a href="javascript:void(0);" onclick="deleteBalance(${vo.id});">删除</a>
							</c:if>
							<c:if test="${vo.optType==2}">
							-
							</c:if>	
							</td>
						</tr>
		</c:forEach>
		<c:if test="${fn:length(companyList) > 0}">
			 <tr style="background-color: yellow;">
					<td align="center" style="font-weight: bold;">合计</td>
					<td>-</td>
					<td align="right">-</td>
					<td align="right">-</td>
					<td align="right" class="J_companyaccount"></td>
					<td>
					-
					</td>
				</tr>
		</c:if>
		<c:forEach var="emplayeeList" items="${emplayeeList}" varStatus="status"> 
						<tr>
							<td align="center">${emplayeeList.payName}</td>
							<td>${emplayeeList.cardNo}</td>
							<td align="right">${emplayeeList.bankName}</td>
							<td align="right"><fmt:formatDate value="${emplayeeList.endTime}" pattern="yyyy-MM-dd HH:mm"/>止</td>
							<td align="right" class="J_empaccount"><fmt:formatNumber value="${emplayeeList.balance}" pattern="##0.00"/></td>
							<td>
							<c:if test="${emplayeeList.optType==1}">
								<a href="javascript:void(0);" onclick="updateBalance(${emplayeeList.id});">编辑</a>
								<a href="javascript:void(0);" onclick="deleteBalance(${emplayeeList.id});">删除</a>
							</c:if>
							<c:if test="${emplayeeList.optType==2}">
							-
							</c:if>	
							</td>
						</tr>
		</c:forEach>
		<c:if test="${fn:length(emplayeeList) > 0}">
		<tr style="background-color: yellow;">
			<td align="center" style="font-weight: bold;">总合计</td>
			<td></td>
			<td align="right">-</td>
			<td align="right">-</td>
			<td align="right" class="J_Total"></td>
			<td>
			-
			</td>
		</tr>
		</c:if>
		<c:forEach var="otherList" items="${otherList}" varStatus="status"> 
						<tr>
							<td align="center">
							<c:if test="${otherList.accId == '-1'}">线上充值</c:if>
							<c:if test="${otherList.accId == '-2'}">客户提现</c:if>
							<c:if test="${otherList.accId == '-3'}">网银在线</c:if>
							</td>
							<td>-</td>
							<td align="right">-</td>
							<td align="right"><fmt:formatDate value="${otherList.endTime}" pattern="yyyy-MM-dd HH:mm"/>止</td>
							<td align="right"><fmt:formatNumber value="${otherList.balance}" pattern="##0.00"/></td>
							<td>
							<c:if test="${otherList.optType==1}">
								<a href="javascript:void(0);" onclick="updateBalance(${otherList.id});">编辑</a>
								<a href="javascript:void(0);" onclick="deleteBalance(${otherList.id});">删除</a>
							</c:if>
							<c:if test="${otherList.optType==2}">
							-
							</c:if>	
							</td>
						</tr>
		</c:forEach>
		
	</tbody>	
</table>
<script type="text/javascript">

</script>