<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left: 20px; line-height: 40px;">
				标题：
				<input type="text" name="newsNoticeTitle" id="newsNoticeTitle"	/>
				&nbsp;
				<input type="button" value="查询" onclick="pageGo(1)"/>
				<%-- <a4j:commandButton id="searchNewsNotices" name="searchNewsNotices"
					value="查询" onclick=""
					action="#{newsNoticeAction.searchNewsNotices}" execute="@form"
					data="#{newsNoticeAction.page}" oncomplete=""
					render="newsNoticeList">
				</a4j:commandButton>
				<h:inputHidden name="pageNo" id="pageNo" value="0"></h:inputHidden> --%>
				&nbsp;
				<input type="button" value="新增" onclick="toAddNotice()"/>
				<%-- <a4j:commandButton id="forinsertNewsNotice"
					name="forinsertNewsNotice" value="新增" onclick=""
					action="#{newsNoticeAction.initForNewsNotices}"
					oncomplete="showAddPopup();" render="insertform">
				</a4j:commandButton> --%>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>标题</th>
				<th>类型</th>
				<th>状态</th>
				<th>作者</th>
				<th>来源出处</th>
				<th>发布时间</th>
				<th>最后维护时间</th>
				<th>点击次数</th>
				<th>操作</th>
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
	var title = $('#newsNoticeTitle').val();
	$.ajax({
		url : '${path}/notice/searchNewsNotices/' + pageNo + '.html',
		data : {
			title : title
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

function delNotice(noticeId,pageNo){
	layer.confirm("确定删除?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/notice/delNotice.html',
			data : {
				noticeId : noticeId
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
function toAddNotice(){
	var _url = '${path}/notice/toNoticeAdd.html';
	$.layer({
		type : 2,
		title : '公告添加',
		area : [ '800px', '630px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}


function editNotice(noticeId,pageNo){
	var _url = '${path}/notice/toNoticeEdit/'+noticeId+'.html';
	$.layer({
		type : 2,
		title : '公告修改',
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