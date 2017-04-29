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
	<form id="queryForm" action="" method="post"  >
	
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
			打款时间：<input
					name="beginTime2" id="payBeginTime" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			至<input
					name="endTime2" id="payEndTime" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			审核人：<input id="verifyName" name="verifyName" class="input1"/>
			&nbsp;	
			<br/>
			审核时间：<input
					name="verifyTimeBeginDate" id="verifyTimeBeginDate" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			至<input
					name="verifyTimeEndDate" id="verifyTimeEndDate" onclick="WdatePicker()"
					styleClass="Wdate">
				</input>
			&nbsp;	
			状态 ：
			 <select name="status"
				id="status"
				class="bigselect" style="width:130px;">
				<option value="">--请选择--</option>
				<option value="0">申请提现</option>
				<option value="1">审核通过</option>
				<option value="-1">审核失败</option>
				<option value="2">打款成功</option>
				<option value="3">取消提现</option>
				<option value="4">提现作废</option>
				<option value="5">打款失败</option>
			 </select>
			 <br/>
            开立存管：
                <select id="isCustody" name="isCustody" value="${rechargeRecordCnd.isCustody}"
                     class="bigselect" style="width: 100px;">
                    <option value="-1">--请选择--</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
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
		var urlPath = '${path}/account/cashrecord/list/' + pageNo + '.html';
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
			url : '${path}/account/cashrecord/count.html',
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
		$("#queryForm").attr("action","${path}/account/cashrecord/export.html");
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
