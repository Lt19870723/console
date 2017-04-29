<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<tbody>
	<c:forEach var="VipLevelVo" items="${page.result}"  varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${VipLevelVo.username}</td>
			<td>${VipLevelVo.realName}</td>
			<td>${VipLevelVo.email}</td>
			<td>${VipLevelVo.mobileNum}</td>
<%-- 			<td>${VipLevelVo.isVip}</td> --%>
			<td>${VipLevelVo.isSvip}</td>
			<td>${VipLevelVo.order}</td>
			<td>
				<fmt:formatDate value="${VipLevelVo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
				<a onclick="forSetSvip(${VipLevelVo.userId})">设置终身顶级会员</a>
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
