<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setAttribute("portal_path", com.cxdai.console.util.PropertiesUtil.getValue("portal_path")); %>
<tbody>
	<c:forEach var="bankApprVo" items="${page.result}" varStatus="i">
						<tr>
							<td>${i.index+1}</td>
							<td>${bankApprVo.username}</td>
							<td>${bankApprVo.realName}</td>
							<td>${bankApprVo.bankName}</td>
							<td>${bankApprVo.cardNum}</td>
							<td>${bankApprVo.branch}</td>
							<td>${bankApprVo.idCardNo}</td>
							<td>${bankApprVo.bankCode}</td>
							<td><fmt:formatDate value="${bankApprVo.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>
								 <a  id="forApproveBank"  name="forApproveBank"  style="${bankApprVo.status==3?'':'display:none'}"   href="javascript:forApproveBank(${bankApprVo.id})" > 立即审核 </a>
								 ${bankApprVo.status==0?'审核通过':''} 
								 ${bankApprVo.status==-1?'审核不通过':''}
								 ${bankApprVo.status==2?'已冻结':''}
								 ${bankApprVo.status==1?'换卡等待审核':''}
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