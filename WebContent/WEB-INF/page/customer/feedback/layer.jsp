<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—客户管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table>
			<tr>
				<td width="100px">联系内容：</td>
				<td colspan="3"><textarea rows="25" name="contactContent"
						style="width: 500px; height: 350px;"></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3"><input type="button" id="btnApproPass"
					name="btnApproPass" value="已解决" onclick="javascript:approPass();">
					&nbsp; <input type="button" id="btnApproReject"
					name="btnApproReject" value="不予解决"
					onclick="javascript:unApproPass();"></td>
			</tr>
		</table>
		<input type="hidden" name="id" type="hidden;" value="${id }" />

	</form>
</body>
<script type="text/javascript">

function approPass() {
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/customer/feedback/approvePass.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				parentLayer.close(_load);
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
				layer.msg(result.message,1,5);
			}else{
				parentLayer.close(_load);
				layer.msg(result.message,1,5);
			}
			
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			parentLayer.close(_load);
		}
	});
}
function unApproPass(){
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/customer/feedback/approveReject.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				parentLayer.close(_load);
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
				layer.msg(result.message,1,1);
			}else{
				parentLayer.close(_load);
				layer.msg(result.message,1,5);
			}
			
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			parentLayer.close(_load);
		}
	});
}
</script>
</html>
