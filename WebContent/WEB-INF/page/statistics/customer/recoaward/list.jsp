<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="list" class="main_cent">
	<div>
		累积推荐成功人数（<font color="red">${invetedUserVo.recommendSuccessUserCount==null?"0":invetedUserVo.recommendSuccessUserCount }</font>）人     
		累积竞技奖（￥<font color="red">${invetedUserVo.athleticsAward==null?"0.00":invetedUserVo.athleticsAward }</font> ）    
		累积直接推荐共享奖（￥<font color="red">${invetedUserVo.directRecommendShareAward==null?"0.00":invetedUserVo.directRecommendShareAward }</font> ）   
		 累积间接推荐共享奖（￥ <font color="red">${invetedUserVo.indirectRecommendShareAward==null?"0.00":invetedUserVo.indirectRecommendShareAward }</font> ） 
	</div>
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 1400px;">
		<tr>
			<th>序号</th>
			<th>推荐人</th>
			<th>年份</th>
			<th>月度</th>
			<th>月度推荐成功人数</th>
			<th>预发竞技奖</th>
			<th>实际竞技奖</th>
			<th>竞技奖发放日期</th>
			<th>直接推荐共享奖</th>
			<th>间接推荐共享奖</th>
			<th>共享奖发放日期</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1 }</td>
				<td>${vo.userName}</td>
				<td>${vo.year }</td>
				<td>${vo.month }</td>
				<td>${vo.recommendSuccessUserCount }</td>
				<td><fmt:formatNumber value="${vo.athleticsAward }" pattern="#,##0.00"/></td>
				<td><fmt:formatNumber value="${vo.athleticsActualAward }" pattern="#,##0.00"/></td>
				<td>${vo.athleticsAwardGrantDateStr }</td>
				<td><a href="javascript:queryInvituser('${vo.id }','${vo.year }')">${vo.directRecommendShareAward }</a></td>
				<td><a href="javascript:queryUserIndirect('${vo.id }')">${vo.indirectRecommendShareAward }</a></td>
				<td>${vo.shareAwardGrantDateStr }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>
