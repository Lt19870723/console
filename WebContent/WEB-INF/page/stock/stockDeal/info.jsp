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
		<td></td>
		<td class="fundclass">转让方</td>
		<td class="fundclass">受让方</td>
	</tr>
	<tr>
		<td class="fundclass">委托人</td>
		<td>${record.sellerName }</td>
		<td>${record.buyerName }</td>
	</tr>
	<tr>
		<td class="fundclass">委托类型</td>
		<td>转让</td>
		<td>受让</td>
	</tr>
	<tr>
		<td class="fundclass">委托时间</td>
		<td><fmt:formatDate value="${seller.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><fmt:formatDate value="${buyer.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
		<td class="fundclass">委托价格</td>
		<td><fmt:formatNumber value="${seller.price }" pattern="#,##0.00"/></td>
		<td><fmt:formatNumber value="${buyer.price }" pattern="#,##0.00"/></td>
	</tr>
	<tr>
		<td class="fundclass">委托数量</td>
		<td><fmt:formatNumber value="${seller.amount }" pattern="#,##0"/></td>
		<td><fmt:formatNumber value="${buyer.amount }" pattern="#,##0"/></td>
	</tr>
	<tr>
		<td class="fundclass">成交时间</td>
		<td colspan="2"><fmt:formatDate value="${record.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	</tr>
	<tr>
		<td class="fundclass">成交价格</td>
		<td colspan="2"><fmt:formatNumber value="${record.turnoverPrice }" pattern="#,##0.00"/></td>
	</tr>
	<tr>
		<td class="fundclass">成交数量</td>
		<td colspan="2">${record.turnoverAmount }</td>
	</tr>
	<tr>
		<td class="fundclass">成交总价</td>
		<td colspan="2"><fmt:formatNumber value="${record.turnoverTotalPrice }" pattern="#,##0.00"/></td>
	</tr>
	<tr>
		<td class="fundclass">交易服务费</td>
		<td><fmt:formatNumber value="${record.sellerFee }" pattern="#,##0.00"/></td>
		<td><fmt:formatNumber value="${record.buyerFee }" pattern="#,##0.00"/></td>
	</tr>
	<tr>
		<td class="fundclass">成交类型</td>
		<td>
			<c:choose>
				<c:when test="${record.dealType == 1}">
					主动转让成交
				</c:when>
				<c:otherwise>
					被动转让成交
				</c:otherwise>
			</c:choose>
		</td>
		<td>
		<c:choose>
			<c:when test="${record.dealType == 2}">
				主动认购成交
			</c:when>
			<c:otherwise>
				被动认购成交
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	
	<tr>
		<td class="fundclass">交易状态</td>
		<td colspan="2">
			<c:choose>
				<c:when test="${record.status == 1}">
					交易处理中
				</c:when>
				<c:otherwise>
					交易完成
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
  </table>

 </body>
</html>