<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易-国诚金融后台管理系统</title>
<style type="text/css">
table
{
border-collapse: collapse;
border: none;
margin-left: 10%;
width: 80%;
margin-top: 10px;
}
td
{
border: solid #000 1px;
}
.fundclass{
	font-weight: bold;
}
</style> 
</head>
<body >
<table>
	<tr>
		<td>平台用户名</td>
		<td>${shareholderRoster.userName}</td>
	</tr>
	<tr>
		<td>姓名</td>
		<td>${shareholderRoster.userRealName}</td>
	</tr>
	<tr>
		<td>证件号码</td>
		<td>${shareholderRoster.idCard}</td>
	</tr>
	<tr>
		<td>手机号码</td>
		<td>${shareholderRoster.mobilenum}</td>
	</tr>
</table>

<table>
	<thead>
		<tr>	
			<th style="width: ;">版本号</th>
			<th style="width: ;">版本变更</th>
			<th style="width: ;">持有份额</th>
			<th style="width: ;">变更时间</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${shareLogList}" var="shareLogList" varStatus="index">
		<tr >
		<c:if test="${(index.index+2) % 2 == 0 }">
			<td style="width: ;" rowspan="2"  >
				${shareLogList.version}
			</td>
		</c:if>
			<td >
				<c:if test="${shareLogList.status == 1 }">
					加入
				</c:if>
				<c:if test="${shareLogList.status == -1 }">
					版本替换作废
				</c:if> 
			</td>
			<td style="width: ;"><fmt:formatNumber value="${shareLogList.stockTotal}" pattern="#,##0"/></td>
			<td style="width: ;"><fmt:formatDate value="${shareLogList.addtime}" pattern="yyyy-MM-dd"/></td>
		</tr>
	</c:forEach>
	</tbody>	
</table>	

 </body>
</html>