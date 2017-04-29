<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%;">
	
		<div style="margin-left:20px;line-height:40px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><font>平台资金统计信息</font></span>
			<div style="font-size:14px;">
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"></span>
			<div style="font-size:14px;">
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">账户总额统计：</span>
			<div style="font-size:14px;">
				${map.sum_total}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span" title="账户总额大于等于50元用户的可用总和">可用资金统计：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${map.sum_use_money}" pattern="#,##0.00"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">可提现资金统计：</span>
			<div style="font-size:14px;">
				${map.sum_draw_money}
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">不可提现资金统计：</span>
			<div style="font-size:14px;">
				${map.sum_no_draw_money}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">冻结资金统计：</span>
			<div style="font-size:14px;">
				${map.sum_no_use_money}
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">待收资金统计：</span>
			<div style="font-size:14px;">
				${map.sum_collection}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">投标直通车余额统计：</span>
			<div style="font-size:14px;">
				${map.sum_first_borrow_use_money}
			</div>
		</div>	
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span">定期宝余额统计：</span>
			<div style="font-size:14px;">
				<fmt:formatNumber value="${map.sum_fix_use_money}" pattern="#,##0.00"/>
			</div>
		</div>
		<div style="color:red;width:500px;" class="left1_input_ts" style="float: left;"><br/>
				<div>统计说明：&nbsp;&nbsp;<br/>
				1、可用资金统计：小于50元的不统计<br/>
				2、不统计用户名为"国诚金融","国阳资产","国诚投监会基金"的账户<br/>
				3、只统计了账户状态正常的理财用户<br/>
			</div>
</div>
	
	</form>

</body>

</html>
