<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融 — 借款用户密码管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="memberForm" action="" method="post">
		<div id="customerCard">
			<div style="margin-left: 20px; line-height: 50px;">
				&nbsp;&nbsp;&nbsp;&nbsp;<a style="font-size:20px;color:red" >*重置所有借款用户登录密码</a>
				<br/>
				&nbsp;&nbsp;&nbsp;&nbsp;请输入新密码：
				<input type="password"  name="newPassword" id="newPassword" class="input1"
					value="${newPassword}" />
				<br /> &nbsp;&nbsp;&nbsp;&nbsp;再次输入密码：
				<input type="password" name="againPassword" id="againPassword" class="input1"
					value="${againPassword}" />
				<br/>	
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<div>
				<br />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:updatePassword();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="更改密码" /> <br />
				<br />
			</div>
			</div>

		</div>
	</form>
</body>
<script type="text/javascript">
/**
 * 校验密码
 */
function validateInputWord(){
	
	var newPassword = $('#newPassword').val();
	 if(null==newPassword || $.trim(newPassword)==""){
		alert("密码不能为空");
		return false;
	}
	var againPassword = $('#againPassword').val();
	if(null==againPassword || $.trim(againPassword)==""){
		alert("请再次输入密码");
		return false;
	}
	if(newPassword !=againPassword ){
		alert("两次密码输入不一致！");
		return false;
	}
	if (confirm("确认要修改？")) {
		return true;
    }else{
    	return false;
    }
	
}

/**
 * 重置登录密码
 */
function updatePassword(){
	if(!validateInputWord()){
		return;
	}
	var _load = parentLayer.load('处理中..');
	$("#memberForm").ajaxSubmit ({
		url : '${path}/customer/managerPassword/updateBorrowsPWD.html',
		data:{
			newPassword:$('#newPassword').val(),
			againPassword:$('#againPassword').val()
			},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				document.forms[0].reset();
			}
			parentLayer.close(_load);
			layer.msg(result.message,1,1);
		},
		error : function(data) {
			parentLayer.close(_load);
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
	 
}	
</script>
</html>
