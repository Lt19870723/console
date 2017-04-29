<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="原始推荐人数量" style="width:200px">推荐人数量【理财推荐人】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.recommendInfoVo.referrerPersons}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="被推荐人数量" style="width:250px">被推荐人数量【理财被推荐人】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.recommendInfoVo.recommendedPersons}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="被推荐用户在时间段内充值成功的总额" style="width:200px">被推荐人充值额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.topupTotalRecommended}" pattern="#,##0.00"/>
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="被推荐用户在时间段内满标的投标总额" style="width:250px">被推荐人投资额【不含债转认购】：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.tenderTotalRecommended}" pattern="#,##0.00"/>
			</div>
		</div>				
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="之前有充值并投标超过100【包含100元】，截止到查询结束时间为止，资产总额小于100元的被推荐投资者总额" style="width:200px">流失被推荐投资者人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${operationMap.losePersonsRecommended}" pattern="#,##00"/>
			</div>
		</div>
	</div>
</div>
