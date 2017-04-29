<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<tbody>
	<c:forEach var="BankinfoLog" items="${page.result}"  varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${BankinfoLog.userName}</td>
			<td>${BankinfoLog.cardNum}</td>
			<td>${BankinfoLog.realName}</td>
			<td>${BankinfoLog.bankName}</td>
			<td>${BankinfoLog.bankVerify}</td>
			<td>${BankinfoLog.jobNum}</td>
			<td>${BankinfoLog.remark}</td>
			<td>
				<fmt:formatDate value="${BankinfoLog.addTime}" type="both"/>
			</td>
			<td>
				<c:if test="${BankinfoLog.type== 0}">锁定</c:if>
				<c:if test="${BankinfoLog.type== 1}">添加</c:if>
				<c:if test="${BankinfoLog.type== 2}">删除</c:if>
				<c:if test="${BankinfoLog.type== 3}">更换</c:if>
				<c:if test="${BankinfoLog.type== 4}">解绑</c:if>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="10">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
