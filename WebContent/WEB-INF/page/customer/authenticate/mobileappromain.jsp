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
			     &nbsp;用户名： 
				<input type="text" id="username" name="username"  value="${realNameApproAction.realNameApproCnd.username}"  class="input1" style="width: 180px;" />&nbsp;
				 &nbsp;手机号码：
				 <input type="text" id="number" name="number"  value="${realNameApproAction.realNameApproCnd.number}"  class="input1" style="width: 180px;" />&nbsp;
					 审核状态：
				<select class="bigselect"   id="auditType" value="${realNameApproAction.realNameApproCnd.auditType}" >
							<option label="待审核用户" value="0">待审核用户</option>
							<option label="--请选择审核状态--" value="99">-请选择审核状态--</option>
							<option label="已审核用户" value="1">已审核用户</option>
							<option label="审核不通过用户" value="-1">审核不通过用户</option>
	 
				</select>	
			 
			
			 <input id="searchrealNameApprs" name="searchrealNameApprs" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
			 
			  
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
				    <tr>
						<th>序号</th>
						<th>用户名</th>
						<th>手机号码</th>
						<th>数据类型</th>
						<th>用户类型</th>
						<th>审核操作</th>
					</tr>
		</thead>
	</table>
	
	
		<div id="approveRealNamePopup" width="500" height="300" style="width:98%;height:95%;padding:0px;  display: none  "  >
			 <input  type="hidden" id="realNameApproVoId" name="realNameApproVoId" value="${realNameApproAction.realNameApproVo.id}" />
		     <input  type="hidden"  id="realNameApproVoVersion" name="realNameApproVoVersion" value="${realNameApproAction.realNameApproVo.version}" />
					
			<table>
				<tr>
					<td width="100px">审核备注：</td>
					<td colspan="3" width="350px">
						<textarea id="approReason"  style="width:350px;height:150px;" ></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input id="btnApproPass" name="btnApproPass" value="审核通过" type="button" onclick="submitCheck('pass');" />&nbsp;
						<input id="btnApproReject" name="btnApproReject" value="审核不通过" type="button" onclick="submitCheck('reject');" />&nbsp;
				    </td>
				</tr>
			</table>
      </div>	
	
	
<div id="checkBorrowPopup"   style="width:98%;height:50%;padding:0px;display: none"  >
	  <input  type="hidden"  name="borrowId" id="borrowId" value="${realNameApproAction.userId}"/> 
	<table style="margin-left:10px;margin-top:5px;">
	 
	<tr><td>
 		<textarea id="remark" style="width:350px;height:80px;">  
 		</textarea>
	</td>
	</tr>
	<tr>
		<td colspan="2">
		   <input id="pass" name="pass" value="提交" type="button" onclick="updateMobileCheck();" />&nbsp;
		</td>
	</tr>	
	</table>

</div>
	 
	
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
});

function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/authenticte/mobileAppro/list/' + pageNo + '.html',
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




function submitCheck(userId,result1){
	if(validateApprInfo(result1)){
		var  _load = parentLayer.load('处理中..');
	  	$.ajax({
			url : '${path}/authenticte/mobileAppro/approve.html',
			data : {
				'userId' : userId,
				'check':result1
			},
			type : 'post',
			success : function(result) {
				parentLayer.close(_load);
				 if (result.code=="1") {
					 pageGo(1);
				}else{
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
}





function beforeCheckFirstBorrow(){
	var creditRating = $('[id="remark"]').val();
	if(creditRating==null || creditRating==""){
		layer.msg("请输入手机号码。", 1, 5);
		return false;
	}
	if($.trim(creditRating).length!=11){
		layer.msg("手机号码必须为11位", 1, 5);
		return false;
	}
    return true;
}


function forCheckBorrow(borrowId){
  	$('[id="borrowId"]').val(borrowId); 
	$('[id="remark"]').val(""); 
	
	 $.layer({
		type: 1,   //0-4的选择,
		title:  ["修改手机" , true],
		closeBtn: [0 , true],
		area: ["500","300"],
		offset : [ '100px', '' ],
		page: {
			dom : '#checkBorrowPopup'
		}
	});
	 
}

function  updateMobileCheck(){
	if (beforeCheckFirstBorrow()) {
		var  _load = parentLayer.load('处理中..');
		$.ajax({
			url : '${path}/authenticte/mobileAppro/updateMobileCheck.html',
			data : {
				'userId' : $.trim($('[id="borrowId"]').val()),
				'number' : $.trim($('[id="remark"]').val()) 
			},
			type : 'post',
			success : function(result) {
				parentLayer.close(_load);
				 if (result.code=="1") {
					//pageGo(1);
					//$("#checkBorrowPopup").hide();
					layer.msg(result.message, 1, 1, function() {
						window.location.href = window.location.href;
					});
				}else{
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}   
	
	 
}
 
 
/**
 * 验证审核的备注内容
 */
function validateApprInfo(approInfo){
	var alertInfo = "确定要审核通过吗?";
	if(approInfo=="reject"){
		alertInfo = "确定要审核不通过吗?";
	}
	if(!confirm(alertInfo)){
		return false;
	}
	return true;
}
	
  

 
  
  
</script>
</html>