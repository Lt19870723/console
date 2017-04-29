<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ include file="/WEB-INF/page/common/public.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>国诚金融—自动投宝记录日志查看详情</title>
<style type="text/css">
#checkBorrowPopupform td{
	height: 40px;
}
</style>
</head>
<body>
	<form id="checkBorrowPopupform" action="" method="post">
		<table width="99%" border="1" style="margin: 5px;">
			<tr>
				<td width="10%">记录编号</td>
				<td bgcolor="#FFFFFF" width="22%">${fixAutoInvestRecordEntity.id}</td>
				<td width="14%">记录时间</td>
				<td bgcolor="#FFFFFF" width="22%"><fmt:formatDate value="${fixAutoInvestRecordEntity.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td width="15%">记录类型</td>
				<td bgcolor="#FFFFFF" width="17%">
					<c:if test="${fixAutoInvestRecordEntity.recordType==1}">设置</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==2}">修改</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==3}">停用 </c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==4}">启用</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==5}">删除</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==6}">投宝</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==7}">流宝</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==8}">撤宝</c:if>
					<c:if test="${fixAutoInvestRecordEntity.recordType==9}">重新排队</c:if>
				</td>
			</tr>
			<tr>
				<td>用户名</td>
				<td bgcolor="#FFFFFF">${fixAutoInvestRecordEntity.userName}</td>
				<td>排队号</td>
				<td bgcolor="#FFFFFF" >${fixAutoInvestRecordEntity.rownum>0?fixAutoInvestRecordEntity.rownum:null}</td>
				<td>状态</td>
				<td bgcolor="#FFFFFF" >
					<c:if test="${fixAutoInvestRecordEntity.status=='0'}">未启用</c:if>
					<c:if test="${fixAutoInvestRecordEntity.status=='1'}">已启用</c:if>
					<c:if test="${fixAutoInvestRecordEntity.status=='-1'}">已删除</c:if>
				</td>
			</tr>
			<tr>
				<td>投宝方式</td>
				<td bgcolor="#FFFFFF">
					<c:if test="${fixAutoInvestRecordEntity.autoTenderType==1}">按金额投宝</c:if>
					<c:if test="${fixAutoInvestRecordEntity.autoTenderType==2}">按账户余额投宝</c:if>
				</td>
				<td>投宝金额(元)</td>
				<td bgcolor="#FFFFFF"><fmt:formatNumber value="${fixAutoInvestRecordEntity.tenderMoney==0?null:fixAutoInvestRecordEntity.tenderMoney}" pattern="###,###" /></td>
				<td>是否使用活期宝</td>
				<td bgcolor="#FFFFFF">
					<c:if test="${fixAutoInvestRecordEntity.isUseCur==0}">不使用</c:if>
					<c:if test="${fixAutoInvestRecordEntity.isUseCur==1}">使用</c:if>
				</td>
			</tr>
			<tr>
				<td>定期宝期限</td>
				<td bgcolor="#FFFFFF">${fixAutoInvestRecordEntity.fixLimitTemp}</td>
				<td>投宝额度(元)</td>
				<td bgcolor="#FFFFFF"><fmt:formatNumber value="${fixAutoInvestRecordEntity.limitMoney}" pattern="###,###" /></td>
				<td>实际投宝金额(元)</td>
				<td bgcolor="#FFFFFF"><fmt:formatNumber value="${fixAutoInvestRecordEntity.recordType==6?fixAutoInvestRecordEntity.autoTenderMoney:null}" pattern="###,###" /></td>
			</tr>
			<tr>
				<td>所投宝编号</td>
				<td bgcolor="#FFFFFF" ><a href="${portalPath }/dingqibao/${fixAutoInvestRecordEntity.fixId}.html" target="_blank">${fixAutoInvestRecordEntity.fixNo}</a></td>
				<td>可用余额(元)</td>
				<td bgcolor="#FFFFFF"><fmt:formatNumber value="${fixAutoInvestRecordEntity.useMoney}" pattern="###,##0.00" /></td>
				<td>活期宝总额(元)</td>
				<td bgcolor="#FFFFFF"><fmt:formatNumber value="${fixAutoInvestRecordEntity.curMoney}" pattern="###,##0.00" /></td>
			</tr>
			<tr>
				<td>所投宝ID</td>
				<td bgcolor="#FFFFFF">${fixAutoInvestRecordEntity.fixId==0?null:fixAutoInvestRecordEntity.fixId}</td>
				<td>所投宝期限(月)</td>
				<td bgcolor="#FFFFFF">${fixAutoInvestRecordEntity.fixType}</td>
				<td>所投宝利率</td>
				<td bgcolor="#FFFFFF">
					<c:if test="${fixAutoInvestRecordEntity.apr!=null}">
						<fmt:formatNumber value="${fixAutoInvestRecordEntity.apr}" pattern="##.##" />%
		        	</c:if>
				</td>
			</tr>
			<tr>
				<td>排队编号</td>
				<td bgcolor="#FFFFFF">${fixAutoInvestRecordEntity.uptime}</td>
				<td>投宝前排队编号</td>
				<td bgcolor="#FFFFFF">${fixAutoInvestRecordEntity.preUptime}</td>
				<td>&nbsp;</td>
				<td bgcolor="#FFFFFF">&nbsp;</td>
			</tr>	
			<tr>
				<td>备注</td>
				<td bgcolor="#FFFFFF" colspan="5">${fixAutoInvestRecordEntity.remark}</td>
			</tr>
		</table>
	</form>
</body>
</html>
