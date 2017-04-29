<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>栏目添加-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform">
	<table style="margin-left:10px;margin-top:5px;width:98%;"  border="1"  >
					<tr>
						<td  align="right"><font color="red">*</font>栏目名称：</td><td> 
						  <input type="text" id="titleForUpdate"  style="width: 550px;" class="input1" maxlength="30"/>
					  </td>
					</tr>
					
					<tr>
						<td  align="right">父栏目：</td><td> 
						  <select id="ChannelParentID" class="bigselect" style="width:120px;" >
						  </select>（不选择默认创建父栏目）
					  </td>
					</tr>
					<tr    style="display:none"   id="ordertr"  >
						<td  align="right">排序：</td><td> 
						  <input type="text" id="order"  class="input1"/>
					  </td>
					</tr>
				 
			  <tr>
				<td align="right"><font color="red">*</font>描述：</td>
				<td>
				<textarea     id="desc"   name="desc"   cols="80"  rows="7"></textarea>
				</td>
			 </tr>
				 
			 <tr    style="display:none"    id="urlCodetr" >
				<td align="right">url_code：</td>
				<td>
				
				<input type="text" id="urlCode" />
				 
				</td>
			 </tr>
			 <tr>
				<td align="right"><font color="red">*</font>页面源代码title：</td>
				<td>
				<input type="text" id="seoTitle"  style="width: 550px;" maxlength="40"	/> 
				</td>
			 </tr>
			 
			  <tr>
				<td align="right"><font color="red">*</font>页面源代码keyword：</td>
				<td>
				<input type="text" id="seoKeywords" style="width: 550px;" maxlength="40"/> 
				</td>
			 </tr>
			 
			 <tr>
				<td align="right"><font color="red">*</font>页面源代码description：</td>
				<td>
				<textarea      id="seoDescription"   cols="80"  rows="5"></textarea> 
				</td>
			 </tr>
			 
			 
			<tr>
				<td colspan="2" align="center">
					<input type="button" onclick="addCmsChannel()" id="pass" name="pass" value="提交"/>
					<%-- <a4j:commandButton id="pass" name="pass" value="提交"
						onclick="return  beforeChecksaveCmsChannel()"  
						action="#{cmsChannelAction.saveCmsChannel}"
						execute="@form"
						data="#{cmsChannelAction.msg}"
						oncomplete="aftersaveCmsChannel(event.data)"  >  
					</a4j:commandButton>     --%> 
				</td> 
			</tr>	
			</table> 
</form>
</body>
<script language="javascript">
$(function() {
	initParentChannelList();
});

function initParentChannelList(){
	$.ajax({
		url : '${path}/cmsChanel/initParentChannelList.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
			jQuery($("#ChannelParentID")).get(0).options.add(new Option("",""));
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#ChannelParentID").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}



function  beforeChecksaveCmsChannel(){
	var  title= $('#titleForUpdate').val();
	if ($.trim(title).length==0) {
		alert("请输入栏目名称");
		return false ;
	}
	
	   var  desc= $('#desc').val();
	   if ($.trim(desc).length==0) {
		   alert("请输入描述");
		   return false;
	   }
	   var  seoTitle= $('#seoTitle').val();
	   if ($.trim(seoTitle).length==0) {
		   alert("请输入页面源代码title");
		   return false;
	   }
	   
	   var  seoKeywords= $('#seoKeywords').val();
	   if ($.trim(seoKeywords).length==0) {
		   alert("请输入页面源代码keyword");
		   return false;
	   }
	   
	   var  seoDescription= $('#seoDescription').val();
	   if ($.trim(seoDescription).length==0) {
		   alert("请输入页面源代码description");
		   return false;
	   }
	return  true  ;
}  
function addCmsChannel(){
	if(beforeChecksaveCmsChannel()){
		var titleForUpdate = $('#titleForUpdate').val();
		var ChannelParentID = $('#ChannelParentID').val();
		var desc = $('#desc').val();
		var seoTitle = $('#seoTitle').val();
		var seoKeywords = $('#seoKeywords').val();
		var seoDescription = $('#seoDescription').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/cmsChanel/saveCmsChannel.html',
			data : {
				id : 0,
				name : titleForUpdate,
				parentId : ChannelParentID,
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