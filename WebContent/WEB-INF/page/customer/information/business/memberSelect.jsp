<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>选择权证人员</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 50px;">
		用户名：<input type="text" id="username" name="username" value="${username}" class="input1" style="width: 180px;" />&nbsp;
		<input id="selectBtn" name="selectBtn" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="40">序号</th>
				<th width="100">用户名</th>
				<th width="90">真实姓名</th>
				<th width="100">手机号</th>
				<th>邮箱</th>
				<th width="150">注册时间</th>
				<th width="50">操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
$(function() {
	pageGo(1);
});
function pageGo(pageNo) {
	var _load = parentLayer.load(2);
	$.ajax({
		url : '${path}/information/business/memberList/' + pageNo + '.html',
		data : {
			'username' : $.trim($('#username').val())
		},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			$('#dataTable tbody').remove();
			$('#dataTable').append(result);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', { icon : 2, time : 1000 });
		}
	});
}
//选择某个权证人员
function pushSelectedBusiness(userId,username){
	window.parent.getPushSelectedBusiness(userId,username);
	parentLayer.close(index);
}
</script>
</html>