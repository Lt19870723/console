<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div id="logList" class="main_cent">
	<div style="margin-left:20px;line-height:40px;height:100px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="新增投资人数" style="width:200px">新增投资人数：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${newInvestCountVo.num }" pattern="#,##0"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="新增投资总额" style="width:250px">新增投资总额：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${newInvestCountVo.totalMoney }" pattern="#,##0.00"/>
			</div>
		</div>
	</div>
</div>
