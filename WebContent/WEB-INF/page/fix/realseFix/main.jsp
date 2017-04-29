<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—借款终审</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" >
	  
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
			 
		<input type="button" onclick="javascript:add();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="新增定期宝" />
		</div>
	</form>

	<div id="loginLogList" class="main_cent"></div>

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
			url : '${path}/fix/realseFix/list/' + pageNo + '.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#loginLogList").html(result);
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
	function add(){
		 
		var _url = '${path}/fix/realseFix/add.html';
		$.layer({
			type : 2,
			title : '定期宝新增',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	}
	function show(id){
		 
		var _url = '${path}/fix/realseFix/detail.html?id='+id;
		$.layer({
			type : 2,
			title : '定期宝查看',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	}
	function edit(id){
		 
		var _url = '${path}/fix/realseFix/edit.html?id='+id;
		$.layer({
			type : 2,
			title : '定期宝修改',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	}
	function submitApprove(id){
		 
		var _url = '${path}/fix/realseFix/approve.html?id='+id;
		$.layer({
			type : 2,
			title : '定期宝确认审核',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	}
	
	function doDelete(id){
		var _load; 
		if(confirm("确认要删除吗？")){
		_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/fix/realseFix/doDelete.html',
			data : {
				'id' : id	 
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '0') {
					
					layer.msg(result.message, 1, 1, function() {
						pageGo(1);
					});
				} else {
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
	function toEdit(id){
		 
		var _url = '${path}/fix/realseFix/toEdit.html?id='+id;
		$.layer({
			type : 2,
			title : '定期宝修改',
			area : [ '80%', '80%' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				 
		    }
		});
	} 
	  
</script>
</html>
