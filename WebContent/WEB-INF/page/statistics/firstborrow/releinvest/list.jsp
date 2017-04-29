<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:528px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">直通车发布数量：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${firstInfoCountVo.publishNum}" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">直通车发布金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${firstInfoCountVo.publishMoney}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:200px">参与直通车的人次：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${firstInfoCountVo.joinNum}" pattern="#,##0"/>
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="" style="width:250px">参与直通车的人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${firstInfoCountVo.joinPeopleNum}" pattern="#,##0"/>
			</div>
		</div>
	</div>
</div>
