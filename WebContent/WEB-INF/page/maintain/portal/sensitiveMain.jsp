<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left:20px;line-height:50px;">
			
			&nbsp;&nbsp;&nbsp;&nbsp;关键字：<input type="text" id="word" class="input1" />
			  		&nbsp;&nbsp;类型： 　
				  <select id="typeSensitive" class="bigselect" style="width:230px;">
				 </select>
 						 
			&nbsp;		
			<input type="button" id="searchrealNameApprs" name="searchrealNameApprs" onclick="pageGo(1)" value="查询"/>
			 <input type="button"  id="forcheck" name="forcheck" onclick="toAddNotice()" value="新增"/>
	 		<%-- <a4j:commandButton id="forcheck" name="forcheck" value="新增"
	 			onclick="forClear()"
	 			action="#{SensitiveAction.querySensitiveTypeList}"
				execute="@form"
				data="#{SensitiveAction.page}"
				oncomplete="" 
				render="realNameApprform"> 
				<rich:componentControl target="checkBorrowPopup" operation="show" />
		    </a4j:commandButton> --%>
			
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>序号</th>
				<th>ID</th>
				<th>关键字名称</th>
				<th>关键字类型</th>
				<th>来源</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	querySensitiveTypeList();
	pageGo(1);
});
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	var word = $('#word').val();
	var type = $('#typeSensitive').val();
	$.ajax({
		url : '${path}/sensitive/searchAll/' + pageNo + '.html',
		data : {
			word : word,
			type : type
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


function querySensitiveTypeList(){
	$.ajax({
		url : '${path}/sensitive/querySensitiveTypeList.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
			jQuery($("#typeSensitive")).get(0).options.add(new Option("",""));
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#typeSensitive").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}


function toAddNotice(){
	var _url = '${path}/sensitive/toSensitiveAdd.html';
	$.layer({
		type : 2,
		title : '敏感词添加',
		area : [ '400px', '330px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function delSensitive(sensitiveId,pageNo){
	layer.confirm("确定删除?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/sensitive/delSensitive.html',
			data : {
				id : sensitiveId
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("删除成功",1,1,function(){
						layer.closeAll();
					});
					pageGo(pageNo);
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}


function editSensitive(sensitiveId){
	var _url = '${path}/sensitive/querySensitiveById/'+sensitiveId+'.html';
	$.layer({
		type : 2,
		title : '敏感词修改',
		area : [ '400px', '330px' ],
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