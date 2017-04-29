<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 <c:if test="${operation==1}">
			定期宝:
			 ${fixAccountVo.contractNo}
			&nbsp;&nbsp;利息收入:
			<fmt:formatNumber type="currency" value="${fixAccountVo.profit}"   maxFractionDigits="2"/> 元
			&nbsp;&nbsp;已支出利息：
			<fmt:formatNumber type="currency" value="${fixAccountVo.repaymentYesAccount}"   maxFractionDigits="2"/> 元
			&nbsp;&nbsp;当前可用余额：
			<fmt:formatNumber type="currency" value="${fixAccountVo.useMoney}"   maxFractionDigits="2"/> 元
	 </c:if>
	 <c:if test="${operation==2}">
				该时间段内，总账户利息收入：
			<fmt:formatNumber type="currency" value="${profitMoney}"   maxFractionDigits="2"/> 元	
			 
			&nbsp;&nbsp;总账户已支出利息:
			<fmt:formatNumber type="currency" value="${payMentMoney}"   maxFractionDigits="2"/> 元
			 
			&nbsp;&nbsp;总账户盈余：
			<fmt:formatNumber type="currency" value="${profitMoney-payMentMoney}"   maxFractionDigits="2"/> 元
			 
	 </c:if>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="40"  align="center">
				序号
			</th>
			<th width="90"  align="center">
				定期宝编号
			</th>
			 <th width="80"  align="center">
				状态
			</th>	
			<th width="100"  align="center">
				开放额度（元）
			</th>
			<th width="50"  align="center">
				锁定期限（月）
			</th>
				<th width="90"  align="center">
				宝还款日
			</th>
			<th width="90"  align="center">
				投标回款本息
			</th>
			<th width="90"  align="center">
				投标回款利息
			</th>
			<th width="120"  align="center">
				回款借款标编号
			</th>
			<th width="140"  align="center">
				投标回款时间
			</th>
		</tr>
		 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center" >
				
					${vo.contractNo }
				</td>
				<td align="center" >
				
					${vo.statusStr }
				</td>
				<td align="center" >
				 <fmt:formatNumber type="currency" value="${vo.planAccount}" currencySymbol="￥"  maxFractionDigits="2"/>
				</td>
				<td align="center" >
					${vo.lockLimit }
				</td>	
				<td align="center" >
				<fmt:formatDate value="${vo.lockEndTime}" pattern="yyyy-MM-dd"/>
				</td>						
				<td align="center" >
					<fmt:formatNumber type="currency" value="${vo.borrowCapital}" currencySymbol="￥" maxFractionDigits="2"/> 
				</td>
				<td align="center" >
				  <fmt:formatNumber type="currency" value="${vo.borrowInterest}" currencySymbol="￥"  maxFractionDigits="2"/>
				</td>
				<td align="center" >
					${vo.borrowContractNo }
				</td>
				<td align="center" >
				　　　<fmt:formatDate value="${vo.borrowRepayTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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