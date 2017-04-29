<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 40px;">
		  <div style="font-weight: bold;">注册来源维护</div>
		  来源渠道：
			<select id="sourceCnd" value=""></select>
			&nbsp;
			<input type="button" id="initEdit" name="initEdit" onclick="pageGo(1)" value="查询"/>
			<input type="button" id="initEdit" name="initEdit"  onclick="editSource()" value="设置"/>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="2%" > </th>
				<th width="4%">序号</th>
				<th width="4%">来源号</th>
				<th width="8%">来源渠道</th>
				<th width="7%">开始时间</th>
				<th width="7%">结束时间</th>
				<th width="7%">设置人</th>
				<th width="7%">设置时间</th>
				<th width="7%">备注</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	getSourceChannel();
	pageGo(1);
	//getWxBindNum();
});
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/source/searchAll.html',
		data : {
			'source' : $('#sourceCnd').val()
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

function getSourceChannel() {
	$.ajax({
		url : '${path}/source/getSourceChannel.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
			jQuery($("#sourceCnd")).get(0).options.add(new Option("",""));
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#sourceCnd").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function editSource(){
	var id_array=new Array();
	var i=0;
	$('input[name="checkbox"]:checked').each(function(){
    	id_array.push($(this).val());//向数组中添加元素
    	i++;
	});
	if(i==0){
		layer.alert("请选择一条记录。");
		return;
	}else if(i>1){
		layer.alert("只能选择一条记录。");
		return;
	}
	var sourceId = id_array[0];
	var _url = '${path}/source/toSourceChannelEdit/'+sourceId+'.html';
	$.layer({
		type : 2,
		title : '注册来源设置',
		area : [ '650px', '420px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
</script>
</html>