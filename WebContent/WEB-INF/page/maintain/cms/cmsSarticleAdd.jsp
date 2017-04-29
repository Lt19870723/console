<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<%@ include file="/WEB-INF/page/common/ueditor.jsp"%>
<script type="text/javascript" src="${path}/js/formValid.js"></script>
<title>文章添加-国诚金融后台管理系统</title>
</head>
<body>		
<form id="insertform" method="post">
	<table style="margin-left:10px;margin-top:5px;width:98%;"  border="1"  >
					<tr>
						<td  align="right"><font color="red">*</font>标题：</td><td> 
						  <input type="text" id="titleForUpdate"  style="width: 550px;"  maxlength="30" class="input1"  onblur="changeSeoTitle()"/>
					  </td>
					</tr>
				
				<tr>
				<td  align="right"><font color="red">*</font>栏目：</td>
				     <td> 
						<select id="channelId1" class="bigselect" style="width:120px;"  >
						  </select>
				    </td>  
			   </tr>
			   
			   <tr>
				<td  align="right">标签：</td>
				     <td>  
						 <input type="text" id="tags"   style="width: 550px;"  maxlength="50" class="input1"  onblur="changeSeoKeyword()" />（如果有多个请用英文逗号“,”分隔）
				    </td>  
			   </tr>
			   
			 <tr>
				<td align="right">缩略图：</td>
				<td>
					<input type="hidden" id="thumbnail" name="thumbnail" value="">
					<img id="oneimageId" style="width:310px;height:310px;" src=""></img><br/>
					<!--<input  class="rz_input mr5" type="file" name="files" id="files" dataType="Require" msg="请选择图片！"  maxlength="180"/>
					<input  class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadPic()" value="图片上传" />-->
					<input  class="rz_input mr5" type="file" name="files" id="btnFile"  onchange="foruploadMore('btnFile','thumbnail','oneimageId')" dataType="Require" msg="请选择图片！" style="width:85px"/>
				（建议尺寸大小  310*310）
				</td>
			 </tr>
				 
			 <tr>
				<td align="right"><font color="red">*</font>文章内容 ：</td>
				<td>
				<textarea    id="content"   name="content"   cols="55"  rows="7"></textarea>
				</td>
			 </tr>
			 
			  <tr>
				<td align="right"><font color="red">*</font>文章摘要：</td>
				<td>
				
				<textarea    id="summary"   name="summary"   cols="80"  rows="7"   onblur="changeSeoDes()"></textarea>
				 
				</td>
			 </tr>
				 
			 <tr>
				<td align="right">点击数：</td>
				<td>
				
				<input type="text"    id="hits" />
				 
				</td>
			 </tr>
			 
			 <tr>
				<td align="right"><font color="red">*</font>来源：</td>
				<td>
				
				<input type="text"    style="width: 550px;" id="author"  maxlength="30"  />
				 
				</td>
			 </tr>
			  
			 <tr>
				<td align="right"><font color="red">*</font>页面源代码title：</td>
				<td>
				<textarea id="seoTitle" maxlength="40"   style="width: 550px;" 	></textarea> 
				</td>
			 </tr>
			 
			  <tr>
				<td align="right"><font color="red">*</font>页面源代码keyword：</td>
				<td>
				<input type="text" id="seoKeywords"  maxlength="40"  style="width: 550px;"	/> 
				</td>
			 </tr>
			 
			 <tr>
				<td align="right"><font color="red">*</font>页面源代码description：</td>
				<td>
				<textarea     id="seoDescription"   cols="80"  rows="5"	></textarea> 
				</td>
			 </tr>
			 
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="pass" name="pass" onclick="addCmsSarticle()" value="提交"/>
				</td> 
			</tr>	
			</table> 
</form>
	<form  id="myfrom1"   name="myfrom1" method="post"  style="position:absolute;top:-1200px;left:-1200px;">
	</form>
</body>
<script language="javascript">

function  changeSeoTitle(){
		var  title= $('#titleForUpdate').val();
		 $('#seoTitle').val(title+"-国诚金融官网");
}

function editPic(){
	 $("#showPic").hide(); 
	 $('#upload').show();
	  
}

function  changeSeoDes(){
		var  summary= $('#summary').val();
		 $('#seoDescription').val(summary);
}

function changeSeoKeyword(){
	var  tags= $('#tags').val();
   if ($.trim(tags).length==0) {
   	var  title= $('#titleForUpdate').val();
   	 $('#seoKeywords').val(title);
	}else{
      $('#seoKeywords').val(tags);
	}
	 
}

$(function() {
	initCmsChannel();
});

function initCmsChannel(){
	$.ajax({
		url : '${path}/cmsArticle/initCmsChannel.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
			jQuery($("#channelId1")).get(0).options.add(new Option("",""));
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#channelId1").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}

function  beforeChecksaveCmsArticle(){
	 
		var  title= $('#titleForUpdate').val();
		
		if ($.trim(title).length==0) {
			alert("请输入标题");
			return false ;
		}
		
     var  channelId1= $('#channelId1').val();
		if ($.trim(channelId1).length==0) {
			alert("请选择栏目");
			return false ;
		}
	   var  content= $('#content').val();
		if ($.trim(content).length==0) {
			alert("请输入文章内容");
			return false ;
		} 
		
		  var  summary= $('#summary').val();
			if ($.trim(summary).length==0) {
				alert("请输入文章摘要");
				return false ;
			}
		
		
		 var  author= $('#author').val();
			if ($.trim(author).length==0) {
				alert("请输入来源");
				return false ;
			}
			
		 var reg = new RegExp("[0-9]+");
		 var  hits= $('#hits').val();
		   if (!reg.test((hits))) {
			   alert("点击数必须为数字");
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
function addCmsSarticle(){
	if(beforeChecksaveCmsArticle()){
		var titleForUpdate = $('#titleForUpdate').val();
		var channelId1 = $('#channelId1').val();
		var tags = $('#tags').val();
		var content = $('#content').val();
		var summary = $('#summary').val();
		var hits = $('#hits').val();
		var author = $('#author').val();
		var seoTitle = $('#seoTitle').val();
		var seoKeywords = $('#seoKeywords').val();
		var seoDescription = $('#seoDescription').val();
		var thumbnail = $('#thumbnail').val();
		var _load = layer.load('处理中..');
		$.ajax({
			url : '${path}/cmsArticle/saveCmsArticle.html',
			data : {
				id : 0,
				title : titleForUpdate,
				channelId : channelId1,
				tags : tags,
				content : content,
				summary : summary,
				hits : hits,
				author : author,
				seoTitle : seoTitle,
				seoKeywords : seoKeywords,
				seoDescription : seoDescription,
				thumbnail : thumbnail
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

function uploadPic(form,thumbnail,oneimageId){

		//验证
		if (Validator.Validate(form,4)==false) return;
		
		//提交
		$("#"+form).ajaxSubmit({
			url : '${path}/cmsArticle/uploadpic.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
						$('#'+thumbnail).val(result.message);
						$('#'+oneimageId).attr("src", result.message);
						layer.alert('上传成功!!',1);
				}else{
					layer.msg(result.message,1);
				}
			},
			error : function(result) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function foruploadMore(btnFileid,thumbnail,oneimageId){
		
		if (!Validator.Require.test($('#'+btnFileid).val())) {
			layer.alert("请选择图片！");
			return;
		}
		
		$('#myfrom1').html("");
		
		var oldElement = $('#' + btnFileid);
		var newElement = $(oldElement).clone();
		$(oldElement).attr('id', 'btnFileMore');
		$(oldElement).before(newElement);
		$(oldElement).appendTo($('#myfrom1'));
		
		uploadPic('myfrom1',thumbnail,oneimageId);

	}
	/**
	 * 编辑器样式
	 */
	 var modifyUm;
	$(function(){
		 
		modifyUm= UM.getEditor('content', {
			initialFrameWidth:700,
			initialFrameHeight:200,
			autoHeightEnabled:false
		});
	});
</script>
</html>