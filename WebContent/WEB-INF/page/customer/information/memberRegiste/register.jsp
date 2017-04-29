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
	<form id="form" action="" method="post" style="background: #fff;">
	  
		<table style="width:400px;  margin:20 auto;  position:relative;  float:none;  padding:0;  text-align:left;"  >
			 
			<tr>
				<td width="100px">用户名：<font color="red">*</font></td>
				<td><input type="text" name="username" id="username" maxlength="12" size="20"/>
				</td>		
			</tr>
			<tr>
				<td width="100px">登录密码：<font color="red">*</font></td>
				<td>
				<input type="password" id="logpassword" name="logpassword" maxlength="12" size="21" />
				</td>
			</tr>
			<tr>
				<td width="100px">确认密码：<font color="red">*</font></td>
				<td><input type="password" id="password2" name="password2" maxlength="12" size="21"/>
				</td>	
			</tr>
			<tr>
				<td width="100px">真实姓名：<font color="red">*</font></td>
				<td><input type="text"  name="realName" id="realName" maxlength="12" size="20"/>
				</td>		
			</tr>
			<tr>
				<td width="100px">身份证号码：<font color="red">*</font></td>
				<td><input type="text" name="idcard" id="idcard" maxlength="18" size="20"/>
				</td>		
			</tr>
			<tr>
				<td width="100px">手机号：<font color="red">*</font></td>
				<td><input type="text"  name="mobileNum" id="mobileNum" maxlength="12" size="20"/>
				</td>		
			</tr>
			<tr>
				<td width="100px">邮箱：<font color="red">*</font></td>
				<td><input type="text"  name="email" id="email" maxlength="50" size="20"/>
				</td>		
			</tr>
			<tr>
				 
				<td colspan="2"> 
						 <input type="button" onclick="javascript:save();" value="保存" />
						 &nbsp; 
						 <input type="button" onclick="javascript:cancle();" value="关闭"/>
				</td>
			</tr>
			 
		</table>
	</form>
</body>
<script type="text/javascript">
 
var _load;

function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
function save(){
	if(!validateAddInfo()){
		return false;
	} 
	$("#form").ajaxSubmit ({
		url : '${path}/information/memberRegiste/save.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '1'){
				parentLayer.close(_load);
				layer.msg(result.message,1,1,function(){
					var username=$('#username').val();
					window.parent.location='${path}/information/memberRegiste/main.html?username='+username;
		    	});
			}else{
				layer.msg(result.message,1,5,function(){
					parentLayer.close(_load);
		    	});
			}
			
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			parentLayer.close(_load);
		}
	}); 
}
/**
* 验证表单数据
*/
function validateAddInfo(){
	var username = $.trim($('#username').val());
	if(username == null || username == ''){
		parent.layer.msg('请输入用户名！',2, 5);
		$('#username').focus();
		return false;
	}
	if(username.length < 2){
		parent.layer.msg('用户名长度必须大于等于2位！',2, 5);
		$('#username').focus();
		return false;
	}
	if(username.length > 16){
		parent.layer.msg('用户名长度不能大于16个字符！',2, 5);
		$('#username').focus();
		return false;
	}
	
	var logpassword = $.trim($('#logpassword').val());
	if(logpassword == null || logpassword == ''){
		parent.layer.msg('请输入登陆密码！',2, 5);
		$('#logpassword').focus();
		return false;
	}
	if(logpassword.length < 6){
		parent.layer.msg('登陆密码长度必须大于等于6个字符！',2, 5);
		$('#logpassword').focus();
		return false;
	}
	if(logpassword.length > 12){
		parent.layer.msg('登陆密码长度不能大于12个字符！',2, 5);
		$('#logpassword').focus();
		return false;
	}
	
	var password2 = $.trim($('#password2').val());
	if(password2!=logpassword){
		parent.layer.msg('2次登录密码输入不一致',2, 5);
		return false;
	}
	var realName = $.trim($('#realName').val());
	if(realName == null || realName == ''){
		parent.layer.msg('请输入真实姓名！',2, 5);
		$('#realName').focus();
		return false;
	}
	var idcard = $.trim($('#idcard').val());
	if(idcard == null || idcard == ''){
		parent.layer.msg('请输入身份证号码！',2, 5);
		$('#idcard').focus();
		return false;
	}
	var mobileNum = $.trim($('#mobileNum').val());
	if(mobileNum == null || mobileNum == ''){
		parent.layer.msg('请输入手机号！',2, 5);
		$('#mobileNum').focus();
		return false;
	}
	if(mobileNum.length != 11){
		parent.layer.msg('手机号必须为11位！',2, 5);
		$('#mobileNum').focus();
		return false;
	}
	var email = $.trim($('#email').val());
	if(email == null || email == ''){
		parent.layer.msg('请输入邮箱！',2, 5);
		$('#email').focus();
		return false;
	}
 
	return true;		
}
</script>
</html>
