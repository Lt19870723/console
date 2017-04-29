<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>取消黑名单-国诚金融后台管理系统</title>
</head>
<body>
<form id="cancelBlackform" name="cancelBlackform" method="post">
	<input type="hidden" id="id" name="id" value='${id}' />
	<div id="tab1" style="margin-left:10px;height:250px;">
		<div class="listzent"></div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				用户名：${username}
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				类型 ：
				<c:choose>
					<c:when test="${type == 1}">禁止手动投标</c:when>
					<c:when test="${type == 2}">禁止认购直通车</c:when>
					<c:when test="${type == 3}">禁止认购债权转让</c:when>
					<c:when test="${type == 4}">禁止设置自动投标</c:when>
					<c:when test="${type == 5}">禁止线上充值</c:when>
					<c:when test="${type == 6}">禁止提现</c:when>
					<c:when test="${type == 7}">禁止发净值标</c:when>
					<c:when test="${type == 8}">直通车下车</c:when>
					<c:when test="${type ==11}">禁止发帖和回帖</c:when>
					<c:when test="${type ==12}">禁止登录</c:when>
				</c:choose>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				<font color="red">*</font>
				&nbsp;&nbsp;备注：
				<textarea id="updateRemark" name="updateRemark" class="input1"  style="width:350px;height:80px;"></textarea>
			</div>
		</div>
		<div style="margin-top:200px;margin-left:200px;">
			<input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="确定" />
			&nbsp;&nbsp;
			<input type="button" id="closeBtn" name="closeBtn" onclick="closeMe();" value="关闭" />	
		</div>
	</div>
				
</form>
</body>
<script type="text/javascript">
	
	function save() {
		
		var remark = $('#updateRemark').val();
		if(remark == null || remark == ''){
			alert("请填写备注信息！");
			return false;
		}
		
		
		if(!confirm("确认要取消该黑名单吗？")) return;
		
		var _load = layer.load('处理中..');
		$("#cancelBlackform").ajaxSubmit({
			url : '${path}/customer/blacklist/update.html',
			type : 'post',
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.msg(result.message, 1, 1, function() {
						window.parent.location.href = window.parent.location.href;
					});
				} else {
					layer.msg(result.message, 1, 5);
				}
			},
			error : function(result) {
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function closeMe() {
		var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
		parent.layer.close(index); //执行关闭
	}
</script>
</html>