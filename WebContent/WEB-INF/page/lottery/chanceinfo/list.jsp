<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			 <th>
				 序号
			 </th>      	 
		     <th>
				 用户名
			 </th>
			 
			  <th>
				 抽奖次数
			 </th>
		     <th>
				 有效可用次数
			 </th>
			 
		     <th>
				 抽奖机会来源
			 </th>
		     <th>
				 添加时间
			 </th>
		</tr>
		<c:forEach items="${page.result }" var="chanceInfo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			      <td>${sta.index+1}</td>
				  <td>${chanceInfo.username}</td>
	 			  <td>${chanceInfo.lotteryNum}</td>
				  <td>${chanceInfo.useNum}</td>
				  <td>${chanceInfo.chanceRuleName}</td>
				  <td>${chanceInfo.addTimeStr}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>