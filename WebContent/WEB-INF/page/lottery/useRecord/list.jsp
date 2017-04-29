<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div>
    抽奖机会发放数量：${recordStatic.chanceSendNum==null?'0':recordStatic.chanceSendNum} 次，
    已抽奖次数：${recordStatic.chanceSendedNum==null?'0':recordStatic.chanceSendedNum} 次，
 	已领取奖品价值：   
 	 <fmt:formatNumber type="currency" value="${recordStatic.getSumAwardMoney==null?'0.00':recordStatic.getSumAwardMoney}" currencySymbol="￥" maxFractionDigits="2"/> 元，
 	 未领取奖品价值：
 	 <fmt:formatNumber type="currency" value="${recordStatic.noGetSumAwardMoney==null?'0.00':recordStatic.noGetSumAwardMoney}" currencySymbol="￥" maxFractionDigits="2"/>元
</div>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		 
		 
		    <tr>
			     <th>
					 用户
				 </th>
			     <th>
					 奖品
				 </th>
			     <th>
					 奖品类型
				 </th>
			     <th>
					 来自活动
				 </th>
			     <th>
					 抽奖时间
				 </th>
		    </tr>
	 
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			 
		     <td>${vo.userName} </td>
		     <td>${vo.goodName} </td>
		     <td>
		         ${vo.awardType==1?"现金":""}
			     ${vo.awardType==2?"实物":""}
			     ${vo.awardType==3?"谢谢参与":""}
			     ${vo.awardType==4?"抽奖机会":""}
			     ${vo.awardType==5?"红包":""}
			     ${vo.awardType==6?"元宝":""}
			  </td>
		     <td>${vo.activeName}</td>
		     <td>${vo.addtimeStr}</td>
					 
				 	
			</tr>
		</c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>