<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权限管理-国诚金融后台管理系统</title>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ztree.jsp"%>
</head>
<body>
<form id="permissionForm" name="permissionForm" method="post">
	<input type="hidden" id="id" name="id" value="${permission.id}" />
	
	<div class="listzent">权限信息(* 必填)</div>
	<table style="margin-left: 10px; margin-top: 5px;">
		<tr>
			<td align="right" width="25%">*权限名称：</td>
			<td><input type="text" id="name" name="name" class="input1" value="${permission.name}" /></td>
		</tr>
		<tr>
			<td align="right" width="25%">资源：</td>
			<td>
				<div id="treeDemo" class="ztree" style="width:290px;height:240px;"></div>
			</td>
		</tr>
		<tr>
			<td align="right" width="25%">描述：</td>
			<td><textarea id="desc" name="desc" style="width:300px;height:80px;">${permission.desc}</textarea></td>
		</tr>
		<tr></tr>
		<tr>
			<td align="center" colspan="2"><input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" /></td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">
function createTree() {
	var setting = {
		view: {
			selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

	var zNodes =[
		<c:forEach var="menu1" items="${menuTree['0']}">
		{ id:${menu1.id}, pId:0, name:"${menu1.name}"${(fn:contains(','.concat(permission.resourcesIds).concat(','), ','.concat(menu1.id.toString()).concat(','))) ? ', checked:true' : ''}},
			<c:forEach var="menu2" items="${menuTree[menu1.id.toString()]}">
			{ id:${menu2.id}, pId:${menu2.pid}, name:"${menu2.name}"${(fn:contains(','.concat(permission.resourcesIds).concat(','), ','.concat(menu2.id.toString()).concat(','))) ? ', checked:true' : ''}},
				<c:forEach var="menu3" items="${menuTree[menu2.id.toString()]}">
				{ id:${menu3.id}, pId:${menu3.pid}, name:"${menu3.name}"${(fn:contains(','.concat(permission.resourcesIds).concat(','), ','.concat(menu3.id.toString()).concat(','))) ? ', checked:true' : ''}},
				</c:forEach>
			</c:forEach>
		</c:forEach>
	];
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}

$(function() {
	createTree();
});

function save() {
	$('#saveBtn').attr("onclick", "");
	
	if ($.trim($("#name").val()) == "") {
		layer.msg('请输入权限名', 1, 5);
		$("#name").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var resourcesIds = new Array();
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	for (var i = 0; i < nodes.length; i++) {
		resourcesIds[i] = nodes[i].id;
	}
	if (resourcesIds.length == 0) {
		layer.msg('请选择资源', 1, 5);
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/system/permission/save.html',
		data : {
			'id' : $("#id").val(),
			'name' : $.trim($("#name").val()),
			'resourcesIds' : resourcesIds,
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