<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		 
		<tr>
		    <th>序号  </th>
			<th>借款标题  </th>
			<th>类型  </th>
			<th>借款用户     </th>
			<th>合同编号    </th>
			<th>年化利率 </th>
			<th>还款方式  </th>
			<th>满标时间   </th>
			<th>是否存管</th>
			<th>操作   </th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
				<td>${vo.name}</td>
				<td> 
				    <c:if test="${vo.borrowtype==1}"> 信用标</c:if>
				    <c:if test="${vo.borrowtype==2}"> 抵押标</c:if>
				    <c:if test="${vo.borrowtype==3}"> 净值标</c:if>
				    <c:if test="${vo.borrowtype==4}"> 秒标</c:if>
				    <c:if test="${vo.borrowtype==5}">担保标</c:if> 
				</td>
				<td>${vo.userName}</td>
				<td>${vo.contractNo}</td>
				<td>${vo.apr}%</td>
				<td>
				     <c:if test="${vo.style == 1 }">等额本息还款</c:if>
				     <c:if test="${vo.style == 2 }">按月付息到期还本</c:if>
				     <c:if test="${vo.style == 3 }">到期还本付息</c:if>
				     <c:if test="${vo.style == 4 }">按天还款</c:if>
                </td>
                <td>${vo.successTimeStr}</td>
				 <td>
		    <c:choose>
		    	<c:when test="${vo.isCustody==1}">
		    	存管标
		    	</c:when>
		    	<c:otherwise>非存管标</c:otherwise>
		    </c:choose>
		    </td>
				<td> 
				   <a href="javascript:;" onclick="forBorrowXiyi(${vo.id});">查看协议</a>
				   </br>
				   <a href="javascript:;" onclick="forTenderRecord(${vo.id});">查看投标记录</a>    
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