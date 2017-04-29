<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		 
		<tr>
		    <th  width="3%">序号  </th>
			<th>借款标题  </th>
			<th width="6%">年化率  </th>
			<th  width="5%">类型  </th>
			<th width="8%">借款用户     </th>
			<th>借款总额    </th>
			<th>合同编号    </th>
			<th>借款时间    </th>
			<th>投标用户   </th>
			<th>投标金额   </th>
			<th>投标状态  </th>
			<th>投标时间  </th>
			<th>是否存管</th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td><a href="${portal_path}/toubiao/${vo.borrowId}.html" target="_blank">${vo.name}</a></td>
			<td>${vo.apr}%</td>
			<td>${vo.borrowtypeStr}</td>
			<td>${vo.borrowUserName}</td>
			<td>
			　<fmt:formatNumber type="currency" value="${vo.account}" currencySymbol="¥" maxFractionDigits="2"/> 
			</td>
			<td>${vo.contractNo}</td>
			<td>${vo.borrowDateTimeStr}</td>
			<td>${vo.tenderUserName}</td>
			<td> 
			　<fmt:formatNumber type="currency" value="${vo.tenderaccount}" currencySymbol="¥" maxFractionDigits="2"/> 	 
			</td>
			<td>${vo.statusStr}</td>
			<td>${vo.tenderDateTimeStr}</td>
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