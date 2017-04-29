<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	 
		
		<c:forEach   var="bankVo"  items="${page.result}"    varStatus="i" >
			<tr class='tr_${i.index%2}'>
				<td>${i.index+1}</td>
				<td>${bankVo.username}</td>
				<td>${bankVo.realName}</td>
				<td>${bankVo.email}</td>
				<td>${bankVo.IDCardNo}</td>
				<td>${bankVo.cardNum}</td>
				<td>${bankVo.bankName}</td>
				<td>${bankVo.branch}</td>
				<td>${bankVo.cnapsCode}</td>
			</tr>
		</c:forEach>
	
	<tr>
		<td style="text-align: left;" colspan="9">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>