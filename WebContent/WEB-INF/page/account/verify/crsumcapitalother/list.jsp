<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<div>
		待收、待还中本金之和是否等于投标、借款标中本金之和, 以整个标为单位
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		 >
		<tr>
			 <th>借款标题 </th>
			 <th>借款状态</th>
			 <th>还款方式</th>
			 <th>借款期限</th>
			 <th>满标时间</th>
			 <th>借贷总金额</th>
			 <th>待收本金总额</th>
			 <th>待还金额总额</th>
			 <th>投标金额总额</th>
			 <th>是否存管</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${vo.name}</td>
				<c:if test="${vo.status==4}">
					<td>还款中</td>
				</c:if>
				<c:if test="${vo.style==0}">
					<td>没有限制</td>
				</c:if>	
				<c:if test="${vo.style==1}">
					<td>等额本息</td>
				</c:if>
				<c:if test="${vo.style==2}">
					<td>按月付息到期还本</td>
				</c:if>
				<c:if test="${vo.style==3}">
					<td>到期还本付息</td>
				</c:if>
				<c:if test="${vo.style==4}">
					<td>按天还款</td>
				</c:if>
				<td>${vo.timeLimit}</td>
				<td>${vo.timeStr}</td>
			    <td>${vo.accountStr}</td>
			    <td>${vo.collectionCapitalStr}</td>
			    <td>${vo.repaymentCapitalStr}</td>
			    <td>${vo.tenderCapitalStr}</td>
			    <td>
				<c:choose>
		    	<c:when test="${vo.isCustody==1}">
		    	存管标
		    	</c:when>
		    	<c:otherwise>非存管标</c:otherwise>
		   		</c:choose>
		   	 	</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>