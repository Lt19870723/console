<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>收支数据分析-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
	<form id="form" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
				数据日期：<input style="width: 183px;" name="beginTime" id="beginTime" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" styleClass="Wdate" placeholder="请输入开始日期" readonly="readonly"/>-
				<input style="width: 183px;" name="endTime" id="endTime" onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}'})" styleClass="Wdate" placeholder="请输入结束日期" readonly="readonly"/>
				<input value="查询" type="button" onclick="query();" />
				<input value="导出到Excel" type="button" onclick="validateExportDataCount();" />
				<input value="收入支出图及净收益图" type="button" onclick="generateIncomePayPic();" />
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
		var date_start = year + "-" + month + "-01";
		var date_end = year + "-" + month + "-" + day;
		$("#beginTime").val(date_start);
		$("#endTime").val(date_end);
		pageGo(1);
	});

	/**
	 *查询功能
	 */
	function query() {
		var s1=$("#beginTime").val();
		var s2=$("#endTime").val();
		if(s1!=null&&s2!=null&s1!=''&s2!=''){
			s1=s1.substring(0,s1.lastIndexOf('-'));
			s2=s2.substring(0,s2.lastIndexOf('-'));
			if(s1!=s2){
				alert("日期选择错误，请选择同一月份");
			}else{
				pageGo(1);
			}
		}else{
			alert("日期不能为空");
		}
	}

	/**
	 *翻页功能
	 */
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#form").ajaxSubmit({
			url : '${path}/finance/breakEvenAnalysis/list/' + pageNo + '.html',
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
		var s1=$("#beginTime").val();
		var s2=$("#endTime").val();
		if(s1!=null&&s2!=null&s1!=''&s2!=''){
			s1=s1.substring(0,s1.lastIndexOf('-'));
			s2=s2.substring(0,s2.lastIndexOf('-'));
			if(s1!=s2){
				alert("日期选择错误，请选择同一月份");
			}else{
				$("#form").ajaxSubmit({
					url : '${path}/finance/breakEvenAnalysis/count.html',
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
		}else{
			alert("日期不能为空");
		}
	}

	/**
	 *数据导出
	 */
	function exportData() {
		var s1=$("#beginTime").val();
		var s2=$("#endTime").val();
		if(s1&&s2){
			s1=s1.substring(0,s1.lastIndexOf('-'));
			s2=s2.substring(0,s2.lastIndexOf('-'));
		}else{
			alert("日期不能为空");
		}
		if(s1!=s2){
			alert("日期选择错误，请选择同一月份");
		}else {
			$("#form").attr("action", "${path}/finance/breakEvenAnalysis/export.html");
			$("#form").submit();
		}
	}

	// 生成收入支出图
	function generateIncomePayPic() {
		//$("#form").attr("action", "${path}/finance/breakEvenAnalysis/generatePics.html?picType=1");
		//$("#form").submit();
		var s1=$("#beginTime").val();
		var s2=$("#endTime").val();
		if(s1!=null&&s2!=null&s1!=''&s2!=''){
			s1=s1.substring(0,s1.lastIndexOf('-'));
			s2=s2.substring(0,s2.lastIndexOf('-'));
			if(s1!=s2){
				alert("日期选择错误，请选择同一月份");
			}else{
				var _url = "${path}/finance/breakEvenAnalysis/generatePics.html?";
				var beginTime = $("#beginTime").val();
				var endTime = $("#endTime").val();
				_url += "beginTime=" + beginTime + "&endTime=" + endTime;
				$.layer({
					type : 2,
					title : '收入支出---净收益图',
					area : [ '800px', '500px' ],
					offset : [ '10px', '' ],
					shade : [ 0.1, '#000' ],
					maxmin : true,
					iframe : {
						src : _url
					}
				});
			}
		}else{
			alert("日期不能为空");
		}
	

	}
	function edit(id) {
		var _url = "${path}/finance/breakEvenAnalysis/" + id + "/edit.html";
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '预期计算',
			area : [ '1000px', '400px' ],
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