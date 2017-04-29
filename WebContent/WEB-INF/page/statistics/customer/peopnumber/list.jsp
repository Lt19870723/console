<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">注册人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.memberCount}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">实名认证人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.realNameApproCount}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">VIP认证人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.vipApproApproCount}" pattern="#,##0"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">新增投资人数(包含债转)：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.addInvestPersonsTotal}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">累计投资人数(包含债转)：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.investPersonsTotal}" pattern="#,##0"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">流失投资者人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.losePersonsTotal}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">现有投资者人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.nowInvestPersonsTotal}" pattern="#,##0"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width: 50%">观望投资者人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${reportVo.observePersonsTotal}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;width: 80%;font-size: 12px;">
		注：现有投资者人数，指资产总额大于等于100元（包含定期宝总额，活期宝总额，普通账户总额）
		</div>
		
	</div>
	
</div>
