<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内部转账-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
<form id="form" action="" method="post">
	 <div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
			&nbsp;&nbsp;&nbsp;&nbsp;转账编号：<input id="operationCode" name="operationCode" class="input1" value=""/>
			状态：
			<select class="bigselect" value="" name="status">
						<option value="">--请选择--</option>
						<option value="1" selected="selected">草稿中</option>
						<option value="2">待审核</option>
						<option value="3">审核通过</option>
						<option value="4">已删除</option>
			</select>
			<input id="searchMoneyOperations" name="searchMoneyOperations" value="查询" type="button" onclick="query();" />&nbsp;
	       <input id="addMoneyOperation" name="addMoneyOperation" value="添加" type="button" onclick="addOperation();" />&nbsp;	
			</div>
	       
	   </div>
	  </form> 
	       
	<div id="list" class="main_cent"></div>
	
	
</body>
<script type="text/javascript">
	$(function() {
		pageGo(1);
	});

	/**
	 *查询功能
	 */
	function query() {
		pageGo(1);
	}

	/**
	 *翻页功能
	 */
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#form").ajaxSubmit({
			url : '${path}/finance/interTransfer/list/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}

	function addOperation() {
		var _url = '${path}/finance/interTransfer/toapplyFor.html'
		$.layer({
			type : 2,
			title : '添加',
			area : [ '800px', '500px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}

	function showVirement(id) {

		var _url = '${path}/finance/interTransfer/detail/' + id + '.html'
		$.layer({
			type : 2,
			title : '详情',
			area : [ '800px', '500px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});

	}

	function editVirement(id) {
		var _url = '${path}/finance/interTransfer/toapplyFor.html';
		if(id != null){
			_url += "?id=" + id;
		}
		
		$.layer({
			type : 2,
			title : '编辑',
			area : [ '800px', '500px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
	function updateStatus(id) {
		parentLayer.confirm("确认 删除?", function() {
			var _load = layer.load('处理中..');
			$.ajax({
				url : '${path}/finance/interTransfer/deleteByPrimaryKey/' + id + '.html',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					if (result.code == '1') {
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
		});
	}
</script>
</html>