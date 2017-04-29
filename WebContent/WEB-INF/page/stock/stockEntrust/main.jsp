<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
	<form id="form" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				委托类型 ：
				 <select id="entrustType" name="entrustType"   style="width:183px;height: 24px;">
				 	<option value="">--请选择--</option>
				 	<option value="1">认购</option>
				 	<option value="2">转让</option>
				 </select>
				 状态 ：
				 <select id="status" name="status"   style="width:183px;height: 24px;">
				 	<option value="">--请选择--</option>
				 	<option value="1">已挂单</option>
				 	<option value="2">部分成交</option>
				 	<option value="3">全部成交</option>
				 	<option value="4">已撤单</option>
				 </select>
				日期：<input style="width: 183px;" name="startDate" id="startDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" styleClass="Wdate" placeholder="请输入开始日期" readonly="readonly"/>-
				<input style="width: 183px;" name="endDate" id="endDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" styleClass="Wdate" placeholder="请输入结束日期" readonly="readonly"/>
				<input value="查询" type="button" onclick="query();" />
				<!-- <input value="导出到Excel" type="button" onclick="exportToExcel();" /> -->
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
			url : '${path}/stock/stockEntrust/list/' + pageNo + '.html',
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
	var cxIndex= "";
	function queryInfoById(id) {
		var _url = "${path}/stock/stockEntrust/" + id + "/info.html";
		cxIndex= $.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '委托详情',
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
				url : '${path}/finance/stockEntrust/checkRechargeByDate.html',
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
	
	
	function revokeById(id, userId){
		layer.confirm("确认 撤销该委托单?", function() {
			var _load = layer.load('处理中..');
			$.ajax({
				url : '${path}/stock/stockEntrust/revokeEntrust.html',
				type : 'post',
				dataType : 'json',
				data : {
					id : id,
					userId : userId
				},
				success : function(result) {
					parentLayer.close(_load);
					if(result.code == "00000"){
						layer.msg("撤单成功！", 1, 1, function() {
							pageGo(1);
						});
					}else{
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