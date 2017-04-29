<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>直通车详情-国诚金融后台管理系统</title>
</head>
<body>
<form id="approveform" name="approveform" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<input type="hidden" id="userId" name="userId" value="${userId}" />
	<table style="margin-left: 10px; margin-top: 10px;">
		<tr>
			<td width="100px">审核备注：</td>
			<td>
				<textarea id="remark" name="remark" value="${remark}"  cols="35" rows="5" style="width:350px;"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="approvedPassBtn" name="approvedPassBtn" onclick="approvedPass();" value="审核通过" />
				&nbsp;
				<input type="button" id="approvedRejectBtn" name="approvedRejectBtn" onclick="approvedReject();" value="审核不通过" />
				&nbsp;
				<input type="button" id="closeBtn" name="closeBtn" onclick="closeMe();" value="关闭" />	
			</td>
		</tr>
	</table>
</form>
</body>
<script type="text/javascript">

	/**
	 * 验证审核的备注内容
	 */
	function validateApprInfo(){
		var remark = $('#remark').val();
		if(remark==null || $.trim(remark)==""){
			alert("请填写备注信息！");
			$("#remark").focus();
			return false;
		}
		
		return true;
	}
	
	function approvedPass() {
		$('#approvedPassBtn').attr("onclick", "");
		$('#approvedRejectBtn').attr("onclick", "");
		$('#closeBtn').attr("onclick", "");
		
		if (!validateApprInfo()){
			$('#approvedPassBtn').attr("onclick", "approvedPass();");
			$('#approvedRejectBtn').attr("onclick", "approvedReject();");
			$('#closeBtn').attr("onclick", "closeMe();");
			return;
		}
		
		$('#approvedPassBtn').attr("onclick", "approvedPass();");
		$('#approvedRejectBtn').attr("onclick", "approvedReject();");
		$('#closeBtn').attr("onclick", "closeMe();");
		
		if(!confirm("确定要审核通过吗?")){
			return;
		}
		
		var _load = layer.load('处理中..');
		$("#approveform").ajaxSubmit({
			url : '${path}/firstborrow/firstunlock/approvedpass.html',
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
	
	function approvedReject() {
		$('#approvedPassBtn').attr("onclick", "");
		$('#approvedRejectBtn').attr("onclick", "");
		$('#closeBtn').attr("onclick", "");
		
		if (!validateApprInfo()){
			$('#approvedPassBtn').attr("onclick", "approvedPass();");
			$('#approvedRejectBtn').attr("onclick", "approvedReject();");
			$('#closeBtn').attr("onclick", "closeMe();");
			return;
		}
		
		$('#approvedPassBtn').attr("onclick", "approvedPass();");
		$('#approvedRejectBtn').attr("onclick", "approvedReject();");
		$('#closeBtn').attr("onclick", "closeMe();");
		
		if(!confirm("确定要审核不通过吗?")){
			return;
		}
		
		var _load = layer.load('处理中..');
		$("#approveform").ajaxSubmit({
			url : '${path}/firstborrow/firstunlock/approvedreject.html',
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
	
	function closeMe() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
</script>
</html>