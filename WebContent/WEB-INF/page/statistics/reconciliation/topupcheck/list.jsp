<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">线上充值总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.onlineRechargeMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">线下充值总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.offlineRechargeMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">网站的充值记录总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.rechargeMoney}" pattern="#,##0.00"/>
			</div>
		</div>		
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">网站充值入账总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${checkMap.webRechargeMoney}" pattern="#,##0.00"/>
			</div>
		</div>
	   	<div class="left1_input_ts"  >
			<span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red' }"
		         style="font-size: 12px;">
		    	<font color="blue">【验证公式】</font>网站的充值记录总额:<fmt:formatNumber value="${checkMap.rechargeMoney}" pattern="#,##0.00"/><font color="blue">=</font>
		    	网站充值入账总额:<fmt:formatNumber value="${checkMap.webRechargeMoney}" pattern="#,##0.00"/>
		    </font></span>
		    <span style="width: 950px;text-align: left;" class="input_span">
		    <font color="${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'black':'red'}" style="font-size: 16px;">
		    	${(checkMap.compareResult=='true' || checkMap.compareResult==null)?'':'注意:充值对账以上表达式没有形成等量关系,请联系技术部' }
		    </font>
		    </span>
		</div>	
	</div>
	
</div>
