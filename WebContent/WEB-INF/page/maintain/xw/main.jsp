<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
<form id="wxBindExport"  method="post" action="">
	<div style="margin-left:20px;line-height:50px;">
			用户名：<input type="text" id="username" name="username"  class="input1"/>&nbsp;
				  是否绑定：
			<select class="bigselect" id="status" name="lockStatus">
				<option value="-1">--请选择状态--</option>
				<option value="0">已绑定</option>
				<option value="1">未绑定</option>
			</select>	
				<br/>
			用户资产总额：
				<input type="text" id="totalStart" name="totalStart"  class="input1"/>
			至
				<input type="text" id="totalEnd" name="totalEnd" class="input1"/>	<br/>
		        绑定日期：
				<input name="createTimeStart" id="createTimeStart" onclick="WdatePicker()" styleClass="Wdate" value=""><f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime></input>
			至
				<input name="createTimeEnd" id="createTimeEnd" onclick="WdatePicker()" styleClass="Wdate" value=""> <f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime></input>
				&nbsp;		
				<input type="button" value="查询"  onclick="pageGo(1)"/>
				<input type="button" value="导出" onclick="validateExportDataCount()"/>
				<br>
		   
			当前绑定微信的用户总数为：<span id="wxBindNumView"></span>
	</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="40">序号</th>
				<th>用户名</th>
				<th>邮箱</th>
				<th width="110">手机</th>
				<th width="80">真实姓名</th>
				<th width="80">是否绑定</th>
				<th>绑定时间</th>
				<th>资产总额</th>
				<th>待收总额</th>
				<th>可用金额</th>
				<th>冻结金额</th>
				<th>活期宝总额</th>
				<th>定期宝总额</th>
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
		url : '${path}/contentSystem/wx/wxList/' + pageNo + '.html',
		data : {
			'username' : $.trim($('#username').val()),
			'lockStatus' : $.trim($('#status').val()),
			'totalStart' : $.trim($('#totalStart').val()),
			'totalEnd' : $.trim($('#totalEnd').val()),
			'createTimeStart' : $.trim($('#createTimeStart').val()),
			'createTimeEnd' : $.trim($('#createTimeEnd').val())
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
	getWxBindNum();
}

function getWxBindNum() {
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/contentSystem/wx/getWxBindNumByTime.html',
		data : {
			'createTimeStart' : $.trim($('#createTimeStart').val()),
			'createTimeEnd' : $.trim($('#createTimeEnd').val())
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			$("#wxBindNumView").text(result);
			layer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}


function validateExportDataCount(){
	$("#wxBindExport").ajaxSubmit ({
		url : '${path}/contentSystem/wx/count.html',
		type : 'post' ,
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				exportData();
			}else{
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		} 
	});
}

function exportData(){
	$("#wxBindExport").attr("action","${path}/contentSystem/wx/export.html");
    $("#wxBindExport").submit();
}
</script>
</html>