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
				日期：<input style="width: 183px;" name="startDate" id="startDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" styleClass="Wdate" placeholder="请输入开始日期" readonly="readonly"/>-
				<input style="width: 183px;" name="endDate" id="endDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" styleClass="Wdate" placeholder="请输入结束日期" readonly="readonly"/>
				状态 ：
				 <select id="isSuccess" name="isSuccess"   style="width:183px;height: 24px;">
				 	<option value="">--请选择--</option>
				 	<option value="1">对账成功</option>
				 	<option value="2">保存草稿</option>
				 	<option value="3">未对账</option>
				 </select>
				<input value="查询" type="button" onclick="query();" />
				<input value="导出到Excel" type="button" onclick="exportToExcel();" />
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
			url : '${path}/finance/checkRecharge/list/' + pageNo + '.html',
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
		var _url = "${path}/finance/checkRecharge/export.html";
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var isSuccess = $("#isSuccess").val();
		_url += "?startDate=" + startDate + "&endDate=" + endDate+ "&isSuccess=" + isSuccess;
		window.location.href = _url;
	}

	function edit(date) {
		var _url = "${path}/finance/checkRecharge/" + date + "/edit.html";
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '充值核对',
			area : [ '1120px', '500px' ],
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
					parentLayer.close(_load);
					layer.msg(result.msg, 1, 1, function() {
						pageGo(1);
					});
				},
				error : function(data) {
					layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				}
			});
		});
	}
</script>
</html>