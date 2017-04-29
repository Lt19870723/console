<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—用户元宝明细</title>
</head>
<body style="background: #f9f9f9;">
	<form id="queryForm" action="" method="post" style="width: 104%">
		<div id="loginLogCard">
			<div style="margin-left: 20px; line-height: 50px;">
				用户名：	<input id="username" class="input1" name="username" style="width: 150px" /> &nbsp; 
				真实姓名：<input id="realname" class="input1" name="realname" style="width: 150px" /> &nbsp; 
				类型：
						<select name="type" id="type" class="bigselect">
							<option value="">不限</option>
							<c:forEach items="${syceeTypes }" var="t">
								<option value="${t.name }">${t.value }</option>
							</c:forEach>
						</select>
						<br/>
				时间： 	<input name="addtimeBegin" id="addtimeBegin" onclick="WdatePicker()" styleClass="Wdate" value="">
							<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
						</input> 
						&nbsp;~&nbsp; 
						<input name="addtimeEnd" id="addtimeEnd" onclick="WdatePicker()" styleClass="Wdate" value="">
							<f:convertDateTime pattern="yyyy-MM-dd"></f:convertDateTime>
						</input> 
				&nbsp; <input type="button" onclick="pageGo(1);" name="Submit1" id="subbtn" class="b_buts" value="查   询" />
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
		$("#queryForm").ajaxSubmit ({
			url : '${path}/account/sycee/list/'+pageNo+'.html',
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
</script>
</html>
