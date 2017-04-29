<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>投资金额</th>
			<th>原投资冻结流水号</th>
			<th>添加时间</th>
			<th>处理结果</th>
			<th>备注</th>
			<th>审核备注</th>
			<th>操作</th>
		</tr>
	
	<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
			<td>${sta.index+1}</td>
			<td>${vo.userName }</td>
			<td><fmt:formatNumber pattern="#,##0.00" value="${vo.account }"></fmt:formatNumber> </td>
			<td>${vo.oriSerialNo }</td>
			<td><fmt:formatDate value="${vo.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			<td>
				<c:if test="${vo.disposeStatus==0}">
					未处理
				</c:if>
				<c:if test="${vo.disposeStatus==1}">
					已处理
				</c:if>
			</td>
			<td>${vo.remark }</td>
			<td>${vo.checkRemark }</td>
			<td>
			<c:if test="${vo.disposeStatus==0}">
					<a href="javascript:;" onclick="showAuditLayer(${vo.id});">审核</a>
				</c:if>
			</td>
			</tr>
	</c:forEach>		
	
	</table>
	<div>
	   <td style="text-align: left;" colspan="12">
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
		</td>
	</div>
</div>