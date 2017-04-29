<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="ShowList" items="${page.result}" varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td><a onclick="openWindow(${ShowList.id})">${ShowList.showText}</a><input type="hidden" id="text${ShowList.id}" value="${ShowList.text}"/></td>
			<td>${ShowList.showStatus}
			</td>
			<td>${ShowList.pushTime}
			</td>
			<td>${ShowList.pushNumTotal}</td>
			<td>${ShowList.pushNum}</td>
			<td>
				<c:if test="${ShowList.status == 2}">
					<a onclick="openEdit(${ShowList.id});">修改&nbsp;</a>
					<a onclick="deleteWxPush(${ShowList.id});">删除&nbsp;</a>
					<a onclick="pushAgain(${ShowList.id});">推送&nbsp;</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="7">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
