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
			<input type="button" id="searchBtn" value="查询" onclick="pageGo(1)"  styleClass="button" />
		</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="4%">序号</th>
				<th width="7%">用户名</th> 
				<th width="8%">验证时间</th> 
				<th width="12%">验证结果</th>
				<th width="8%">银行卡号</th>
				<th width="7%">真实姓名</th>
				<th width="8%">身份证号</th>
				<th width="9%">银行预留手机号</th>
				<th width="5%">状态</th>
				<th width="5%">操作类型</th>
				<th width="6%">操作</th>
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
	$.ajax({
		url : '${path}/bankCardChange/serachVerify/' + pageNo + '.html',
		data : {
			userName : userName
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

function delVerify(id){
	var _load; 
	if(confirm("确认要删除吗？")){
	_load = layer.load('处理中..');
	$.ajax({
		url : '${path}/bankCardChange/delVerify.html',
		data : {
			'id' : id	 
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					pageGo(1);
				});
			} else {
				 
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			parentLayer.close(_load);
	    }
	  });		
	 
	}
} 
</script>
</html>