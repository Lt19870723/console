<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—提现管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="checkBorrowPopupform" action="" method="post">
	<table style="margin-left:10px;margin-top:5px;">
	<tr>
		<td>IP白名单信息(* 必填)</td>
		<td>
		   <input id="ip" name="ip" maxlength="20" class="input1" value="${iPWhileList.ip}"/>
		</td>
	</tr>
	<tr>
		<td>访问接口类型：</td>
		<td>
	      <select  name="accessType" id="accessType" class="input1"
				class="bigselect" style="width:130px;">
			  		<c:forEach items="${thirdPlatformList }" var="vo">
			  		 <c:if test="${vo.value==iPWhileList.accessType}"> 
			  		   <option value="${vo.value }"selected="selected">${vo.label }</option>
			  		 </c:if>
			  		 <c:if test="${vo.value!=iPWhileList.accessType}"> 
			  		   <option value="${vo.value }" >${vo.label }</option>
			  		 </c:if>
			  		</c:forEach> 
		  </select>
		</td>
	</tr> 
	<tr>
		<td>公司名称</td>
		<td>
		   <input class="input1" id="company" name="company" value="${iPWhileList.company}"   maxlength="50"  />
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" onclick="javascript:save();" value="保 存" />
		</td>
	</tr>	
	</table>
	  <input type="hidden" id="id" name="id" value="${iPWhileList.id}"/>
	</form>
</body>
<script type="text/javascript">

var _load;

function save(){
	if(!validateInsertIPWhileForm()){
		return false;
	} 
	_load = layer.load('处理中..');
	$.ajax({
		url : '${path}/system/ipWhile/saveOrUpdateIPWhile.html',
		data : {
			'id' : $("#id").val(),
			'company' : $.trim($("#company").val()),
			'accessType' : $("#accessType").val(),
			'ip':$("#ip").val()
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.code == '0') {
				layer.msg(result.message, 1, 1, function() {
					parentLayer.close(_load);
					var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
					parent.layer.close(index); //执行关闭
					layer.msg(result.message,1,1);
				});
			}else  {
				parentLayer.close(_load);
				layer.msg(result.message, 1, 5);
			}
		},
		error : function(result) {
			layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			parentLayer.close(_load);
	    }
	}); 
}
function validateInsertIPWhileForm(){
	var ip = $('#ip').val();
	if(ip==null || $.trim(ip)==""){
		alert("请填写IP地址！");
		return false;
	}
	var company = $('#company').val();
	if(company==null || $.trim(company)==""){
		alert("请填写公司名称！");
		return false;
	}
	return true;
}
</script>
</html>
