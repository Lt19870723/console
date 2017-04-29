<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="WxBlackListVo" items="${page.result}" varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${WxBlackListVo.username}</td>
			<td>
				<c:if test="${WxBlackListVo.status==1}">已禁止</c:if>
				<c:if test="${WxBlackListVo.status==-1}">未禁止</c:if>
				<c:if test="${WxBlackListVo.status==2}">已处理</c:if>
			</td>
			<td>
				<c:if test="${WxBlackListVo.status==1}">
					<a onclick="cancelBlackList(${WxBlackListVo.id},${page.pageNo})">取消</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="4">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
	<tr>
		<td  style="margin-top:5px;color:red;text-align:left" colspan="4">
			&nbsp;&nbsp;温馨提示：<br/>
			&nbsp;&nbsp;1、对于微信群推,需要用户自行回复TDXXQT退订,回复DYXXQT取消退订<br/>
		</td>
	</tr>
</tbody>
