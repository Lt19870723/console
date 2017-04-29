<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<tbody>
	<c:forEach var="financingVo" items="${page.result}" varStatus="i">
						<tr>
							<td>${i.index+1}</td>
							<td>${financingVo.name}</td>
							<td>${financingVo.tel}</td>
							<td>${financingVo.qq}</td>
							<td>
							${financingVo.mortgageType==1?'房产抵押':''}
							${financingVo.mortgageType==2?'车产抵押':''}
							${financingVo.mortgageType==3?'民间抵押':''}
							</td>
							<td>
							<fmt:formatNumber   value="${financingVo.account*10000}"  maxFractionDigits="2"/>
						         元</td>
							<td>${financingVo.timeLimit}月</td>
							<td>${financingVo.provinceName}</td>
							<td>${financingVo.cityName}</td>
							<td>
							     ${financingVo.approveStatus==1?'待审核':''}
							     ${financingVo.approveStatus==2?'审核通过':''} 
								 ${financingVo.approveStatus==3?'审核不通过':''}
						    </td>
							<td>${financingVo.appTime}</td>
							<td> 
							     <a style="${financingVo.approveStatus!=1?'':'display:none'}"  href="javascript:viewRealName(${financingVo.id})">查看 </a>
								 <a  id="forApproveRealName"  name="forApproveRealName"  style="${financingVo.approveStatus==1?'':'display:none'}"   href="javascript:forApproveRealName(${financingVo.id})" > 审核 </a>
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