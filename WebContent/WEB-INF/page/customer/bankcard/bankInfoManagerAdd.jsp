<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>银行信息添加-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<div id="tab1" style="height:300px;">
		<div class="listzent">银行信息(* 必填)</div>
		<div class="left1_input_ts" style="float: left;width:600px;">
			<span class="input_span">省市区：</span>
			<div style="margin-top:10px;font-size:14px;">
				<select id="province" class="bigselect" style="width:120px;" onchange="queryCityByList();">
					<option>--请选择--</option>
				 </select>
				 &nbsp;
				 <select id="city" class="bigselect" style="width:120px;" onchange="queryDistrictByList();">
					<option>--请选择--</option>
				 </select>
				 &nbsp;
				 <select id="district" class="bigselect" style="width:120px;">
					<option>--请选择--</option>
				 </select>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;width:600px;">
			<span class="input_span">银行名称：</span>
			<div style="margin-top:10px;font-size:14px;">
				<select id="bankName" class="bigselect" style="width:320px;">
					<option>--请选择--</option>
				 </select>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;width:600px;">
			<span class="input_span">支行名称：</span>
			<div style="margin-top:10px;font-size:14px;">
				<input type="text" class="input1" id="branchName" name="branchName" maxlength="50" style="width:320px;"/>
			</div>
		</div>
		<div class="left1_input_ts" style="float: left;width:600px;">
			<span class="input_span">联行号：</span>
			<div style="margin-top:10px;font-size:14px;">
				<input type="text" class="input1" id="cnapsCode" name="cnapsCode" maxlength="50" style="width:320px;"/>
			</div>
		</div>
		<div class="left1_input_ts">
			<div class="savebutton">
				<input type="button"  id="saveOrUpdateBank" name="saveOrUpdateBank" onclick="addBankInfoManager()" value="确定"/>
			</div>
		</div>
	</div>
</form>
</body>
<script language="javascript">
$(function() {
	initProvinceList();
	initBankList();
});
function initProvinceList(){
	$.ajax({
		url : '${path}/bankManager/initProvinceList.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#province").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
function queryCityByList(){
	var province = $('#province').val();
	if(province!=null){
		$("#city").find("option").remove();
		$("#district").find("option").remove();
		$.ajax({
			url : '${path}/bankManager/initCityList.html',
			data : {province : province},
			type : 'post',
			dataType : 'html',
			success : function(result) {
				if (result == null){return;}
				result = result.substring(1,result.length-1);
	            var data1 = result.split('|')
				for (var i = 0; i < data1.length-1; i++) {
					var data2 = data1[i].split(',');
					$("#city").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
}
function queryDistrictByList(){
	var city = $('#city').val();
	if(province!=null){
		$("#district").find("option").remove();
		$.ajax({
			url : '${path}/bankManager/initDistrictList.html',
			data : {city : city},
			type : 'post',
			dataType : 'html',
			success : function(result) {
				if (result == null){return;}
				result = result.substring(1,result.length-1);
	            var data1 = result.split('|')
				for (var i = 0; i < data1.length-1; i++) {
					var data2 = data1[i].split(',');
					$("#district").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
				}
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
}
function initBankList(){
	$.ajax({
		url : '${path}/bankManager/initBankList.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#bankName").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function validateInsertBankForm(){
	var province = $('#province').val();
	if(province==null || $.trim(province)==""){
		alert("请选择省份！");
		return false;
	}
	var bankName = $('#bankName').val();
	if(bankName==null || $.trim(bankName)==""){
		alert("请选择银行名称！");
		return false;
	}
	var branchName = $('#branchName').val();
	if(branchName==null || $.trim(branchName)==""){
		alert("请填写支行名称！");
		return false;
	}
	var cnapsCode = $('#cnapsCode').val();
	if(cnapsCode==null || $.trim(cnapsCode)==""){
		alert("请填写联行号！");
		return false;
	}
	var num = /^[1-9]\d*$/;
	if(!num.test(cnapsCode)){
		alert("联行号必须为数字！");
		return false;
	}
	_load = layer.load('处理中..');
	return true;
}

function addBankInfoManager(){
	if(validateInsertBankForm()){
		var province = $('#province').val();
		var city = $('#city').val();
		var district = $('#district').val();
		var bankName = $('#bankName').val();
		var branchName = $('#branchName').val();
		var cnapsCode = $('#cnapsCode').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/bankManager/saveOrUpdateBankVo.html',
			data : {
				id : 0,
				province : province,
				city : city,
				district : district,
				bankName : bankName,
				branchName : branchName,
				cnapsCode : cnapsCode
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
</html>