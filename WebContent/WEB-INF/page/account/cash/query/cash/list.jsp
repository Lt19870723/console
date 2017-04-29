<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<div>
		<font color="red">
			提现金额总计： <fmt:formatNumber type="currency" value="${resultMap.sumTotal}" currencySymbol="¥" maxFractionDigits="2"/> &nbsp;&nbsp;&nbsp;&nbsp;
			到账金额总计：<fmt:formatNumber type="currency" value="${resultMap.sumCredited}" currencySymbol="¥" maxFractionDigits="2"/>&nbsp;&nbsp;&nbsp;&nbsp;
			手续费总计：<fmt:formatNumber type="currency" value="${resultMap.sumFee}" currencySymbol="¥" maxFractionDigits="2"/> &nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${isCustody == null or isCustody == 0}">超级VIP未收取手续费总计：<fmt:formatNumber type="currency" value="${resultMap.sumUnFee}" currencySymbol="¥" maxFractionDigits="2"/>&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
	    </font>
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>用户类型</th>
            <th >开立存管</th>
			<th>开户人</th>
			<th>银行账户</th>
			<th>银行名称</th>
			<th>银行支行</th>
			<th>提现时间</th>
			<th>提现金额</th>
			<th>手续费</th>
			<th>到账金额</th>
			<th>审核人</th>
			<th>审核时间</th>
			<th>审核备注</th>
			<th>打款人</th>
			<th>打款时间</th>
		    <th>银行提现流水号</th>
			<th>状态</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'
				style="${vo.isFinancialUser == 0 ? 'background: red':'' } ">
				<c:if test="vo.isFinancialUser == 0 "></c:if>
				<td>${sta.index + 1}</td>
				<td>${vo.username}</td>
				<td>${vo.isFinancialUserStr}</td>
                <td><c:if test="${vo.isCustody == null or vo.isCustody == 0}">否</c:if> <c:if
                        test="${vo.isCustody == 1}">是</c:if></td>
				<td>${vo.realname}</td>
				<td>${vo.account}</td>
				<td>${vo.bank}</td>
				<td>${vo.branch}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.totalStr }</td>
				<td>${vo.feeStr}</td>
				<td>${vo.creditedStr}</td>
				<td>${vo.verifyName}</td>
				<td>${vo.verifyTimeYmdhms}</td>
				<td>${vo.verifyRemark}</td>
				<td>${vo.verifyName2}</td>
				<td>${vo.verifyTime2Ymdhms}</td>
			    <td>${vo.etradeNo}</td>
				<td>${vo.statusStr}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>