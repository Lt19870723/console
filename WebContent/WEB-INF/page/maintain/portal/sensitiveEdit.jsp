<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>		
	<table style="margin-left:10px;margin-top:5px;">
		<tr>
			<td>关键字</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" id="senId" name="senId" value="${SensitiveVo.id }"/>
		 		<textarea id="word" style="width:350px;height:80px;">${SensitiveVo.word }</textarea>
			</td>
		</tr>
	 	<tr>
			<td>类型</td>
		</tr>
		 	<td>
			  <select id="type" class="bigselect" style="width:350px;"></select>
			</td>
	 <tr>
	  <td>来源 </td>
	</tr>	
	<tr>
			<td> 
		 		<select id="source" class="bigselect" style="width:350px;">
					 <option value="1" <c:if test="${SensitiveVo.source==1}">selected="selected"</c:if>>注册敏感词</option>
					 <option value="2" <c:if test="${SensitiveVo.source==2}">selected="selected"</c:if>>bbs敏感词</option>
		 		</select>
			</td>
	 </tr>	
	<tr>
		<td colspan="2">
			<input type="button"  id="save" name="save" onclick="updateSensitive()" value="保存"/>
		</td>
	</tr>	
	</table>
</body>
<script language="javascript">
$(function() {
	querySensitiveTypeList();
});
function querySensitiveTypeList(){
	$.ajax({
		url : '${path}/sensitive/querySensitiveTypeList.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
			jQuery($("#type")).get(0).options.add(new Option("",""));
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				if("${SensitiveVo.type}"==data2[0]){
					$("#type").append("<option selected='selected' value=\"" + data2[0] + "\">" + data2[1] + "</option>");
				}else{
					$("#type").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
				}
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
/**
 *  验证不能为空
 */
function beforeCheckFirstBorrow(){
	var creditRating = $('#word').val();
	if(creditRating==null || creditRating==""){
		alert("请输入关键字。");
		return false;
	}
	/**
	var type = $('#type').val();
	if(type==null || type == 0){
		alert("请选择类型");
		return false;
	}
	*/
	return true;
}
function updateSensitive(){
	if(beforeCheckFirstBorrow()){
		var word = $('#word').val();
		var type = $('#type').val();
		var source = $('#source').val();
		var senId = $('#senId').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/sensitive/updateSensitive.html',
			data : {
				word : word,
				type : type,
				source : source,
				id : senId
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