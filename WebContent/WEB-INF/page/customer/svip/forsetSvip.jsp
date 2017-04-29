<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>银行信息编辑-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<div id="tab1" style="height:230px;">
		<div class="listzent">设置终身顶级会员(* 必填)</div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				<font color="red">*</font>备注：<textarea id="remark" style="width:350px;height:80px;"></textarea>
			</div>
		</div>
		<div style="margin-top:110px;margin-left:200px;">
			<input type="button" id="setSvipForUserId" name="setSvipForUserId" onclick="forsetSvip()" value="确定"/>
			<input type="hidden" id="userId" name="userId" value="${userId}"/>
		</div>
	</div>
</form>
</body>
<script language="javascript">
function validateSetSvipForm(){
	var remark = $('#remark').val();
	if(remark==null || $.trim(remark)==""){
		alert("请填写备注！");
		return false;
	}
	return true;
}
function forsetSvip(){
	if(validateSetSvipForm()){
		var _load = layer.load('处理中..');
		var userId = $('#userId').val();
		var remark = $('#remark').val();
		$.ajax({
			url : '${path}/vipLevel/setSvipForUserId.html',
			data : {
				userId : userId,
				remark : remark
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("设置成功",1,1,function(){
						window.parent.location.href = window.parent.location.href;
					});
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
}


</script>
</html>