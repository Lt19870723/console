<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setAttribute("portal_path", com.cxdai.console.util.PropertiesUtil.getValue("portal_path")); %>
<tbody>
	<c:forEach var="realNameApprVo" items="${page.result}" varStatus="i">
						<tr>
							<td>${i.index+1}</td>
							<td>${realNameApprVo.username}</td>
							<td>${realNameApprVo.realName}</td>
							<td>${realNameApprVo.cardType}</td>
							<td>${realNameApprVo.idCardNo}</td>
							<td><c:if test="${realNameApprVo.pic1!=null}"><a target="_blank" href="${portal_path}/${realNameApprVo.pic1 }">点击查看</a></c:if><c:if test="${realNameApprVo.pic1==null}">/</c:if></td>
							<td><c:if test="${realNameApprVo.pic2!=null}"><a target="_blank" href="${portal_path}/${realNameApprVo.pic2 }">点击查看</a></c:if><c:if test="${realNameApprVo.pic2==null}">/</c:if></td>
							<td>${realNameApprVo.appTimeStr}</td>
							<td>
								${realNameApprVo.approveUser=='-1'?'自动审核':realNameApprVo.approveUser}								
							</td>
							<td>
								 <a  id="forApproveRealName"  name="forApproveRealName"  style="${realNameApprVo.isPassed==0?'':'display:none'}"   href="javascript:forApproveRealName(${realNameApprVo.id},${realNameApprVo.version})" > 立即审核 </a>
								 ${realNameApprVo.isPassed==1?'审核通过':''} 
								 ${realNameApprVo.isPassed==-1?'审核不通过':''}
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