<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>提现核对-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
	<form id="form" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				日期：<input style="width: 183px;" name="startDate" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" styleClass="Wdate" placeholder="请输入日期" readonly="readonly"/>
				<input value="查询" type="button" onclick="query();" />
				<input value="导出到Excel" type="button" onclick="exportToExcel();" />
				<input value="添加记录" type="button" onclick="edit();" />
			</div>
		</div>
	</form> 
	<div id="list" class="main_cent"></div>
</body>
<script type="text/javascript">
	$(function() {
		var today = formatDateTimeYmd(new Date());
		$("input[name=startDate]").val(today);
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
			url : '${path}/finance/flow/list/' + pageNo + '.html',
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

	// 根据条件导出Excel
	function exportToExcel() {
		var startDate = $("#startDate").val();
		if(startDate == "" || startDate==null){
			layer.msg('请选择流水导出日期！', 1, 5);
			return;
		}
		var _url = "${path}/finance/flow/export.html";
		_url += "?date=" + startDate;
		window.location.href = _url;
	}

	function edit(date) {
		var _url = "${path}/finance/flow/edit.html";
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '添加记录',
			area : [ '600px', '500px' ],
			offset : [ '7px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});

	}
	
	function updateWithdrawal(id, billDate){
		layer.confirm("确认 更新?", function() {
			var _load = layer.load('处理中..');
			$.ajax({
				url : '${path}/finance/checkRecharge/checkRechargeByDate.html',
				type : 'post',
				dataType : 'json',
				data : {
					id : id,
					billDateStr : billDate
				},
				success : function(result) {
					
					layer.msg(result.msg, 1, 1, function() {
						parentLayer.close(_load);
						parent.pageGo(1);
					});
				},
				error : function(data) {
					layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				}
			});
		});
	}
	
	updateFlow = function(id){
		var _url = "${path}/finance/flow/updateFlow/"+id+".html";
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '编辑流水记录',
			area : [ '600px', '500px' ],
			offset : [ '7px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});

	}
	
	
 function deleteFlow(id){
		layer.confirm("确认作废这条记录么?", function() {
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/finance/flow/deleteFlow.html?id='+id,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				//var dataObj=eval("("+result+")");
				if(result.isSuccess){
					layer.msg("操作成功!", 1, 1, function() {
						pageGo(1);
					});
				}else{
					layer.msg("操作失败！"+dataObj,message, 1, 5);
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