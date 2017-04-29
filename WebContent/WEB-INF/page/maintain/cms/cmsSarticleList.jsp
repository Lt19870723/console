<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="CmsArticleVo" items="${page.result}">
		<tr>
			<td><input  name="selectCheckbox"    type="checkbox"  value="${CmsArticleVo.id}"   /> </td>
		     <td>${CmsArticleVo.title}</td>
			 <td>${CmsArticleVo.author}</td>
		     <td>${CmsArticleVo.channelName}</td>
			 <td>${CmsArticleVo.isTop?'是':'否'}</td>
			 <td>${CmsArticleVo.createTimeStr}</td>
			 <td>  
			 
			 <c:if test="${CmsArticleVo.isTop}">
				 	<a onclick="hiddenSarticle(${CmsArticleVo.id},0,${page.pageNo})">取消置顶</a>
			 </c:if>
		 	 <c:if test="${!CmsArticleVo.isTop}">
			 		<a onclick="hiddenSarticle(${CmsArticleVo.id},1,${page.pageNo})">置顶</a>
		 	 </c:if>
			 <a onclick="editCmsSarticle(${CmsArticleVo.id})">编辑</a> 
			    <%-- <a4j:commandLink id="fortop" name="fortop" value="#{cmsarticle.isTop?'取消置顶':'置顶'}"
			       action="#{cmsArticleAction.updateTop()}"
					execute="@this"
					data=""
					oncomplete="afterCmsArticle()" 
					render="CmsArticleList">
					<a4j:param   name="topStaus"   value="#{cmsarticle.isTop?0:1}"   assignTo="#{cmsArticleAction.topStaus}"></a4j:param>
					<a4j:param   name="editCmsArticleId"   value="#{cmsarticle.id}"   assignTo="#{cmsArticleAction.cmsArticleId}"></a4j:param>
				 </a4j:commandLink>
			 &nbsp;&nbsp;
			     <a4j:commandLink id="forcheck" name="forcheck" value="编辑"
			       action="#{cmsArticleAction.toSaveArticle()}"
					execute="@form"
					data=""
					oncomplete="showAddPopup(1);" 
					render="addcmsarticlePopupform">
					<a4j:param   name="editCmsArticleId"   value="#{cmsarticle.id}"   assignTo="#{cmsArticleAction.cmsArticleId}"></a4j:param>
				 </a4j:commandLink> --%>
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
