<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>直通车转让-国诚金融后台管理系统</title>
</head>
<body>
<form id="transferForm" name="transferForm" method="post">
	<input type="hidden" id="id" name="id" value="${transfer.id}" />
	
	<table style="margin-left:10px;margin-top:5px;">
		<tr>
			<td>取消备注</td>
		</tr>
	
		<tr>
			<td >
				<textarea id="remark" name="remark" style="width:350px;height:230px;">${transfer.remark}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="确定" />
			 </td>
		</tr>	
	</table>
</form>
</body>
<script type="text/javascript">

function save() {
	$('#saveBtn').attr("onclick", "");
	if ($.trim($("#remark").val()) == "") {
		layer.msg('请输入取消备注', 1, 5);
		$("#remark").focus();
		$('#saveBtn').attr("onclick", "save();");
		return;
	}
	
	$('#saveBtn').attr("onclick", "save();");
	var _load = layer.load('处理中..');
	$("#transferForm").ajaxSubmit({
		url : '${path}/firsttransfer/transfering/save.html',
		type : 'post',
		success : function(result) {
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					window.parent.location.href = window.parent.location.href;
				});
			} else {
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
	    }
	});
}
</script>
</html>