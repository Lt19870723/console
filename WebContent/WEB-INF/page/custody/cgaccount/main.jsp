<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—存管单笔流水查询</title>
</head>
<body style="background: #f9f9f9;">
<form id="queryForm" action="" method="post" style="width: 100%;">
		<div>
			<div style="margin-left:20px;line-height:50px;">
				用户名：<input id="userName" class="input1" name="userName" />

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
													  onclick="javascript:querySingle();" type="button" name="Submit1"
													  id="subbtn" class="b_buts" value="查   询" />
			</div>
		</div>
</form>
<div id="single" class="main_cent"></div>
</body>
<script type="text/javascript">
	function validate(){
		var orderNo = $('#userName').val();
		if(null==orderNo || $.trim(orderNo)==""){
			alert("用户名不能为空。");
			return false;
		}

		return true;
	}

	function querySingle() {

		if(!validate()){
			return;
		}
		var _load = parentLayer.load('处理中..');
		$("#queryForm").ajaxSubmit ({
			url : '${path}/custody/findCGAccount.html',
			type : 'post',
			dataType : 'html',
			success : function(result) {
				$("#single").html(result);
				parentLayer.close(_load);
			},
			error : function(result) {
				layer.msg('网络连接异常，请刷新页面或稍后重试！', 1, 5);
			}
		});
	}
</script>

</html>
