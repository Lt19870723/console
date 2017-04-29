<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>活期宝转入转出-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post" >
		<div style="margin-left:20px;line-height:50px;">
			 用户名：<input type="text" id="userName" name="userName" class="input1"/>
			&nbsp;
			转入\转出时间：
			<input
				name="saddtime" id="saddtime" onclick="WdatePicker()"
				class="Wdate"  />
			至
			<input type="text"
				name="eaddtime" id="eaddtime" onclick="WdatePicker()"
				class="Wdate"  />
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;
			<br/>
			转入\转出方式：
			<select id="type_zj" name="type_zj" class="bigselect">
				<option value="">--请选择状态--</option>
				<option value="1">转入</option>
				<option value="2">转出</option>
			</select>
			&nbsp;
			资金类型：
			<select id="type" name="type" class="bigselect">
				<option value="">--请选择--</option>
				<option value="1" >可用余额转入</option>
				<option value="2" >投标资金退回</option>
				<option value="3" >购买债权资金退回</option>
				<option value="4" >定期宝转入</option>
				<option value="5" >转出到可用余额</option>
				<option value="6" >投标转出</option>
				<option value="7" >开通直通车转出</option>
				<option value="8" >购买债权转出</option>
				<option value="9" >购买直通车转让转出</option>
				<option value="10" >购买定期宝转出</option>	 
				<option value="11" >内转交易转出</option>	 
			</select>
			<br/>	
			转入金额累计: <span>${ sumMoneyIn}</span>
			元
			&nbsp;&nbsp;
			转出金额累计: <span>${sumMoneyOut }</span>
			元
			&nbsp;
			转入金额合计: <span id="sumIn">${sumMoneyIn}</span>
			元
			&nbsp;&nbsp;
			转出金额合计: <span id="sumOut">${sumMoneyOut}</span>
			元
			&nbsp;
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="5%">序号  </th>
				<th>用户名 </th>
				<th>转入资金 </th>
				<th>转出资金    </th>
				<th>操作金额    </th>
				<th>转入\转出时间    </th>
				<th width="20%">转入\转出方式</th>
				<th>活期宝资产 </th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
	$(function() {
		query();
	});

	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/curaccount/curaccountinout/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$('#dataTable tbody').remove();
				$('#dataTable').append(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				parentLayer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}

	/**
	*查询功能
	*/
	function query(){
		sum();
		pageGo(1);
	}
	
	function sum() {
		$("#queryForm").ajaxSubmit({
			url : '${path}/curaccount/curaccountinout/sum.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#sumIn").html(result["sumIn"]);
				$("#sumOut").html(result["sumOut"]);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
</script>
</html>