<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left:20px;line-height:50px;">
			栏目：<input type="text" id="name"  class="input1"/>
				
				<input type="button" id="btnSearch" onclick="pageGo(1)" value="查询"/>&nbsp;
				<input type="button" id="btnAdd" onclick="addCmsChannel()" value="新增栏目"/>
			</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>栏目名</th>
				<th>排序</th>
				<th>url_code</th>
				<th>是否在前台菜单栏显示</th>
				<th>父栏目</th>
				<th> 操作</th>
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
	var name = $('#name').val();
	$.ajax({
		url : '${path}/cmsChanel/searchCmsChannelList/' + pageNo + '.html',
		data : {
			name : name
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
function delCmsChannel(cmsChannelId,pageNo){
	layer.confirm("确定删除?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/cmsChanel/delChannel.html',
			data : {
				cmsChannelId : cmsChannelId
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("删除成功",1,1,function(){
						pageGo(pageNo);
					});
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}
function addCmsChannel(){
	var _url = '${path}/cmsChanel/toCmsChannelAdd.html';
	$.layer({
		type : 2,
		title : '栏目添加',
		area : [ '800px', '430px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function editCmsChannel(cmsChannelId){
	var _url = '${path}/cmsChanel/toCmsChannelEdit/'+cmsChannelId+'.html';
	$.layer({
		type : 2,
		title : '栏目修改',
		area : [ '800px', '430px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function hiddenChannel(cmsChannelId,status,pageNo){
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/cmsChanel/hiddenCmsChannel.html',
		data : {
			cmsChannelId : cmsChannelId,
			status:status
		},
		type : 'post',
		dataType : "json",
		success : function(result) {
			layer.close(_load);
			if (result.code == '0') {
				layer.alert(result.message);
			} else {
				layer.msg("成功",1,1,function(){
					pageGo(pageNo);
				});
			}
		},
		error : function(data) {
			layer.close(_load);
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
</script>
</html>