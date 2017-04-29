<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="vo" items="${page.result}" varStatus="status">
	<tr class="tr_${status.index%2}">
		<td>${status.index + 1}</td>
		
						<td>${vo.username}</td>
						<td>
						    <c:if test="${vo.flag==1}">
							${vo.account}
							</c:if>
						</td>
						<td>
						    <c:if test="${vo.flag==2}">
							${vo.account}
							</c:if>
						</td>
						 
						<td>${vo.account}</td>
						<td><fmt:formatDate value="${vo.addtime}" type="both"/></td>
						<td>
						     <c:if test="${vo.flag==1}">
                                   <c:if test="${vo.type==1}">可用余额转入</c:if>
                                   <c:if test="${vo.type==2}">投标资金退回</c:if>
                                   <c:if test="${vo.type==3}">购买债权资金退回</c:if>
                                   <c:if test="${vo.type==5}">定期宝转入</c:if>
                             </c:if>
						     <c:if test="${vo.flag==2}">
						           <c:if test="${vo.type==0}">转出到可用余额</c:if>
						           <c:if test="${vo.type==1}">投标转出</c:if>
                                   <c:if test="${vo.type==2}">开通直通车转出</c:if>
                                   <c:if test="${vo.type==3}">购买债权转出</c:if>
                                   <c:if test="${vo.type==4}">购买直通车转让转出</c:if>
                                   <c:if test="${vo.type==5}">购买定期宝转出</c:if>
                                   <c:if test="${vo.type==6}">内转交易转出</c:if>
						     </c:if>
						</td>
						<td>${vo.total}</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="8">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>