<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内转交易-国诚金融后台管理系统</title>
</head>
<body>
   <form id="" name="" method="post">
     <div class="listzent">挂单状态：
     <c:if test="${record.status == 1}">已挂单</c:if>
	<c:if test="${record.status == 2}">部分成交</c:if>
	<c:if test="${record.status == 3}">全部成交</c:if>
	<c:if test="${record.status == -1}">已撤销</c:if>
	（${record.entrustCode}）
     </div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;width:600px;">
		    <tr>
		       <td align="right" width="25%">委托人：</td>
		       <td colspan="3">${record.userName }</td>
		    </tr>
			<tr>
				<td align="right" width="25%">委托类型：</td>
				<td >
					<c:if test="${record.entrustType == 1}">认购</c:if>
					<c:if test="${record.entrustType == 2}">转让</c:if>
				</td>
				<td align="right" width="25%">委托时间：</td>
				<td ><fmt:formatDate value="${record.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td align="right" width="25%">委托价格：</td>
				<td ><fmt:formatNumber value="${record.price}" pattern="#,##0.00"/></td>
				<td align="right" width="25%">委托数量：</td>
				<td><fmt:formatNumber value="${record.amount}" pattern="#,##0"/></td>
			</tr>
				<tr>
					<td align="right" width="25%">成交数量：</td>
					<td ><fmt:formatNumber value="${record.dealAmount}" pattern="#,##0"/></td>
					<td align="right" width="25%">未成交数量：</td>
					<td><fmt:formatNumber value="${record.residueAmount}" pattern="#,##0"/></td>
				</tr>
			<tr>
					<td align="right" width="25%">成交总价：</td>
					<td><fmt:formatNumber value="${record.dealTotalPrice}" pattern="#,##0.00"/></td>
					<td align="right" width="25%">交易服务费：</td>
					<td><fmt:formatNumber value="${record.dealFee}" pattern="#,##0.00"/></td>
			</tr>
		</table>	
		<c:if test="${list != null}">
		<table id="" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th style="width: 40px">序号</th>
			<th style="width: ;">成交人</th>
			<th style="width: ;">成交时间</th>
			<th style="width: ;">成交数量（份）</th>
			<th style="width: ;">成交价格（元/份）</th>
			<th style="width: ;">总价（元）</th>
			<th style="width: ;">交易服务费（元）</th>
			<th style="width: ;">成交类型</th>
			<th style="width: ;">状态</th>
			<th style="width: ;">操作</th>
		</tr>
	</thead>
	<tbody>
				<c:forEach var="deal" items="${list }" varStatus="vIndex" >
						<tr>
							<td>${vIndex.index+1}</td>
							<td align="right">
								<c:if test="${deal.sellerEntrustId == record.id }">
									${deal.buyerName }	
								</c:if>
								<c:if test="${deal.buyerEntrustId == record.id }">
									${deal.sellerName }	
								</c:if>
							</td>
							<td>
								<fmt:formatDate value="${deal.addtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</td>
							<td>
								<fmt:formatNumber value="${deal.turnoverAmount }" pattern="#,##0"/>
							</td>
							<td>
								<fmt:formatNumber value="${deal.turnoverPrice }" pattern="#,##0.00"/>
							</td>
							<td><fmt:formatNumber value="${deal.turnoverTotalPrice }" pattern="#,##0.00"/></td>
							<td>
								<c:if test="${deal.sellerEntrustId == record.id }">
									<fmt:formatNumber value="${deal.sellerFee }" pattern="#,##0.00"/>
								</c:if>
								<c:if test="${deal.buyerEntrustId == record.id }">
									<fmt:formatNumber value="${deal.buyerFee }" pattern="#,##0.00"/>	
								</c:if>
							</td>
							<td>
								<c:if test="${deal.sellerEntrustId == record.id }">
									<c:choose>
										<c:when test="${deal.dealType == 1}">
											主动转让成交
										</c:when>
										<c:otherwise>
											被动转让成交
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${deal.buyerEntrustId == record.id }">
									<c:choose>
										<c:when test="${deal.dealType == 2}">
											主动认购成交
										</c:when>
										<c:otherwise>
											被动认购成交
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td>
								<c:if test="${deal.status == 1 }">
									交易处理中	
								</c:if>
								<c:if test="${deal.status == 2 }">
									交易完成
								</c:if>
							</td>
							<td align="right">
								<input  type="button"  onclick="queryDealInfoById('${deal.id}');" value="详情" />
							</td>
						</tr>
				</c:forEach>
	</tbody>	
</table>	
</c:if>	
 </form>
 </body>
 <script type="text/javascript">
	queryDealInfoById = function(id){
		
		//parent.layer.close();
		var _url = "${path}/stock/stockDeal/" + id + "/info.html";
		$.layer({
			type : 2,
			fix : false,
			shade : [ 0 ],
			title : '成交详情',
			area : [ '800px', '450px' ],
			offset : [ '7px', '' ],
			shade : [ 0.1, '#000' ],
			maxmin : true,
			iframe : {
				src : _url
			}
		});
	}
 
 	function downloadInterTransferExcel(id){
 		window.location.href = '${path}/finance/interTransfer/' + id + "/downloadInterTransferExcel.html";
 	}
 </script>
</html>