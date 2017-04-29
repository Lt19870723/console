<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>客户管理-元宝商场-商品兑换记录</title>
</head>
<body>
	<form id="approForm" action="" method="post">
		<input type="hidden" name="id" id="id" value="${id}" />
		<table style="padding-top: 10px; padding-left: 20px;" width="100%">
			<tr>
				<td colspan="4"><font>兑换信息</font></td>
			</tr>
			<tr>
				<td align="right" width="20%">用户名称：</td>
				<td width="30%">${vo.username}</td>
				<td align="right" width="20%">手机号码：</td>
				<td>${vo.mobilenum }</td>
			</tr>
			<tr>
				<td align="right">商品名称：</td>
				<td>${vo.name}</td>
				<td align="right">商品分类：</td>
				<td>
					${vo.firstClass==1?'线上':'线下'}- 
					<c:if test="${vo.firstClass==1}">
				    	${vo.secondClass==1?'红包':'抽奖'}
					</c:if> 
					<c:if test="${vo.firstClass==2}">
					    ${vo.secondClass==1?'电影劵':'实物'}
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="right">实际价值(元)：</td>
				<td>${vo.money}</td>
				<td align="right">单价(元宝)：</td>
				<td>${vo.price}</td>
			</tr>
			<tr style="color: red;">
				<td align="right">兑换数量：</td>
				<td>${vo.num }</td>
				<td align="right">兑换使用元宝：</td>
				<td>${vo.sycee}</td>
			</tr>
			<tr>
				<td align="right">兑换时间：</td>
				<td><fmt:formatDate value="${vo.exchangeTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
			<tr style="${opt=='handle'?'display:none;':''}">
				<td align="right">处理备注：</td>
				<td colspan="3">${vo.dealRemark}</td>
			</tr>
			<tr>
				<td colspan="4"><font>联系方式</font></td>
			</tr>
			<tr>
				<td align="right">收件人：</td>
				<td>${address.name}</td>
				<td align="right">手机号码：</td>
				<td>${address.phone}</td>
			</tr>
			<tr>
				<td align="right">收件地址：</td>
				<td>${address.address}</td>
				<td align="right">邮编：</td>
				<td>${address.zip_code}</td>
			</tr>
			<tr>
				<td align="right">填写时间：</td>
				<td><fmt:formatDate value="${address.updatetime!=null?address.updatetime:address.addtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
			<tr style="${opt=='handle'?'':'display:none;'}">
				<td align="right"><font color="red">*</font>处理备注：</td>
				<td colspan="3"><textarea id="dealRemark" name="dealRemark" style="width: 470px; height: 70px;"></textarea></td>
			</tr>
			<tr style="${opt=='handle'?'':'display:none;'}">
				<td></td>
				<td colspan="3">
					<input type="button" onclick="javascript:saveButton();" value="保存" /> &nbsp; 
					<input type="button" onclick="javascript:cancleButton();" value="取消" />
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function cancleButton() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
	/**
	 * 审核通过，批准
	 */
	var _load;

	function saveButton() {

		var verifyRemark = $("#dealRemark").val();

		if (null == verifyRemark || $.trim(verifyRemark) == "") {
			layer.msg('备注不能为空。', 1, 5);
			return false;
		}
		layer.confirm("确定处理吗?", function() {
			$.ajax({
				url : '${path}/account/syceeExchange/dealResult.html',
				data : {
					'id' : $("#id").val(),
					'dealRemark' : $("#dealRemark").val()
				},
				type : 'post',
				async : false,
				dataType : 'json',
				beforeSend : function() {
					_load = parentLayer.load('处理中..');
				},
				success : function(result) {
					parentLayer.close(_load);
					if (result.code == '1') {
						layer.msg(result.message, 1, 1,
								function() {

									var index = parent.layer
											.getFrameIndex(window.name); //获取当前窗体索引
									parent.layer.close(index); //执行关闭
									layer.msg(result.message, 1, 1);
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
		});
	}
</script>
</html>
