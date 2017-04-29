<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 50px;">
		用户名：
		<input type="text" id="userName" class="input1" />
		&nbsp; 银行卡号：
		<input type="text" id="cardNum" class="input1" />
		&nbsp;
		<input type="button"  id="btnSearch" value="查询" onclick="pageGo(1);">
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>银行卡号</th>
				<th>真实姓名</th>
				<th>开户行</th>
				<th>四要素验证状态</th>
				<th>操作人</th>
				<th>操作备注</th>
				<th>操作时间</th>
				<th>操作类型</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
});

function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	var userName = $('#userName').val();
	var cardNum = $('#cardNum').val();
	$.ajax({
		url : '${path}/userBankCardOperateLog/serachAll/' + pageNo + '.html',
		data : {
			userName : userName,
			cardNum : cardNum
		},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			$('#dataTable tbody').remove();
			$('#dataTable').append(result);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
</script>
</html>