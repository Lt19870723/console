<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td align="center" >
			${vo.username }
		</td>
		<td align="center" >
			${vo.accountStr }
		</td>
		<td align="center" >
			${vo.useMoneyStr }
		</td>										
		<td align="center">
			${vo.orderNum}
		</td>
		<td align="center">
			${vo.statusStr}
		</td>
		<td align="center">
			<fmt:formatDate value="${vo.buyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td align="center">
			<fmt:formatDate value="${vo.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>
			<a href="javascript:;" onclick="unlock(${vo.id},${vo.userId});" style="${vo.unLockYn=='Y'?'':'display:none'}">申请解锁</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="9">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>