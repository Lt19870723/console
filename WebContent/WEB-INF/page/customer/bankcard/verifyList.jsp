<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<tbody>
	<c:forEach var="BankcardVerification" items="${page.result}"  varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${BankcardVerification.userName}</td>
			<td>${BankcardVerification.verifyTime}</td>
			<td>${BankcardVerification.auResultInfo}</td>
			<td>${BankcardVerification.bankCardNum}</td>
			<td>${BankcardVerification.realName}</td>
			<td>${BankcardVerification.idCardNo}</td>
			<td>${BankcardVerification.bankPhone}</td>
			<td>
			   <c:if test="${BankcardVerification.deleteFlag==-1}">
				     已删除
				</c:if> 
				<c:if test="${BankcardVerification.deleteFlag!=-1}">
				   未删除
				</c:if> 
		    </td>
			<td>
				<c:if test="${BankcardVerification.type==1}">
				     绑卡
				</c:if> 
				<c:if test="${BankcardVerification.type==2}">
				     换卡
				</c:if> 
			</td>
			<td>
		     	<c:if test="${BankcardVerification.deleteFlag!=-1}">
				     <a href="javascript:delVerify('${BankcardVerification.id}')">删除</a>
				</c:if> 
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="11">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>
