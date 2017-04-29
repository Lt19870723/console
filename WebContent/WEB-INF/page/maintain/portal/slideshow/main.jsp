<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 100%;">
		<div id="loginLogCard">
				<div style="margin-left:20px;line-height:50px;">
			标题：<input id="title" class="input1" name="title"/>&nbsp;&nbsp;
			类型：<select id="sType" name="sType">
				<option value="">--请选择类型--</option>
				<option value="1">官网首页</option>
				<option value="7">官网首页公司动态</option>
				<option value="2">资讯首页</option>
				<option value="3">论坛首页</option>
				<option value="4">论坛首页视频</option>
				<option value="5">移动端首页</option>
				<option value="6">移动端推荐</option>
				</select>
				&nbsp;&nbsp;
			<input type="button" onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />&nbsp;
			<input type="button" onclick="javascript:show(0,'add')" type="button" name="Submit1"
					id="btnAdd" class="b_buts" value="添   加" />
			</div>
		</div>
	</form>

	<div id="list" class="main_cent"></div>

</body>
<script type="text/javascript">

	$(function() {
		pageGo(1);
	});
	
	/**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/maintain/slideshow/searchslideshowList/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#list").html(result);
				parentLayer.close(_load);
			},
			error : function(data) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
	
	/**
	*查询功能
	*/
	function query(){
		pageGo(1);
	}
	
	/**删除*/
	function deleteslide(slideshowId){
		if(!confirm('确定删除？')){
			return false;
		}
		$("#queryForm").ajaxSubmit({
			url:'${path}/maintain/slideshow/deleteSlideshow/' + slideshowId + '.html',
			type:'post',
			dataType:'json',
			success:function(result){
				alert(result.message);
				window.location.href = window.location.href;
			},
			error:function(data){
				alert(result.message);
			}
		});		
	}
	
	function show(slideshowId,type){
		var _url = '${path}/maintain/slideshow/toSaveSlideshow/'+slideshowId+'/'+type+'.html';
		if(type=='add'){
			title = "添加";
		}else{
			title = "修改";
		}
		$.layer({
		type : 2,
		title : title,
		area : [ '800px', '500px' ],
		offset : [ '10px', '' ],
		shade : [ 0.1, '#000' ],
		maxmin : true,
		iframe : {
			src : _url
		},
		end: function(){
			//pageGo(1);
	    }
	});
	}
	
</script>
</html>
