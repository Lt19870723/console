<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>债权转让-国诚金融后台管理系统</title>
</head>
<body>
<form id="transferForm" name="transferForm" method="post">
	<input type="hidden" id="id" name="id" value="${transfer.id}" />
	
	<table style="margin-left:10px;margin-top:5px;">
		<tr>
			<td>审核意见</td>
		</tr>
		<tr>
			<td >
				<textarea id="remark" name="remark" style="width:350px;height:200px;">${transfer.remark}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" id="pass" name="pass" onclick="save();" value="复审" />
			</td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">

function save() {
	$('#pass').attr("onclick", "");

	if ($.trim($("#remark").val()) == "") {
		layer.msg('请输入审核意见。', 1, 5);
		$("#remark").focus();
		$('#pass').attr("onclick", "save();");
		return;
	}
	
	var _load = layer.load('处理中..');
	$("#transferForm").ajaxSubmit({
		url : '${path}/transfer/transfercheck/save.html',
		type : 'post',
		success : function(result) {
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					window.parent.location.href = window.parent.location.href;
				});
			} else {
				layer.msg(result.message, 1, 5);
				$('#pass').attr("onclick", "save();");
			}
		},
		error : function(result) {
			$('#pass').attr("onclick", "save();");
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
	    }
	});
}
</script>
</html>