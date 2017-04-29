<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
						<th>序号</th>
						<th>借款标题</th>
						<th>类型</th>
						<th>借款用户</th>
						<th>借款总额</th>
						<th>年化利率</th>
						<th>还款方式</th>
						<th width="100">借款标状态</th>
						<th>审核进度</th>
						<th>审核类型</th>
						<th width="200">审核时间</th>
						<th >审核人</th>
						<th >审核备注</th>
						<th>是否存管</th>

		</tr>
		<c:forEach items="${page.result }" var="onePageBorrow" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
				<td>${onePageBorrow.name}</td>
							<td>${onePageBorrow.borrowTypeStrExp}</td>
							<td><a href="" target="">${onePageBorrow.userName}</a></td>
							<td>${onePageBorrow.accountStrExp}</td>
							<td>${onePageBorrow.aprStr}</td>
							<td>${onePageBorrow.styleStrExp}</td>
							<td>
							 <c:if  test="${onePageBorrow.status == -1}">流标</c:if> 
							 <c:if  test="${onePageBorrow.status == -2}">被撤销</c:if>
							 <c:if  test="${onePageBorrow.status == -3}">审核失败</c:if>   
						    </td>
							<td>${onePageBorrow.appraStatusStr}</td>
							<td>${onePageBorrow.auditType}</td>		
							<td>${onePageBorrow.verifyTime}</td>
							<td>${onePageBorrow.verifyUser}</td>	
							<td>${onePageBorrow.verifyRemark}</td>	
								<td>
				<c:choose>
		    	<c:when test="${onePageBorrow.isCustody==1}">
		    	存管标
		    	</c:when>
		    	<c:otherwise>非存管标</c:otherwise>
		   		</c:choose>
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