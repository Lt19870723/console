<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		<td>${vo.title}</td>
		<td>
			<a target="_blank" href="${empty vo.picurl?'javascript:;': consoleWxImagePath}${vo.picurl}">查看</a>
		</td>
		<td><a target="_blank" href="${vo.url}">${vo.url}</a></td>
		<td>${vo.showStatus}</td>
		<td><fmt:formatDate value="${vo.pushTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${vo.pushNumTotal}</td>
		<td>${vo.pushNum}</td>
		<td>
			<a href="javascript:;" onclick="toAddModify(${vo.id});" style="${vo.status ==2?'':'display:none'}" >修改&nbsp;</a>
			<a href="javascript:;" onclick="doDelete(${vo.id});" style="${vo.status ==2?'':'display:none'}" >删除&nbsp;</a>
			<a href="javascript:;" onclick="push(${vo.id});" style="${vo.status ==2?'':'display:none'}" >推送&nbsp;</a>
			<a href="javascript:;" onclick="pushAgain(${vo.id});" style="${vo.status ==1?'':'display:none'}" >复制&nbsp;</a>
			<a href="javascript:;" onclick="detail(${vo.id});" >查看</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="9">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>