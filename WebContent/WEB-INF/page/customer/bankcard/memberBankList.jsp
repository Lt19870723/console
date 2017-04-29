<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<tbody>
	<c:forEach var="MemberBankCardInfoVo" items="${page.result}"  varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${MemberBankCardInfoVo.username}</td>
			<td>${MemberBankCardInfoVo.realName}</td>
			<td>${MemberBankCardInfoVo.email}</td>
			<td>${MemberBankCardInfoVo.IDCardNo}</td>
			<td>${MemberBankCardInfoVo.cardNum}</td>
			<td>${MemberBankCardInfoVo.bankName}</td>
			<td>${MemberBankCardInfoVo.branch}</td>
			<td>${MemberBankCardInfoVo.bankVerify}</td>
			<td><fmt:formatDate value="${MemberBankCardInfoVo.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${MemberBankCardInfoVo.cnapsCode}</td>
			<td>
				<c:if test="${MemberBankCardInfoVo.bankCardStatus == -1}">已删除</c:if>
				<c:if test="${MemberBankCardInfoVo.bankCardStatus == 0}">已绑定</c:if>
				<c:if test="${MemberBankCardInfoVo.bankCardStatus == 1}">审核中</c:if>
				<c:if test="${MemberBankCardInfoVo.bankCardStatus == 2}">已冻结</c:if>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="12">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
