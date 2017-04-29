<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>修改密码-后台管理系统</title>
</head>
<body style="overflow: hidden;">
	<table style="background-color: #fff" width="100%">
		<tr>
			<td>旧&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
			<td><input type="password" id="oldPassword" name="oldPassword" /></td>
		</tr>
		<tr>
			<td>新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
			<td><input type="password" id="password1" name="password1" /></td>
		</tr>
		<tr>
			<td>确认密码：</td>
			<td><input type="password" id="password2" name="password2" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="saveBtn" name="saveBtn" onclick="onSavePwd();" value="保存" />
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
function onSavePwd() {
	$('#saveBtn').attr("onclick", "");
	if ($.trim($("#oldPassword").val()) == "") {
		parentLayer.msg('请输入旧密码', 1, 5);
		$("#oldPassword").focus();
		$('#saveBtn').attr("onclick", "onSavePwd();");
		return;
	}
	if ($.trim($("#password1").val()) == "") {
		parentLayer.msg('请输入新密码', 1, 5);
		$("#password1").focus();
		$('#saveBtn').attr("onclick", "onSavePwd();");
		return;
	}
	if ($.trim($("#password2").val()) == "") {
		parentLayer.msg('请输入确认密码', 1, 5);
		$("#password2").focus();
		$('#saveBtn').attr("onclick", "onSavePwd();");
		return;
	}	
	if ($.trim($("#password1").val()) != $.trim($("#password2").val())) {
		parentLayer.msg('新密码与确认密码不一致', 1, 5);
		$('#saveBtn').attr("onclick", "onSavePwd();");
		return;
	}
	
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/changePwd/save.html',
		type : 'post',
		data : {
			'oldPassword' : $.trim($("#oldPassword").val()),
			'password' : $.trim($("#password1").val())
		},
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if (result.code == '0') {
				parentLayer.msg(result.message, 1, 1, function() {window.parent.location.href = '${path}/';});
				var index = parent.layer.getFrameIndex(window.name); // 获取当前窗体索引
				parent.layer.close(index); // 执行关闭
			} else {
				$('#saveBtn').attr("onclick", "onSavePwd();");
				parentLayer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			$('#saveBtn').attr("onclick", "onSavePwd();");
			parentLayer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
</script>
</html>