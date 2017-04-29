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
	 用户名称：
	<input type="text" class="input1" id="userName" name="userName" maxlength="50" style="width:200px;"/>
	真实姓名：
	<input type="text" class="input1" id="realName" name="realName" maxlength="50" style="width:200px;"/>
	四要素验证状态：
	<select class="bigselect" id="verifyStatus" name="verifyStatus">
	     <option value="">请选择</option>
	     <option value="0">未验证</option>
	     <option value="1">已验证</option>
	</select>
	</br>
	  绑卡日期：
				<input
					name="dateTimeStart" id="dateTimeStart" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			至
				<input
					name="dateTimeEnd" id="dateTimeEnd" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
	<input type="button" onclick="pageGo(1)" value="查询" />
	&nbsp;
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
				<th>四要素验证状态</th>
				<th>绑卡时间 </th>
				<th>联行号 </th>
				<th>状态</th>
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
	var realName = $('#realName').val();
	var verifyStatus = $('#verifyStatus').val();
	var dateTimeStart = $('#dateTimeStart').val();
	var dateTimeEnd = $('#dateTimeEnd').val();
	$.ajax({
		url : '${path}/memberBank/serachAll/' + pageNo + '.html',
		data : {
			userName : userName,
			realName : realName,
			verifyStatus : verifyStatus,
			dateTimeStart : dateTimeStart,
			dateTimeEnd : dateTimeEnd
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