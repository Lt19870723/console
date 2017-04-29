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
	&nbsp;&nbsp;
	省市区：
		<select id="province"class="bigselect" style="width:120px;" onchange="queryCityByList();">
			<option value="">--请选择--</option>
		</select>
	 &nbsp;
		<select id="city" class="bigselect" style="width:120px;" onchange="queryDistrictByList();">
			<option value="">--请选择--</option>
		</select>
		 &nbsp;
		<select id="district" class="bigselect" style="width:120px;">
			<option value="">--请选择--</option>
		</select>
	 &nbsp;
	 银行名称：
		<select id="bankName" class="bigselect">
			<option value="">--请选择--</option>
		</select>
	 &nbsp;
	 <br/>
	 支行名称：
	<input type="text" class="input1" id="branchName" name="branchName" maxlength="50" style="width:200px;"/>
	&nbsp;
	联行号：
	<input type="text" class="input1" id="cnapsCode" name="cnapsCode" maxlength="50" />
	&nbsp;
	状态：
	<select id="status" class="bigselect" style="width:120px;">
		<option value="-2">--请选择--</option>
		<option value="0">正常</option>
		<option value="-1">失效</option>
	</select>
	&nbsp;	
	<input type="button" id="searchBankList" name="searchBankList" onclick="pageGo(1)" value="查询"/>
	&nbsp;
	<input type="button"  id="forInsertBank" name="forInsertBank" onclick="addBankManager()" value="新增"/>
	</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th>序号  </th>
				<th>省</th>
				<th>市</th>
				<th>区</th>
				<th>银行名称</th>
				<th>支行名称</th>
				<th>联行号 </th>
				<th>添加人</th>
				<th>添加时间 </th>
				<th>修改人  </th>
				<th>修改时间 </th>
				<th>状态 </th>
				<th>操作 </th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	initProvinceList();
	initBankList();
	pageGo(1);
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
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	var province = $('#province').val();
	var city = $('#city').val();
	var district = $('#district').val();
	var bankName = $('#bankName').val();
	var branchName = $('#branchName').val();
	var cnapsCode = $('#cnapsCode').val();
	var status = $('#status').val();
	$.ajax({
		url : '${path}/bankManager/serachAll/' + pageNo + '.html',
		data : {
			province : province,
			city : city,
			district : district,
			bankName : bankName,
			branchName : branchName,
			cnapsCode : cnapsCode,
			status : status
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
function delBank(bankId,pageNo){
	layer.confirm("确定删除?",function(){
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/bankManager/delBankVo.html',
			data : {
				bankId : bankId
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
function addBankManager(){
	var _url = '${path}/bankManager/toBankManagerAdd.html';
	$.layer({
		type : 2,
		title : '银行信息添加',
		area : [ '800px', '430px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function editBank(bankId){
	var _url = '${path}/bankManager/initBankVo/'+bankId+'.html';
	$.layer({
		type : 2,
		title : '银行信息修改',
		area : [ '800px', '430px' ],
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