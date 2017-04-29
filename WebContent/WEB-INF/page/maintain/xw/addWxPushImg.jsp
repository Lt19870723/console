<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>微信单图文推送添加</title>
</head>
<body>

<form id="addNewsForm" action="">

	<table style="border:0;width:500px;height:400px;" align="center">
		<tr>
			<td width="100px"><font style="color:red;">*</font>图片</td>
			<td width="400px"><img id="oneimageId" style="width:360px;height:200px;"/></td>
		</tr>
		<tr>
			<td></td>
			<td><input name="imgFile" id="imgFile" type="file" value="上传"/></td>
		</tr>
		<tr>
			<td><font style="color:red;">*</font>标题</td>
			<td><input type="text" id="title" name="title"/></td>
		</tr>
		<tr>
			<td><font style="color:red;">*</font>描述</td>
			<td><textarea style="width:400px" ></textarea></td>
		</tr>
		<tr>
			<td><font style="color:red;">*</font>超链接</td>
			<td><input type="text" id="url" name="url"/></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center"><input type="button" value="保存"/></td>
		</tr>
	</table>
<form>
</body>
<script type="text/javascript">
	function wxPushImgSubmit(){
		
	}
</script>
</html>