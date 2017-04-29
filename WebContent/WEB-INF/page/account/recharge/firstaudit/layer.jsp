<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—充值管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table>
			<tr>
				<td width="100px">充值订单号：</td>
				<td colspan="3">${rechargeRecordVo.tradeNo}</td>
			</tr>
			<tr>
				<td width="100px">充值类型：</td>
				<td colspan="3"><font style="color: green;">${rechargeRecordVo.typeStr}</font></td>
			</tr>
			<tr>
				<td width="100px">充值金额：</td>
				<td><font style="color: red; font-weight: 600;">${rechargeRecordVo.moneyStr}</font>
				</td>
				<td width="100px">手续费：</td>
				<td><font style="color: red; font-weight: 600;">${rechargeRecordVo.feeStr}</font></td>
			</tr>
			<tr>
				<td width="100px">充值用户：</td>
				<td><font style="color: red; font-weight: 600;">${rechargeRecordVo.username}</font>
				</td>
				<td width="100px">状态：</td>
				<td><font style="color: red; font-weight: 600;"></font>${rechargeRecordVo.statusStr}</td>
			</tr>
			<tr>
				<td width="100px">审核备注：</td>
				<td colspan="3" width="350px"><textarea id="verifyRemark" name="verifyRemark"
						
						style="width:350px;height:100px;" >${rechargeRecordVo.verifyRemark}</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3"> 
						 <input type="button" onclick="javascript:approPass();" value="审核通过" />
						 &nbsp; 
						 <input type="button" onclick="javascript:approReject();" value="审核不通过"/>
				</td>
			</tr>
			<tr></tr>
		</table>
		<input type="hidden" name="id" type="hidden;" value="${rechargeRecordVo.id }" />
		<input type="hidden" name="version" type="hidden;" value="${rechargeRecordVo.version }" />

	</form>
</body>
<script type="text/javascript">
/**
 * 审核通过，批准
 */
function approPass(){
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/firstaudit/pass.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				parentLayer.close(_load);
				layer.msg(result.message,1,1);
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

/**
 * 审核不通过，驳回
 */
function approReject(){
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/firstaudit/reject.html',
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
