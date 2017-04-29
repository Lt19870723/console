<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<c:if test="${fixTenderDetailVo.contractNo != null}">
		定期宝:${fixTenderDetailVo.contractNo}&nbsp;&nbsp;
		开放额度:${fixTenderDetailVo.planAccount}&nbsp;&nbsp;
		用户加入总额:${fixTenderDetailVo.sumAccount}
	</c:if>
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th width="40">序号</th>
			<th>定期宝编号</th>
			<th>用户名</th>
			<th>分笔加入金额(元)</th>
			<th width="160">加入时间</th>
			<th width="80">认购方式</th>
			<th>自动投宝方式</th>
			<th width="70">排队号</th>
			<th width="100">投宝日志编号</th>
			<th width="70">状态</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.contractNo }</td>
				<td>${vo.userName }</td>
				<td><fmt:formatNumber value="${vo.account}" pattern="#,##0.00" /></td>
				<td><fmt:formatDate value="${vo.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><c:if test="${vo.tenderType==1 }">
				              自动投宝
				  </c:if> <c:if test="${vo.tenderType==0 }">
				              手动投宝
				  </c:if></td>
				<td><c:if test="${vo.autoTenderType==1 }">
				              按金额投宝
				  </c:if> <c:if test="${vo.autoTenderType==2 }">
				              按账户余额
				  </c:if></td>
				<td><c:if test="${vo.tenderType==1 }">
				       ${vo. rownum}
				  </c:if></td>
				<td><c:if test="${vo.tenderType==1 }">
				       ${vo. autoInvestRecordId}
				  </c:if></td>
				<td>${vo.statusStr }</td>
			</tr>
		</c:forEach>
		<tr>
			<td style="text-align: left;" colspan="10"><%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
			</td>
		</tr>
	</table>
</div>