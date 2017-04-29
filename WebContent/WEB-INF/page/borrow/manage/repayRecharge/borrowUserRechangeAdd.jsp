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
	 
	<div style="margin-left:10px;margin-top:5px;">
				<table style="margin-left:10px;height:200px;margin-top:5px;border:1px;" border="1">
					<tr>
						<td width="100px">用户名：</td>
						<td>
						${borrowUserRechangeVo.userName}
						<input type="hidden" value="${borrowUserRechangeVo.userName}" id="userName"/>
						</td>		
					</tr>
					<tr>
						<td width="100px">账户总额：</td>
						<td>
						<fmt:formatNumber type="currency" value="${accountVo.total}" currencySymbol="¥" maxFractionDigits="2"/> 
						</td>		
					</tr>
					<tr>
						<td width="100px">可用余额：</td>
						<td>
						<fmt:formatNumber type="currency" value="${accountVo.useMoney}" currencySymbol="¥" maxFractionDigits="2"/> 
						</td>		
					</tr>
					<tr>
						<td width="100px">可提金额：</td>
						<td>
						<fmt:formatNumber type="currency" value="${accountVo.drawMoney}" currencySymbol="¥" maxFractionDigits="2"/> 
						</td>		
					</tr>
					<tr>
						<td width="100px">受限金额：</td>
						<td>
						<fmt:formatNumber type="currency" value="${accountVo.noDrawMoney}" currencySymbol="¥" maxFractionDigits="2"/> 
						</td>		
					</tr>
					<tr>
						<td width="100px">充值金额：<font color="red">*</font></td>
						<td>
						<input type="hidden" value="${userId}" id="userId"/>
						<input type="text" id="reChangeMoney"value="${borrowUserRechangeVo.reChangeMoney}" maxlength="12" size="21"/>
						 
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" height="60px;">
							   
			                <input type="button" onclick="javascript:showRecharge();" class="b_buts" value="充值" />
 
							<input type="button" onclick="javascript:exit()" class="b_buts" value="关闭" />   
					 
							  						
						</td>					
					</tr>																																	
				</table>
				</div>
</body>

<script type="text/javascript">

var _load;

function showRecharge(){
    if(!validateAddInfo()){
   	return false;
    }		
    _load = layer.load('处理中..');
	 $.ajax({
		url : '${path}/borrow/manage/repayRecharge/doBorrowUserRechange.html',
		data : {
			'id' :$('#userId').val(),
			'userName' :$('#userName').val(),
			'reChangeMoney' :$('#reChangeMoney').val()
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			 
			if (result.code == '1') {
				layer.msg(result.message, 1, 1, function() {
						parentLayer.close(_load);
						var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
						parent.layer.close(index); //执行关闭
					});
			} else {
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
//关闭窗口
function exit(){
	 
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
/**
* 验证表单数据
*/
function validateAddInfo(){
	//金额的正则表达式
	var zfdsReg = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	var reChangeMoney = $.trim($('#reChangeMoney').val());
	if(reChangeMoney==""){
	    alert("请输入充值金额");
	    return false;
	}     
	if(!zfdsReg.test(reChangeMoney)){
		alert("充值金额输入有误!");
		return false;
	}
	if(!confirm("确定要充值【"+reChangeMoney+"块钱】吗？")){
		return false;
	}
	return true;		
}
</script>
</html>
