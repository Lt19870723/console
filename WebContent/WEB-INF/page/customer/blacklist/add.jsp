<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>添加黑名单-国诚金融后台管理系统</title>
</head>
<body>
<form id="insertBlackform" name="insertBlackform" method="post">
	<div id="tab1" style="margin-left:10px;height:250px;">
		<div class="listzent"></div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				<font color="red">*</font>
				用户名：<input type="text" id="username" name="username"  dataType="Require" msg="请输入用户名！"  class="input1" style="width:180px;"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				<font color="red">*</font>&nbsp;&nbsp;类型 ：
				<select id="type" name="type" class="bigselect" >
					<option value="">--请选择--</option>
					<option value="1">禁止手动投标</option>
					<option value="2">禁止认购直通车</option>
					<option value="3">禁止认购债权转让</option>
					<option value="4">禁止设置自动投标</option>
					<option value="5">禁止线上充值</option>
					<option value="6">禁止提现</option>
					<option value="7">禁止发净值标</option>
					<option value="8">直通车下车</option>
				<!--<option value="9">禁止微信消息群推</option>-->
					<option value="10">禁止加入活期宝</option>
					<option value="11">禁止发帖和回帖</option>
					<option value="12">禁止登录</option>
					<option value="13">禁止加入定期宝</option>
				</select>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<div style="margin-top:10px;font-size:14px;">
				<font color="red">*</font>
				&nbsp;&nbsp;备注：
				<textarea id="remark" name="remark" class="input1" style="width:350px;height:80px;" ></textarea>
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
		
		//验证
		var username = $('#username').val();
		if(username == null || username == ''){
			alert("请填写用户名！");
			return false;
		}
		
		var type = $('#type').val();
		if(type == null || type == '' || type == '-1'){
			alert("请填写类型！");
			return false;
		}
		
		var remark = $('#remark').val();
		if(remark == null || remark == ''){
			alert("请填写备注信息！");
			return false;
		}
		
		var msg = "确认要添加该条黑名单吗？";
		if(type == '8'){
			msg = "确认让该用户的直通车全部下车吗？";
		}
		
		if(!confirm(msg)) return;
		
		var _load = layer.load('处理中..');
		$("#insertBlackform").ajaxSubmit({
			url : '${path}/customer/blacklist/save.html',
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