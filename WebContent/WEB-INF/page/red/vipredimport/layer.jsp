<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—红包管理</title> 
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center">
			<tr>
				<td width="100px"><font style="color: red; font-weight: 600;">*</font>用户名：</td>
				<td><input id="useName" class="input1" name="useName"/>&nbsp;&nbsp;</td>
			</tr>
			<tr>
			<td width="100px"><font style="color: red; font-weight: 600;">*</font>红包类型：</td>
		    <td>
		    <select id="redType" name="redType" class="bigselect" onchange="changeredType()">
		    	<option value="">--请选择--</option>
				<option value="1930">贵宾特权红包</option>
				<option value="1990">50亿活动红包</option>
				<option value="1970">新人活动红包</option>
				<option value="1980">活动推荐红包</option>
				<option value="2000">奖励红包</option>
				<option value="2010">活动红包</option>
			</select>
			</td>
			</tr>
			<tr>
			<td width="100px"><font style="color: red; font-weight: 600;">*</font>红包金额：</td>
			 <td id="redMoneyTemp4">
		     <select  class="bigselect">
				<option value="">--请选择--</option>
			</select>
			</td>
		    <td id="redMoneyTemp1" style="display: none;">
		     <select id="redMoney" name="redMoney" class="bigselect" >
				   <option value="">--请选择--</option>
					<c:forEach items="${redInfos.billReds}" var="billReds">
				      <option value="${billReds.redMoney}">${billReds.redMoney}</option>
					</c:forEach>
			</select>
			</td>
			 <td id="redMoneyTemp2" style="display: none;">
		       <select id="vipRedMoney" name="vipRedMoney" class="bigselect" >
		        <option value="">--请选择--</option>
				<c:forEach items="${redInfos.vipReds}" var="vipReds">
			      <option value="${vipReds.redMoney}">${vipReds.redMoney}</option>
				</c:forEach>
			  </select>
			</td>
			 <td id="redMoneyTemp3" style="display: none;">
		     <select id="newRedMoney" name="newRedMoney" class="bigselect">
					 <option value="">--请选择--</option>
					<c:forEach items="${redInfos.activeReds}" var="activeReds">
				      <option value="${activeReds.redMoney}">${activeReds.redMoney}</option>
					</c:forEach>
			</select>
			</td>
			<td id="redMoneyTemp5" style="display: none;">
		     <select id="interRedMoney" name="interRedMoney" class="bigselect">
				 <option value="">--请选择--</option>
				<c:forEach items="${redInfos.interReds}" var="interReds">
			      <option value="${interReds.redMoney}">${interReds.redMoney}</option>
				</c:forEach>
			</select>
			</td>
			<td id="redMoneyTemp6" style="display: none;">
		     <select id="rewardRedMoney" name="rewardRedMoney" class="bigselect">
				 <option value="">--请选择--</option>
				<c:forEach items="${redInfos.rewardReds}" var="rewardReds">
			      <option value="${rewardReds.redMoney}">${rewardReds.redMoney}</option>
				</c:forEach>
			</select>
			</td>
			<td id="redMoneyTemp7" style="display: none;">
		     <select id="huodongRedMoney" name="huodongRedMoney" class="bigselect">
				 <option value="">--请选择--</option>
				<c:forEach items="${redInfos.huodongReds}" var="huodongReds">
			      <option value="${huodongReds.redMoney}">${huodongReds.redMoney}</option>
				</c:forEach>
			</select>
			</td>
			</tr>
			<tr>
				<td width="100px"><font style="color: red; font-weight: 600;">*</font>备注：</td>
				<td colspan="3" width="300px"><textarea id="remark" name="remark"
						style="width:300px;height:100px;" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
				  <input type="button" onclick="javascript:approPass();" value="确定" />&nbsp;&nbsp;&nbsp;&nbsp;
				  <input type="button" onclick="javascript:closeWindow();" value="取消" />
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
function changeredType(){
	if($("#redType").val()==1930){
		$("#redMoneyTemp1").hide();
		$("#redMoneyTemp3").hide();
		$("#redMoneyTemp4").hide();
		$("#redMoneyTemp5").hide();
		$("#redMoneyTemp2").show();
		$("#redMoneyTemp6").hide();
		$("#redMoneyTemp7").hide();
	}else if($("#redType").val()==1990){
		$("#redMoneyTemp1").show();
		$("#redMoneyTemp5").hide();
		$("#redMoneyTemp2").hide();
		$("#redMoneyTemp3").hide();
		$("#redMoneyTemp4").hide();
		$("#redMoneyTemp6").hide();
		$("#redMoneyTemp7").hide();
	}else if($("#redType").val()==1970){
		 $("#redMoneyTemp1").hide();
		 $("#redMoneyTemp2").hide();
		 $("#redMoneyTemp4").hide();
		 $("#redMoneyTemp5").hide();
		 $("#redMoneyTemp3").show();
		 $("#redMoneyTemp7").hide();
	}else if($("#redType").val()==1980){
		 $("#redMoneyTemp1").hide();
		 $("#redMoneyTemp2").hide();
		 $("#redMoneyTemp4").hide();
		 $("#redMoneyTemp5").show();
		 $("#redMoneyTemp3").hide();
		 $("#redMoneyTemp6").hide();
		 $("#redMoneyTemp7").hide();
	}else if($("#redType").val()==2000){
		 $("#redMoneyTemp1").hide();
		 $("#redMoneyTemp2").hide();
		 $("#redMoneyTemp4").hide();
		 $("#redMoneyTemp5").hide();
		 $("#redMoneyTemp3").hide();
		 $("#redMoneyTemp6").show();
		 $("#redMoneyTemp7").hide();
	}else if($("#redType").val()==2010){
		 $("#redMoneyTemp1").hide();
		 $("#redMoneyTemp2").hide();
		 $("#redMoneyTemp4").hide();
		 $("#redMoneyTemp5").hide();
		 $("#redMoneyTemp3").hide();
		 $("#redMoneyTemp6").hide();
		 $("#redMoneyTemp7").show();
	}else{
		 $("#redMoneyTemp1").hide();
		 $("#redMoneyTemp2").hide();
		 $("#redMoneyTemp4").show();
		 $("#redMoneyTemp3").hide();
		 $("#redMoneyTemp5").hide();
		 $("#redMoneyTemp6").hide();
		 $("#redMoneyTemp7").hide();
	}
}


/**
 * 确定
 */
function approPass(){
	var pattern= /^-?[1-9]\d*$/;
	var useName = $('#useName').val();
	var remark = $('#remark').val();
	var redType = $('#redType').val();
	var vipRedMoney = $('#vipRedMoney').val();
	var newRedMoney = $('#newRedMoney').val();
	var redMoney = $('#redMoney').val();
	var interRedMoney = $('#interRedMoney').val();
	if(useName==null || $.trim(useName)==""){
		alert("用户名不能为空!");
		return false;
	}
	if(remark==null || $.trim(remark)==""){
		alert("请填写备注!");
		return false;
	}else{
		if(remark.length>190){
			alert("备注太长!");
			return false;
		}
	}
	if(redType==null || $.trim(redType)==""){
		alert("红包类型不能为空!");
		return false;
	}else{
		if(redType==1930){
			if(vipRedMoney==null || $.trim(vipRedMoney)==""){
				alert("红包金额不能为空!");
				return false;
			 }
			}
		if(redType==1970){
			if(newRedMoney==null || $.trim(newRedMoney)==""){
				alert("红包金额不能为空!");
				return false;
			 }
			}
		if(redType==1980){
			if(interRedMoney==null || $.trim(interRedMoney)==""){
				alert("红包金额不能为空!");
				return false;
			 }
			}
		if(redType==1990){
			if(redMoney==null || $.trim(redMoney)==""){
				alert("红包金额不能为空!");
				return false;
			 }
			}
	}
	var _load = parentLayer.load('处理中..');
	$("#approForm").ajaxSubmit ({
		url : '${path}/vipredImport/submit.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if(result.code == '0'){
				layer.msg('单个新增成功', 1, 1,function(){
					 parent.window.location.href=parent.window.location.href;
					 var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					 parent.layer.close(index); //执行关闭
		    	});
				
			}else{
				layer.msg(result.message,2,5);
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			parentLayer.close(_load);
		}
	});
}
function closeWindow(){
	 var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	 parent.layer.close(index); //执行关闭
}
</script>
</html>
