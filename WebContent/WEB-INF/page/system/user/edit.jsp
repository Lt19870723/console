<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户管理-国诚金融后台管理系统</title>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>
<body>
<form id="userForm" name="userForm" method="post">
	<input type="hidden" id="id" name="id" value="${user.id}" />
	
	<div class="listzent">用户信息(* 必填)</div>
	<table style="margin-left: 10px; margin-right: 10px; margin-top: 5px;">
		<tr>
			<td align="right" width="25%">*用户名：</td>
			<td><input type="text" id="userName" name="userName" class="input1" value="${user.userName}" /></td>
			<td align="right" width="25%">*员工姓名：</td>
			<td><input type="text" id="name" name="name" class="input1" value="${user.name}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">*部门：</td>
			<td><input type="text" id="dept" name="dept" class="input1" value="${user.dept}" /></td>
			<td align="right" width="25%">*职位：</td>
			<td><input type="text" id="position" name="position" class="input1" value="${user.position}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">邮箱：</td>
			<td><input type="text" id="email" name="email" class="input1" value="${user.email}" /></td>
			<td align="right" width="25%">手机：</td>
			<td><input type="text" id="mobile" name="mobile" class="input1" value="${user.mobile}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">座机：</td>
			<td colspan="3"><input type="text" id="tel" name="tel" class="input1" value="${user.tel}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">*角色：</td>
			<td colspan="3">
				<select id="roleId" name="roleId">
					<option value="">请选择</option>
					<c:forEach var="role" items="${roles}">
					<option value="${role.id}" ${user.roleId == role.id ? 'selected="selected"' : ''}>${role.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr></tr>
		<tr>
			<td align="center" colspan="4"><input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">
$(function() {
});

function save() {
	$('#saveBtn').attr("onclick", "");

	if ($.trim($("#userName").val()) == "") {
		layer.msg('请输入用户名', 1, 5);
		$("#userName").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	if ($.trim($("#name").val()) == "") {
		layer.msg('请输入员工姓名', 1, 5);
		$("#name").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	if ($.trim($("#dept").val()) == "") {
		layer.msg('请输入部门', 1, 5);
		$("#dept").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	if ($.trim($("#position").val()) == "") {
		layer.msg('请输入职位', 1, 5);
		$("#position").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	if ($("#roleId").val() == "") {
		layer.msg('请选择角色', 1, 5);
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var _load = layer.load('处理中..');
	$("#userForm").ajaxSubmit({
		url : '${path}/system/user/save.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					window.parent.location.href = window.parent.location.href;
				});
			} else {
				layer.msg(result.message, 1, 5);
				$('#saveBtn').attr("onclick", "save();");
			}
		},
		error : function(result) {
			$('#saveBtn').attr("onclick", "save();");
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
	    }
	});
}
</script>
</html>