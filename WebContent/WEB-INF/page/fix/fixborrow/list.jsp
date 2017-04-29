<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="35">序号</th>
			<th width="90">定期宝编号</th>
			<th width="60">状态</th>
			<th>宝类型</th>
			<th>年利率</th>
			<th width="70">开放额度<br/>(万元)</th>
			<th width="70">锁定期限<br/>(月)</th>
			<th width="90">还款日</th>
			<th width="60">总投宝<br/>人次</th>
			<th width="120">已获得总利息(元)</th>
			<th width="120">已支出用户利息(元)</th>
			<th width="100">可用余额(元)</th>
<!-- 			<th width="95">限制投标<br/>截止日期</th> -->
			<th width="100">操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.contractNo }</td>
				<td>${vo.speicStatusStr1 }</td>
				<td>
				<c:if test="${vo.areaType==0}">普通宝</c:if>
				<c:if test="${vo.areaType==1}">新手宝</c:if>
				</td>
				<td>${vo.apr}%</td>
				<td>${vo.planAccountStr }</td>
				<td>${vo.lockLimit }</td>
				<td>${vo.lockEndtimeStr }</td>
				<td>${vo.tenderTimes }</td>
				<td><fmt:formatNumber value="${vo.totalInvest }" pattern="###,##0.00"/></td>
				<td><fmt:formatNumber value="${vo.repaymentMoney }" pattern="###,##0.00"/></td>
				<td><fmt:formatNumber value="${vo.useMoney }" pattern="###,##0.00"/></td>
<%-- 				<td><fmt:formatDate value='${vo.tenderBidDate }' pattern='yyyy-MM-dd'/></td> --%>
				<td>
					<a href="javascript:;" onclick="show(${vo.id});">查看</a> 
					<a href="javascript:;" onclick="confirmDoCancel(${vo.id});" style="${vo.status==3?'':'display:none'}">撤销</a>
<%-- 					<c:if test="${vo.status==3 or (vo.status==5 and vo.useMoney!='0.00') }"><a href="javascript:show(${vo.id },'setTenderBid');">限制投标</a></c:if> --%>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="text-align: left;" colspan="13"><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></td>
		</tr>
	</table>
</div>
