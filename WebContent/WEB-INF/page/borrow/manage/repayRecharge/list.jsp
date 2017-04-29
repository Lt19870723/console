<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
 
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
	 
		<tr>
		    <th width="5%">序号</th>
			<th width="15%">用户名</th>
			<th width="10%">用户类型</th>
			<th width="10%">用户状态</th>
			<th width="10%">真实姓名</th>
			<th width="7%">资产总额 </th>
			<th width="6%">可用</th>
			<th width="6%">可提 </th>
			<th width="6%">不可提</th>
			<th width="6%">冻结</th>
			<th width="7%">待收</th>
			<th width="7%">直通车可用</th>
			<th width="5%">操作</th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.username}</td>
			<td>
			 <c:if   test="${vo.isFinancialUser == 1}">理财用户</c:if> ]
			 <c:if   test="${vo.isFinancialUser == 0}">借款用户</c:if>
			</td>
			<td>
			<c:if   test="${vo.status == -1}">账号注销</c:if> 
			<c:if   test="${vo.status == -2}">待审核</c:if>
			<c:if   test="${vo.status == -3}">审核不通过</c:if>
			<c:if   test="${vo.status == 0}">正常状态</c:if>
			</td>
			<td>${vo.realName}</td>
			<td>${vo.totalStr}</td>
			<td>${vo.useMoneyStr}</td>
			<td>${vo.drawMoneyStr}</td>
			<td>${vo.noDrawMoneyStr}</td>
			<td>${vo.noUseMoneyStr}</td>
			<td>${vo.collectionStr}</td>
			<td>${vo.firstBorrowUseMoneyStr}</td>
			<td>
			    <a href="javascript:;" onclick="showRecharge('${vo.id}');">充值</a>
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