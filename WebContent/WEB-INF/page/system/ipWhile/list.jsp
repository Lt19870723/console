<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/taglib.jsp"%>
<div id="logList" class="main_cent">
	 
	<table id="dataTable" class="fulltable" style="width: 100%;">
		<tr>
		    <th>序号  </th>
			<th>IP  </th>
			<th>访问接口类型 </th>
			<th>公司名称   </th>
			<th>创建人    </th>
			<th>创建时间 </th>
			<th>修改人  </th>
			<th>修改时间 </th>
			<th>状态 </th>
			<th>操作 </th>
		</tr>
		<c:forEach items="${page.result }" var="vo" varStatus="sta">
			<tr class='tr_${sta.index%2}'>
				<td>${sta.index+1}</td>
				<td>${vo.ip}</td>
				<td>${vo.accessTypeStr}</td>
				<td>${vo.company}</td>
				<td>${vo.addStaffName}</td>
				<td>${vo.addtimeStr}</td>
				<td>${vo.updateStaffName}</td>
				<td>${vo.updateTimeStr}</td>
				<td>${vo.statusStr}</td>
				<td>
			    <a href="javascript:;" onclick="updateIP(${vo.id});">修改</a>
			    &nbsp;&nbsp;
			    <a href="javascript:;" onclick="deleteIP(${vo.id});">删除</a>
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