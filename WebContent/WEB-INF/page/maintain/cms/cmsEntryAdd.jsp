<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>词条添加-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<table style="margin-left:10px;margin-top:5px;width:98%;"  border="1"  >
			<tr>
				<td  align="right"> <font color="red">*</font>词条名称：</td><td> 
                      <input type="text" id="titleForUpdate"  style="width: 550px;"  maxlength="30" class="input1"/>
			  </td>
			</tr>
			  <tr>
				<td align="right"><font color="red">*</font>描述：</td>
				<td>
				<textarea id="desc" name="desc" cols="80" rows="7"/></textarea>
				</td>
			 </tr>
			 <tr>
				<td align="right"><font color="red">*</font>页面源代码title：</td>
				<td>
				<input type="text" id="seoTitle"  maxlength="40" style="width: 550px;"/> 
				</td>
			 </tr>
			  <tr>
				<td align="right"><font color="red">*</font>页面源代码keyword：</td>
				<td>
				<input type="text" id="seoKeywords" maxlength="40" style="width: 550px;"/> 
				</td>
			 </tr>
			 <tr>
				<td align="right"><font color="red">*</font>页面源代码description：</td>
				<td>
				<textarea id="seoDescription" cols="80" rows="5" /></textarea> 
				</td>
			 </tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button"  id="pass" name="pass" onclick="addCmsEntry()" value="提交"/>
				   
				</td> 
			</tr>	
				
		</table>
</form>
</body>
<script language="javascript">
/**
 * 编辑器样式
 */
$(function(){
	 
	var modifyUm = UM.getEditor('desc', {
		initialFrameWidth:600,
		initialFrameHeight:150,
		autoHeightEnabled:false
	});
});
 


function  beforeChecksaveCmsPediaEntry(){
	var  title= $('#titleForUpdate').val();
	if ($.trim(title).length==0) {
		layer.alert("请输入栏目名称");
		return false ;
	}
	 var  desc= $('#desc').val();
		if ($.trim(desc).length==0) {
			layer.alert("请输入描述");
			return false ;
		} 
	 var  seoTitle= $('#seoTitle').val();
	   if ($.trim(seoTitle).length==0) {
		   layer.alert("请输入页面源代码title");
		   return false;
	   }
	   
	   var  seoKeywords= $('#seoKeywords').val();
	   if ($.trim(seoKeywords).length==0) {
		   layer.alert("请输入页面源代码keyword");
		   return false;
	   }
	   var  seoDescription= $('#seoDescription').val();
	   if ($.trim(seoDescription).length==0) {
		   layer.alert("请输入页面源代码description");
		   return false;
	   }
	return true;
}  
function addCmsEntry(){
	if(beforeChecksaveCmsPediaEntry()){
		var titleForUpdate = $('#titleForUpdate').val();
		var desc = $('#desc').val();
		var seoTitle = $('#seoTitle').val();
		var seoKeywords = $('#seoKeywords').val();
		var seoDescription = $('#seoDescription').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/cmsEntry/saveCmsPediaEntry.html',
			data : {
				id : 0,
				name : titleForUpdate,
				desc : desc,
				seoTitle : seoTitle,
				seoKeywords : seoKeywords,
				seoDescription : seoDescription
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