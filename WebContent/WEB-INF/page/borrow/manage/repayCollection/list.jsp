<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div style="margin-left:10px;">
  <c:if test="${useMoney!=null }">
   <span style="color:red;">用户可用余额：<fmt:formatNumber type="currency" value="${useMoney}" currencySymbol="¥" maxFractionDigits="2"/></span> 
  </c:if>  
 <span style="color:red;">&nbsp;&nbsp;待还总额：<fmt:formatNumber type="currency" value="${sumRepayAccount}" currencySymbol="¥" maxFractionDigits="2"/>  </span> 
</div>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
	 
		<tr>
		    <th>序号</th>
			<th width="8%">应还款时间 </th>
			<th>借款标题</th>
			<th>借款编号</th>
			<th>类型</th>
			<th>借款用户</th>
			<th>周期</th>
			<th>年化利率</th>
			<th>期数</th>
			<th>还款总额</th>
			<th>失败笔数</th>
			<th>失败金额</th>
			<th>状态</th>

		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>
				${vo.repaymentTimeStr}
			</td>
			<td><a href="${portal_path}/toubiao/showBorrowDetail/${vo.borrowId}.html" target="_blank">${vo.name}</a></td>
			<td>${vo.contractNo}</td>
			<td>${el:desc(300,vo.borrowtype)}</td>
			<td>${vo.username}</td>
			<td>${vo.timeLimit}
			    <c:if test="${vo.style != 4}">
			                 个月
			     </c:if>
			     <c:if test="${vo.style == 4}">
			               天
			     </c:if>
				 
			</td>
			<td>
				${vo.apr}%
			</td>
			<td>
			     <c:if test="${vo.style == 1 or vo.style == 2}">
			       ${vo.order}/${vo.timeLimit }
			     </c:if>
			     <c:if test="${vo.style != 1 and vo.style != 2}">
			       1/1
			     </c:if>
            </td>
			<td>
			    <fmt:formatNumber type="currency" value="${vo.repaymentAccount}" currencySymbol="¥" maxFractionDigits="2"/> 
			</td>
			<td>
				<a href="javascript:showFailCollection('${vo.id }','1')"> ${vo.eFailCount}</a>
			</td>
			<td>
					${vo.eFailSumStr}
			</td>
			<td>
			    <c:if test="${vo.status == 0 and vo.webstatus == 1}">
			           已垫付
			    </c:if>
			    <c:if test="${vo.status == 0 and vo.webstatus == 0}">
			           还款中
			    </c:if>
				<c:if test="${vo.status == 4}">
					   银行还款中
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