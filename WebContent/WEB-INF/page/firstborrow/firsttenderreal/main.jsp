<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>直通车开通详情-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			用户名：<input type="text" id="username" name="username" class="input1"/>
			&nbsp;
			状态：
			<select id="status" name="status" class="bigselect">
						<option value="">--请选择状态--</option>
						<option value="0">锁定中</option>
						<option value="1">已解锁</option>
						<option value="2">解锁中</option>
						<option value="3">转让中</option>
						<option value="4">已转让</option>
			</select>
			&nbsp;					
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;					
			<input type="button"
					onclick="javascript:validateExportExcelData();" name="exportExcelBtn"
					id="exportExcelBtn" class="b_buts" style="width: 150px;" value="批量导出Excel" />
			<br/>
			当前直通车开通金额的总额为：
			<span id="firstTenderRealAccount" style="color:red" ></span>
			元
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="40"  align="center">
					序号
				</th>
				<th width="40"  align="center">
					用户名
				</th>						
				<th width="60"  align="center">
					开通金额
				</th>
				<th width="60"  align="center">
					开通余额
				</th>
				<th width="50"  align="center">
					序号（投标）
				</th>	
				<th width="50"  align="center">
					状态
				</th>
				<th width="50"  align="center">
					认购时间
				</th>
				<th width="50"  align="center">
					生效时间
				</th>									
				<th width="50"  align="center">
					操作
				</th>
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
		var urlPath = '${path}/firstborrow/firsttenderreal/list/' + pageNo + '.html';
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
		var _load = layer.load('处理中..');
		$("#queryForm").ajaxSubmit({
			url : '${path}/firstborrow/firsttenderreal/sum.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				$("#firstTenderRealAccount").html(result["firstTenderRealAccount"]);
				layer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	function validateExportExcelData() {
		if(!confirm("确认批量导出Excel吗？")){
			return false;
		}
	
		$("#queryForm").ajaxSubmit({
			url : '${path}/firstborrow/firsttenderreal/validateexport.html',
			type : 'post',
			success : function(result) {
				if (result.code == '0') {
					exportExcel();
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function exportExcel() {
		$("#queryForm").attr("action","${path}/firstborrow/firsttenderreal/exportexcel.html");
        $("#queryForm").submit();
	}

	function unlock(id,userId) {
		if(!confirm("你确定要解除直通车锁定吗？")){
			return;
		}
	
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/firstborrow/firsttenderreal/unlock/' + id + '/' + userId + '.html',
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
</script>
</html>