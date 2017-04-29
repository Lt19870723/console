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
			 <th>借款标题</th>
		     <th>借款标状态</th>
			 <th>投标时间</th>
			 <th>投标金额</th>
			 <th>待收本金总额</th>
			 <th>投标利息</th>
			 <th>待收利息总额</th>
			 <th>投标待收金额</th>
			 <th>待收预还金额总额</th>
			 <th>是否存管</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${vo.name}</td>
				<c:if test="${vo.bStatus==4}">
					<td>还款中</td>
				</c:if>
				<c:if test="${vo.bStatus==5}">
					<td>还款结束</td>
				</c:if>
				<c:if test="${vo.bStatus==42}">
					<td>已垫付</td>
				</c:if>
				<td>${vo.tAddtimeStr}</td>
				<td>${vo.accountStr}</td>
				<td>${vo.sumCapitalStr}</td>
				<td>${vo.interestStr}</td>
			    <td>${vo.sumInterestStr}</td>
			    <td>${vo.repaymentAccountStr}</td>
			    <td>${vo.sumRepayAccountStr}</td>
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