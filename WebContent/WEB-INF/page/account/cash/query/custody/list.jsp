<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>用户类型</th>
			<th>开户人</th>
			<th>银行账户</th>
			<th>银行名称</th>
			<th>银行支行</th>
			<th>提现时间</th>
			<th>提现金额</th>
			<th>手续费</th>
			<th>到账金额</th>
			<th>状态</th>
            <th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'
				style="${vo.isFinancialUser == 0 ? 'background: red':'' } ">
				<c:if test="vo.isFinancialUser == 0 "></c:if>
				<td>${sta.index + 1}</td>
				<td>${vo.username}</td>
				<td>${vo.isFinancialUserStr}</td>
				<td>${vo.realname}</td>
				<td>${vo.account}</td>
				<td>${vo.bank}</td>
				<td>${vo.branch}</td>
				<td>${vo.addtimeymdhms}</td>
				<td>${vo.totalStr }</td>
				<td>${vo.feeStr}</td>
				<td>${vo.creditedStr}</td>
				<td>
                    <c:if test="${vo.status==0}">申请提现</c:if>
					<c:if test="${vo.status==10}">处理中</c:if>
					<c:if test="${vo.status==40}">可疑</c:if>
                </td>
                <td><a href="javascript:toLoseOrder('${vo.bizNo}')">补单</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>