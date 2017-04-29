<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="list" class="main_cent">
	<table cellspacing="0" cellpadding="0" class="fulltable"
		style="width: 100%;">
		<tr>
			 <th>
				类型
			 </th>
		     <th>
				标题
			 </th>
			 <th>
				排序
			 </th>
			 <th>
				图片点击路径
			 </th>
			<th>
				 操作
			</th>
		</tr>
		<c:forEach items="${page.result}" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>
					<c:if test="${vo.sType ==1}">官网首页</c:if>
					<c:if test="${vo.sType ==2}">资讯首页</c:if>
					<c:if test="${vo.sType ==3}">论坛首页</c:if>
					<c:if test="${vo.sType ==4}">论坛首页视频</c:if>
					<c:if test="${vo.sType ==5}">移动端首页</c:if>
					<c:if test="${vo.sType ==6}">移动端推荐</c:if>
					<c:if test="${vo.sType ==7}">官网首页公司动态</c:if>
				</td>
				<td>${vo.title}</td>
				<td>${vo.order}</td>
				<td>${vo.imageUrl}</td>
				<td>
					<a href="javascript:show('${vo.id }','update')">编辑</a>
					<a href="javascript:deleteslide('${vo.id }')">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<%@ include file="/WEB-INF/page/common/ajaxpage.jsp"%>
	</div>
</div>