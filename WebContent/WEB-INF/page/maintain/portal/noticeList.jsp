<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="NewsNoticeVo" items="${page.result}">
		<tr>
			<td>${NewsNoticeVo.title}</td>
			<td>${NewsNoticeVo.typeStr}</td>
			<td>${NewsNoticeVo.statusStr}</td>
			<td>${NewsNoticeVo.author}</td>
			<td>${NewsNoticeVo.source}</td>
			<td>${NewsNoticeVo.addtimeStr}</td>
			<td>${NewsNoticeVo.updatetimeStr}</td>
			<td>${NewsNoticeVo.hits}</td>
			<td>
				<a onclick="delNotice(${NewsNoticeVo.id},${page.pageNo})">删除</a>&nbsp;
				<a onclick="editNotice(${NewsNoticeVo.id},${page.pageNo})">修改</a>
			<%-- <a4j:commandLink id="deletenewsNotice"
					name="deletenewsNotice" value="删除"
					onclick="return deleteMessage();"
					action="#{newsNoticeAction.deleteNewsNotice}" data=""
					oncomplete="afterdeleteNewsNotice()">
					<a4j:param name="newsNoticeId" value="#{newsNoticeVo.id}"
						assignTo="#{newsNoticeAction.newsNotice.id}" />
				</a4j:commandLink> &nbsp; <a4j:commandLink id="forupdatenewsNotice"
					name="forupdatenewsNotice" value="修改" onclick=""
					action="#{newsNoticeAction.searchNewsNoticeByPrimaryKey}"
					oncomplete="showMoidfyPopup();" render="updateform">
					<a4j:param name="newsNoticeId" value="#{newsNoticeVo.id}"
						assignTo="#{newsNoticeAction.newsNotice.id}" />
				</a4j:commandLink> --%>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="9">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
