<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post">
	
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			&nbsp;
			用户：<input id="userName" name="userName" class="input1" style="width:150px;"/>
			&nbsp;
			申请时间：<input
					name="beginPublicTimeStr" id="beginPublicTimeStr" onclick="WdatePicker()"
					styleClass="Wdate" >
				</input>
			至
			<input
					name="endPublicTimeStr" id="endPublicTimeStr" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			<select
				id="status" name="status"
				class="bigselect" style="width:130px;">
				<option value="">--请选择--</option>
				<option value="0">申请转可提</option>
				<option value="1">审核通过</option>
				<option value="2">取消转可提</option>
				<option value="-1">审核失败</option>
			</select>
			&nbsp;	
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			 &nbsp;	
			<input type="button"
					onclick="javascript:exportCallBackData();" type="button" name="Submit1"
					id="export" class="b_buts" value="导出EXCEL" />
			<br/>
			</div>
			
		
	</form>

	<div id="list" class="main_cent"></div>

</body>
<script type="text/javascript">

	$(function() {
		pageGo(1);
	});
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/account/drawablerocord/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				parentLayer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	/**
	*查询功能
	*/
	function query(){
		pageGo(1);
	}
	
	/**
	 *导出数据
	 */
	function exportCallBackData(){
		
		if(!validateExportCallbackData()){
			return;
		}
		validateExportDataCount();
	}
	
	/**
	 *校验数据条数是否符合要求
	 */
	function validateExportDataCount(){
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/drawablerocord/count.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				if(result.code == '0'){
					exportData();
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
	 *数据导出
	 */
	function exportData(){
		$("#queryForm").attr("action","${path}/account/drawablerocord/export.html");
        $("#queryForm").submit();
	}
	/**
	 *校验导出数据的过滤条件
	 */
	function validateExportCallbackData(){
		
		/* var beginTime = $('#appBeginTimeInputDate').val();
		var endTime = $('#endTimeInputDate').val();
		var payBeginTime = $('#payBeginTimeInputDate').val();
		var payEndTime = $('#payEndTimeInputDate').val();
		var username =$('#username').val();
		var status = $('#status').val();
		if((beginTime.length == 0 || endTime.length == 0) &amp;&amp; (payBeginTime.length == 0 || payEndTime.length == 0) &amp;&amp; username.length==0 &amp;&amp; status.length==0){
			alert('您没有选择任何条件，至少需要选择一个条件进行导出。');
			return false;
		}
		
		if(!confirm("确认要导出Excel吗？")){
			return false;
		} */
		return true;
	}
</script>
</html>
