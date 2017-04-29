<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信绑定查询-国诚金融后台管理系统</title>
</head>
<body>
	<div style="margin-left:20px;line-height:50px;">
			标题：<input type="text" id="name" class="input1"/>
			<input type="hidden" id="cmsArticleIdsStr"/>
				  &nbsp;
			栏目:<select id="channelId" class="bigselect" style="width:120px;" >
				  </select>
                  &nbsp; 
			来源：<input type="text" id="author" class="input1"/>
			      &nbsp;  
				
				<input type="button"  id="btnSearch" onclick="pageGo(1);" value="查询"/> 	
					&nbsp;
				<input type="button" id="btnAdd" onclick="addCmsSarticle()" value="添加文章"/>	
					&nbsp;
				<input type="button" id="btnDelte" onclick="removeCmsArticle()" value="批量删除"/>					
				 
			</div>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<thead>
			<tr>
				<th><input type="checkbox"  onclick="selectAll($(this).attr('checked'))"/></th>
				<th>标题</th>
				<th>来源</th>
				<th>栏目</th>
				<th>置顶</th>
				<th>添加时间</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript">
$(function() {
	pageGo(1);
	initCmsChannel();
});
function pageGo(pageNo) {
	var _load = parentLayer.load('处理中..');
	var title = $('#name').val();
	var channelId = $('#channelId').val();
	var author = $('#author').val();
	$.ajax({
		url : '${path}/cmsArticle/searchCmsSarticleList/' + pageNo + '.html',
		data : {
			title : title,
			channelId : channelId,
			author : author
		},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			$('#dataTable tbody').remove();
			$('#dataTable').append(result);
			parentLayer.close(_load);
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
function initCmsChannel(){
	$.ajax({
		url : '${path}/cmsArticle/initCmsChannel.html',
		data : {},
		type : 'post',
		dataType : 'html',
		success : function(result) {
			if (result == null){return;}
			result = result.substring(1,result.length-1);
			jQuery($("#channelId")).get(0).options.add(new Option("",""));
            var data1 = result.split('|')
			for (var i = 0; i < data1.length-1; i++) {
				var data2 = data1[i].split(',');
				$("#channelId").append("<option value=\"" + data2[0] + "\">" + data2[1] + "</option>");
			}
		},
		error : function(data) {
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
function  validateBatchDelete(){
	 var  checks= $('[name=selectCheckbox]:checked');
	 var checkLength= checks.length;
	 if (checkLength==0) {
		layer.alert("请选择需要删除的选项");
		return false;
	}
	 
	 
	 if(confirm("确定要删除？")){
		 var ids ="";
		 $('[name=selectCheckbox]:checked').each(function (index){
			 if (checkLength!=index+1) {
				 ids+=$(this).val()+",";	
			}else{
				ids+=$(this).val();
			}
		 });
		 $('#cmsArticleIdsStr').val(ids); 
		return  true;		 
	 }
	  return  false;
}

function removeCmsArticle(){
		if(validateBatchDelete()){
			var _load = layer.load('处理中..');
			var cmsArticleIdsStr = $('#cmsArticleIdsStr').val();
			$.ajax({
				url : '${path}/cmsArticle/removeCmsArticle.html',
				data : {
					cmsArticleIdsStr : cmsArticleIdsStr
				},
				type : 'post',
				dataType : "json",
				success : function(result) {
					layer.close(_load);
					if (result.code == '0') {
						layer.alert(result.message);
					} else {
						layer.msg("删除成功",1,1,function(){
							pageGo(1);
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
function addCmsSarticle(){
	var _url = '${path}/cmsArticle/toCmsSarticleAdd.html';
	$.layer({
		type : 2,
		title : '文章添加',
		area : [ '800px', '530px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function editCmsSarticle(cmsSarticleId){
	var _url = '${path}/cmsArticle/toCmsSarticleEdit/'+cmsSarticleId+'.html';
	$.layer({
		type : 2,
		title : '文章修改',
		area : [ '800px', '530px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		}
	});
}
function hiddenSarticle(cmsArticleId,status,pageNo){
	var _load = layer.load('处理中..');
	$.ajax({
		url : '${path}/cmsArticle/updateTop.html',
		data : {
			cmsArticleId : cmsArticleId,
			topStaus : status
		},
		type : 'post',
		dataType : "json",
		success : function(result) {
			layer.close(_load);
			if (result.code == '0') {
				layer.alert(result.message);
			} else {
				layer.msg("成功",1,1,function(){
					pageGo(pageNo);
				});
			}
		},
		error : function(data) {
			layer.close(_load);
			layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
		}
	});
}
</script>
</html>