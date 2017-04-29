<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信维护-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			推送时间：
			<input
				name="pushTime" id="pushTime" onclick="WdatePicker()"
				class="Wdate"  />
			至
			<input
				name="pushTime2" id="pushTime2" onclick="WdatePicker()"
				class="Wdate"  />
			&nbsp;
			推送状态：
			<select id="isPush" name="isPush" class="bigselect">
						<option value="">--请选择状态--</option>
						<option value="1">已推送</option>
						<option value="2">未推送</option>
						<option value="3">推送中</option>
			</select>
			&nbsp;					
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;					
			<input type="button"
					onclick="javascript:add();" name="addBtn"
					id="addBtn" class="b_buts" value="添加" />
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
							<th width="3%">序号</th>
							<th width="20%">标题</th>
							<th width="4%">图片</th>
							<th width="15%">超链接</th>
							<th width="20%">描述</th>
							<th width="6%">状态</th>
							<th width="14%">最近推送时间</th>
							<th width="4%">推送人数</th>
							<th width="5%">推送成功人数</th>
							<th width="10%">操作</th>
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
		var urlPath = '${path}/maintain/xw/wxpushone/list/' + pageNo + '.html';
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
	
	function add() {
		var _url = '${path}/maintain/xw/wxpushone/add.html';
		$.layer({
			type : 2,
			title : '新增-单图文消息',
			area : [ '850px', '530px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
	function detailOneNews(id) {
		var _url = '${path}/maintain/xw/wxpushone/detailone/' + id + '.html';
		$.layer({
			type : 2,
			title : '描述',
			area : [ '860px', '300px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}

	function doDelete(id) {
		if(!confirm("确定删除吗？")){
			return;
		}
	
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/maintain/xw/wxpushone/delete/' + id + '.html',
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
	
	function push(id) {
		if(!confirm("确定推送吗？")){
			return;
		}
	
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/maintain/xw/wxpushone/push/' + id + '.html',
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
	
	function pushAgain(id) {
		if(!confirm("确定复制吗？")){
			return;
		}
	
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/maintain/xw/wxpushone/pushagain/' + id + '.html',
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

	function toAddModify(id) {
		var _url = '${path}/maintain/xw/wxpushone/modify/' + id + '.html';
		$.layer({
			type : 2,
			title : '修改-单图文消息',
			area : [ '850px', '530px' ],
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