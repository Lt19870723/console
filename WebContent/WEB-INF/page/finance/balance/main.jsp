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
				日期：<input style="width: 183px;" name="date" id="date" value="${nowDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" styleClass="Wdate"  readonly="readonly"/>
				<input value="查询" type="button" onclick="query();" />
				<input value="导出到Excel" type="button" onclick="exportList();" />
				<input value="添加余额信息" type="button" onclick="addBalanceInfo();" />
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
	function pageGo() {
		var date= $("input[name=date]").val();
		if(date == "" || date == null){
			layer.msg('请输入您要查询的日期！', 1, 5);
			return;
		}
		var _load = parentLayer.load('处理中..');
		$("#form").ajaxSubmit({
			url : '${path}/finance/balance/queryBalanceByTime.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
				var sumAccount=0;
				var empAccount = 0;
				$(".J_companysum").each(function (){ 
					sumAccount += parseFloat($(this).html());
				});
				$(".J_empaccount").each(function (){ 
					empAccount += parseFloat($(this).html());
				});
				$(".J_companyaccount").html(formatCash(sumAccount,2));
				$(".J_Total").html(formatCash(empAccount+sumAccount,2));
				
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
	
	updateBalance = function(id){
		var endTime= $("input[name=date]");
		var _url = "${path}/finance/balance/edit.html?endTime="+endTime+"&id="+id;
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '修改余额信息',
			area : [ '530px', '400px' ],
			offset : [ '7px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	};
	
	
	deleteBalance = function(id){
		layer.confirm("确认作废这条记录么?", function() {
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/finance/balance/deleteBalcance.html?id='+id,
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				if(result.code == 'true'){
					layer.msg("操作成功!", 1, 1, function() {
						pageGo(1);
					});
				}else{
					layer.msg("操作失败！", 1, 5);
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
		});
	};

	function addBalanceInfo() {
		var endTime= $("input[name=date]");
		var _url = "${path}/finance/balance/edit.html?endTime="+endTime;
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '添加余额信息',
			area : [ '530px', '400px' ],
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
	exportList = function(){
		var startDate = $("#date").val();
		if(startDate == "" || startDate==null){
			layer.msg('请选择导出日期！', 1, 5);
			return;
		}
		var _url = "${path}/finance/balance/exportBalanceByTime.html";
		_url += "?date=" + startDate;
		window.location.href = _url;
	}
</script>
</html>