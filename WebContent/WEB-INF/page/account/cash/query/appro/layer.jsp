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
				<td width="100px">用户名：</td>
				<td colspan="3">${cashRecordVo.username}</td>
			</tr>
			<tr>
				<td width="100px">提现银行：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashRecordVo.bank}</td>
				<td width="100px">银行卡号：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashRecordVo.account }</td>
				<td width="100px">资产总额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashMap.total}</td>
			</tr>
			<tr>
				<td width="100px">提现金额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashRecordVo.totalStr}
				</td>
				<td width="120px">净值待还金额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashMap.repaymentAccountTotal}</td>
				<td width="100px">净值额度：</td>
				<td><font style="color: red; font-weight: 600;"></font>${maxAccount}</td>
			</tr>
			<tr>
				<td width="100px">待收利息：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashMap.collectionInterest}
				</td>
				<td width="120px">提现冻结总额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashMap.cashFreezeTotal}</td>
				<td width="100px">可提取总额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${cashMap.drawTotal}</td>
			</tr>
			<tr>
				<td width="100px">参考费率：</td>
				<td colspan="3">2</td>
			</tr>
			<tr>
				<td width="100px">到账金额：</td>
				<td colspan="3">${cashRecordVo.creditedStr}</td>
			</tr>
			<tr>
				<td colspan="6">最近${cashListCount}次提现到账情况</td>
			</tr>
			<c:forEach items="${latelyCashList}" var="layerList">
			<tr>
				<td colspan="6">银行账户 :${layerList.account},提现金额:${layerList.total}</td>
			</tr>
			</c:forEach>
			<tr>
				<td width="100px">实际手续费：</td>
				<td colspan="5">
					<font color="red">*</font>${cashRecordVo.fee}
				</td>
			</tr>		
			<tr>
				<td width="100px">审核备注：</td>
				<td colspan="3" width="350px"><font color="red">*</font><textarea id="verifyRemark" name="verifyRemark" style="width:350px;height:100px;" ></textarea></td>
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
		<input type="hidden" name="id" value="${cashRecordVo.id}" />
		<input type="hidden" name="version" value="${cashRecordVo.version}" />
		<input type="hidden" name="total" value="${cashRecordVo.total}" />

	</form>
</body>
<script type="text/javascript">
/**
 * 审核通过，批准
 */
function approPass(){
	var verifyRemark = $("#verifyRemark").val();
	if(null==verifyRemark || $.trim(verifyRemark)==""){
		alert("审核备注不能为空。");
		return false;
	}
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/cashauditrecord/pass.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			 
			if(result.code == '0'){
				layer.msg(result.message,1,1);
				var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
				parent.layer.close(index); //执行关闭
			}else{
				layer.msg(result.message,1,5);
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
    var verifyRemark = $("#verifyRemark").val();
	if(null==verifyRemark || $.trim(verifyRemark)==""){
		alert("审核备注不能为空。");
		return false;
	}
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/cashauditrecord/reject.html',
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
