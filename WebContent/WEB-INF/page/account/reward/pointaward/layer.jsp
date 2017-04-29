<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—奖励管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center">
			<tr>
				<td width="100px">用户名：</td>
				<td>${memberVo.username }</td>
			</tr>
			<tr>
				<td width="100px">累计总元宝：</td>
				<td><font style="color: red;"></font>${memberVo.accumulatepoints }</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td width="100px">可兑换积分：</td> -->
<%-- 				<td><font style="color: red; font-weight: 600;"></font>${memberVo.gainaccumulatepoints }</td> --%>
<!-- 			</tr> -->
			<tr>	
				<td width="100px"><font style="color: red; font-weight: 600;">*</font>奖励元宝：</td>
				<td><input type="text" name="points" id="points"/></td>
			</tr>
			<tr>	
				<td width="100px"><font style="color: red; font-weight: 600;">*</font>明细：</td>
				<td><input type="text" name="detail" id="detail"/></td>
			</tr>
			<tr>
				<td width="100px"><font style="color: red; font-weight: 600;">*</font>备注：</td>
				<td colspan="3" width="300px"><textarea id="verifyRemark" name="verifyRemark"
						style="width:300px;height:100px;" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
						 <input type="button" onclick="javascript:approPass();" value="确定" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="id"   value="${memberVo.id }" />
		<input type="hidden" name="version"   value="" />

	</form>
</body>
<script type="text/javascript">
/**
 * 确定
 */
function approPass(){
	var pattern= /^-?[1-9]\d*$/;
	var points = $('#points').val();
	var verifyRemark = $('#verifyRemark').val();
	var detail = $('#detail').val();
	if(points==null || $.trim(points)==""){
		alert("奖励元宝不能为空!!");
		return false;
	}else{
		if(!pattern.test(points)){
			alert("奖励元宝必须为整数！");
			return false;
		}
		if(points>10000000){
			alert("奖励元宝不能大于10000000！");
			return false;
		}
	}
	if(verifyRemark==null || $.trim(verifyRemark)==""){
		alert("请填写备注！");
		return false;
	}
	if(detail==null || $.trim(detail)==""){
		alert("请填写明细！");
		return false;
	}

	if (confirm('确定要进行奖励？')) {
	 var _load = parentLayer.load('处理中..');
	 $("#approForm").ajaxSubmit ({
		url : '${path}/account/pointaward/subPointAward.html',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			parentLayer.close(_load);
			if(result.code == '0'){
				layer.msg('元宝奖励成功', 1, 1,function(){
					//location.href="/cxdai_portal/home/index.html";
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
</script>
</html>
