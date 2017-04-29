<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>菜单管理-国诚金融后台管理系统</title>
</head>
<body>
	 <div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
			&nbsp;&nbsp;&nbsp;&nbsp;用户名：<input    id="userName" class="input1" value="#{realNameApproAction.memberCnd.username}" />
			审核状态：
			<select class="bigselect"
						value="#{realNameApproAction.memberCnd.status}">
						<option label="等待审核"   value="-2"></option>
						<option label="审核通过"   value="0"></option>
						<option label="审核不通过" value="-3"></option>
						<option label="所有数据"   value="1000"></option>
			</select>	
			 
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a4j:commandButton id="batchapproveborrowMember" name="searchrealNameApprs" value="批量审核借款用户"
				onclick="return validatePayed();"
				action="#{realNameApproAction.getRemark}"
				execute="@form"
				data=""
				oncomplete="" 
				render="">
				<rich:componentControl target="batchapproveRealNamePopup" operation="show" />
			</a4j:commandButton>
			
			<h:inputHidden name="pageNo" id="pageNo" value="0"></h:inputHidden>
			<h:inputHidden  name="checkStatus" id="checkStatus" value="0"></h:inputHidden>
			</div>
	       <input id="searchrealNameApprs" name="searchrealNameApprs" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
	       <input id="batchapproveborrowMember" name="searchrealNameApprs" value="批量审核借款用户" type="button" onclick="batchPayed();" />&nbsp;
	 </div>
	
	
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
					<th>
					 <a href="javascript:selectAll();"><h:outputText id="cashTip" value="全选" style="color:red;"></h:outputText></a>
						</th>
						<th>序号</th>
						<th>用户名</th>
						<th>邮箱</th>
						<th>手机</th>
						<th>注册时间</th>
						<th>用户类型</th>
						<th>审核时间</th>
						<th>审核人</th>
						<th>审核操作</th>
					</tr>
		</thead>
	</table>
	
	 
<!-- 审核窗口 -->
	 
		<div id="approveRealNamePopup"  style="width:98%;height:95%;padding:0px;display: none"   >
			 
			<table>
			<tr>
				<td width="100px">审核备注：</td>
				<td colspan="3" width="350px">
					<h:inputTextarea id="reason"  name = "reason" value="#{realNameApproAction.memberAuditVo.reason}" style="width:350px;height:150px;"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<a4j:commandButton id="btnApproPass" name="btnApproPass" value="审核通过"
						action="#{realNameApproAction.borrowMemberAuditPass}"
						execute="@form"
						data="#{realNameApproAction.resultMsg}"
						oncomplete="afterAppprove(event.data,'pass')"
						onclick="return validateApprInfo('pass')"
						>
					</a4j:commandButton>
					&nbsp;
					<a4j:commandButton id="btnApproReject" name="btnApproReject" value="审核不通过"
						action="#{realNameApproAction.borrowMemberAuditUnPass}"
						execute="@form"
						data="#{realNameApproAction.resultMsg}"
						oncomplete="afterAppprove(event.data,'reject')"
						onclick="return validateApprInfo('reject')"
						>
					</a4j:commandButton>					
					<h:inputHidden id="realNameApproVoId" name="realNameApproVoId" value="#{realNameApproAction.ids}"></h:inputHidden>
				</td>
			</tr>
			 
			</table>
		 </div>
		
				<!-- 批量审核处理窗口 -->
		 
		<div id="batchapproveRealNamePopup" width="500" height="300" style="width:98%;height:95%;padding:0px;display:none"  >
			 
			 
			<table>
					<tr>
						<td width="100px">审核备注：</td>
						<td colspan="3" width="350px">
							<textarea id="reason"  name = "reason"  style="width:350px;height:150px;"  >#{realNameApproAction.memberAuditVo.reason}</textarea>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="3">
						 
						 
						 <input  id="btnApproPass"   type="button"  value="审核通过"    >
						 <input  id="btnApproReject"   type="button"  value="审核不通过" >
						 
						 
						 
							<a4j:commandButton id="btnApproPass" name="btnApproPass" value=""
								action="#{realNameApproAction.borrowMemberAuditPass}"
								execute="@form"
								data="#{realNameApproAction.resultMsg}"
								oncomplete="afterbatchAppprove(event.data,'pass')"
								onclick="return validatebatchApprInfo('pass')"
								>
							</a4j:commandButton>
							&nbsp;
							<a4j:commandButton id="btnApproReject" name="btnApproReject" value="审核不通过"
								action="#{realNameApproAction.borrowMemberAuditUnPass}"
								execute="@form"
								data="#{realNameApproAction.resultMsg}"
								oncomplete="afterbatchAppprove(event.data,'reject')"
								onclick="return validatebatchApprInfo('reject')"
								>
							</a4j:commandButton>
							<h:inputHidden name="id" id="id" value="#{realNameApproAction.ids}"></h:inputHidden>	
						</td>
					</tr>
					<tr></tr>
					</table>
			</div>
	 
	
	
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
});


function   batchPayed(){
	
	if (validatePayed()) {
		var _load = parentLayer.load('处理中..');
		$.ajax({
			url : '${path}/authenticte/emailAppro/list/' + pageNo + '.html',
			data : {
				'username' : $.trim($('#username').val()),
				'number' : $.trim($('#number').val()),
				'auditType' : $.trim($('#auditType').val())
			},
			type : 'post',
			dataType : 'html',
			success : function(result) {
				parentLayer.close(_load);
				
				
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	
}

function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/authenticte/emailAppro/list/' + pageNo + '.html',
		data : {
			'username' : $.trim($('#username').val()),
			'number' : $.trim($('#number').val()),
			'auditType' : $.trim($('#auditType').val())
		},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			$('#dataTable tbody').remove();
			$('#dataTable').append(result);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function forApproveRealName(id){
	//清空备注内容
	$('[id="approveform:reason"]').val("");
	$('[id="approveform:realNameApproVoId"]').val(id);
}
/**
 * 进入批量审核页面
 */
function forbatchApproveRealName(){
	//清空备注内容
	$('[id="batchapproveform:reason"]').val("");
}
/**
 * 验证审核的备注内容
 */
function validateApprInfo(approInfo){
	var remark = $('[id="approveform:reason"]').val();
	if(remark==null || $.trim(remark)==""){
		alert("请填写备注信息！");
		return false;
	}
	var alertInfo = "确定要审核通过吗?";
	if(approInfo=="reject"){
		alertInfo = "确定要审核不通过吗?";
	}
	if(!confirm(alertInfo)){
		return false;
	}
	return true;
}
/**
 * 验证批量审核的备注内容
 */
function validatebatchApprInfo(approInfo){
	var remark = $('[id="batchapproveform:reason"]').val();
	if(remark==null || $.trim(remark)==""){
		alert("请填写备注信息！");
		return false;
	}
	var alertInfo = "确定要审核通过吗?";
	if(approInfo=="reject"){
		alertInfo = "确定要审核不通过吗?";
	}
	if(!confirm(alertInfo)){
		return false;
	}
	return true;
}
/**
 * 审核成功或失败后执行的操作
 */
function afterAppprove(data,approInfo){
	layer.close(_load);
	if(data!="success"){
		alert(data);
		return;
	}else{
		if(approInfo=="reject"){
			alert("借款用户审核成功！");
		}else{
			alert("借款用户审核成功！");
		}
	}
	$('[id="realNameApprform:searchrealNameApprs"]').trigger("click");
	#{rich:component('approveRealNamePopup')}.hide();
}
/**
 * 批量审核成功或失败后执行的操作
 */
function afterbatchAppprove(data,approInfo){
	layer.close(_load);
	if(data!="success"){
		alert(data);
		return;
	}else{
		if(approInfo=="reject"){
			alert("借款用户审核成功！");
		}else{
			alert("借款用户审核成功！");
		}
	}
	$('[id="realNameApprform:searchrealNameApprs"]').trigger("click");
	#{rich:component('batchapproveRealNamePopup')}.hide();
}
//全选
function selectAll(){
	var checkStatus = $("[id='realNameApprform:checkStatus']").val();
	if(checkStatus == 0){
		$("input[name='cashId']").attr("checked",true);
		$("[id='realNameApprform:checkStatus']").val(1);
		$("[id='realNameApprform:cashTip']").html("取消全选");
	}else{
		$("input[name='cashId']").attr("checked",false);
		$("[id='realNameApprform:checkStatus']").val(0)
		$("[id='realNameApprform:cashTip']").html("全选");
	}
}
function validatePayed(){
	var ids = $('[id="batchapproveform:id"]').val();
	ids = "";
	if(!$("input[name='cashId']").is(':checked')){
		alert("至少需要选择一条记录");
		return false;
	}
	$("input[name='cashId']").each(function(){
		if($(this).is(':checked')){
			ids = ids + $(this).val()+",";
		}
	});
	if(ids.length > 0){
		ids= ids.substring(0,ids.length-1);
	}
	$('[id="batchapproveform:id"]').val(ids);
	if(!confirm("确认批量进行审核吗？")){
		return false;
	}
	forbatchApproveRealName();
	return true;
}
  
</script>
</html>