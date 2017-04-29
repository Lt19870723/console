<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>直通车列表-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			发布日期：
			<input
				name="beginTime" id="beginTime" onclick="WdatePicker()"
				class="Wdate"  />
			至
			<input
				name="endTime" id="endTime" onclick="WdatePicker()"
				class="Wdate"  />
			&nbsp;
			状态：
			<select id="status" name="status" class="bigselect">
						<option value="">--请选择状态--</option>
						<option value="0">草稿标</option>
						<option value="1">新标,审核中</option>
						<option value="2">审核不通过</option>
						<option value="3">审核通过投标中</option>
						<option value="4">满标复审中</option>
						<option value="5">满标审核通过</option>
						<option value="6">满标审核拒绝</option>
						<option value="-1">流标</option>
						<option value="-2">被撤销</option>
						<option value="-3">已过期</option>
						<option value="-4">已删除</option>
						<option value="-5">已失效</option>
			</select>
			&nbsp;					
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;					
			<input type="button"
					onclick="javascript:validateadd();" name="addBtn"
					id="addBtn" class="b_buts" value="添加" />
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
						<th>
							序号
						</th>
						<th>
							状态
						</th>
						<th>
							计划金额(万元)
						</th>
						<th>
							发布时间
						</th>
						<th>
							投标有效时间
						</th>
						<th>
							总计投标人次
						</th>				
						<th width="200px">
							操作
						</th>
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
		var urlPath = '${path}/firstborrow/firstborrowlist/list/' + pageNo + '.html';
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
	
	function validateadd() {
		//var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/firstborrow/firstborrowlist/validateadd.html',
			type : 'post',
			success : function(result) {
				if (result.code == '0') {
					//parentLayer.close(_load);
					add();
				} else {
					//parentLayer.close(_load);
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				//parentLayer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function add() {
		var _url = '${path}/firstborrow/firstborrowlist/add.html';
		$.layer({
			type : 2,
			title : '直通车新增',
			area : [ '880px', '350px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}

	function doDelete(id,version) {
		if(!confirm("你确定要删除此投标直通车吗？")){
			return;
		}
	
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/firstborrow/firstborrowlist/delete/' + id + '/' + version + '.html',
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

	function toAddModify(id,operation) {
		var _url = '${path}/firstborrow/firstborrowlist/detail/' + id + '/' + operation + '.html';
		$.layer({
			type : 2,
			title : '直通车详情',
			area : [ '880px', '400px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
	function toUpdate(id,operation) {
		var _url = '${path}/firstborrow/firstborrowlist/update/' + id + '/' + operation + '.html';
		$.layer({
			type : 2,
			title : '直通车详情',
			area : [ '880px', '400px' ],
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