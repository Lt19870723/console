<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable" style="width: 104%">
		<tr>
			<th width="3%">序号</th>
			<th width="8%">用户名</th> 
			<th width="5%">真实<br/>姓名</th> 
			<th width="3%">期权<br/>排名</th>
			<th width="4%">期权数<br/>量(份)</th>
			<th width="5%">期权<br/>状态</th>
			<th width="9%">现金行权金额</th>
			<th width="7%">现金行权<br/>时间</th>
			<th width="4%">是否<br/>管理员<br/>行权</th>
			<th width="9%">当前总额</th>
			<th width="9%">活期宝总额</th>
			<th width="9%">定期宝总额</th>
			<th width="9%">历史最高总额<br/>(2013/9/26-2014/9/26)</th>
			<th width="7%">最高总额<br/>时间</th> 
			<th width="4%">是否<br/>减仓<br/>超九成</th>
			<th width="6%">操作</th>
		</tr>
		<c:forEach items="${page.result}" var="s" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${s.username}</td>
				<td>${s.realname}</td>
				<td>${s.rank}</td>
				<td>${s.stockNum}</td>
				<td>${s.statusStr}</td>
				<td>${s.stockMoneyStr}</td>
				<td>${s.exerciseTimeStr}</td>
				<td>${s.isAdminExerciseStr}</td>
				<td>${s.totalStr}</td>
				<td>${s.curTotalStr}</td>
				<td>${s.fixTotalStr}</td>
				<td>${s.maxTotalStr}</td>
				<td>${s.maxTimeStr}</td>
				<td>${s.isReduceStr}</td>
				<td>
					<c:if test="${s.status==1}">
						<a href="javascript:showstock('${s.id }','${s.isReduce }')">现金行权</a>
					</c:if>
				</td>
				
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
