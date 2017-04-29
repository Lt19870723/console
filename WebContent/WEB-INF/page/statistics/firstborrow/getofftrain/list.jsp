<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">投标直通车发布总金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${informationStatisticsVO.publishTotalAmount}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">直通车下车总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${informationStatisticsVO.getoffTotalAmount}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">新上车总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${informationStatisticsVO.newgetonTotalAmount}" pattern="#,##0.00"/>
			</div>
		</div>
		
	</div>
</div>
