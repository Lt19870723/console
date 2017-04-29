<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">定期宝发布数量：</span>
			<div style="font-size:14px;">
				${fixStaticVo.pubNum}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">定期宝发布金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${fixStaticVo.pubMoney}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">实际满宝金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${fixStaticVo.sucMoney}" pattern="#,##0"/>
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">参与人次：</span>
			<div style="font-size:14px;">
				${fixStaticVo.joinNum}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">参与人数：</span>
			<div style="font-size:14px;">
				${fixStaticVo.joinUser}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">普通宝满宝金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${fixStaticVo.sucComMoney}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">新手宝满宝金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${fixStaticVo.sucNewMoney}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">新手转普通满宝金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${fixStaticVo.sucNewToComMoney}" pattern="#,##0"/>
			</div>
		</div>
	</div>
</div>
