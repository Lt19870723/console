<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>角色管理-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 50px;">
		角色名称：<input type="text" id="name" name="name" class="input1" style="width: 180px;" />&nbsp;
		状态：
		<select id="status" name="status" class="bigselect" size="1" style="width: 100px;">
			<option value="">所有</option>
			<option value="0" selected="selected">启用</option>
			<option value="1">禁用</option>
		</select>&nbsp;
		<input id="selectBtn" name="selectBtn" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
		<input id="addBtn" name="addBtn" value="添加" type="button" onclick="edit('');" />
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>名称</th>
				<th>描述</th>
				<th>状态</th>
				<th>操作</th>
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
		url : '${path}/system/role/list/' + pageNo + '.html',
		data : {
			'name' : $.trim($('#name').val()),
			'status' : $('#status').val()
		},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			$('#dataTable tbody').remove()
			$('#dataTable').append(result);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function edit(roleId) {
	var _url = '${path}/system/role/edit.html';
	if (roleId != '') {
		_url += '?roleId=' + roleId;
	}
	$.layer({
		type : 2,
		title : '编辑',
		area : [ '650px', '420px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}

function updateStatus(roleId) {
	layer.confirm("确认禁用?", function() {
		$.ajax({
			url : '${path}/system/role/updateStatus/' + roleId + '.html',
			type : 'post',
			dataType : 'json',
			beforeSend:function(){
				 _load = parentLayer.load('处理中..');
			}, 
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						parent.layer.close(index); //执行关闭
						layer.msg(result.message,1,1);
						pageGo(1);
					});
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}
</script>
</html>