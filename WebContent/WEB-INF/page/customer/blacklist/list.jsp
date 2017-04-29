<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.username}</td>
		<td>
			<c:choose>
				<c:when test="${vo.type == 1}">禁止手动投标</c:when>
				<c:when test="${vo.type == 2}">禁止认购直通车</c:when>
				<c:when test="${vo.type == 3}">禁止认购债权转让</c:when>
				<c:when test="${vo.type == 4}">禁止设置自动投标</c:when>
				<c:when test="${vo.type == 5}">禁止线上充值</c:when>
				<c:when test="${vo.type == 6}">禁止提现</c:when>
				<c:when test="${vo.type == 7}">禁止发净值标</c:when>
				<c:when test="${vo.type == 8}">直通车下车</c:when>
				<c:when test="${vo.type == 10}">禁止加入活期宝</c:when>
				<c:when test="${vo.type == 11}">禁止发帖和回帖</c:when>
				<c:when test="${vo.type == 12}">禁止登录</c:when>
				<c:when test="${vo.type == 13}">禁止认购定期宝</c:when>
			</c:choose>
		</td>
		<td>
			<c:choose>
				<c:when test="${vo.type == 1}">已禁止</c:when>
				<c:when test="${vo.type == -1}">未禁止</c:when>
				<c:when test="${vo.type == 3}">已处理</c:when>
			</c:choose>
		</td>
		<td>${vo.remark}</td>
		<td>
			<a href="javascript:;" onclick="doCancel(${vo.id},'${vo.userId}','${vo.type}');" 
				style="${vo.status == 1 ?'':'display:none'}">取消</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="6">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>