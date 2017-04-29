<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<div id="tab1" style="height:630px;">
		<div class="listzent">新闻公告信息(* 必填)</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><span class="red">*</span> 标题：</span>
			<div class="input">
				<input type="hidden" id="noticeId" value="${newsNotice.id }"/>
				<input type="text" class="input1" id="title" value="${newsNotice.title}" name="title" maxlength="50"/>
			</div>
		</div>
		<div class="left1_input_ts">
			<span class="input_span"><span class="red">*</span> 作者：</span>
			<div class="input">
				<input type="text" class="input1" id="author" name="author" value="${newsNotice.author}" maxlength="20"/>
			</div>
		</div>
		<div class="left1_input_ts">
			<span class="input_span"><span class="red">*</span> 类型：</span>
			<div class="input_zhuce">
				<select id="type" class="bigselect" value="${newsNotice.type}">
					<option></option>
					<option value="0" <c:if test="${newsNotice.type==0}">selected="selected"</c:if>>网站公告</option>
					<option value="1" <c:if test="${newsNotice.type==1}">selected="selected"</c:if>>新闻动态</option>
					<option value="2" <c:if test="${newsNotice.type==2}">selected="selected"</c:if>>行业动态</option>
				</select>
			</div>
		</div>
		<div class="left1_input_ts">
			<span class="input_span"><span class="red">*</span> 状态：</span>
			<div class="input_zhuce">
				<select id="status" class="bigselect" value="${newsNotice.status}">
					<option></option>
					<option value="0" <c:if test="${newsNotice.status==0}">selected="selected"</c:if>>正常</option>
					<option value="1" <c:if test="${newsNotice.status==1}">selected="selected"</c:if>>隐藏</option>
				</select>
			</div>
		</div>
		<div class="left1_input_ts">
			<span class="input_span"><span class="red">*</span> 来源出处：</span>
			<div class="input">
				<input type="text" class="input1" id="source" value="${newsNotice.source}" name="source" maxlength="100"/>
			</div>
		</div>
		<div class="left1_input_tst">
			<span class="input_span">正文：</span>
			<div class="input_IDs">
				<textarea id="context" class="textarea" dataType="Limit" min="1" max="3000" msg="借款介绍不能为空，且不能超过3000个字符" name="context">
					${newsNotice.context}
				</textarea>
	        </div>
		</div>
				
		<div style="clear: both;"></div>
		<br />
		<div class="left1_input_tst" style="margin-top:130px;">
			<div class="savebutton">
				<input type="button" value="保存"  id="insertNewsNotice" name="insertNewsNotice" onclick="updateNewsNotice()"/>
			</div>
		</div>
	</div>
</form>
</body>
<script language="javascript">

var um = UM.getEditor('context', {
	initialFrameWidth:700,
	initialFrameHeight:200,
	autoHeightEnabled:false
});

function validateInsertNewsNoticeForm(){
	var title = $('#title').val();
	if(title==null || $.trim(title)==""){
		layer.alert("请填写标题信息！");
		return false;
	}
	var author = $('#author').val();
	if(author==null || $.trim(author)==""){
		layer.alert("请填写作者信息！");
		return false;
	}
	var source = $('#source').val();
	if(source==null || $.trim(source)==""){
		layer.alert("请填写来源出处信息！");
		return false;
	}
	_load = layer.load('处理中..');
	return true;
}
function updateNewsNotice(){
	if(validateInsertNewsNoticeForm()){
		var noticeId = $('#noticeId').val();
		var title = $('#title').val();
		var author = $('#author').val();
		var type = $('#type').val();
		var status = $('#status').val();
		var source = $('#source').val();
		var context = $('#context').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/notice/editNotice.html',
			data : {
				id : noticeId,
				title : title,
				author : author,
				type : type,
				status : status,
				source : source,
				context : context
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("修改成功",1,1,function(){
						window.parent.location.href = window.parent.location.href;
					});
				}
			},
			error : function(data) {
				layer.close(_load);
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
}

</script>