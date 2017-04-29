<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—充值管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%;">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
			充值订单号：<input id="tradeNo" class="input1" name="tradeNo"
				value="${rechargeRecordCnd.tradeNo}" />
			&nbsp;
			充值日期：
				<input
					name="beginTime" id="beginTime" onclick="WdatePicker()"
					styleClass="Wdate" value="${rechargeRecordCnd.beginTime }">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
					 &nbsp;~&nbsp; 
				<input name="endTime" id="endTime"
					onclick="WdatePicker()" styleClass="Wdate"
					value="${rechargeRecordCnd.endTime }">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
			<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			用户名：<input id="username" class="input1" name="username"
				value="${rechargeRecordCnd.username}" />
			&nbsp;
			充值类型 ：
				 <select id="type" name="type" value="${rechargeRecordCnd.type }"
					class="bigselect" style="width: 130px;">
					<option value="">--请选择--</option>
					<option value="1">在线充值</option>
					<option value="2">线下转账</option>
					
				</select>
				 &nbsp;
				充值状态 ：
				<select id="status" name="status"
					value="${rechargeRecordCnd.status}"
					class="bigselect" style="width:130px;">
					<option value="">--请选择--</option>
					<option value="0">充值审核中</option>
					<option value="1">充值成功</option>
					<option value="9">充值失败</option>
					<option value="2">在线充值待付款</option>
					<option value="3">初审成功</option>
				</select>
				 &nbsp;
				 <br/>
				 
				 支付平台 ：
				 <select id="onlinetype" name="onlinetype"
					value="${rechargeRecordCnd.onlinetype}"
					class="bigselect" style="width:130px;">
				 	<option value="">--请选择--</option>
				 	<option value="1">网银在线</option>
				 	<option value="2">国付宝</option>
				 	<option value="3">盛付通</option>
				 	<option value="4">新浪支付</option>
				 	<option value="5">连连支付</option>
				 	<option value="6">富友支付</option>
                     <option value="8">浙商存管支付</option>
				 </select>
				 &nbsp;
				  平台来源 ：
				  <select id="platFrom" name="platFrom"
					value="${rechargeRecordCnd.platFrom}"
					class="bigselect" style="width:130px;">
				 	<option value="">--请选择--</option>
				 	<option value="1">网页</option>
				 	<option value="2">微信</option>
				 	<option value="3">安卓</option>
				 	<option value="4">IOS</option>
				 </select>
				 &nbsp;
				  用户类型 ：
				  <select id="userType" name="userType"
					value="${rechargeRecordCnd.userType}"
					class="bigselect" style="width:130px;">
				  		<option value="">--请选择--</option>
				  		<option value="1">理财用户</option>
				  		<option value="0">借款用户</option>
				  </select>
                    开立存管：
                <select id="isCustody" name="isCustody" value="${rechargeRecordCnd.isCustody}"
                     class="bigselect" style="width: 100px;">
                    <option value="-1">--请选择--</option>
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button"
					onclick="javascript:exportCallBackData();" type="button" name="Submit1"
					id="export" class="b_buts" value="导出EXCEL" />
			</div>
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

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
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/rechargerecords/list/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#loginLogList").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
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
			url : '${path}/account/rechargerecords/count.html',
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
		$("#queryForm").attr("action","${path}/account/rechargerecords/export.html");
        $("#queryForm").submit();
	}
	/**
	 *校验导出数据的过滤条件
	 */
	function validateExportCallbackData(){
		var startTime = $('#beginTime').val();
		if(null==startTime || $.trim(startTime)==""){
			alert("充值日期开始时间不能为空。");
			return false;
		}
		var endTime = $('#endTime').val();
		if(null==endTime || $.trim(endTime)==""){
			alert("充值日期截止日期不能为空。");
			return false;
		}
		
		//比较日期，必须是30天之内（含）
		startTime = startTime.replace(/-/g, "/"); 
		endTime = endTime.replace(/-/g, "/"); 
		startTime = new Date(startTime); 
		endTime = new Date(endTime);
		
		var days= endTime.getTime() - startTime.getTime(); 
		var time = parseInt(days / (1000 * 60 * 60 * 24));
		if(isNaN(time) || (time < 0) || time>30){
			alert("日期段天数须在30天以内！");
			return false;
		}	
		
		if(!confirm("确认要导出Excel吗？")){
			return false;
		}
		return true;
	}
</script>
</html>
