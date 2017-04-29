<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>现金日记账-国诚金融后台管理系统</title>
<style type="text/css">
	.text-hide{display: inline-block;text-align: left;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;width:250px; }
</style>
</head>
<body>
	<div class="listzent">对账详情</div>
	<table class="fulltable">
        <thead>
            <tr>
                <th width="25%">备注</th>
                <th width="25%">操作时间</th>
            </tr>
        </thead>
        <c:forEach var="item" items="${reconFiles}">
		<tr>
            <td>${item.remark}</td>
	       <td><fmt:formatDate value="${item.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    </tr>
        </c:forEach>
	</table>			
</body>
<script type="text/javascript">
	//鼠标经过提示全名
	function allName(e) {
		$(e).hover(function() {
			$(this).attr("title", $(this).text())
		}, function() {
			$(this).attr("title")
		});
	}
	$(function() {
		allName(".text-hide");
	});
</script>
</html>