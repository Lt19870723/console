<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<div>
		<font color="red"> 直通车可用余额：<fmt:formatNumber value="${firstUseMoneyTotal }" pattern="#,#00.00#"/></font>
	</div> 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		    <th>序号  </th>
			<th>借款标题  </th>
			<th>借款类型  </th>
			<th>借款用户     </th>
			<th>合同编号    </th>
			<th>年化利率 </th>
			<th>借款总额  </th>
			<th>已投金额  </th>
			<th>剩余金额  </th>
			<th>发布时间   </th>
			<th>操作   </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.name}</td>
				<td>
				<c:if test="${vo.borrowtype==1}">信用标</c:if>
				<c:if test="${vo.borrowtype==2}">抵押标</c:if>
				<c:if test="${vo.borrowtype==3}">净值标</c:if>
				<c:if test="${vo.borrowtype==4}">秒标</c:if>
				<c:if test="${vo.borrowtype==5}">担保标</c:if>
				</td>
				<td>${vo.userName}</td>
				<td>${vo.contractNo}</td>
				<td>${vo.apr}%</td>
				<td><fmt:formatNumber value="${vo.account}" pattern="#,#00.00#" /> </td>
				<td><fmt:formatNumber value="${vo.accountYes }" pattern="#,#00.00#"/></td>
				<td><fmt:formatNumber value="${vo.account - vo.accountYes}" pattern="#,#00.00#"/></td>
				<td>${vo.publishTimeStr}</td>	 
				<td><a href="javascript:;" onclick="handleFirstTender(${vo.id});">手动直通车投标</a></td> 
				 
			</tr>
		</c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>