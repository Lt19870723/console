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
				充值日期：<input style="width: 183px;" name="beginTime" id="beginTime" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" styleClass="Wdate" placeholder="请输入开始日期" readonly="readonly"/>-
				<input style="width: 183px;" name="endTime" id="endTime" onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}'})" styleClass="Wdate" placeholder="请输入结束日期" readonly="readonly"/>
				<input value="查询" type="button" onclick="query();" />
				<input value="导出到Excel" type="button" onclick="validateExportDataCount();" />
			</div>
		</div>
	</form> 
	<div id="list" class="main_cent"></div>
</body>
<script type="text/javascript">
	$(function() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate() - 1;
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var date_start = year + "-" + month + "-" + day;
		$("#beginTime").val(date_start);
		$("#endTime").val(date_start);
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
			url : '${path}/finance/rechargeAnalysis/list/' + pageNo + '.html',
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

	/**
	 *校验数据条数是否符合要求
	 */
	function validateExportDataCount() {
		$("#form").ajaxSubmit({
			url : '${path}/finance/rechargeAnalysis/count.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					exportData();
				} else {
					layer.alert(result.message);
				}
			},
			error : function(result) {
				layer.alert(result.message);
			}
		});
	}

	/**
	 *数据导出
	 */
	function exportData() {
		$("#form").attr("action", "${path}/finance/rechargeAnalysis/export.html");
		$("#form").submit();
	}
</script>
</html>