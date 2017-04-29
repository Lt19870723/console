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
	<div class="listzent">现金日记账详情</div>
	<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
		<tr>
	       <td align="right" width="25%">操作日期：</td>
	       <td><fmt:formatDate value="${journal.operationDate}" pattern="yyyy-MM-dd"/></td>
	    </tr>
	    <c:if test="${journal.debitAmount != null}">
	    	<tr>
				<td align="right" width="25%">借(收入)方(元)：</td>
				<td>${journal.debitAmount}</td>
			</tr>
	    </c:if>
		<c:if test="${journal.creditAmount != null}">
			<tr>
				<td align="right" width="25%">贷(支出)方(元)：</td>
				<td>${journal.creditAmount}</td>
			</tr>
		</c:if>
		<tr>
			<td align="right" width="25%">摘要：</td>
			<td><span class="text-hide">${journal.remark}</span></td>
		</tr>
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