<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—统计分析</title>
</head>
<body style="background: #f9f9f9;">
	<form id="approForm" action="" method="post">
		<table align="center" border="1" bordercolor="black" style="width: 95%">
			<tr>
				<td colspan="2">利息净收益明细</td>
				<td colspan="3"  >被推荐人：${invitedUsername }</td>
			</tr>
			<tr>
				<td >
					序号
				</td>
				<td >
					借款标标题
				</td>
				<td >
					 利息收益
				</td>
				<td >
					 利息管理费
				</td>
				<td >
					 利息净收益
				</td>
			</tr>
			<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1 }</td>
				<td>${vo.borrowName}</td>
				<td>${vo.interest+vo.interestManagerFree }</td>
				<td>${vo.interestManagerFree }</td>
				<td>${vo.interest }</td>
			</tr>
		</c:forEach>
		</table>
	<div align="center">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
	</form>
</body>
		
	<script type="text/javascript">
		
		function pageGo(pageNo){
			window.location.href = '${path}/customer/recommendaward/usercollection/${id}/${invitedUserId}/${invitedUsername}/${year}/'+pageNo+'.html';
		}
		
	</script>
	
</html>
