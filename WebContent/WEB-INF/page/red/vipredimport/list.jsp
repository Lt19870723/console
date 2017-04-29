<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	<table  cellspacing="0" cellpadding="0" class="fulltable">
		<tr>
			<th>序号</th>
			<th>操作人</th>
			<th>上传日期</th>
			<th>总记录数</th>
			<th width="170px">发放时间</th>
			<th>状态</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.optName}</td>
				<td><fmt:formatDate value="${vo.addTime}" type="date"/></td>
				<td><a href="javascript:detail(${vo.id});">${vo.number}</a></td>
				<td width="170px"><fmt:formatDate value="${vo.optTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
					<c:if test="${vo.status==0}">未发放</c:if>
					<c:if test="${vo.status==1}">已发放</c:if>
				</td>
				<td>${vo.remarkInfo}</td>
				<td>
				<c:if test="${vo.status==0}">
				<input type="button" onclick="javascript:operate('${vo.id }');" type="button" name="Submit1"
				id="subbtn"  value="发放红包" />
				<input type="button" onclick="javascript:deleteImport('${vo.id }');" type="button" name="Submit1"
				id="subbtn"   value="删除" />
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>