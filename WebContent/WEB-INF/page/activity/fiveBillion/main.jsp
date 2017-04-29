<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—用户分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			时间选项：
				<input name="beginTime" id="beginTime" onclick="WdatePicker({minDate:'2016-05-10',maxDate:'2016-06-08'})" styleClass="Wdate" />&nbsp;至&nbsp;
				<input name="endTime" id="endTime" onclick="WdatePicker({minDate:'2016-05-10',maxDate:'2016-06-08'})" styleClass="Wdate" />
			&nbsp;	
			 <input type="button" onclick="javascript:exportData();" type="button" name="Submit1" id="subbtn" class="b_buts" style="width: 250px;" value="自动投标成功用户名单导出excel" />
		</div>
	</form>
   <form id="queryForm1" action="" method="post">
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			时间选项：
				<input name="beginTime" id="beginTime1" onclick="WdatePicker({minDate:'2016-05-10',maxDate:'2016-06-08'})" styleClass="Wdate" />&nbsp;至&nbsp;
				<input name="endTime" id="endTime1" onclick="WdatePicker({minDate:'2016-05-10',maxDate:'2016-06-08'})" styleClass="Wdate" />
			&nbsp;	
			 <input type="button" onclick="javascript:exportData1();" type="button" name="Submit1" id="subbtn" class="b_buts"  style="width: 250px;"  value="欢乐50颂奖励名单导出excel" />
		</div>
	</form>
	<div id="list" class="main_cent"></div>

</body>
<script type="text/javascript">
	
/**
 *导出数据
 */
function exportData(){
	var beginTime = $('#beginTime').val();
	if(null==beginTime || ""==beginTime){
		alert("请选择查询开始时间");
		return false;
	}
	
	var endTime = $('#endTime').val();
	if(null==endTime || ""==endTime){
		alert("请选择查询结束时间");
		return false;
	}
	
	 var d1 = new Date(beginTime.replace(/\-/g, "\/"));  
	 var d2 = new Date(endTime.replace(/\-/g, "\/"));  
	 if( d1 >d2)  
	 {
		  alert("开始时间不能大于结束时间！");  
		  return false;  
	 }
	$("#queryForm").ajaxSubmit ({
		url : '${path}/activity/fiveBillion/count.html',
		type : 'post' ,
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				$("#queryForm").attr("action","${path}/activity/fiveBillion/export.html");
		        $("#queryForm").submit();
			}else{
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		} 
	});
}
/**
 *导出数据
 */
function exportData1(){
	var beginTime = $('#beginTime1').val();
	if(null==beginTime || ""==beginTime){
		alert("请选择查询开始时间");
		return false;
	}
	
	var endTime = $('#endTime1').val();
	if(null==endTime || ""==endTime){
		alert("请选择查询结束时间");
		return false;
	}
	
	 var d1 = new Date(beginTime.replace(/\-/g, "\/"));  
	 var d2 = new Date(endTime.replace(/\-/g, "\/"));  
	 if( d1 >d2)  
	 {
		  alert("开始时间不能大于结束时间！");  
		  return false;  
	 }
	$("#queryForm1").ajaxSubmit ({
		url : '${path}/activity/fiveBillion/count1.html',
		type : 'post' ,
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				$("#queryForm1").attr("action","${path}/activity/fiveBillion/export1.html");
		        $("#queryForm1").submit();
			}else{
				alert(result.message);
			}
		},
		error : function(result) {
			alert(result.message);
		} 
	});
}
</script>
</html>
