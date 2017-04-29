<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<table id="dataTable" class="fulltable" style="width: 100%;">
	<thead>
		<tr>	
			<th style="width: 50px;">日期</th>
			<th style="width: ;">充值端口</th>
			<th style="width: ;">充值总额(元)</th>
			<th style="width: ;">充值成功总额(元)</th>
			<th style="width: ;">实际充值总额(元)</th>
			<th style="width: ;">计算手续费(元)</th>
			<th style="width: ;">实际手续费(元)</th>
			<th style="width: ;">手续费差异(元)</th>
			<th style="width: ;">第三方到账总额(元)</th>
			<th style="width: ;">差异(元)</th>
			<th style="width: ;">借款者虚拟充值总额(元)</th>
			<th style="width: 70px;">状态</th>
			<th style="width: 70px;">备注</th>
			<th style="width: 60px;">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${page.result}" varStatus="status"> 
				<c:forEach var="vType" items="${vo }" varStatus="vIndex" >
						<tr>
							<c:if test="${vIndex.index == 0}"><td rowspan="6" align="center"><fmt:formatDate value="${vType.date}" pattern="yyyy-MM-dd"/></td></c:if>
							<td>
								<c:if test="${vType.type == 1}"><span style="color: ;">网银在线</span></c:if>
								<c:if test="${vType.type == 4}"><span style="color: ;">新浪支付</span></c:if>
								<c:if test="${vType.type == 5}"><span style="color: ;">连连支付</span></c:if>
								<c:if test="${vType.type == 6}"><span style="color: ;">富友支付</span></c:if>
								<c:if test="${vType.type == 7}"><span style="color: ;">支付宝</span></c:if>
								<c:if test="${vType.type == 8}"><span style="font-weight:bold;color: black;">合计(不含虚拟充值)</span></c:if>
							</td>
							<td align="right"><fmt:formatNumber value="${vType.totalMoney}" pattern="#,##0.00"/></td>
							<td align="right"><fmt:formatNumber value="${vType.successMoney}" pattern="#,##0.00"/></td>
							<td align="right">
								<c:if test="${vType.checkSuccessMoney == null}">--</c:if>
								<fmt:formatNumber value="${vType.checkSuccessMoney}" pattern="#,##0.00"/>
							</td>
							<td align="right"><fmt:formatNumber value="${vType.calculationFee}" pattern="#,##0.00"/></td>
							<td align="right">
								<c:if test="${vType.checkFee == null}">--</c:if>
								<c:if test="${vType.checkFee != null}"><fmt:formatNumber pattern="#,##0.00" value="${vType.checkFee}"/></c:if>
							</td>
							<td align="right">
								<c:if test="${vType.differenceFee == null}">--</c:if>
								<c:if test="${vType.differenceFee != null}">
									<c:if test="${vType.differenceFee eq '0.00' || vType.differenceFee eq '0'}">
										<font color="green">0.00</font>
									</c:if>
									<c:if test="${vType.differenceFee ne '0.00' &&  vType.differenceFee ne '0'}">
										<font color="red"><fmt:formatNumber pattern="#,##0.00" value="${vType.differenceFee}"/></font>
									</c:if>
								</c:if>
							</td>
							<td align="right">
								<c:if test="${vType.receiveMoney == null}">--</c:if>
								<c:if test="${vType.receiveMoney != null}"><fmt:formatNumber pattern="#,##0.00" value="${vType.receiveMoney}"/></c:if>
							</td>
							<td align="right">
								<c:if test="${vType.differenceTotal == null}">--</c:if>
								<c:if test="${vType.differenceTotal != null}">
									<c:if test="${vType.differenceTotal eq '0.00' || vType.differenceTotal eq '0'}">
										<font color="green">0.00</font>
									</c:if>
									<c:if test="${vType.differenceTotal ne '0.00' && vType.differenceTotal ne '0'}">
										<font color="red"><fmt:formatNumber pattern="#,##0.00" value="${vType.differenceTotal}"/></font>
									</c:if>
								</c:if>
							</td>
							<td align="right"> 
								<c:if test="${vType.fictitiousRecharge == null}">--</c:if>
								<c:if test="${vType.fictitiousRecharge != null}"><fmt:formatNumber pattern="#,##0.00" value="${vType.fictitiousRecharge}"/></c:if>
							</td>
							<c:if test="${vIndex.index == 0}">
								<td rowspan="6" >
											<c:if test="${vType.isSuccess == 1}"><span style="color: green;">对账成功</span></c:if>
											<c:if test="${vType.isSuccess == 2}"><span style="color: red;">保存草稿</span></c:if>
											<c:if test="${vType.isSuccess == 3}"><span style="color: blue;">未对账</span></c:if>
											<c:if test="${vType.isSuccess == 4}"><span style="color: red;">信息不符</span></c:if>
								</td>
							</c:if>
							<c:if test="${vIndex.index == 0}">
								<td rowspan="6"  <%-- title="${vType.remarks}"  style="width:110px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;color: blue;" --%>> ${vType.remarks}</td>
							</c:if>
							<c:if test="${vIndex.index == 0}">
								<td rowspan="6" >
									<c:if test="${vType.isSuccess == 1}">--</c:if>
									<c:if test="${vType.isSuccess == 2 || vType.isSuccess == 3}"><a href="javascript:void(0);" onclick="edit('<fmt:formatDate value="${vType.date}" pattern="yyyy-MM-dd"/>')">核对</a></c:if>
									<c:if test="${vType.isSuccess == 4}">
										<a href="javascript:void(0);" onclick="updateWithdrawal(1,'<fmt:formatDate value="${vType.date}" pattern="yyyy-MM-dd"/>');">数据更新</a>
									</c:if>
								</td>
							</c:if>		
						</tr>
				</c:forEach>
		</c:forEach>
	</tbody>	
</table>
<div><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%></div>