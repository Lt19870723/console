<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>国诚金融后台管理系统</title>
</head>
<body>
	<div id="container">
		<div id="topdiv">
			<div style="width: 100%; height: 30px; line-height: 30px; background: #35a8e1; color: #FFFFFF; font-size: 12px;">
				<span style="float: left; display: inline; font-size: 12px;">欢迎您进入后台管理系统 </span>
				<span style="float: right; padding-right: 20px; font-size: 14px; color: #FFFFFF;">${el:currentUser().userName}&nbsp;&nbsp;
					<a href="javascript:;" onclick="changePwd();" style="color: #FFFFFF;">修改密码</a>&nbsp;&nbsp;
					<a href="javascript:;" onclick="logout();" style="color: #FFFFFF; font-weight: bold;">退出</a>
				</span>
			</div>
			<div style="width: 100%; height: 54px; line-height: 30px; background: url(${path}/images/nav_bg.png) repeat-x; color: #FFFFFF; font-size: 14px;">
				<div style="float: left; height: 52px;line-height: 52px;width:99%; color: #FFFFFF;">
					<c:forEach var="topMenu" items="${topMenus}" varStatus="status">
					  <a href="javascript:;" class="menuschage" id="menus${topMenu.id}" onclick="getLeftMenu('${topMenu.id}','menus${topMenu.id}')"
					   style="color: #FFFFFF;display: block;float: left;width: 6%; font-size: 15px; font-weight: bolder; padding: 0 15px;line-height: 54px;">
					   ${topMenu.name}
					  </a>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="maindiv">
			<table class="fulltable" style="border-collapse: inherit; border: 0;">
				<tr style="border: 0;">
					<th scope="row" class="nenu_left">
						<div id="leftdiv" class="main_left">
							<span><img src="${path}/images/top_lists.png" /></span>
							<div id="nav">
							<%-- 左侧菜单 --%>
							</div>
						</div>
					</th>
					<td><iframe id="ifr" frameborder="0" align="left" scrolling="auto"></iframe></td>
				</tr>
			</table>
		</div>
		<div id="foot" class="foot">版权所有2016©上海国诚金融信息服务有限公司</div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	getLeftMenu('${topMenus[0].id}');
	$('[id="nav"]').css("width", "228px");
	$('[id="nav"]').css("height", $(window).height() - $('#topdiv').height() - $('#foot').height() - 58 + "px");
	$('[id="ifr"]').css("width", $(window).width() - $('#leftdiv').width());
	$('[id="ifr"]').css("height", $('#leftdiv').height());
});
function getLeftMenu(pid,id) {
	 
	$('#ifr').attr("src", '');
	$('.menuschage').css('background-color','');
	$('.menuschage').css('color','#FFF'); 
	$('.menuschage').css('border-top',''); 
	$('#'+id).css('background-color','#eee');
	$('#'+id).css('border-top','2px solid #055775');
	$('#'+id).css('color','#055775');
	$.ajax({
		url : '${path}/main/menu-' + pid + '.html',
		data : {},
		type : 'post',
		dataType : 'text',
		success : function(result) {
			$("#nav").html(result);
			menuBind();
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
function menuBind() {
	var xx = $("#nav").find("h3").eq(0)[0];
	$("#nav").find("h3").bind("click", function() {
		if($(this).next()[0].tagName == "DIV") {
			if(xx == this) {
				$(this).next().slideUp();
				$(this).css("background", "url('${path}/images/list_gb.png') repeat-x scroll 0% 0% #EBF6FC");
				xx = null;
				return;
			}
			$(this).next().slideDown();
			$(this).css("background", "url('${path}/images/list_gbs.png') repeat-x scroll 0% 0% #EBF6FC");
			$(this).next().siblings("DIV").slideUp();
			$(this).siblings("h3").css("background", "url('${path}/images/list_gb.png') repeat-x scroll 0% 0% #EBF6FC");
			xx = this;
		}
	});
}
function logout() {
	layer.confirm("确认退出?", function() {
		$.getJSON("${path}/logout.html", function(result) {
			window.location.href = '${path}/';
		});
	});
}
function changeSrc(url,id) {
    $('.menuchage').css('background-color','');
    $('.menuchage').css('color','');
	$('#'+id).css('background-color','#c5e2f5');
	$('#'+id).css('color','#055775');
	$('#ifr').attr("src", url);
}
function changePwd() {
	$.layer({
		type : 2,
		title : '修改密码',
		area : [ '286px', '158px' ],
		offset : [ '80px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : '${path}/changePwd.html'
		}		
	});	
}
</script>
</html>