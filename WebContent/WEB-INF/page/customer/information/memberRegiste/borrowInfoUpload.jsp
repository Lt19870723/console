<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>上传图片</title>
</head>
<body style="background: #fff;">
	<form id="myform" action="" method="post" style="background: #fff;">
	    <div style="border:2px solid #ebebeb ;margin:10px;">
		<table style="margin:20 auto;  position:relative;  float:none;  padding:0;  text-align:left;"  >
			 
		<c:if test="${map.borrowVo.pledgeType == 2}">
			<tr>
				<td colspan="2">使用历史标资料</td>
			</tr>
			<tr>
				<td width="150px">使用该用户历史标资料：</td>
				<td>
				 
					<select id="oldBorrowId" style="width:300px;" class="bigselect" name="oldBorrowId"> 
				        <option value="">--请选择--</option>
						<c:forEach items="${oldBorrowOptionsList}" var="o">
							<option value="${o.value }">${o.label }</option>
						</c:forEach>
					</select>
					 &nbsp;
					<input  class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadForm()" value="上传" /> 
				</td>		
			</tr>
			</c:if>
				<tr>
					<td colspan="2">上传借款资料</td>
			    </tr>
				<tr>
					<td width="150px">证件类型：<font color="red">*</font></td>
					<td>
					    <select id="style" style="width:120px;" class="bigselect" name="style"> 
					        <option value="">--请选择--</option>
							<c:forEach items="${styleOptionsList}" var="o">
								<option value="${o.value}">${o.label}</option>
							</c:forEach>
				       </select>
					</td>		
				</tr>
				<tr>
					<td width="150px">上传：</td>
					<td>
					 	 <form  id="myfrom"   name="myfrom" method="post" >
					 	 <input  class="rz_input mr5" type="file" name="files" id="files"/>
				         <input  class="rz_searchbtn" type="button" name="upbtn" id="upbtn" onclick="uploadPic()" value="图片上传" />
				         </form>
					</td>		
				</tr>
				<tr>
					<td colspan="2" style="color:red;">
					 	*说明：请上传后缀为jpg,jpeg,gif,png格式的图片。
					 	<a href="javascript:;" onclick="deleteAllDoc('${borrowId}')">【删除全部资料】</a>
					</td>		
				</tr>
			 
		</table>
		</div>
		<input type="hidden" name="borrowId" id="borrowId" value="${borrowId}"/>
	    <input type="hidden" name="userId" id="userId" value="${userId}"/>
		<div style="width:700px;margin-left: auto;margin-right: auto;">
		    <c:forEach items="${accountUploadDocVoList}" var="o">
		       <div style="float:left;margin-left:10px;margin-top:10px;width: 45%; text-align: center;border:1px solid #FF8C00;">		       			
		       			<c:if test="${o.flagExist=='1'}">
		       				<img src="${portal_path}/${o.docPath}" width="229" height="140" />
		       			</c:if>
		       			<c:if test="${o.flagExist=='0'}">
		       				<img src="${path}/images/index_logo.png" width="229" height="140" />
		       			</c:if>						
						<br/>
						类型：${o.styleLabel }&nbsp;&nbsp;
						<a href="javascript:;" onclick="deleteDoc('${o.id}')">【删除】</a>
						</div>
		    </c:forEach>
		</div>
	</form>
</body>
<script type="text/javascript">
 
var _load, borrowId,userId;
/*关闭*/
function cancle(){
	var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	parent.layer.close(index); //执行关闭
}
$(function(){
      borrowId=$('#borrowId').val();
	  userId=$('#userId').val();
});
/*上传图片*/
function uploadPic(){
	
		var files = $('#files').val();
		var style=$('#style').val();
		 
		if(null==files || $.trim(files)==""){
			layer.msg('请选择需要上传的图片!!', 1, 5);
			return false;
		}
		if(null==style || $.trim(style)==""){
			layer.msg('请选择证件类型', 1, 5);
			return false;
		}
		$("#myform").ajaxSubmit ({
			url : '${path}/information/memberRegiste/upload.html',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if(result.code == '1'){
					parentLayer.close(_load);
					layer.msg(result.message,1,1,function(){
						refresh(borrowId,userId);
			    	});
				}else{
					layer.msg(result.message,1,5,function(){
						parentLayer.close(_load);
			    	});
				}
				
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
				parentLayer.close(_load);
			}
		}); 
		 
	}
/*删除所有图片*/
function deleteAllDoc(id){
	if(confirm("确认要删除吗？")){
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/information/memberRegiste/deleteAllDoc.html',
			data : {
				'borrowId' : id	 
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '1') {
					layer.msg(result.message, 1, 1, function() {
						refresh(borrowId,userId);
					});
				} else {
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
}
/*删除单张图片*/
function deleteDoc(id){
	if(confirm("确认要删除吗？")){
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/information/memberRegiste/deleteDoc.html',
			data : {
				'id' : id	 
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				if (result.code == '1') {
					layer.msg(result.message, 1, 1, function() {
						refresh(borrowId,userId);
					});
				} else {
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
}
function  uploadForm(){
	var oldBorrowId = $('#oldBorrowId').val();
	var borrowId=$('#borrowId').val();
	if(oldBorrowId == '' || oldBorrowId.length == 0){
		parent.layer.msg('请选择历史标资料！', 2, 5);
		return false;
	}
	if(!confirm("确认要上传历史标资料吗？")){
		return false;
	}
	$.ajax({
		url : '${path}/information/memberRegiste/uploadOldBorrowInfos.html',
		data : {
			'borrowId' : borrowId,
			'oldBorrowId':oldBorrowId
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.code == '1') {
				parentLayer.close(_load);
				layer.msg(result.message,1,1,function(){
					refresh(borrowId,userId);
		    	});
			} else {
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
function refresh(borrowId,userId){
	location.href='${path}/information/memberRegiste/initUpload.html?borrowId='+borrowId+'&userId='+userId;
}
</script>
</html>
