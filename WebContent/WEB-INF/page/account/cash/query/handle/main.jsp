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
			提现时间：
				<input
					name="beginTime" id="appBeginTime" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			至
				<input
					name="endTime" id="endTime" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			用户名：<input id="username" name="username" class="input1"/>
			&nbsp;	
			<br/>
			是否已导出 ：
			 <select name="isExport"
				id="isExport"
				class="bigselect" style="width:130px;">
				<option value="">--请选择--</option>
				<option value="0">未导出</option>
				<option value="1">已导出</option>
			 </select>
			 &nbsp;	
			 <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			 &nbsp;	
			<input type="button"
					onclick="javascript:validatePayed();" type="button" name="Submit1"
					id="subdk" class="b_buts" value="批量打款成功" />
			<br/>
<!-- 			<input type="button" -->
<!-- 					onclick="javascript:exportExcelForMS();" type="button" name="Submit1" style="width: 200px" -->
<!-- 					id="export1" class="b_buts" value="批量导出Excel(民生打款)" /> -->
<!-- 			&nbsp; -->
<!-- 			<input type="button" -->
<!-- 					onclick="javascript:exportTxtForMS();" type="button" name="Submit1" style="width: 200px" -->
<!-- 					id="export2" class="b_buts" value="批量导出Txt(民生打款)" /> -->
<!-- 			&nbsp; -->
<!-- 			&nbsp; -->
			<input type="button"
					onclick="javascript:exportExcelForWY();" type="button" name="Submit1" style="width: 200px"
					id="export3" class="b_buts" value="批量导出Excel(网银打款)" />
			&nbsp;
			<input type="button"
					onclick="javascript:exportTxtForWY();" type="button" name="Submit1" style="width: 200px"
					id="export4" class="b_buts" value="批量导出Txt(网银打款)" />
			</div>
			
		<input type="hidden" name="checkStatus" id="checkStatus" value="0" />
		
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
		var urlPath = '${path}/account/cashhandlerecord/list/' + pageNo + '.html';
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
	
	function validatePayed(){
		var ids = "";
		if(!$("input[name='cashId']").is(':checked')){
			alert("至少需要选择一条记录");
			return ;
		}
		$("input[name='cashId']").each(function(){
			if($(this).is(':checked')){
				ids = ids + $(this).val()+",";
			}
		});
		
		if(ids.length > 0){
			ids= ids.substring(0,ids.length-1);
		}
		if(!confirm("确认批量打款成功吗？")){
			return;
		}
		//$("#queryForm").attr("action","${path}/account/cashhandlerecord/batchPayCash/"+ids+".html");	
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/cashhandlerecord/batchpaycash/'+ids+'.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				alert(result.message);
				window.location.href = window.location.href;
			},
			error : function(result) {
				alert(result.message);
			} 
		});
	}
	
	function exportExcelForMS(){
		var ids = "";
		if(!$("input[name='cashId']").is(':checked')){
			alert("至少需要选择一条记录");
			return;
		}
		$("input[name='cashId']").each(function(){
			if($(this).is(':checked')){
				ids = ids + $(this).val()+",";
			}
		});
		
		if(ids.length > 0){
			ids= ids.substring(0,ids.length-1);
		}
        $("#queryForm").ajaxSubmit({
        	url:'${path}/account/cashhandlerecord/count/'+ids+'.html',
        	type:'post',
        	dataType:'json',
        	success:function(result){
        		if(result.code == '0'){
        			$("#queryForm").attr("action","${path}/account/cashhandlerecord/MSExportExcel/"+ids+".html");
        	        $("#queryForm").submit();
    			}else{
    				layer.msg(result.message,1,5);
    			}
        	},
        	error:function(result){
        		layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
    			parentLayer.close(_load);
        	}
        });
        
	}
	
	function exportTxtForMS(){
		var ids = "";
		if(!$("input[name='cashId']").is(':checked')){
			alert("至少需要选择一条记录");
			return;
		}
		$("input[name='cashId']").each(function(){
			if($(this).is(':checked')){
				ids = ids + $(this).val()+",";
			}
		});
		
		if(ids.length > 0){
			ids= ids.substring(0,ids.length-1);
		}
		
		$("#queryForm").ajaxSubmit({
        	url:'${path}/account/cashhandlerecord/count/'+ids+'.html',
        	type:'post',
        	dataType:'json',
        	success:function(result){
        		if(result.code == '0'){
        			$("#queryForm").attr("action","${path}/account/cashhandlerecord/MSExportTxt/"+ids+".html");
        	        $("#queryForm").submit();
    			}else{
    				layer.msg(result.message,1,5);
    			}
        	},
        	error:function(result){
        		layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
    			parentLayer.close(_load);
        	}
        });
	}
	
	function exportExcelForWY(){
		var ids = "";
		if(!$("input[name='cashId']").is(':checked')){
			alert("至少需要选择一条记录");
			return;
		}
		$("input[name='cashId']").each(function(){
			if($(this).is(':checked')){
				ids = ids + $(this).val()+",";
			}
		});
		
		if(ids.length > 0){
			ids= ids.substring(0,ids.length-1);
		}
		$("#queryForm").ajaxSubmit({
        	url:'${path}/account/cashhandlerecord/count1/'+ids+'.html',
        	type:'post',
        	dataType:'json',
        	success:function(result){
        		if(result.code == '0'){
        			$("#queryForm").attr("action","${path}/account/cashhandlerecord/WYExportExcel/"+ids+".html");
        	        $("#queryForm").submit();
    			}else{
    				layer.msg(result.message,1,5);
    			}
        	},
        	error:function(result){
        		layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
    			parentLayer.close(_load);
        	}
        });
	}
	
	function exportTxtForWY(){
		var ids = "";
		if(!$("input[name='cashId']").is(':checked')){
			alert("至少需要选择一条记录");
			return;
		}
		$("input[name='cashId']").each(function(){
			if($(this).is(':checked')){
				ids = ids + $(this).val()+",";
			}
		});
		
		if(ids.length > 0){
			ids= ids.substring(0,ids.length-1);
		}
		$("#queryForm").ajaxSubmit({
        	url:'${path}/account/cashhandlerecord/count1/'+ids+'.html',
        	type:'post',
        	dataType:'json',
        	success:function(result){
        		if(result.code == '0'){
        			$("#queryForm").attr("action","${path}/account/cashhandlerecord/WYExportTxt/"+ids+".html");
        	        $("#queryForm").submit();
    			}else{
    				layer.msg(result.message,1,5);
    			}
        	},
        	error:function(result){
        		layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
    			parentLayer.close(_load);
        	}
        });
	}
	
	function selectAll(){
		var checkStatus = $('#checkStatus').val();
		var code_Values = document.getElementsByTagName("input"); 
		for(i = 0;i < code_Values.length;i++){
		if(code_Values[i].type == "checkbox"){
			if(checkStatus == 0){
				code_Values[i].checked = true;
				$('#cashTip').html("取消全选");
				$("#checkStatus").val(1);
			}else{
				code_Values[i].checked = false;
				$('#cashTip').html("全选");
				$("#checkStatus").val(0);
			}
		 }
		}
	}
	
	function showpaycash(id){
		if(!confirm("是否立即打款?")){
			return;
		}
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/cashhandlerecord/paycash/'+id+'.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				alert(result.message);
				window.location.href = window.location.href;
			},
			error : function(result) {
				alert(result.message);
			} 
		});
	}
	
	function showcancelcash(id){
		if(!confirm("是否取消提现?")){
			return;
		}
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/cashhandlerecord/cancelcash/'+id+'.html',
			type : 'post' ,
			dataType : 'json',
			success : function(result) {
				alert(result.message);
				window.location.href = window.location.href;
			},
			error : function(result) {
				alert(result.message);
			} 
		});
	}
	
	function showfailcashLayer(id){
		var _url = '${path}/account/cashhandlerecord/detailfailcash/'+id+'.html';
		$.layer({
			type : 2,
			title : '打款失败',
			area : [ '80%', '100%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
		
	}
</script>
</html>
