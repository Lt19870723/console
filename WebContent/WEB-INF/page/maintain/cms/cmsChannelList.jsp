<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="CmsChannel" items="${page.result}">
		<tr>
			<td>${CmsChannel.name}</td>
			 <td>${CmsChannel.order}</td>
		     <td>${CmsChannel.urlCode}</td>
		     <td>${CmsChannel.isHidden==0?'隐藏':'显示'}</td>
		     <td>${CmsChannel.parentChannelName}</td>
			 <td>   
			 	<c:if test="${CmsChannel.isHidden==1}">
				 	<a onclick="hiddenChannel(${CmsChannel.id},0,${page.pageNo})">隐藏</a>
			 	</c:if>
			 	<c:if test="${CmsChannel.isHidden==0}">
				 	<a onclick="hiddenChannel(${CmsChannel.id},1,${page.pageNo})">显示</a>
			 	</c:if>
				 <a onclick="editCmsChannel(${CmsChannel.id})">编辑</a>
				 <a onclick="delCmsChannel(${CmsChannel.id},${page.pageNo})">删除</a>
			 </td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="6">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
