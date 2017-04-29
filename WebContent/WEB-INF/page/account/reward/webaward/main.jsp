<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—奖励管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post">
		<div id="loginLogCard">
			<div>
				<div style="margin-left:20px;line-height:50px;">
			用户名：<input id="username" class="input1" name="username"/>&nbsp;&nbsp;
			真实姓名:<input id="realName" class="input1" name="realName"/>&nbsp;&nbsp;
			<input type="button" onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
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
		$("#loginLogForm").ajaxSubmit ({
			url : '${path}/account/webaward/list/' + pageNo + '.html',
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
	
	function showAuditLayer(id){
		var _url = '${path}/account/webaward/intForWeb/'+id+'.html';
		$.layer({
			type : 2,
			title : '网站奖励',
			area : [ '500px', '400px' ],
			offset : [ '10px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			},
			end: function(){
				pageGo(1);
		    }
		});
	}
	
</script>
</html>
