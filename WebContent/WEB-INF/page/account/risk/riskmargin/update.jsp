<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—风险备用金</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center">
			<tr>
				<td width="120px"><font style="color: red; font-weight: 600;">*</font>风险备用金：</td>
				<td><input name="account" id="account" value="${riskMargin.account}"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
						 <input type="button" onclick="javascript:approPass();" value="确定" />
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
/**
 * 审核通过，批准
 */
function approPass(){
	var reg= /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	var account = $('#account').val();
	if(account==null || $.trim(account)==""){
		alert("请填写风险备用金！");
		return false;
	}else{
		if(!reg.test(account)){
			alert("风险备用金不是正确的金额(整数位不能超过10位，小数位不得超过2位)！");
			return false;
		}
	}
	
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/riskMargin/updateRisk.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				parentLayer.close(_load);
				layer.msg(result.message,1,1);
				parent.window.location.href = parent.window.location.href;
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
