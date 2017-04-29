<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>错误日志-定时任务管理-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 50px;">
		任务名：<input type="text" id="jobName" name="jobName" class="input1" style="width: 180px;" />&nbsp;
		起止时间：<input type="text" id="startTime" name="startTime" class="input1" style="width: 180px;" />&nbsp;-&nbsp;<input type="text" id="endTime" name="endTime" class="input1" style="width: 180px;" />
		<input id="selectBtn" name="selectBtn" value="查询" type="button" onclick="pageGo(1);" />
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="30%">发生时间</th>
				<th>错误信息</th>
				<th width="15%">操作</th>
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
		url : '${path}/system/scheduler/log/error/list/' + pageNo + '.html',
		data : {
			'jobName' : $.trim($('#jobName').val()),
			'startTime' : $('#startTime').val(),
			'endTime' : $('#endTime').val()
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

function detail(objId) {
	$.layer({
		type : 1,
		title : false,
	    area: ['auto', 'auto'],
		offset : [ '40px', '' ],
		shade : [ 0.1, '#000' ],
		page : {
	        dom: '#' + objId, 
		}
	});
}
</script>
</html>