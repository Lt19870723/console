<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<style type="text/css">
*{ margin:0; padding:0; font-size:12px; font-family:"宋体"}
h1,h2,h3{ font-weight:normal;}
a{ text-decoration:none;}
img{ display: block; border:none;}
li{ list-style:none;}
input{outline:0;-moz-border-radius:5px;-khtml-border-radius:5px;-webkit-border-radius:5px;border-radius:5px;}

.landed{background-color:#0b6bc1;}
.landed_container{ padding-bottom:100px; overflow:hidden;}
.landed_title{ font-size:60px; text-align:center; color:#FFF;font-family:"微软雅黑";  padding:80px 0; }
.landed_content{background-color:#7dc6ed;width:900px; height:400px; margin:auto; }
.landed_logo{  float:left;}
.landed_logo img{ margin:130px 20px;}
.landed_right{ float:left; margin:50px 50px; padding-top:40px;}
.landed_right li{ color:#FFF; line-height:60px; font-size:22px;}
.yhm{width:250px; height:39px;font-size:16px;color:#ccc;line-height:40px;border:1px solid #dbdbdb;}
.jzma{font-size:14px;  Arial, System; color:#fefcfc;line-height:25px; padding-left:90px; margin-top:30px;}

.safe_button01{height:41px; width:100px; text-align:center; background:#00ade9; border:0px; color:#FFFFFF; font-size:14px; padding:0 10px; margin-left:5px; }
.safe_button01:hover{height:41px; text-align:center; background:#005a99; border:0px; color:#FFFFFF; font-size:14px;}
 
.remend{ margin-right:5px; margin-top:5px; border-radius:0;}
.yhm_yz{width:130px;height:39px;font-size:16px;color:#ccc;line-height:40px;border:1px solid #dbdbdb;}
.denglu_btn{ text-align:center;margin:20px auto; background:#00a7e5; color:#fff; padding:0 35px; height:38px; border:0; font-size:18px; display:block;}
.denglu_btn:hover{background:#005a98;}

.footbg{ background:#555; color:#ccc; }
.footer-copyright {font-size:14px;line-height:40px;position: relative;text-align:center; width:1000px; margin:auto;}
.overflow-h { overflow-y:hidden;}	
</style>
<title>登录-国诚金融后台管理系统</title>
</head>
<body class="overflow-h">
	<div class="landed">
		<div class="landed_container">
			<div class="landed_title">国诚金融后台管理系统</div>
			<div class="landed_content">
				<div class="landed_logo">
					<img src="${path}/images/rig_03.png" />
				</div>
				<div class="landed_right" style="float: none; padding-top: 130px">
					<ul>
						<li>用户名：<input id="username" name="username" value="ligang" type="text" class="yhm" style="color: #000;" onkeypress="if (event.keyCode == '13') { $('#loginBtn').trigger('click'); }" tabindex="1" /></li>
						<li>密&nbsp;码：<input type="password" id="password" value="1" type="password" style="color: #000;" class="yhm" onkeypress="if (event.keyCode == '13') { $('#loginBtn').trigger('click'); }" /></li>
					</ul>
					<div class="dlbt">
						<input type="button" id="loginBtn" name="loginBtn"  onclick="onLogin();" value="登录" class="safe_button01" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="footbg">
		<div class="footer-copyright">Copyright 2016 © 上海国诚金融信息服务有限公司 版权所有 | 上海市虹口区西江湾路388号凯德龙之梦A座32楼 电话：021-60711900 | 沪ICP备13021943号-1</div>
	</div>
</body>

<script type="text/javascript">
	function onLogin() {
		$('#loginBtn').attr("onclick", "");
		if ($.trim($("#username").val()) == "") {
			layer.msg('请输入用户名', 1, 5);
			$("#username").focus();
			$('#loginBtn').attr("onclick", "onLogin();");
			return;
		}
		if ($("#password").val() == "") {
			layer.msg('请输入密码', 1, 5);
			$("#password").focus();
			$('#loginBtn').attr("onclick", "onLogin();");
			return;
		}
		layer.load('处理中..');
		$.ajax({
			url : '${path}/onLogin.html',
			type : 'post',
			data : {
				'username' : $.trim($("#username").val()),
				'password' : $("#password").val()
			},
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
					window.location.href = '${path}/';
				} else {
					layer.msg(result.message, 1, 5);
					$('#loginBtn').attr("onclick", "onLogin();");
				}
			},
			error : function(result) {
				$('#loginBtn').attr("onclick", "onLogin();");
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
</script>
</html>