<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	
	<div style="margin-left:20px;line-height:40px;height:100px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:200px">发布净值数量：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${netValueBorrowCountVo.publishCount }" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:250px">成交净值数量：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${netValueBorrowCountVo.netValueCount }" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" style="width:200px">净值标成交金额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${netValueBorrowCountVo.netValueAccount }" pattern="#,##0"/>
			</div>
		</div>	
	</div>
</div>
