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
<style type="text/css">
table
{
border-collapse: collapse;
border: none;
margin-left: 10%;
width: 80%;
}
td
{
border: solid #000 1px;
}
</style> 
</head>
<body>
	<form id="form" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				日期：<input style="width: 183px;" name="startDate" id="startDate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" styleClass="Wdate" placeholder="请输入开始日期" readonly="readonly"/>-
				<input style="width: 183px;" name="endDate" id="endDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" styleClass="Wdate" placeholder="请输入结束日期" readonly="readonly"/>
				<input value="查询" type="button" onclick="query();" />
				<!-- <input value="导出到Excel" type="button" onclick="exportToExcel();" /> -->
			</div>
		</div>
	</form> 
	<div id="valueList" class="main_cent"></div>
</body>
<script type="text/javascript">
	$(function() {
		pageGo();
	});

	/**
	 *查询功能
	 */
	function query() {
		pageGo();
	}

	/**
	 *翻页功能
	 */
	function pageGo() {
		$("#form").ajaxSubmit({
			url : '${path}/stock/stockDeal/sumStock.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#valueList").html(result);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
</script>
</html>