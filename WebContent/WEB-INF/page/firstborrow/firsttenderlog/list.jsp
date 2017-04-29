<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.username}</td>
		<td>
		第${vo.orderNum}位
		</td>
		<td>
		<a target="_blank" href="${portal_path}/toubiao/${vo.borrowId}.html">
		${vo.borrowName}
		</a>
		</td>
		<td>
		${vo.borrowContractNo}
		</td>
		<td>
			<fmt:formatNumber value="${vo.remaindMoney}" pattern="#,##0.00"/>
		</td>
		<td>
			<fmt:formatNumber value="${vo.useMoney}" pattern="#,##0.00"/>
		</td>
		<td>
			<fmt:formatNumber value="${vo.realAccount}" pattern="#,##0.00"/>
		</td>
		<td>
			<c:if test="${vo.status==1}">
				已投标
			</c:if>
			<c:if test="${vo.status==2}">
				未投标
			</c:if>
		</td>
		<td><fmt:formatDate value="${vo.addTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>
			<a href="javascript:;" onclick="showADetailPopup(${vo.id});">${fn:substring(vo.remark,0,5)}...</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="11">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>