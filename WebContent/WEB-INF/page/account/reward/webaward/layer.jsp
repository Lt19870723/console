<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—奖励管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center">
			<tr>
				<td width="100px">用户名：</td>
				<td>${memberVo.username }</td>
			</tr>
			<tr>
				<td width="100px">账户总额：</td>
				<td><font style="color: red;"></font>${accountVo.totalStr }</td>
			</tr>
			<tr>
				<td width="100px">可用余额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${accountVo.useMoneyStr }</td>
			</tr>
			<tr>	
				<td width="100px">可提现金额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${accountVo.drawMoneyStr }</td>
			</tr>
			<tr>
				<td width="120px">不可提现金额：</td>
				<td><font style="color: red; font-weight: 600;"></font>${accountVo.noDrawMoneyStr }</td>
			</tr>
			<tr>	
				<td width="100px"><font style="color: red; font-weight: 600;">*</font>奖励金额：</td>
				<td><input type="text" name="money" id="money"/></td>
			</tr>
			<tr>
				<td width="100px">备注：</td>
				<td colspan="3" width="300px"><textarea id="verifyRemark" name="verifyRemark"
						style="width:300px;height:100px;" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
						 <input type="button" onclick="javascript:approPass();" value="确定" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" id="id"  value="${memberVo.id}" />
		<input type="hidden" name="version"   value="${accountVo.version}" />

	</form>
</body>
<script type="text/javascript">
/**
 * 审核通过，批准
 */
function approPass(){
	var reg= /^[-]{0,1}(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	var money = $('#money').val();
	if(money==null || $.trim(money)==""){
		alert("奖励金额不能为空!!");
		return false;
	}else{
		if(!reg.test(money)){
			alert("奖励金额不是正确的金额(整数位不能超过10位，小数位不得超过2位)！");
			return false;
		}
	}
	var id=$('#id').val();
	if (confirm('确定要进行奖励？')) {
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/webaward/subWebAward/'+id+'.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if(result.code == '0'){
				alert("网站奖励成功!!");
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
}
</script>
</html>
