<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色管理-国诚金融后台管理系统</title>
</head>
<body>
<form id="roleForm" name="roleForm" method="post">
	<input type="hidden" id="id" name="id" value="${role.id}" />
	
	<div class="listzent">角色信息(* 必填)</div>
	<table style="margin-left: 10px; margin-top: 5px;">
		<tr>
			<td align="right" width="25%">*角色名称：</td>
			<td><input type="text" id="name" name="name" class="input1" value="${role.name}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">资源：</td>
			<td>
				<table>
					<c:forEach var="permission" items="${permissions}">
					<tr><td><input type="checkbox" id="permissionIds" name="permissionIds" value="${permission.id}" ${(fn:contains(','.concat(role.permissionIds).concat(','), ','.concat(permission.id.toString()).concat(','))) ? 'checked="checked"' : ''}/>${permission.name}</td></tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" width="25%">描述：</td>
			<td><textarea id="desc" name="desc" style="width:350px;height:80px;">${role.desc}</textarea></td>
		</tr>
		<tr></tr>
		<tr>
			<td align="center" colspan="2"><input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">
$(function() {
});

function save() {
	$('#saveBtn').attr("onclick", "");
	
	if ($.trim($("#name").val()) == "") {
		layer.msg('请输入角色名', 1, 5);
		$("#name").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var permissionIds = new Array();
	$("#permissionIds:checked").each(function(index) {
		permissionIds[index] = $(this).val();
	});
	
	if (permissionIds.length == 0) {
		layer.msg('请选择权限', 1, 5);
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/system/role/save.html',
		data : {
			'id' : $("#id").val(),
			'name' : $.trim($("#name").val()),
			'permissionIds' : permissionIds,
			'desc' : $("#desc").val()
		},
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