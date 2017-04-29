<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—客户管理</title>
</head>
<body style="background: #f9f9f9;">
	<form id="loginLogForm" action="" method="post" >
		<table>
			<tr>
				<td width="100px">回访内容：</td>
				<td colspan="3" >
					<textarea rows="25" readonly="readonly" style="width:500px;height:380px;">${feedbackInfoVo.contactContent }</textarea>
				</td>
			</tr>
			</table>
	</form>
</body>
<script type="text/javascript">
</script>
</html>
