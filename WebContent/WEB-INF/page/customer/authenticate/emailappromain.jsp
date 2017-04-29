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
				<input type="text" id="username" name="username"    class="input1" style="width: 180px;" />&nbsp;
				 &nbsp;邮箱：
				 <input type="text" id="number" name="number"    class="input1" style="width: 180px;" />&nbsp;
					 审核状态：
				<select class="bigselect"   id="auditType"   >
							<option label="待审核用户" value="0">待审核用户</option>
							<option label="--请选择审核状态--" value="99">--请选择审核状态--</option>
							<option label="已审核用户" value="1">已审核用户</option>
							<option label="审核不通过用户" value="-1">审核不通过用户</option>
	 
				</select>	
	       <input id="searchrealNameApprs" name="searchrealNameApprs" value="查询" type="button" onclick="pageGo(1);" />&nbsp;
	 </div>
	
	
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
					<tr>
						<th width="10%">序号</th>
						<th width="20%">用户名</th>
						<th>邮箱</th>
						<th width="15%">数据类型</th>
						<th width="10%">用户类型</th>
						<th width="15%">审核操作</th>
					</tr>
		</thead>
	</table>
	
	 
	 
<div id="checkBorrowPopup"  style="display: none"  >
	  <input  type="hidden"  name="borrowId" id="borrowId" value="">
	<table style="margin-left:10px;margin-top:5px;">
	 
	<tr><td>
 		<textarea id="remark" style="width:350px;height:80px;">  
 		</textarea>
	</td>
	</tr>
	<tr>
		<td colspan="2">
		   <input id="pass" name="pass" value="提交" type="button" onclick="updateEmailCheck();" />&nbsp;
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



function submitCheck(userId,result1){
	if(validateApprInfo(result1)){
		var  _load = parentLayer.load('处理中..');
	  	$.ajax({
			url : '${path}/authenticte/emailAppro/approve.html',
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



function forCheckBorrow(borrowId){
  	$('[id="borrowId"]').val(borrowId); 
	$('[id="remark"]').val(""); 
	
	 $.layer({
			type: 1,   //0-4的选择,
			title:  ["修改邮箱" , true],
			closeBtn: [0 , true],
			area: ["500","300"],
			offset : [ '100px', '' ],
			page: {
				dom : '#checkBorrowPopup'
			}
 });
	 
}



function beforeCheckFirstBorrow(){
	var creditRating = $('[id="remark"]').val();
	if(creditRating==null || creditRating==""){
		alert("请输入邮箱。");
		return false;
	}
	var patten_email = new RegExp(/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/);
	if(!patten_email.test(creditRating)){
		alert("请输入有效邮箱。");
		return false;
	}
	
	return  true;
 
}


function  updateEmailCheck(){
	if (beforeCheckFirstBorrow()) {
		var  _load = parentLayer.load('处理中..');
		$.ajax({
			url : '${path}/authenticte/emailAppro/updateEmailCheck.html',
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