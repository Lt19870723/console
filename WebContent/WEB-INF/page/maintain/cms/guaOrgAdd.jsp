<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>担保机构新增-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<div id="tab1" style="height:330px;">
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><span class="red">*</span> 担保机构名称：</span>
			<div class="input">
				<input type="text" class="input1" id="name" name="name"  maxlength="100"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><span class="red">*</span> 担保机构类型：</span>
			<div class="input">
				<input type="text" class="input1" id="type" name="type" maxlength="100"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;">
			<span class="input_span"><span class="red">*</span> 电话：</span>
			<div class="input">
				<input type="text" class="input1" id="tel" name="tel" maxlength="50"/>
			</div>
		</div>
		<div class="left1_input_tst" style="float: left;">
			<span class="input_span"><span class="red">*</span> 地址：</span>
			<div class="input_IDs">
				<input type="text" class="input1" id="addr" name="addr" maxlength="200" style="width:100%"/>
			</div>
		</div>
		<div class="left1_input_tst" style="float: left;">
			<span class="input_span">描述：</span>
			<div class="input_IDs">
				<textarea class="input1" id="descr" name="descr" style="height:60px;width:100%;" ></textarea>
			</div>
		</div>
		<div class="left1_input_tst" style="padding-top: 50px;">
			<div class="savebutton">
				<input type="button"  id="doAddBtn" name="doAddBtn" onclick="addGuaOrg()" value="保存"/>
			</div>
		</div>
	</div>
</form>
</body>
<script language="javascript">
function validForm(){
	var reg=/\S/; 
	var value=$('#name').val();
	if(!reg.test(value)){
		alert("请输入担保机构名称");
		return false;
	}
	if(value.length>100){
		alert("担保机构名称输入长度过长");
		return false;
	}
	value=$('#type').val();
	if(!reg.test(value)){
		alert("请输入担保机构类型");
		return false;
	}
	if(value.length>100){
		alert("担保机构类型输入长度过长");
		return false;
	}
	value=$('#tel').val();
	if(!reg.test(value)){
		alert("请输入电话");
		return false;
	}
	if(value.length>100){
		alert("电话输入长度过长");
		return false;
	}
	value=$('#addr').val();
	if(!reg.test(value)){
		alert("请输入地址");
		return false;
	}
	if(value.length>100){
		alert("地址长度过长");
		return false;
	}
	return true;
}
function addGuaOrg(){
	if(validForm()){
		var name = $('#name').val();
		var type = $('#type').val();
		var tel = $('#tel').val();
		var addr = $('#addr').val();
		var descr = $('#descr').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/guarantyOrganization/addGuaOrg.html',
			data : {
				name : name,
				type : type,
				tel : tel,
				addr : addr,
				descr : descr
			},
			type : 'post',
			dataType : "json",
			success : function(result) {
				layer.close(_load);
				if (result.code == '0') {
					layer.alert(result.message);
				} else {
					layer.msg("保存成功",1,1,function(){
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