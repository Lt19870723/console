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
			<th>借款总额    </th>
			<th>已投金额    </th>
			<th>合同编号    </th>
			<th>发布时间  </th>
			<th>有效天数  </th>
			<th>操作   </th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.name}</td>
			<td> 
			    <c:if test="${vo.borrowtype==1}">信用标</c:if>
			    <c:if test="${vo.borrowtype==2}">抵押标</c:if>
			    <c:if test="${vo.borrowtype==3}">净值表</c:if>
			    <c:if test="${vo.borrowtype==4}">秒还标</c:if>
			    <c:if test="${vo.borrowtype==5}">担保标</c:if>
			</td>
			<td>
			  ${vo.userName}</a>
			</td>
			<td><fmt:formatNumber type="currency" value="${vo.account}" currencySymbol="￥" maxFractionDigits="2"/> </td>
		    <td><fmt:formatNumber type="currency" value="${vo.accountYes}" currencySymbol="￥" maxFractionDigits="2"/> </td>
			<td>${vo.contractNo}</td>
		    <td>${vo.publishTimeStr}</td>
		    <td>${vo.validTime}</td>
			<td>
			  <c:if test="${vo.areaType != null and vo.areaType==1 }">
			    <a href="javascript:;" onclick="cancelBorrow(${vo.id});">新手转普通</a>
			  </c:if>  
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