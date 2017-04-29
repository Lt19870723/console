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
	用户名：<input type="text"  name="username" id="username" class="input1" style="width:180px;"/>
	&nbsp;
	真实姓名：<input type="text"  name="realName" id="realName" class="input1" style="width:180px;"/>
	&nbsp;
<!-- 	是否VIP： -->
<!-- 	 <select id="isVip" class="bigselect" style="width:100px;"> -->
<!-- 		<option value="">--请选择--</option> -->
<!-- 		<option value="1">是</option> -->
<!-- 		<option value="-1">否</option> -->
<!-- 	 </select> -->
<!-- 	 &nbsp; -->
	是否SVIP ：
	 <select id="isSvip" class="bigselect" style="width:100px;">
		<option value="">--请选择--</option>
		<option value="1">是</option>
		<option value="-1">否</option>
	 </select>
	 &nbsp;
	 <input type="button" id="searchVipLevel" name="searchVipLevel" onclick="pageGo(1)" value="查询"/>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="50">序号  </th>
				<th>用户名  </th>
				<th>真实姓名 </th>
				<th>邮箱   </th>
				<th>手机号码   </th>
<!-- 				<th>是否VIP </th> -->
				<th>是否终身顶级会员 </th>
				<th>终身顶级会员序列 </th>
				<th>终身顶级会员开通时间</th>
				<th>操作 </th>
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
	var username = $('#username').val();
	var realName = $('#realName').val();
	var isVip = $('#isVip').val();
	var isSvip = $('#isSvip').val();
	$.ajax({
		url : '${path}/vipLevel/serachAll/' + pageNo + '.html',
		data : {
			username : username,
			realName : realName,
			isVip : isVip,
			isSvip : isSvip
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
function forSetSvip(userId){
	var _url = '${path}/vipLevel/forSetSvip/'+userId+'.html';
	$.layer({
		type : 2,
		title : 'VIP设置',
		area : [ '500px', '480px' ],
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