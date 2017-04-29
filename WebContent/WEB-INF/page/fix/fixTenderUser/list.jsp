<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 <c:if test="${fixTenderDetailVo.contractNo != null}">
				定期宝:
			 ${fixTenderDetailVo.contractNo}
			 
			&nbsp;&nbsp;开放额度:
			${fixTenderDetailVo.planAccount}
			&nbsp;&nbsp;用户加入总额:
			${fixTenderDetailVo.sumAccount}
			
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
				还款日期
			</th>	
			<th width="120"  align="center">
				所投借款标
			</th>
			<th width="150"  align="center">
				借款标编号
			</th>
			<th width="150"  align="center">
				借款标所属 原定期宝
			</th>
			<th width="150"  align="center">
				投标金额
			</th>
			<th width="150"  align="center">
				投标时间
			</th>
		</tr>
		 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center" >
				
					${vo.fixContractNo }
				</td>
				<td align="center" >
				
					${vo.lockEndtime }
				</td>
				<td align="center" >
					${vo.borrowName }
				</td>
				<td align="center" >
					${vo.contractNo }
				</td>							
				<td align="center" >
					${vo.parentContractNo }
				</td>
				<td align="center" >
				   <fmt:formatNumber   value="${vo.account}"  pattern="#,##0.00"/> 
				</td>
				<td align="center" >
			     ${vo.tenderTime } 
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