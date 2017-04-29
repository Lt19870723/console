<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<div></div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 1400px;">
		<tr>
			<th>推荐人</th>
			<th>被推荐人</th>
			<th>被推荐人注册时间</th>
			<th>实名认证</th>
			<th>手机认证</th>
			<th>邮箱认证</th>
			<th>VIP认证</th>
			<th>累计充值金额</th>
			<th>累计投资金额</th>
			<th>普通待收</th>
			<th>直通车待收</th>
			<th>定期宝待收</th>
			<th>赠送金额</th>
			<th>推荐成功时间</th>
			<th>推荐成功</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${vo.userName}</td>
				<td>${vo.invitedUsername }</td>
				<td>${vo.registerTimeStr }</td>
				<td>${vo.realnamePassed==1?"通过":"不通过" }</td>
				<td>${vo.mobilePassed==1?"通过":"不通过" }</td>
				<td>${vo.emailPassed==1?"通过":"不通过" }</td>
				<td>${vo.vipPassed==1?"通过":"不通过" }</td>
				<td>${vo.totalInterest==null?"0.00":vo.totalInterest }</td>
				<td>${vo.tenderMoney==null?"0.00":vo.tenderMoney }</td>
				<td>${vo.normalColl}</td>
				<td>${vo.firstColl}</td>
				<td>${vo.fixColl}</td>
				<td>${vo.awardmoney==null?"0.00":vo.awardmoney }</td>
				<td>${vo.inviteSuccessTimeStr }</td>
				<td>${vo.inviteSuccessTime==null?"否":"是" }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>