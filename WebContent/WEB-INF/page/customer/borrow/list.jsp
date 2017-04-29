<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tbody>
	<c:forEach var="memberVo" items="${page.result}" varStatus="i">
						<tr>
						<td>
							<input type="checkbox" name="cashId" value="${memberVo.id}"  
							style="${memberVo.status == 0 or memberVo.status == -3 ?'display:none':''}"/>
						</td>
							<td>${i+1}</td>
							<td>${memberVo.userName}</td>
							<td>${memberVo.email}</td>
							<td>${memberVo.mobileNum}</td>
							<td>${memberVo.addTime}</td>
							<td>
								${memberVo.type== 0?'正式身份':''}
								${memberVo.type==-1?'游客身份':''}
							</td>
							<td>${memberVo.auditTime}</td>
							<td>${memberVo.auditName}</td>
							<td>
							   <a id="forApproveRealName"   href="javascript:"  style="${memberVo.status==-2?'':'display:none'}"  >立即审核</a>
								 ${memberVo.status==0?'审核通过':''} 
								 ${memberVo.status==-3?'审核不通过':''}  
							</td>						
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