<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<tbody>
	<c:forEach var="BankVo" items="${page.result}"  varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${BankVo.province}</td>
			<td>${BankVo.city}</td>
			<td>${BankVo.district}</td>
			<td>${BankVo.bankName}</td>
			<td>${BankVo.branchName}</td>
			<td>${BankVo.cnapsCode}</td>
			<td>${BankVo.addUserName}</td>
			<td>
				<fmt:formatDate value="${BankVo.addTime}" pattern="yyyy-MM-dd" />
			</td>
			<td>${BankVo.updateUserName}</td>
			<td>
				<fmt:formatDate value="${BankVo.updateTime}" pattern="yyyy-MM-dd" />
			</td>
			<td>${BankVo.status==0?"正常":"失效"}</td>
			<td>
				<a onclick="editBank(${BankVo.id})">修改</a>&nbsp;
				<a onclick="delBank(${BankVo.id},${page.pageNo })">删除</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="13">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
