<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
    <div style="background-color: #EEEEEE;">
	&nbsp;&nbsp;&nbsp;&nbsp; 借款总额：<fmt:formatNumber value="${borrowVo.accountTotal }" pattern="###,###"/> 应还总额:<fmt:formatNumber value="${borrowVo.repaymentAccountTotal }" pattern="###,###"/> 
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		    <th>
				序号
			</th>
			<th>
				借款标题
			</th>
			<th>
				类型
			</th>
			<th>
				借款用户
			</th>
			<th>
				借款总额
			</th>
			<th >
				应还总额
			</th>
			<th>
				年化利率
			</th>
			<th>
				还款方式
			</th>
			<th>
				发布时间
			</th>
			<th>
				满标时间
			</th>
			<th>是否存管</th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.name}</td>
			<td>${vo.borrowTypeStrExp}</td>
			<td>${vo.userName} </td>
			<td>${vo.accountStrExp}</td>
			<td>${vo.repaymentAccountSrt}</td>
			<td>${vo.aprStr}</td>
			<td>${vo.styleStrExp}</td>
			<td>${vo.publishTimeYmdhms}</td>
			<td>${vo.successTimeStr}</td>
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
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>