<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	 <div style="margin-left:20px;line-height:40px;">
	    	用户名：<input type="text name="userName" id="userName" class="input1"/>
	    	&nbsp;
			姓名：<input type="text"  name="realName" id="realName" class="input1"/>
			&nbsp;
			<input type="button" id="searchBtn" value="查询" onclick="pageGo(1)"  styleClass="button" />
		</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="2%">序号</th>
				<th width="7%">用户名</th> 
				<th width="4%">真实姓名</th> 
				<th width="9%">银行卡号</th>
				<th width="6%">开户行</th>
				<th width="8%">换卡次数</th>
				<th width="4%">申请次数</th>
				<th width="8%">累积点击申请次数</th>
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
	$.ajax({
		url : '${path}/bankCardChange/serachAll/' + pageNo + '.html',
		data : {
			userName : userName,
			realName : realName
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