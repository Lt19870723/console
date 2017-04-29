<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>黑名单设置-国诚金融后台管理系统</title>
</head>
<body>
	<form id="queryForm" action="" method="post">
		<div style="margin-left:20px;line-height:50px;">
			用户名：
			<input type="text" name="username" id="username" class="input1" />
			&nbsp;
			<%
			/**
			 * 黑名单禁止操作类型【1：禁止手动投标，2：禁止认购直通车，3：禁止认购债权转让，4：禁止设置自动投标，5：禁止线上充值，
			 6：禁止提现，7： 禁止发净值标, 8：直通车下车，9：禁止微信消息群推，10：禁止加入活期宝,11:禁止加入定期宝】 BEGIN
			 */
			%>
			类型 ：
			<select id="type" name="type" class="bigselect">
				<option value="-1">--请选择--</option>
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
			&nbsp;					
			<input type="button"
					onclick="javascript:query();" name="selectBtn"
					id="selectBtn" class="b_buts" value="查询" />
			&nbsp;					
			<input type="button"
					onclick="javascript:add();" name="addBtn"
					id="addBtn" class="b_buts" value="添加" />
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">用户名</th>
				<th width="20%">类型</th>
				<th width="10%">状态</th>
				<th>备注</th>
				<th width="5%">操作</th>
			</tr>
		</thead>
	</table>
	<div style="margin-top:5px;color:red;">
		&nbsp;&nbsp;温馨提示：<br/>
		&nbsp;&nbsp;1、禁止认购直通车后，请对已有的直通车进行下车处理。<br/>
		&nbsp;&nbsp;2、禁止设置自动投标时，同时会停用已启用的自动投标规则。<br/>
		&nbsp;&nbsp;3、禁止发净值标时，请手动取消现有的净值标。<br/>
		&nbsp;&nbsp;4、禁止提现时，请通知客服人员对申请中的提现进行审核失败处理。<br/>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		pageGo(1);
	});

	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		var urlPath = '${path}/customer/blacklist/list/' + pageNo + '.html';
		$("#queryForm").ajaxSubmit ({
			url : urlPath,
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$('#dataTable tbody').remove();
				$('#dataTable').append(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				parentLayer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}

	/**
	*查询功能
	*/
	function query(){
		pageGo(1);
	}
	
	function add() {
		var _url = '${path}/customer/blacklist/add.html';
		$.layer({
			type : 2,
			title : '添加黑名单',
			area : [ '500px', '300px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
	
	function doCancel(id,userId,type) {
		var _url = '${path}/customer/blacklist/cancle/' + id + '/' + userId+ '/' + type + '.html';
		$.layer({
			type : 2,
			title : '取消黑名单',
			area : [ '500px', '300px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}

</script>
</html>