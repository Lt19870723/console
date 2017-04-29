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
		<input type="text" name="username" id="username" class="input1" />	
		&nbsp;
		&nbsp;
		<input type="button" value="查询" onclick="pageGo(1);"/>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">用户名</th>
				<th width="10%">状态</th>
				<th width="5%">操作</th>
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
		url : '${path}/wxBlack/wx/wxBlackList/' + pageNo + '.html',
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
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function cancelBlackList(id,pageNo){
	layer.confirm("确定取消?",function(){
	var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/wxBlack/wx/cancelBlackList.html',
			data : {
				'id' : id,
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("取消成功",1,1,function(){
						layer.closeAll();
					});
					pageGo(pageNo);
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}
</script>
</html>