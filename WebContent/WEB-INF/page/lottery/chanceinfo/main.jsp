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
		<div style="margin-left: 20px; line-height: 35px; width: 100%">
			<b>签到活动时间：</b>${d.beginDate }(0点)~${d.endDate }(0点)
			<b style="margin-left: 10px;">连续签到天数：</b>${d.awardNum }天
			<input type="button" onclick="show(0)" class="b_buts" style="margin-left: 10px;" value="设 置" /> 
		</div>
		<div id="loginLogCard" style="margin-left:20px;line-height:50px;">
		 用户名：<input type="text" name="userName" id="userName" class="input1"/>
			<input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查 询" />
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
			url : '${path}/lottery/chanceinfo/list/' + pageNo + '.html',
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
	 
	
	function show(id) {
		var _title = '元宝商城';
		var _width = '70%';
		var _height = '70%';
		_title = '设置签到活动时间';
		var _url = '${path}/lottery/chanceinfo/SignDate.html';
		$.layer({
			type : 2,
			title : _title,
			area : [ _width, _height ],
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
