<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权证人员编辑</title>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>
<body>
<form id="businessForm" name="businessForm" method="post">
	<input type="hidden" id="id" name="id" value="${businessVo.id}" />
	<input type="hidden" id="status" name="status" value="${businessVo.status}" />	
	<input type="hidden" id="userId" name="userId" value="${businessVo.userId}" />		
	<table style="margin-left: 10px; margin-right: 10px; margin-top: 5px; width:95%">
		<tr>
			<td colspan="2"><div class="listzent">权证人员信息</div></td>
		</tr>
		<tr></tr>
		<tr>
			<td align="right" width="30%"><span class="span_red"></span>权证人员</td>
			<td width="50%">
				<input type="text" id="username" name="username" class="input1" value="${businessVo.username}" readonly/>&nbsp;&nbsp;				
				<input id="selectBtn" style="width: 100px" name="selectBtn"  value="查询" type="button" onclick="queryBusiness();" />
			</td>			
		</tr>		
		<tr></tr>		 
		<tr>
			<td align="center" colspan="2">
				<input type="button" id="saveBtn" name="saveBtn" onclick="save();" value="保存" />
			</td>
		</tr>		
		<tr></tr>
	</table>
</form>
</body>
<script type="text/javascript">
var index = parent.layer.getFrameIndex(window.name);
//保存
function save() {
	if(validateFrom("save")==false){
		return;
	}
	//草稿中
	$("#status").val("0");
	parentLayer.confirm("确认保存?", function(index) {
		parentLayer.close(index);
		var _load = layer.load(2);
		$("#businessForm").ajaxSubmit({
			url : '${path}/information/business/businessSave.html',		
			type : 'post',
			dataType : 'json',
			success : function(result) {			
				if (result.code == '0') {					
					layer.msg(result.message,1,1,function(){
						window.parent.location=window.parent.location;
			    	});
				} else {					
					layer.msg(result.message, 1, 5);
					$('#saveBtn').attr("onclick", "save();");
				}
			},
			error : function(result) {	
				$('#saveBtn').attr("onclick", "save();");
				layer.msg('网络连接超时,请您稍后重试.',  1, 5);
		    }
		});
	});
}

//校验表单
function validateFrom(type){
	$('#'+type+'Btn').attr("onclick", "");
	if ($.trim($("#username").val()) == "") {
		alert("请选择权证人员！");
		$('#'+type+'Btn').attr("onclick", type+"();");
		return false;
	}	
}
//打开客户页面
function queryBusiness(){
	var _url = '${path}/information/business/memberSelect.html';	
	$.layer({
		type : 2,
		title : "权证人员查询",
		area : [ '800px', '300px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
//获取客户信息
function getPushSelectedBusiness(userId,username){
	 $("#userId").val(userId);
	 $("#username").val(username); 
}
</script>
</html>