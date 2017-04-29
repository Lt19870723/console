<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>日志记录-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			用户名：<input type="text" name="username" id="username" class="input1" style="width:150px;"/>&nbsp;
			借款标名称：<input type="text"  name="borrowName" id="borrowName" class="input1" style="width:150px;"/>&nbsp;
			借款标编号：<input type="text"  name="borrowContractNo" id="borrowContractNo" class="input1" style="width:150px;"/>
			&nbsp;
			类型 ：
			<select id="status" name="status" class="bigselect" style="width:120px;">
						<option value="">--请选择状态--</option>
						<option value="1">已投标</option>
						<option value="2">未投标</option>
			</select>
			 &nbsp;	
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			<br/>
			<input type="button"
					onclick="javascript:delFirstTenderLog();" name="delFirstTenderLogBtn"
					id="delFirstTenderLogBtn" class="b_buts" value="清除失败记录" />（仅清除7天前所有失败记录）
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>序号  </th>
				<th>用户名  </th>
				<th>序号（投标） </th>
				<th>所投借款标</th>
				<th>借款标编号  </th>
				<th>投标前标剩余金额 </th>
				<th>投标前可用余额 </th>
				<th>投标金额 </th>
				<th>状态</th>
				<th>投标时间</th>
				<th>备注说明 </th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
	$(function() {
		pageGo(1);
	});

	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/firstborrow/firsttenderlog/list/' + pageNo + '.html';
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
		pageGo(1);
	}
	
	function delFirstTenderLog(){
		if(!confirm("确认要清除前7天的所有投标失败记录吗？")){
			return;
		}
		
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/firstborrow/firsttenderlog/delfirsttenderlog.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						pageGo(1);
					});
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
		
	}

	function showADetailPopup(id) {
		var _url = '${path}/firstborrow/firsttenderlog/detail/' + id + '.html';
		$.layer({
			type : 2,
			title : '备注说明',
			area : [ '800px', '200px' ],
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