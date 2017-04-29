<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center" border="1" bordercolor="black" style="width: 95%">
			<tr>
				<td >
					序号
				</td>
				<td >
					点击申请时间
				</td>
				<td >
					出错类型
				</td>
			</tr>
			<c:forEach items="${clicks}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1 }</td>
				<td>${vo.addTimeStr}</td>
				<td>${vo.errorMsg}</td>
			</tr>
			</c:forEach>
		</table>
	</form>
</body>
	
</html>
