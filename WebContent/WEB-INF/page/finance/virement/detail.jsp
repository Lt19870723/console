<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<title>内部转账-国诚金融后台管理系统</title>
</head>
<body>
   <form id="" name="" method="post">
     <input type="hidden" id="id" name="id" value="" />
     <div class="listzent">转账详情</div>
		<table style="margin-left: 10px;margin-right: 10px; margin-top: 5px;">
		    <tr>
		       <td align="right" width="25%">汇款编号：</td>
		       <td colspan="3">${moneyOperation.operationCode }</td>
		    </tr>
			<tr>
				<td align="right" width="25%">汇款金额：</td>
				<td >${moneyOperation.operationrMoney}</td>
				<td align="right" width="25%">业务发生时间：</td>
				<td ><fmt:formatDate value="${moneyOperation.businessTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td align="right" width="25%">付款人：</td>
				<td >${moneyOperation.payerName}</td>
				<td align="right" width="25%">收款人：</td>
				<td>${moneyOperation.payeeName}</td>
			</tr>
			<c:if test="${moneyOperation.outType == 2}">
				<tr>
					<td align="right" width="25%">付款支行：</td>
					<td >${moneyOperation.payerSubbranch}</td>
					<td align="right" width="25%">收款支行：</td>
					<td>${moneyOperation.payeeSubbranch}</td>
				</tr>
			</c:if>
			<tr>
				<c:if test="${moneyOperation.outType == 2}">
					<td align="right" width="25%">付款银行：</td>
					<td>${moneyOperation.payerBankName}</td>
				</c:if>
				<c:if test="${moneyOperation.inType == 2}">
						<td align="right" width="25%">收款银行：</td>
						<td>${moneyOperation.payeeBankName}</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${moneyOperation.outType == 2}">
				<td align="right" width="25%">付款银行卡号：</td>
				<td>${moneyOperation.payerCardNo}</td>
				</c:if>
			<c:if test="${moneyOperation.inType == 2}">
				<td align="right" width="25%">收款银行卡号：</td>
				<td >${moneyOperation.payeeCardNo}</td>
			</c:if>
			</tr>
			<tr>
				<td align="right" width="25%">付款方式：</td>
				<td>
					<c:choose>
						<c:when test="${moneyOperation.outType==2}">银行卡转账</c:when>
						<c:when test="${moneyOperation.outType==1}">现金</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
				</td>
				
				<td align="right" width="25%">收款方式：</td>
				<td >
				<c:choose>
						<c:when test="${moneyOperation.inType==2}">银行卡转账</c:when>
						<c:when test="${moneyOperation.inType==1}">现金</c:when>
						<c:otherwise></c:otherwise>
				</c:choose>
				</td>
			</tr>
			
			<tr>
				<td align="right" width="25%">备注：</td>
				<td colspan="3">${moneyOperation.remark }</td>
			</tr>
			
			<c:if test="${fn:length(fileList) > 0}">
			<tr>
				<td align="right" width="25%">附件：</td>
				<td colspan="3">
				<c:forEach items="${fileList}" var="fileList">
				<a href="${path}/${fileList.url}" target="_bank">查看</a>
				</c:forEach>
				</td>
			</tr>
			</c:if>
			
			<tr>
				<td align="right" width="25%">申请人：</td>
				<td >${moneyOperation.operator}</td>
				<td align="right" width="25%">审核人：</td>
				<td >${moneyOperation.approvalUser}</td>
			</tr>
			
			<c:if test="${moneyOperation.status == 3}">
			<tr>
				<td align="center" colspan="4"><input onclick="downloadInterTransferExcel(${moneyOperation.id});" type="button" id="printBtn" name="printBtn" value="导出打印" />
				</td>
			</tr>
			</c:if>	
		</table>			
 </form>
 </body>
 <script type="text/javascript">
 	function downloadInterTransferExcel(id){
 		window.location.href = '${path}/finance/interTransfer/' + id + "/downloadInterTransferExcel.html";
 	}
 </script>
</html>