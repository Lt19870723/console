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
	   <input type="hidden" name="borrowId" id="borrowId"  value="${id}" />
	   <input type="hidden" name="flag" id="flag" />
		<table>
			 
			<tr>
				<td width="100px">审核备注：</td>
				<td colspan="3" width="350px"><font color="red">*</font><textarea id="remark" name="remark" style="width:350px;height:100px;" ></textarea></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3"> 
						 <input type="button" onclick="javascript:approPass(0);" value="审核通过" />
						 &nbsp; 
						 <input type="button" onclick="javascript:approPass(1);" value="审核不通过"/>
				</td>
			</tr>
			 
		</table>
	</form>
</body>
<script type="text/javascript">
/**
 * 审核通过，批准
 */
function approPass(flag){
 
	var verifyRemark = $("#remark").val();
	if(null==verifyRemark || $.trim(verifyRemark)==""){
		alert("审核备注不能为空。");
		return false;
	}
	var _load = parentLayer.load('处理中..');
	$("#flag").val(flag);
	 
	$.ajax({
		url : '${path}/borrow/approve/pass.html',
		data : {
			'borrowId' : $("#borrowId").val(),
			'flag' : $.trim($("#flag").val()),
			'remark' : $("#remark").val()
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					parent.layer.close(index); //执行关闭
					layer.msg(result.message,1,1);
				});
			} else if(result.code == '-1') {
				layer.msg(result.message, 1, 5);
				parentLayer.close(_load);			
			}else  {
				parentLayer.close(_load);
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			parentLayer.close(_load);
	    }
	});
}
 
</script>
</html>
