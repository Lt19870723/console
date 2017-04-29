<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="main_cent" id="logList">
	<div style="line-height: 40px; margin-left: 20px;">
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">网银在线充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.onlinePaymentRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">新浪支付充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.sinaPayRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">盛付通充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.shengFutongRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">支付宝充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.alipayRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">国付宝充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.guoFuBaoRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>

		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">富友支付充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.fuiouRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
	</div>
 
 
 <div style="clear:both"></div>
 <div style="line-height: 40px; margin-left: 20px;">

		<div class="left1_input_ts" >
			<span title="" class="input_span" style="width: 200px;">连连支付充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.lianlianRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
        <div class="left1_input_ts" >
			<span title="" class="input_span" style="width: 200px;">浙商存管充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.czbankRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px;">充值总额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.onlinePaymentRechargeTotal+accountLogMap.sinaPayRechargeTotal+accountLogMap.shengFutongRechargeTotal+accountLogMap.alipayRechargeTotal+accountLogMap.guoFuBaoRechargeTotal+accountLogMap.fuiouRechargeTotal+accountLogMap.lianlianRechargeTotal+accountLogMap.czbankRechargeTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" >
			&nbsp;
		</div>
        <div style="border:1px solid #4dbec8; overflow:hidden; padding:20px;float:left;width: 90%">
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span"  style="width: 200px; color:#4dbec8">连连(PC)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForPCTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px; color:#4dbec8">连连(微信)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForWXTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px; color:#4dbec8">连连(苹果)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForIOSTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px; color:#4dbec8">连连(安卓)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForAndroidTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		
        </div>
        <div style="border:1px solid #4dbec8; overflow:hidden; padding:20px;float:left;width: 90%">
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span"  style="width: 200px; color:#4dbec8">网银在线(PC)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.wyRechargeForPCTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px; color:#4dbec8">网银在线(微信)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.wyRechargeForWXTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px; color:#4dbec8">网银在线(苹果)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.wyRechargeForIOSTotal}" pattern="#,##0.00"/>
			</span>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span title="" class="input_span" style="width: 200px; color:#4dbec8">网银在线(安卓)充值金额：</span>
			<span style="font-size: 14px;">
				<fmt:formatNumber value="${accountLogMap.wyRechargeForAndroidTotal}" pattern="#,##0.00"/>
			</span>
		</div>
        </div>
	</div>
</div>


<!-- <div id="logList" class="main_cent"> -->
	
<!-- 	<div style="margin-left:20px;line-height:40px;height:528px;"> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">网银在线充值总额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.onlinePaymentRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">新浪支付充值总额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.sinaPayRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">盛付通充值总额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.shengFutongRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div>	 -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">支付宝充值总额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.alipayRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div>				 -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">国付宝充值总额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.guoFuBaoRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">连连支付充值总额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.lianlianRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">连连(PC)充值金额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForPCTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">连连(微信)充值金额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForWXTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">连连(苹果)充值金额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForIOSTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">连连(安卓)充值金额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.lianlianRechargeForAndroidTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="left1_input_ts" style="float: left;"> -->
<!-- 			<span class="input_span" title="" style="width:200px">富友支付充值金额：</span> -->
<!-- 			<div style="font-size:14px;"> -->
<%-- 				<fmt:formatNumber value="${accountLogMap.fuiouRechargeTotal}" pattern="#,##0.00"/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->




<!-- </div> -->
