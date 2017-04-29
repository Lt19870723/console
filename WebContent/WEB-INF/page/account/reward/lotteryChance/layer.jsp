<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—奖励抽奖机会管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center">
			<tr>
				<td width="120px">用户名：</td>
				<td>${memberVo.username }</td>
			</tr>
			<tr>
				<td width="120px">累积抽奖次数：</td>
				<td>
					<font style="color: red;"></font>${sumNum}
				</td>
			</tr>
			<tr>
				<td width="120px">抽奖机会类型：</td>
				<td>
				<select class="bigselect" name="lotteryChanceRuleInfoId" id="lotteryChanceRuleInfoId">
					<c:forEach var="chanceRuleInfo" items="${chanceRuleInfoList}">
					<option value="${chanceRuleInfo.id}">${chanceRuleInfo.name}</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td width="120px"><font style="color: red; font-weight: 600;">*</font>奖励抽奖次数：</td>
				<td><input type="text" name="lotteryNum" id="lotteryNum"/></td>
			</tr>
			<tr>
				<td width="120px"><font style="color: red; font-weight: 600;">*</font>备注：</td>
				<td colspan="3" width="300px">
				<textarea id="remark" name="remark" style="width:300px;height:100px;" ></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
				 	<input type="button" onclick="javascript:addChanceInfo();" value="确定" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="userId" value="${memberVo.id }" />
		<input type="hidden" name="username"  id="username"  value="${memberVo.username }" />
	</form>
</body>
<script type="text/javascript">
function addChanceInfo(){
	var pattern= /^-?[1-9]\d*$/;
	var lotteryNum = $('#lotteryNum').val();
	var remark = $('#remark').val();
	
	
	if(lotteryNum==null || $.trim(lotteryNum)==""){
		alert("奖励抽奖次数不能为空!");
		return 
	}
	
	if(!pattern.test(lotteryNum)){
		alert("奖励抽奖次数必须为整数！");
		return;
	}
	
	if(lotteryNum>10){
		alert("奖励抽奖次数不能大于10次！");
		return;
	}
	
	if(remark==null || $.trim(remark)==""){
		alert("请填写备注！");
		return;
	}
	if (confirm('确定要进行奖励？')) {
	var _load = parentLayer.load('处理中..');
	
	$("#approForm").ajaxSubmit ({
		url : '${path}/account/lotteryChance/addOrUpdateLottery.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if(result.code == '0'){
				layer.msg('奖励抽奖次数成功', 1, 1,function(){
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
}
// /**
//  * 确定
//  */
// function approPass(){
// 	var pattern= /^-?[1-9]\d*$/;
// 	var points = $('#points').val();
// 	var verifyRemark = $('#verifyRemark').val();
// 	if(points==null || $.trim(points)==""){
// 		alert("奖励元宝不能为空!!");
// 		return false;
// 	}else{
// 		if(!pattern.test(points)){
// 			alert("奖励元宝必须为整数！");
// 			return false;
// 		}
// 		if(points>10000000){
// 			alert("奖励元宝不能大于10000000！");
// 			return false;
// 		}
// 	}
// 	if(verifyRemark==null || $.trim(verifyRemark)==""){
// 		alert("请填写备注！");
// 		return false;
// 	}
// 	var _load = parentLayer.load('处理中..');
// 	$("#approForm").ajaxSubmit ({
// 		url : '${path}/account/pointaward/subPointAward.html',
// 		type : 'post',
// 		dataType : 'json',
// 		success : function(result) {
// 			parentLayer.close(_load);
// 			if(result.code == '0'){
// 				layer.msg('元宝奖励成功', 1, 1,function(){
// 					//location.href="/cxdai_portal/home/index.html";
// 					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
// 					parent.layer.close(index); //执行关闭
// 		    	});
// 			}else{
// 				layer.msg(result.message,2,5);
// 			}
// 		},
// 		error : function(data) {
// 			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
// 			parentLayer.close(_load);
// 		}
// 	});
// }
</script>
</html>
