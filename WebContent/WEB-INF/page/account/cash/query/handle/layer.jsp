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
	<form id="approForm" action="" method="post">
		<table align="center">
			<tr>
				<td width="100px">失败原因：</td>
				<td colspan="3" width="350px"><font color="red">*</font><textarea id="remark" name="remark" style="width:350px;height:100px;" ></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3"> 
					<input type="button" onclick="javascript:approPass(${id});" value="提交" />
				</td>
			</tr>
			<tr></tr>
		</table>
		<input type="hidden" name="id" type="hidden" value="${id}" />

	</form>
</body>
<script type="text/javascript">
/**
 * 提交
 */
function approPass(id){
	var verifyRemark = $("#remark").val();
	if(null==verifyRemark || $.trim(verifyRemark)==""){
		alert("请填写失败原因!");
		return false;
	}
	if(verifyRemark.length>255){
		alert("失败原因长度不能超过255个字!");
		return false;
	}
	
	if(!confirm("确定要提交吗?")){
		return false;
	}
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/cashhandlerecord/failcash/'+id+'.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				alert(result.message);
				layer.msg(result.message,1,1);
				parentLayer.close(_load);
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
			}else{
				layer.msg(result.message,1,5);
				parentLayer.close(_load);
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
