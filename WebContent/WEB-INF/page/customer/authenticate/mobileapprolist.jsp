<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="MobileApproVo" items="${page.result}" varStatus="i">
						 <tr>
							<td>${i.index+1}</td>
							<td>${MobileApproVo.username}</td>
							<td>${MobileApproVo.mobileNum}</td>
					 		<td>
						 		${MobileApproVo.passed==1?'已审核用户':''}
						 		${MobileApproVo.passed==0?'待审核用户':''}
						 		${MobileApproVo.passed==-1?'审核不通过用户':''}
							</td>
						  <td>
						  
						     ${MobileApproVo.memberType==0?'借款用户':''}
						     ${MobileApproVo.memberType==1?'理财用户':''}
					 	 </td>
							 <td>
								 <a        style="${MobileApproVo.passed == 0?'':'display:none'}"   href="javascript:submitCheck(${MobileApproVo.userId},'pass')"   >通过</a>
								 &nbsp;&nbsp;
								 <a       style="${MobileApproVo.passed == 0?'':'display:none'}"   href="javascript:submitCheck(${MobileApproVo.userId},'reject')"   >不通过</a>
								 &nbsp;&nbsp;
								  <a     style="${MobileApproVo.passed == 1?'':'display:none'}"    href="javascript:forCheckBorrow(${MobileApproVo.userId})"   >修改</a>
							 </td>				
						</tr>
	</c:forEach>
	<tr>
		<td style="text-align: left;" colspan="6">
			共&nbsp;${page.totalPage}&nbsp;页 &nbsp;共&nbsp;${page.totalCount}&nbsp;条 第 &nbsp;${page.pageNo}/${page.totalPage}&nbsp;页
			<input value="首页" type="button" onclick="pageGo(1);" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="上一页" type="button" onclick="pageGo(${page.prePage});" ${page.hasPre ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="下一页" type="button" onclick="pageGo(${page.nextPage});" ${page.hasNext ? '' : 'disabled="disabled"'} />&nbsp;
			<input value="末页" type="button" onclick="pageGo(${page.totalPage});" ${page.totalPage > 1 ? '' : 'disabled="disabled"'} />
		</td>
	</tr>
</tbody>