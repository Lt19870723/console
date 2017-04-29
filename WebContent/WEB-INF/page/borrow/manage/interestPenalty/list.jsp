<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		    <th>序号</th>
			<th>借款标题</th>
			<th>借款用户</th>
			<th>应还款时间 </th>
			<th>垫付时间 </th>
			<th>实际还款时间</th>
			<th>还款总额</th>
			<th>应还本金</th>
			<th>应还利息</th>
			<th>是否存管</th>
			<th>操作</th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.name}</td>
			<td>${vo.username}</td>
			<td>
				${vo.repaymentTimeStr}
			</td>
			<td>
			<fmt:formatDate value="${vo.advancetime}" pattern="yyyy-MM-dd"/>
				 
			</td>
			<td>
				${vo.repaymentYestimeStr}
			</td>
			<td>
			    <fmt:formatNumber type="currency" value="${vo.repaymentAccount}" currencySymbol="¥" maxFractionDigits="2"/> 
			</td>
			<td>
			    <fmt:formatNumber type="currency" value="${vo.capital}" currencySymbol="¥" maxFractionDigits="2"/> 
			</td>
			<td>
			 <fmt:formatNumber type="currency" value="${vo.interest}" currencySymbol="¥" maxFractionDigits="2"/> 
				 
			</td>
			 <td>
		    <c:choose>
		    	<c:when test="${vo.isCustody==1}">
		    	存管标
		    	</c:when>
		    	<c:otherwise>非存管标</c:otherwise>
		    </c:choose>
		    </td>
			<td>
			   <a href="javascript:;" onclick="repairInterest(${vo.id});">处理罚息</a>
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