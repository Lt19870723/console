<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>菜单管理-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 50px;">
		用户名：<input type="text" id="name" name="name" class="input1" style="width: 180px;" />&nbsp;
		<input id="selectBtn" name="selectBtn" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>序号  </th>
				<th>用户名</th>
				<th>真实姓名</th>
				<th>邮箱</th>
				<th>身份证号码</th>
				<th>银行账号</th>
				<th>银行名称 </th>
				<th>支行名称</th>
				<th>联行号 </th>
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
	$.ajax({
		url : '${path}/customer/bankCardQuery/list/' + pageNo + '.html',
		data : {
			'userName' : $.trim($('#name').val())
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