<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>菜单管理-国诚金融后台管理系统</title>
</head>
<body>
<form id="menuForm" name="menuForm" method="post">
	<input type="hidden" id="id" name="id" value="${menu.id}" />
	<input type="hidden" id="pid" name="pid" value="${menu.pid}" />
	
	<div class="listzent">菜单信息(* 必填)</div>
	<table style="margin-left: 10px; margin-top: 5px;">
		<tr>
			<td align="right" width="25%">*菜单名称：</td>
			<td><input type="text" id="name" name="name" class="input1" value="${menu.name}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">*菜单层级：</td>
			<td>
				<c:if test="${not empty menu.id}">
				<input type="hidden" id="level" name="level" value="${menu.level}">
				<input type="text" class="input1" value='<c:if test="${menu.level == 1}">一级菜单</c:if><c:if test="${menu.level == 2}">二级菜单</c:if><c:if test="${menu.level == 3}">三级菜单</c:if>' readonly="readonly" />
				</c:if>
				<c:if test="${empty menu.id}">
				<select id="level" name="level" class="bigselect">
					<option value="1" ${menu.level == 1 ? 'selected="selected"' : ''}>一级菜单</option>
					<option value="2" ${menu.level == 2 ? 'selected="selected"' : ''}>二级菜单</option>
					<option value="3" ${menu.level == 3 ? 'selected="selected"' : ''}>三级菜单</option>
				</select>
				</c:if>
			</td>
		</tr>
		<tr id="parentMenuTr" style="display: none;">
			<td align="right" width="25%">*所属父级菜单：</td>
			<td>
				<select id="fristMenuId" name="fristMenuId" class="bigselect">
					<option value="">请选择一级菜单</option>
					<c:forEach var="firstMenu" items="${firstMenus}">
					<option value="${firstMenu.id}" ${menu.fristMenuId == firstMenu.id ? 'selected="selected"' : ''}>${firstMenu.name}</option>
					</c:forEach>
				</select>
				<select id="secondMenuId" name="secondMenuId" class="bigselect" style="display: none;">
					<option value="">请选择二级菜单</option>
					<c:forEach var="secondMenu" items="${secondMenus}">
					<option value="${secondMenu.id}" ${menu.secondMenuId == secondMenu.id ? 'selected="selected"' : ''}>${secondMenu.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="25%">URL：</td>
			<td><input type="text" id="url" name="url" class="input1" value="${menu.url}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">描述：</td>
			<td><textarea id="desc" name="desc" style="width:350px;height:80px;">${menu.desc}</textarea></td>
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
	if (${menu.level == 2}) {
		$("#parentMenuTr").show();
	} else if (${menu.level == 3}) {
		$("#parentMenuTr").show();
		$("#secondMenuId").show();
	}
});

$("#level").change(function() {
	var level = $(this).val();
	if (level == 1) {
		$("#parentMenuTr").hide();
	} else if (level == 2) {
		$("#parentMenuTr").show();
		$("#secondMenuId").hide();
		$('#fristMenuId').attr("onchange", "");
	} else if (level == 3) {
		$("#parentMenuTr").show();
		$("#secondMenuId").show();
		$('#fristMenuId').attr("onchange", "getChildren();");
		$("#fristMenuId").trigger("onchange");
	}
});

function getChildren() {
	$("#secondMenuId").empty();
	$("#secondMenuId").append('<option value="">请选择二级菜单</option>');
	
	var fristMenuId = $("#fristMenuId").val();
	if (fristMenuId == '') {
		return;
	}
	
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/system/menu/children/' + fristMenuId + '.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			$.each(result, function(idx, obj) {
				$("#secondMenuId").append('<option value="' + obj.id + '">' + obj.name + '</option>');
			});
			layer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function save() {
	$('#saveBtn').attr("onclick", "");
	if ($.trim($("#name").val()) == "") {
		layer.msg('请输入菜单名', 1, 5);
		$("#name").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var level = $("#level").val();
	if (level == 1) {
		$("#pid").val(0);
	} else if (level == 2) {
		$("#pid").val($("#fristMenuId").val());
	} else if (level == 3) {
		$("#pid").val($("#secondMenuId").val());
		
		if ($.trim($("#url").val()) == "") {
			layer.msg('请输入URL', 1, 5);
			$("#url").focus();
			$('#saveBtn').attr("onclick", "save();");
			return;
		}
	}
	
	if ($.trim($("#pid").val()) == "") {
		layer.msg('请选择父菜单', 1, 5);
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var _load = layer.load('处理中..');
	$("#menuForm").ajaxSubmit({
		url : '${path}/system/menu/save.html',
		type : 'post',
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