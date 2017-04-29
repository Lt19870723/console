<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>新增-单图文消息</title>
</head>
<body>

<div >
	<div class="left1_input_tst" style="margin-bottom:210px;">
		<font class="red">&nbsp;图片建议像素为360*200</font>
		<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>图片：</span>
		<div class="input_IDs">
			<img id="oneimageId" style="width:360px;height:200px;"
				src="${consoleWxImagePath}${wxArticles.picurl}"></img>
			<form  id="myfrom"   name="myfrom" method="post" >
				<input  class="rz_input mr5" type="file" name="files" id="btnFile" onchange="forupload('myfrom')" accept="image/gif, image/jpeg, image/png" dataType="Require" msg="请选择图片！" style="width:85px"/>
			</form>
		</div>
	</div>
	<form id="addOneNewsForm" name="addOneNewsForm" method="post">
		<input type="hidden" id="id" name="id" value='${wxArticles.id}' />
		<input type="hidden" id="picurl" name="picurl" value='${wxArticles.picurl}' />
		<div class="left1_input_tst"  style="margin-bottom:5px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>标题：</span>
			<div class="input_IDs">
				<input type="text" id="title" name="title" value="${wxArticles.title}" class="input1" dataType="Require" msg="请输入标题！" style="width:90%" maxlength="64" />
			</div>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>描述：</span>
			<div class="input_IDs">
				<textarea id="description" name="description"  rows="2" dataType="Require" msg="请输入描述！" class="input2" style="width:90%">${wxArticles.description}</textarea>
			</div>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>超链接：</span>
			<div class="input_IDs">
				<input type="text" id="url" name="url" value="${wxArticles.url}" dataType="Require" msg="请输入超链接！" class="input1" style="width:90%" maxlength="255" />
			</div>
		</div>
		<div class="savebutton">
			<input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" />
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
	function forupload(form){
		//验证
		if (Validator.Validate(form,4)==false) return;
		
		//提交
		var _load = layer.load('处理中..');
		$("#"+form).ajaxSubmit({
			url : '${path}/maintain/xw/wxpushone/uploadpics.html',
			type : 'post',
			dataType :"json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
						$('#picurl').val(result.message);
						$('#oneimageId').attr("src", '${consoleWxImagePath}' + result.message);
				}else{
					layer.alert(result.message);
				}
			},
			error : function(result) {
				layer.close(_load);
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}

	function save() {
		
		//验证
		if (Validator.Validate("addOneNewsForm",4)==false) return;
		
		var _load = layer.load('处理中..');
		$("#addOneNewsForm").ajaxSubmit({
			url : '${path}/maintain/xw/wxpushone/save.html',
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

</script>
</html>