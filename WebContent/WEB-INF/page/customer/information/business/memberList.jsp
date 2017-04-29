<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>  
	<c:forEach var="b" items="${page.result}" varStatus="n">
	<tr class="tr_${n.index%2}">
		<td>${n.count }</td>
		<td>${b.username}</td>
		<td>${b.realName}</td>
		<td>${b.mobileNum}</td>
		<td>${b.email}</td>
		<td><fmt:formatDate value="${b.addtimeDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td>
			<a href="javascript:pushSelectedBusiness('${b.id}','${b.username}');">选择</a>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="7">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</tr>
</tbody>

 
