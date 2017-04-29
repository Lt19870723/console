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
				<td >
					序号
				</td>
				<td >
					投资人
				</td>
				<td >
					应收款时间
				</td>
				<td >
					借款标题
				</td>
				<td >
					周期
				</td>
				<td >
					期数
				</td>
				<td >
					收款总额
				</td>
				<td >
					状态
				</td>

			</tr>
			<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1 }</td>
				<td>${vo.username}</td>
				<td>${vo.repay_timeStr}</td>
				<td>
				    ${fn:substring(vo.name,0,10)}
					<c:if test="${fn:length(vo.name)>10}">..</c:if>
				</td>
				<td>${vo.time_limitStr}</td>
				<td>${vo.order}/${vo.borrowOrder}</td>
				<td>${vo.repayYesaccount}</td>
				<td>${vo.eRepayMentStatusStr}</td>
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
			window.location.href = '${path}/borrow/manage/repayCollection/fail/${repaymentId}/'+pageNo+'.html';
		}
		
	</script>
	
</html>
