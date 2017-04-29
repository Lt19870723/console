<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="BankCardLockVo" items="${page.result}" varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${BankCardLockVo.vusername}</td>
			<td>${BankCardLockVo.vemial}</td>
			<td>${BankCardLockVo.vmobile}</td>
			<td>${BankCardLockVo.vrealname}</td>
			<td>${BankCardLockVo.vlockStatus}</td>
			<td><fmt:formatDate value="${BankCardLockVo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${BankCardLockVo.vtotal}</td>
			<td>${BankCardLockVo.vcollection}</td>
			<td>${BankCardLockVo.vuseMoney}</td>
			<td>${BankCardLockVo.vnoUseMoney}</td>
			<td>${BankCardLockVo.curTotal}</td>
			<td>${BankCardLockVo.fixTotal}</td>
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
