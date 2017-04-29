<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<tbody>
	<c:forEach var="BankcardChange" items="${page.result}"  varStatus="i">
		<tr>
			<td>${i.index+1}</td>
			<td>${BankcardChange.userName}</td>
			<td>${BankcardChange.realName}</td>
		   	<c:if test="${BankcardChange.newBankcard!=null}"> 
		    	 <td>${BankcardChange.newBankcard}</td>
		         <td>${BankcardChange.newBank}</td>
			</c:if>
			  <c:if test="${BankcardChange.newBankcard==null}">
			    <td>${BankcardChange.oldBankcard}</td>
		         <td>${BankcardChange.oldbank}</td>
			  </c:if>
			<td>${BankcardChange.addTimeStr}</td>
			<td>${BankcardChange.lastAddTimeStr}</td>
			<td>${BankcardChange.updateReason}</td>
			  <td>
			   	<c:if test="${BankcardChange.newBankcard!=null}"> 
				    换卡
				</c:if>
				  <c:if test="${BankcardChange.newBankcard==null}">
				    解绑
				</c:if>
			</td>
			<td>
				<c:if test="${BankcardChange.approveStatus==0}">
					<a href="javascript:approveBankInfo(${BankcardChange.id})">审核</a>
				</c:if>
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
