<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
    <c:if test="${fixBorrowVo!=null && fixBorrowVo.contractNo!=null  }">
				定期宝:
			 ${fixBorrowVo.contractNo}
			&nbsp;&nbsp;开放额度:
			${fixBorrowVo.planAccount} 元
			&nbsp;&nbsp;所有用户获得总利息：
			${fixBorrowVo.totalInterest} 元
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
				用户名
			</th>	
			<th width="120"  align="center">
				加入总金额（元）
			</th>
			<th width="120"  align="center">
				用户回款本息（元）
			</th>
				<th width="90"  align="center">
				用户利息收入
			</th>
			<th width="90"  align="center">
				回款时间
			</th>
			
		</tr>
		 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center" >
				
					${vo.contractNo }
				</td>
				<td align="center" >
				
					${vo.userName }
				</td>
				<td align="center" >
				 <fmt:formatNumber   value="${vo.account}"   pattern="#,##0.00"/>
				</td>
				<td align="center" >
				 <fmt:formatNumber   value="${vo.repaymentMoney}"   pattern="#,##0.00"/>
				</td>							
				<td align="center" >
				 <fmt:formatNumber   value="${vo.repaymentMoney-vo.account}"   pattern="#,##0.00"/>
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