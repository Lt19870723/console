<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易-国诚金融后台管理系统</title>
<style type="text/css">
</style> 
</head>
 <body>
 <div style="margin-top: 20px;">
	<select id="J_sharedowntype">
		<option value="1">最新内转名册</option>
		<option value="2">需退出内转名册</option>
		<option value="3">需加入内转名册</option>
	</select>
	<input type="button" value="导出" onclick="exportToExcel();"/>
	</div>
 </body>
 <script type="text/javascript">
//根据条件导出Excel
	function exportToExcel() {
		var shareDownType = $("#J_sharedowntype").val();
		var _url = "${path}/shareholder/exportShareholder/"+shareDownType+".html";
		window.location.href = _url;
	}
 </script>
</html>