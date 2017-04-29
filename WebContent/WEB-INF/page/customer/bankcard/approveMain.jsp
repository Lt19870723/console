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
		<input type="button"  id="searchBtn" value="查询" onclick="pageGo(1)" styleClass="button" />
		<input type="hidden" value="0" id="approveStatus" name="approveStatus"/>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="2%">序号</th>
				<th width="7%">用户名</th> 
				<th width="4%">真实姓名</th> 
				<th width="9%">银行卡号</th>
				<th width="6%">开户行</th>
				<th width="8%">本次申请时间</th>
				<th width="8%">上次申请时间</th>
				<th width="4%">换卡原因</th>
				<th width="4%">操作类型</th>
				<th width="3%">操作</th>
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
		url : '${path}/bankCardChange/serachApproveAll/' + pageNo + '.html',
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
		title : '银行卡更换审核',
		area : [ '900px', '430px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}

function recheckApproveBankInfo(bankcardChangeId){
	var _url = '${path}/bankCardChange/toRecheckApproveBank/'+bankcardChangeId+'.html';
	$.layer({
		type : 2,
		title : '银行卡更换审核',
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