<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—修改密码</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<input type="hidden" name="username" value="${username }"/>
		<table  width="100%"  heigth="100%" border="1" align="center">
				 <tr  align="center">
						<td  colspan="2">
						 用户名 ：<font color="red"><label id="usernameResetPass">${username }</label></font>
						</td>
					</tr>
					
					<tr  align="center">
					     <td> ${passwordType == 'login'?'新登录密码：':'新交易密码：' }</td>
						<td>
						  <input type="password"  id="${passwordType== 'login'? 'newLoginPassWord':'newDealPassWord' }" 
						  name="${passwordType== 'login'? 'newLoginPassWord':'newDealPassWord' }"  class="yhm" style="color:#000;"  "></input>
						</td>
						
					</tr>
					
					<tr  align="center">
					 <td>${passwordType=='login'?'确认新登录密码：':'确认新交易密码：' }</td>
					    <td>
					       <input type="password"  id="${passwordType=='login'?'newSureLoginPassWord':'newSureDealPassWord'}"
					       name="${passwordType=='login'?'newSureLoginPassWord':'newSureDealPassWord'}" class="yhm" style="color:#000;"  ></input>
						</td>
					</tr>
					
					 <tr  align="center">
						 <td>
						 	  <a style="cursor: pointer;" onclick="javascript:resetPassword();">提交</a>
						 </td>
					     <td>
						      <a style="cursor: pointer;" onclick="javascript:cancel();">取消</a>
						</td>
					</tr>
				</table>
	</form>
</body>
<script type="text/javascript">

function resetPassword() {
	var _url = '${path}/customer/managerPassword/';
	if('${passwordType}' == 'login'){
		_url =_url + 'resetLoginPwd.html';
		if(!validateInputLoginPassword('newLoginPassWord','newSureLoginPassWord')){
			return;
		}
	}else {
		_url =_url + 'resetDealPwd.html';
		if(!validateInputLoginPassword('newDealPassWord','newSureDealPassWord')){
			return;
		}
	}
	
	$("#approForm").ajaxSubmit ({
		url : _url,
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
			}else{
				layer.msg(result.message,1,5);
			}
			
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
/**
 * 取消按钮事件
 */
function cancel(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
/**
 * 校验密码
 */
function validateInputLoginPassword(newPasswordId,againPasswordId){
	
	var newPassword = $('#'+newPasswordId).val();
	 if(null==newPassword || $.trim(newPassword)==""){
		alert("密码不能为空");
		return false;
	}
	var againPassword = $('#'+againPasswordId).val();
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
</script>
</html>
