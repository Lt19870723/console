<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>描述</title>
</head>
<body>
	<table width="100%" border="0" style="border: 1px solid #CCCCCC;">
		<tr>
			<td width="100%">
				<textarea id="description" name="description"  style="width:99%;height:252px" readonly="readonly">${wxArticles.description}</textarea>
			</td>
		</tr>
	</table>
</html>