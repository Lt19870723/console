<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>权证人员管理</title>
</head>
<body>	
	<div style="margin-left: 20px; line-height: 50px;">
		<table>
			<tr style="height:10px;"></tr>
			<tr>
				<td>用户名：</td>
				<td><input type="text" id="username" name="username" value="${username}" class="input1" style="width: 180px;" />&nbsp;</td>
				<td align="right">
					<input id="selectBtn" name="selectBtn" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
					<input id="addBtn" name="addBtn" value="添加" type="button" onclick="edit('');" />
				</td>
			</tr>			
			<tr style="height:10px;"></tr>
		</table>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>用户名</th>
				<th>更新时间</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
});
//分页查询
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/information/business/businessList/' + pageNo + '.html',
		data : {
			'username' : $.trim($('#username').val())
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
//编辑
function edit(id) {
	var _url = '${path}/information/business/businessEdit.html';
	if (id != '') {
		_url += '?id=' + id;
	}
	$.layer({
		type : 2,
		title : "权证人员编辑",
		area : [ '900px', '400px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			 
	    }
	});	
}
//删除
function updateStatus(id,status) {
	parentLayer.confirm("确认删除?", function(index) {
		parentLayer.close(index);
		var _load = layer.load(2);
		$.ajax({
			url : '${path}/information/business/updateStatus/' + id + "&" + status +'.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.msg(result.message, { icon : 1, time : 1000 }, function() {
						pageGo(1);
					});
				} else {
					layer.msg(result.message, { icon : 2, time : 1000 });
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', { icon : 2, time : 1000 });
			}
		});
	});
}
</script>
</html>