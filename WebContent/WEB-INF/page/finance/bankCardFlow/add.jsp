<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
</head>
<body>
   <form id="approForm" name="" method="post">
   	<input type="hidden" id="prentId" value="${id }">
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
			<tr>
				<td align="right" width="25%"><font color="red">*</font>类型名称：</td>
				<td colspan="3">
					<input type="text" id="typeName" name="typeName" value="">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<input type="button" id="saveBtn" name="saveBtn" onclick="saveType();" value="保存" />
				</td>
			</tr>	
		</table>
</form>

<div style="display: none;width: 500px;height: 300px;" id="hiddenDiv">
		类型名称:<input type="text" id="typeName" name="typeName" value="">
</div>
</body>
<script type="text/javascript">
saveType=function(){
	var typeName= $("#typeName").val();
	var ecTypeName = encodeURI(encodeURI(typeName)) ;
	var prentId= $("#prentId").val();
	var _load = parentLayer.load('处理中..');
	$.ajax({
		url : '${path}/finance/flow/saveType.html?type=1800&order=78&name=78&value='+ecTypeName,
		type : 'post',
		dataType : 'html',
		success : function(result) {
			parentLayer.close(_load);
			var dataObj=eval("("+result+")");
			if(dataObj.isSuccess){
				layer.msg('保存成功！', 1, 1,function(){
					window.parent.location.href = window.parent.location.href;
					if(prentId == '' || prentId == null){
						edit();
					}else{
						updateFlow(prentId);
					}
				});
				
				
			}else{
				layer.msg('保存失败！'+dataObj.message, 1, 5);
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}


updateFlow = function(id){
	var _url = "${path}/finance/flow/updateFlow/"+id+".html";
	$.layer({
		type : 2,
		fix : false,
		shade : [ 0 ],
		title : '编辑流水记录',
		area : [ '1000px', '500px' ],
		offset : [ '7px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});

}


function edit() {
	var _url = "${path}/finance/flow/edit.html";
	$.layer({
		type : 2,
		fix : false,
		shade : [ 0 ],
		title : '添加记录',
		area : [ '1000px', '500px' ],
		offset : [ '7px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});

}
</script>
</html>
