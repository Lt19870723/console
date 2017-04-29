<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>债权转让-国诚金融后台管理系统</title>
</head>
<body>
	<table  class="fulltable" style="width: 100%;">
		<thead>
			<tr  align="center">
				<th>
					认购人
				</th>
				<th>
					认购金额
				</th>
				<th>
					认购时间
				</th>
			</tr>
		</thead>
		<c:forEach var="vo" items="${subscribelist}" varStatus="status">
		<tr class="tr_${status.index%2}">
			 <td>${vo.subscribeUsername}</td>
			 <td>${vo.account}</td>
			 <td>${vo.addTimeStr}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>