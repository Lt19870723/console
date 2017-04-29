<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.statusStr}</td>
		<td>${vo.planAccountStr}</td>
		<td>${vo.publishTimeStr}</td>
		<td>${vo.validTimeStr }</td>
		<td>${vo.tenderTimes}</td>
		<td>
			<a href="javascript:;" onclick="toAddModify(${vo.id},'view');">查看</a>
			<c:if test="${vo.status == 0}">
			&nbsp;<a href="javascript:;" onclick="toAddModify(${vo.id},'modify');">修改</a>
			&nbsp;<a href="javascript:;" onclick="doDelete(${vo.id},'${vo.version}');">删除</a>
			&nbsp;<a href="javascript:;" onclick="toAddModify(${vo.id},'submitApprove');">提交审核</a>
			</c:if>
			<c:if test="${vo.status == 3 && vo.flagUpdate==1}">
			&nbsp;<a href="javascript:;" onclick="toUpdate(${vo.id},'modify');">修改</a>			
			</c:if>			
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="7">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>