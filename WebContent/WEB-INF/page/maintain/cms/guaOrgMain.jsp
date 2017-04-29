<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left:20px;line-height:40px;">
	    	名称：<input type="text"  name="searchName" id="searchName"/>
	    	<input type="button" id="searchBtn" value="查询" onclick="pageGo(1)" styleClass="button"/>
	    	<input type="button"  id="addBtn" name="addBtn" onclick="addGuaOrg()" value="新增"/>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th width="4%">序号</th>
				<th width="15%">担保机构名称</th>
				<th width="10%">类型</th>
				<th width="10%">电话</th>
				<th>地址  </th>
				<th width="15%">添加时间 </th>
				<th width="10%">操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
});
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	var searchName = $('#searchName').val();
	$.ajax({
		url : '${path}/guarantyOrganization/searchAll/' + pageNo + '.html',
		data : {
			name : searchName
		},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			$('#dataTable tbody').remove();
			$('#dataTable').append(result);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
function delGuaOrg(guaOrgId,pageNo){
	layer.confirm("确定删除?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/guarantyOrganization/delGuaOrg.html',
			data : {
				guaOrgId : guaOrgId
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("删除成功",1,1,function(){
						pageGo(pageNo);
					});
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}
function addGuaOrg(){
	var _url = '${path}/guarantyOrganization/toGuaOrgAdd.html';
	$.layer({
		type : 2,
		title : '担保机构添加',
		area : [ '800px', '630px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function editGuaOrg(guaOrgId){
	var _url = '${path}/guarantyOrganization/toGuaOrgEdit/'+guaOrgId+'.html';
	$.layer({
		type : 2,
		title : '担保机构添加修改',
		area : [ '800px', '630px' ],
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