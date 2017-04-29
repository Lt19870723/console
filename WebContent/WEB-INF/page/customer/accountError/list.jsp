<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th>序号</th>
			<th>投资人用户名</th>
			<th>账户总额</th>
			<th>平台存管可用金额</th>
			<th>平台存管冻结金额</th>
			<th>平台存管待收金额</th>
			<th>浙商存管可用金额</th>
			<th>浙商存管冻结金额</th>
			<th>浙商存管可提金额</th>
			<th>账户异常时间</th>
			<th>状态</th>
			<th>业务发生场景</th>
			<!-- <th>操作</th> -->
		</tr>
	
	<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.userName }</td>
			<td>${vo.total }</td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.p2peUseMoney }"></fmt:formatNumber> </td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.p2peNoUseMoney }"></fmt:formatNumber> </td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.p2peCollection }"></fmt:formatNumber> </td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.zsEUseMoney }"></fmt:formatNumber> </td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.zsEFreezeMoney }"></fmt:formatNumber> </td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.zsWithdrawamount }"></fmt:formatNumber> </td>
			<td><fmt:formatDate value="${vo.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			<td>
				<c:if test="${vo.status==0}">
				未处理
				</c:if>
				<c:if test="${vo.status==1}">
				已处理 
				</c:if>
			</td>
			<td>${vo.scene }</td>
			<%-- <td>
			<c:if test="${vo.status==0}">
			<a href="javascript:;" onclick="showAuditLayer(${vo.id});">解决</a>
			</c:if>
			</td> --%>
			</tr>
	</c:forEach>		
	
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>