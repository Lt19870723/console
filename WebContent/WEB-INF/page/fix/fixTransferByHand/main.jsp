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
		url : '${path}/fix/fixTransferByHand/list/' + pageNo + '.html',
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
function subsByHand(id){
	var _load; 
	if(confirm("确认要手动转让吗")){
	_load = layer.load('处理中..');
		$.ajax({
			url : '${path}/fix/fixTransferByHand/toTransferCancel.html',
			data : {
				'id' : id	 
			},
			type : 'post',
			dataType : 'json',
			success : function(result) {
				parentLayer.close(_load);
				if (result.code == '1') {
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
</script>
</html>
