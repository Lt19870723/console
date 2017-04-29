<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="CmsPediaEntry" items="${page.result}">
		<tr>
			<td>${CmsPediaEntry.name}</td>
		     <td>${CmsPediaEntry.initials}</td>
			 <td>   
			 	<a onclick="editCmsEntry(${CmsPediaEntry.id})">编辑</a>&nbsp;
			 	<a onclick="delCmsEntry(${CmsPediaEntry.id},${page.pageNo})">删除</a>
			 </td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="3">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
