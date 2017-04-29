<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内部转账-国诚金融后台管理系统</title>
<style type="text/css">
	a{ cursor:pointer }
</style>
</head>
<body>
<form id="form" action="" method="post">
	 <div style="margin-left:20px;line-height:50px;">
			<div style="margin-left:20px;line-height:50px;">
			&nbsp;&nbsp;&nbsp;&nbsp;转账编号：<input id="operationCode" name="operationCode" class="input1" value=""/>
			<input id="searchMoneyOperations" name="searchMoneyOperations" value="查询" type="button" onclick="query();" />&nbsp;
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
	*查询功能
	*/
	function query(){
		pageGo(1);
	}
 
 /**
	*翻页功能
	*/
	function pageGo(pageNo) {
		var _load = parentLayer.load('处理中..');
		$("#form").ajaxSubmit ({
			url : '${path}/finance/interTransfer/searchList/' + pageNo + '.html',
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

	function showVirement(id) {

		var _url = '${path}/finance/interTransfer/detail/' + id + '.html'
		$.layer({
			type : 2,
			title : '详情',
			area : [ '800px', '500px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});

	}
 
</script>
</html>