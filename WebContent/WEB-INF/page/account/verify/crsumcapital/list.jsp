<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<div>
		待收、待还的每期本金之和是否相等
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		 >
		<tr>
			 <th>借款标题 </th>
			 <th>借款状态</th>
			 <th>满标时间</th>
			 <th>待还记录排序</th>
			 <th>待还记录本金</th>
			 <th>待收记录本金总额</th>
			 <th>是否存管</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${vo.name}</td>
				<c:if test="${vo.status==4}">
					<td>还款中</td>
				</c:if>
				<td>${vo.timeStr}</td>
				<td>${vo.order}</td>
				<td>${vo.capitalStr}</td>
			    <td>${vo.sumCapitalStr}</td>
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