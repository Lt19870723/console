<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title></title>
</head>
<body>

<div >
	
	<div class="left1_input_tst" style="margin-bottom:210px;">
		<font class="red">&nbsp;图片建议像素为310*200</font>
		<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>图片：</span>
		<div class="input_IDs">
			<img id="oneimageId" style="width:310px;height:200px;'" src=""></img>
			<form  id="myfrom"   name="myfrom" method="post" >
				<div id="fileupload">
					<input  class="rz_input mr5" type="file" name="files" id="files" dataType="Require" msg="请选择图片！"  maxlength="180"/>
					<input  class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadPic('myfrom')" value="图片上传" />
				</div>
			</form>
		</div>
	</div>
	
	<form id="approForm" name="approForm" method="post">
	
		<div class="left1_input_tst"  style="margin-bottom:5px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>标题：</span>
			<div class="input_IDs">
				 <input type="text" id="titleForUpdate"  style="width: 550px;"  maxlength="30" class="input1"  onblur="changeSeoTitle()"/>
				 <input type="hidden" id="thumbnail" name="thumbnail" value="">
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:5px;" id="ordertr">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>栏目：</span>
			<div class="input_IDs">
				<select id="channelId1" class="bigselect" style="width:120px;"  >
						  </select>
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red"></span>标签：</span>
			<div class="input_IDs">
				<input type="text" id="tags"   style="width: 550px;"  maxlength="50" class="input1"  onblur="changeSeoKeyword()" />（如果有多个请用英文逗号“,”分隔）
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red"></span>文章内容：</span>
			<div class="input_IDs">
				<textarea    id="content"   name="content"   cols="80"  rows="3"></textarea>
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>文章摘要：</span>
			<div class="input_IDs">
				<textarea    id="summary"   name="summary"   cols="80"  rows="3"   onblur="changeSeoDes()"></textarea>
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>点击数：</span>
			<div class="input_IDs">
				<input type="text"    id="hits" />
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>来源：</span>
			<div class="input_IDs">
				<input type="text"    style="width: 550px;" id="author"  maxlength="30"  />
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>页面源代码title：</span>
			<div class="input_IDs">
				<textarea id="seoTitle" maxlength="40"   style="width: 550px;" 	></textarea> 
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>页面源代码keyword：</span>
			<div class="input_IDs">
				<input type="text" id="seoKeywords"  maxlength="40"  style="width: 550px;"	/> 
			</div>
		</div>
		
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>页面源代码description：</span>
			<div class="input_IDs">
				<textarea     id="seoDescription"   cols="80"  rows="2"	></textarea> 
			</div>
		</div>
		
		<div class="savebutton">
			<input type="button" onclick="javascript:addCmsSarticle()" type="button" name="Submit1" id="subbtn" class="b_buts" value="提交" /> 
		</div>
	</form>
</div>
</body>
<script type="text/javascript">

	function  changeSeoTitle(){
		var  title= $('#titleForUpdate').val();
		 $('#seoTitle').val(title+"-国诚金融官网");
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

function uploadPic(form){
		var files = $('#files').val();
		if(null==files || $.trim(files)==""){
			alert("请选择需要上传的图片!!");
			return false;
		}
		$("#"+form).ajaxSubmit ({
			url : '${path}/cmsArticle/upload.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '0') {
						$('#thumbnail').val(result.message);
						$('#oneimageId').attr("src", '${cmsChannelPath}' + result.message);
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

</script>
</html>