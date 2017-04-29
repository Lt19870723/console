<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>换卡审核</title>
</head>
<body>
	 <div style="margin-left:20px;line-height:40px;">
    	用户名：<input type="text"  name="userName" id="userName" class="input1"/>
    	&nbsp;
		姓名：<input type="text"  name="realName" id="realName" class="input1"/>
		&nbsp;
		卡号：<input type="text"  name="newBankcard" id="newBankcard" class="input1"/>
		&nbsp;
		状态：<select id="approveStatus" name="approveStatus">
			<option value="">不限</option>
			<option value="1">初审通过</option>
			<option value="2">复审通过</option>
			<option value="-2">复审不通过</option>
			<option value="-1">初审不通过</option>
		</select>
		&nbsp;
		<input type="button"  id="searchBtn" value="查询" onclick="pageGo(1)" styleClass="button" />
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th> 
				<th>真实姓名</th> 
				<th>银行卡号</th>
				<th>开户行</th>
				<th>申请时间</th>
				<th>审核状态</th>
				<th>审核时间</th>
				<th>审核人</th>
				<th>操作类型</th>
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
	var userName = $('#userName').val();
	var realName = $('#realName').val();
	var newBankcard = $('#newBankcard').val();
	var approveStatus = $('#approveStatus').val();
	
	$.ajax({
		url : '${path}/bankCardChange/serachApproveLogAll/' + pageNo + '.html',
		data : {
			userName : userName,
			realName : realName,
			newBankcard : newBankcard,
			approveStatus : approveStatus
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

function approveBankInfo(bankcardChangeId){
	var _url = '${path}/bankCardChange/toApproveBank/'+bankcardChangeId+'.html';
	$.layer({
		type : 2,
		title : '银行卡更换',
		area : [ '900px', '430px' ],
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