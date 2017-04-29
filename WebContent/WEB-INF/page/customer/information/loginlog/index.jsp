<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—菜单管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post">
		<div id="loginLogCard" style="padding-top:10px;">
			<div>
				&nbsp;&nbsp;&nbsp;&nbsp;用户名： <input name="userName" id="userName"
					class="input1" value="${loginRecordCnd.userName}" /> &nbsp; 登录时间段：
				<input name="loginStartTime" id="loginStartTime"
					onclick="WdatePicker()" styleClass="Wdate"
					value="${loginRecordCnd.loginStartTime}">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input> ~ <input name="loginEndTime" id="loginEndTime"
					onclick="WdatePicker()" styleClass="Wdate"
					value="${loginRecordCnd.loginEndTime}">
				<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
				</input>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
					onclick="javascript:query();" type="button" name="Submit1"
					id="subbtn" class="b_buts" value="查   询" /> <br />
				<br />
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
			url : '${path}/customer/memberloginlog/query/' + pageNo + '.html',
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
	
</script>
</html>
