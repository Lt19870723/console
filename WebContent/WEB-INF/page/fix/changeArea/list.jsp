<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
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
			<th width="120"  align="center">
				开放额度(万元)
			</th>
			<th width="120"  align="center">
				锁定期限 (月)
			</th>
				<th width="90"  align="center">
				还款日
			</th>
			<th width="90"  align="center">
				总投标人次
			</th>
			<th width="90"  align="center">
			已获得总利息(元)
			</th>				
			<th width="80"  align="center">
			已支出用户利息(元)
			</th>
			<th width="100"  align="center">
			可用余额(元)
			</th>	
		     <th width="80"  align="center">
				来源
			</th>		
			<th width="100"  align="center">
				操作
			</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td align="center" >
				
					${vo.contractNo }
				</td>
				<td align="center" >
				
					${vo.speicStatusStr1 }
				</td>
				<td align="center" >
					${vo.planAccountStr }
				</td>
				<td align="center" >
					${vo.lockLimit }
				</td>							
				<td align="center" >
					${vo.lockEndtimeStr }
				</td>
				<td align="center" >
					${vo.tenderTimes }
				</td>
				<td align="center" >
					${vo.totalInvest }
				</td>										
				<td align="center" >
					${vo.repaymentMoney }
				</td>
				<td align="center" >
					${vo.useMoney }
				</td>	
				<td align="center" >
					${vo.sourceFromStr }
				</td>
				<td>
			     <a href="javascript:;" onclick="show(${vo.id});">查看</a>
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