<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>文本信息推送-国诚金融后台管理系统</title>
</head>
<body style="background: #f9f9f9;">
<div style="margin-left: 20px; line-height: 50px;">
	 <form id="wxNewMain">
		<div style="width: 1300px;">
			&nbsp; 推送状态：
			<select styleClass="input_select" id="isPush" name="isPush">
				<option value="" ></option>
				<option value="1" >已推送</option>
				<option value="2" >未推送</option>
				<option value="3" >推送中</option>
			</select>
			&nbsp; 推送时间
			<input name="beginTime" id="beginTime" name="beginTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input>
				至
			<input name="endTime" id="endTime" name="endTime" onclick="WdatePicker()"
					styleClass="Wdate" value="">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
			</input>
			<input type="button" value="查询" onclick="pageGo(1);"/>
			&nbsp;
			<input type="button" value="新增" onclick="openAdd()"/>
		</div>
	</form>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<th width="3%">序号</th>
			<th width="20%">标题</th>
			<th width="4%">图片</th>
			<th width="15%">超链接</th>
			<th width="20%">描述</th>
			<th width="6%">状态</th>
			<th width="14%">最近推送时间</th>
			<th width="4%">推送人数</th>
			<th width="5%">推送成功人数</th>
			<th width="10%">操作</th>
		</thead>
	</table>
			<br></br>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	//$('[id="wxNewsform:searchNewsAll"]').trigger("click");
	pageGo(1);
});
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/newSystem/wx/serachWxImgPush/' + pageNo + '.html',
		data : {
			'isPush' : $.trim($('#isPush').val()),
			'beginTime' : $.trim($('#beginTime').val()),
			'endTime' : $.trim($('#endTime').val())
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
function openWindow(textid) {
	var textvalue = $('#text'+textid).val();
	$.layer({
	    type : 1,
	    title : false,
	    fix : false,
	    offset:['50px' , ''],
	    area : ['350px','260px'],
	    page : {html : 	'<textarea style="width:350px;height:260px;"  readonly="readonly">'+textvalue+'</textarea>'}
	});
}
function openEdit(textid){
	var textvalue = $('#text'+textid).val();
	$.layer({
	    type : 1,
	    title : false,
	    fix : false,
	    offset:['50px' , ''],
	    area : ['350px','260px'],
	    page : {html : 	'<table style="width:350px;height:260px;border:0;"><tr><td>文本内容</td><td><textarea style="width:100%;height:100%" id="editWxPsuh" name="editWxPsuh">'+textvalue+'</textarea></td></tr><tr><td colspan="2" style="text-align: center;"><input type="button" value="保存" onclick="editWxPush('+textid+')"/></td></tr></table>'}
	});
}

function editWxPush(textid){
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/newSystem/wx/editWxPushText.html',
		data : {
			'id' : textid,
			'description' : $('#editWxPsuh').val()
		},
		type : 'post',
		dataType : "json",
		success : function(result) {
			layer.close(_load);
			if (result.code == '0') {
				layer.alert(result.message);
			} else {
				layer.msg("更新成功",1,1,function(){
					layer.closeAll();
				});
				pageGo(1);
			}
		},
		error : function(data) {
			layer.close(_load);
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function deleteWxPush(wxPushId){
	layer.confirm("确定删除?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/newSystem/wx/delWxPushText.html',
			data : {
				'id' : wxPushId,
				'type':3
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
					pageGo(1);
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}


function openAdd(){
	var _url = '${path}/newSystem/wx/addWxPushImg.html';
	$.layer({
		type : 2,
		title : '编辑',
		area : [ '650px', '420px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
	
	/*
	$.layer({
	    type : 1,
	    title : false,
	    fix : false,
	    offset:['50px' , ''],
	    area : ['350px','260px'],
	    page : {html : 	'<table style="width:350px;height:260px;border:0;"><tr><td>文本内容</td><td><textarea style="width:100%;height:100%" id="addWxPush" name="addWxPush"></textarea></td></tr><tr><td colspan="2" style="text-align: center;"><input type="button" value="保存" onclick="addWxPush()"/></td></tr></table>'}
	});*/
}



function addWxPush(){
	var _load = layer.load('处理中..');
	var addTextValue = $('#addWxPush').val();
	$.ajax({
		url : '${path}/newSystem/wx/addWxPushText.html',
		data : {
			'description' : addTextValue,
			'type':3
		},
		type : 'post',
		dataType : "json",
		success : function(result) {
			layer.close(_load);
			if (result.code == '0') {
				layer.alert(result.message);
			} else {
				layer.msg("添加成功",1,1,function(){
					layer.closeAll();
				});
				pageGo(1);
			}
		},
		error : function(data) {
			layer.close(_load);
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}



function pushAgain(wxPushId){
	layer.confirm("确定推送?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/newSystem/wx/pushAgain.html',
			data : {
				'id' : wxPushId,
				'type':3
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("推送成功",1,1,function(){
						layer.closeAll();
					});
					pageGo(1);
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	});
}


</script>
</html>