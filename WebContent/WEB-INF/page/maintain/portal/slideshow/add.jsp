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
	<div class="left1_input_tst"  style="margin-bottom:5px;">
		<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>类型：</span>
		<div class="input_IDs">
		 	<select class="bigselect" id="sType" name="sType" onchange="changePicTip()">
				<option value="">--请选择类型--</option>
				<option value="1" ${slideshow.sType==1?'selected':'' }>官网首页</option>
				<option value="7" ${slideshow.sType==7?'selected':'' }>官网首页公司动态</option>
				<option value="2" ${slideshow.sType==2?'selected':'' }>资讯首页</option>
				<option value="3" ${slideshow.sType==3?'selected':'' }>论坛首页</option>
				<option value="4" ${slideshow.sType==4?'selected':'' }>论坛首页视频</option>
				<option value="5" ${slideshow.sType==5?'selected':'' }>移动端首页</option>
				<option value="6" ${slideshow.sType==6?'selected':'' }>移动端推荐</option>
  			</select>
		</div>
	</div>
	<div class="left1_input_tst" style="margin-bottom:210px;">
		<font class="red" style="font-size: 11px;" id="tipFont"></font>
		<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>图片：</span>
		<div class="input_IDs">
			<img id="oneimageId" ${type=='update'?'style="width:360px;height:200px;display: none"':'style="width:360px;height:200px;' } src=""></img>
			<form  id="myfrom"   name="myfrom" method="post" >
			<div id="fileupload" ${type=='update'?'style="display: none"':'' }>
				<input  class="rz_input mr5" type="file" name="files" id="files" dataType="Require" msg="请选择图片！"  maxlength="180"/>
				<input  class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadPic('myfrom')" value="图片上传" />
			</div>	
				<div  id="showPic" ${type=='add'?'style="display: none"':'' }> <img  width="360px"  height="200px"   src="${slideshow.sImage}" />  <a href="javascript:editPic()">编辑</a> 
					 		</div>
			</form>
		</div>
	</div>
	<form id="approForm" name="approForm" method="post">
	
		<div class="left1_input_tst"  style="margin-bottom:5px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>标题：</span>
			<div class="input_IDs">
				<input type="text" id="title" name="title" value="${slideshow.title }" class="input1" dataType="Require" msg="请输入标题" style="width:90%" maxlength="64" />
				<input name="sImage" id="sImage" value="${slideshow.sImage}" type="hidden"/>
				<input id="id" name="id" value="${slideshowId }" type="hidden"/>
			</div>
		</div>
		<c:if test="${type=='update'}">
			<div class="left1_input_tst"  style="margin-bottom:5px;" id="ordertr">
				<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>排序：</span>
				<div class="input_IDs">
					<input type="text" id="order" name="order" value="${slideshow.order }" class="input1" dataType="Require" msg="请输入排序号" style="width:90%" maxlength="64" />
				</div>
			</div>
		</c:if>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red"></span>超链接：</span>
			<div class="input_IDs">
				<input type="text" id="imageUrl" name="imageUrl" value="${slideshow.imageUrl}" dataType="Require" msg="请输入超链接" class="input1" style="width:90%" maxlength="255" />
			</div>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red"></span>备注：</span>
			<div class="input_IDs">
				<textarea id="description" name="description"  rows="2" dataType="Require" msg="请输入描述" class="input2" style="width:90%">${slideshow.description}</textarea>
			</div>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>是否立即生效：</span>
			<div class="input_IDs">
				<c:if test="${slideshow.createTime==slideshow.startTime}">
					<input type="radio" id="ra1" name="ra1" value="1" checked="checked" onclick="timeHide()">是</input>
				  	<input type="radio" id="ra2" name="ra1" value="2" onclick="timeShow()">否</input>
				</c:if>
				<c:if test="${slideshow.createTime!=slideshow.startTime}">
					<input type="radio" id="ra1" name="ra1" value="1" onclick="timeHide()">是</input>
				  	<input type="radio" id="ra2" name="ra1" value="2" checked="checked" onclick="timeShow()">否</input>
				</c:if>
			</div>
		</div>
		<div class="left1_input_tst" ${(slideshow.createTime!=slideshow.startTime)?'style="margin-bottom:10px;"':'style="margin-bottom:10px;display: none"' }  id="startTimeTr">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>发布开始时间：</span>
			<div class="input_IDs">
				<input  name="startTime" id="startTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" styleClass="Wdate"  value="<fmt:formatDate value="${slideshow.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</div>
		</div>
		<div class="left1_input_tst" ${(slideshow.createTime!=slideshow.startTime)?'style="margin-bottom:10px;"':'style="margin-bottom:10px;display: none"' }  id="endTimeTr">
			<span class="input_span"  style="padding-top:10px;"><span class="red"></span>发布结束时间：</span>
			<div class="input_IDs">
				<input  name="endTime" id="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" styleClass="Wdate" value="<fmt:formatDate value="${slideshow.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					  		<font color="red">(如果结束时间为空，则永久有效)</font>
			</div>
		</div>
		<div class="left1_input_tst"  style="margin-bottom:10px;">
			<span class="input_span"  style="padding-top:10px;"><span class="red">*</span>是否Nofollow：</span>
			<div class="input_IDs">
					<input type="radio" id="ra2" name="noFollow" ${slideshow.noFollow==1?"checked='checked'":""}  value="1"   >是</input>
					<input type="radio" id="ra1" name="noFollow" ${slideshow.noFollow==0||slideshow.noFollow==null ?"checked='checked'":""}value="0" >否</input>
			</div>
		</div>
		<div class="savebutton">
			<input type="button" onclick="javascript:beforeChecksaveSlideshow('${type}')" type="button" name="Submit1" id="subbtn" class="b_buts" value="提交" /> 
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
    
	function beforeChecksaveSlideshow(){
		if(!saveSlideshow()){
			return false;
		}
		var  sType= $('#sType').val();
		
		$("#approForm").ajaxSubmit ({
			url : '${path}/maintain/slideshow/saveSlideshow/'+${slideshowId}+'.html',
			type : 'post',
			dataType : 'json',
			data : {
				'sType' : sType
			},
			success : function(result) {
				if(result.code=='0'){
					layer.msg(result.message, 1, 1, function() {
						window.parent.location.href = window.parent.location.href;
					});
				}else{
					layer.msg(result.message, 1);
				}
			},
			error : function(data) {
				layer.msg('网络连接超时,请您稍后重试.', 1, 5);
			}
		});
	}
	
	function uploadPic(form){
		var files = $('#files').val();
		if(null==files || $.trim(files)==""){
			alert("请选择需要上传的图片!!");
			return false;
		}
		
		var  sType= $('#sType').val();
		
		$("#"+form).ajaxSubmit ({
			url : '${path}/maintain/slideshow/upload.html',
			type : 'post',
			dataType : 'json',
			data :{
				'sType' : sType
			},
			success : function(result) {
				if (result.code == '0') {
						$('#sImage').val('${slideImagePath}' +result.message);
						$('#oneimageId').attr("src", '${slideImagePath}' + result.message);
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

	function saveSlideshow(type){
		var  title= $('#title').val();
		if ($.trim(title).length==0) {
			alert("请输入标题");
			return false ;
		}
		
		if(type=='update'){
			var reg = new RegExp("[0-9]+");
			 var  hits= $('#order').val();
	
			 if(hits!=''){
				 if (!reg.test((hits))) {
				   alert("排序必须为数字");
				   return false;
			   } 
			 }
		}
		
		
		var  sType= $('#sType').val();
		if (sType=="" || sType==null) {
			alert("请正确选择类型");
			return false ;
		}
		
		var  ra1= $("input:radio[name='ra1']:checked").val();
		//var ra1 = $('input[name="ra1"]:checked').val();
		if(ra1==2){
			var  startTime= $('#startTime').val();
			if ($.trim(startTime).length==0) {
				alert("非立即生效的，发布开始时间不能为空");
				return false ;
			}
			
			 	var date1=strToDate(startTime);
			 	var date2=new Date();
				var iday = (date1.getTime() - date2.getTime()) / (24*3600*1000);  
				
				if(iday>7){
					alert("发布开始时间大于7天");
				}
			
			if(!confirm('幻灯片发布开始时间为'+startTime+'，是否确定提交？')){
				 return false;
			 }
		}else{
			if(!confirm('幻灯片发布后立即生效，是否确定提交？')){
				 return false;
			 }
		}

		return  true  ;
	}
	
	 function timeShow(){
		 $('[id="startTimeTr"]').css("display","");
		 $('[id="endTimeTr"]').css("display",""); 
		 $('#startTime').val('');
		 $('#endTime').val('');
	 }
	 function timeHide(){
		 $('[id="startTimeTr"]').css("display","none");
		 $('[id="endTimeTr"]').css("display","none");
		 $('#startTime').val('');
		 $('#endTime').val('');
	 }
	 
	 function editPic(){
	 	$("#showPic").hide();
	 	$("#fileupload").show();
	 	$('#oneimageId').show();
	 }
	 
	  function strToDate(str){	
	 	str = str.replace(/-/g,"/");
	 	return new Date(str);
	 }
	  var _tipAry = new Array(7);
	  $(function(){
		  _tipAry[0]="【官网首页】图片尺寸1349*400px";
		  _tipAry[1]="【资讯首页】图片尺寸310*233px";
		  _tipAry[2]="【论坛首页】图片尺寸440*295px";
		  _tipAry[3]="【论坛首页视频】图片尺寸252*169px";
		  _tipAry[4]="【移动端首页】图片尺寸745*435px";
		  _tipAry[5]="【移动端推荐】大图尺寸1080*550px，小图尺寸1080*280px。有活动日期的请写在图片里。";
		  _tipAry[6]="【官网首页公司动态】图片尺寸222*138px";
		  changePicTip();
	  });
	  
	  function changePicTip(){
		  var _sType = $("#sType").val();
		  if(_sType!=''){$("#tipFont").text(_tipAry[_sType-1]);}
	  }
</script>
</html>